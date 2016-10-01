package servlets.XML_Operations;

import XMLentities.*;
import dao.BidDAO;
import dao.ItemDAO;
import entities.Bid;
import entities.Item;
import entities.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.IOException;
import java.nio.file.InvalidPathException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@WebServlet("/LoadXml")
public class LoadXml extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect("error_page.jsp");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // Error handling
        User user = (User) request.getSession().getAttribute("user");
        if (user == null || user.getRole() != 0)
            response.sendRedirect("error_page.jsp");

        String xmlpath = request.getParameter("xml_path");

        try {

            File file = new File(xmlpath);
            JAXBContext jaxbContext = JAXBContext.newInstance(XmlItems.class);

            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            XmlItems xml_items = (XmlItems) jaxbUnmarshaller.unmarshal(file);

            List<XmlItem> xmlitems = xml_items.getItems();

            // Loop over the built XMLitems
            for (XmlItem xml_item : xmlitems){

                // Create the app entity using the xml_entity
                buildEntity(xml_item);
            }

            request.setAttribute("message","H φόρτωση του αρχείου ολοκληρώθηκε.");
            RequestDispatcher dispatcher = request.getRequestDispatcher("loadXML.jsp");
            dispatcher.forward(request, response);

        } catch (JAXBException | InvalidPathException ex) {
            request.setAttribute("error", "Κάτι πήγε στραβά! Ίσως το μονοπάτι δεν είναι έγκυρο.");
            RequestDispatcher dispatcher = request.getRequestDispatcher("loadXML.jsp");
            dispatcher.forward(request, response);
            return;
        }
    }

    private void buildEntity(XmlItem xml_item){

        // Print the XML item
        // printXmlItem(xml_item);
        // System.out.println("Building item: " + xml_item.getName());

        //========================================================
        //============ Get INFO from the XML entity ==============
        //========================================================
        Integer id = xml_item.getId();
        String name = xml_item.getName();
        String currently = xml_item.getCurrently().substring(1).replace(",", "");
        String buy_price = xml_item.getBuy_price();
        String first_bid = xml_item.getFirst_bid().substring(1).replace(",", "");
        Integer total_offers = xml_item.getTotal_offers();
        XmlLocation location_info = xml_item.getLocation();
        String location = location_info.getLocation();
        Double latitude = location_info.getLatitude();
        Double longitude = location_info.getLongitude();
        String starts = xml_item.getStarts();
        String ends = xml_item.getEnds();
        String country = xml_item.getCountry();
        String description = xml_item.getDesc();
        XmlSeller seller_info = xml_item.getSeller();
        String sellerName = seller_info.getSellerName();
        Integer sellerRating = seller_info.getRating();

        //========================================================
        //============ Convert data type IF needed ===============
        //========================================================
        Double currently_d = DoubleConvert(currently);

        if (buy_price != null)
            buy_price = buy_price.substring(1);

        Double buy_price_d = DoubleConvert(buy_price);
        Double first_bid_d = DoubleConvert(first_bid);
        Date starts_d = DateConvert(starts);
        Date ends_d = DateConvert(ends);

        Calendar starts_cal = Calendar.getInstance();
        Calendar ends_cal = Calendar.getInstance();
        starts_cal.setTime(starts_d);
        ends_cal.setTime(ends_d);
        starts_cal.set(Calendar.YEAR, 2017);
        ends_cal.set(Calendar.YEAR, 2017);

        starts_d = starts_cal.getTime();
        ends_d = ends_cal.getTime();

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm");
        sdf.format(starts_d);
        sdf.format(ends_d);

        //========================================================
        //============ Build the bids for this Item ==============
        //========================================================
        List<String> categories = xml_item.getCategories();
        List<Bid> bids = constructBidsList(xml_item.getBids());

        //========================================================
        //=========== Create Item using the XML info =============
        //========================================================
        Item item_ent = new Item(id, name, currently_d, first_bid_d, buy_price_d, country, location, latitude, longitude,
                new Date(), starts_d, ends_d, sellerName, description, categories, bids, 1, "", total_offers);

        item_ent.setBids(bids); // Add the Bids List

        //========================================================
        //=========== Fill In the Database tables  ===============
        //========================================================
        ItemDAO idao = new ItemDAO(true);
        idao.loadXmlEntities(item_ent);

        new BidDAO(true).insertRating(sellerName, sellerRating);
    }

    private Double DoubleConvert(String str){
        Double d;
        try {
            d = Double.parseDouble(str);
        }
        catch(NumberFormatException | NullPointerException nfe){
            return null;
        }
        return d;
    }

    private Date DateConvert(String str){
        SimpleDateFormat sdf = new SimpleDateFormat("MMM-dd-yy hh:mm:ss");
        SimpleDateFormat sdf2 = new SimpleDateFormat("dd/MM/yyyy hh:mm");

        Date date;
        try {
            date = sdf.parse(str);
        }
        catch (ParseException ex) {
            throw new RuntimeException("Unable to Parse Date String");
        }
        sdf2.format(date);
        return date;
    }

    private void printXmlItem(XmlItem item){
        System.out.println("Item ID: " + item.getId());
        System.out.println("Item Name: " + item.getName());
        System.out.println("Current bid: " + item.getCurrently());
        System.out.println("Buy Price: " + item.getBuy_price());
        System.out.println("First Bid: " + item.getFirst_bid());
        System.out.println("Num of Bids: " + item.getTotal_offers());
        System.out.println("Item Location: " + item.getLocation().getLocation());
        System.out.println("Item latitude: " + item.getLocation().getLatitude());
        System.out.println("Item Longitude: " + item.getLocation().getLongitude());
        System.out.println("Item StartDate: " + item.getStarts());
        System.out.println("Item EndDate: " + item.getEnds());
        System.out.println("Item Country: " + item.getCountry());
        System.out.println("Item Description: " + item.getDesc());
        System.out.println("Seller name: " + item.getSeller().getSellerName());

        for (int i = 0; i < item.getCategories().size(); i ++ )
            System.out.println("Item Category[" + i +"]: " + item.getCategories().get(i));


        List<XmlBid> bids = item.getBids().getBids();
        if (bids != null) {
            for (XmlBid bid : bids) {
                System.out.println("Bid Time: " + bid.getTime());
                System.out.println("Bid Amount: " + bid.getAmount());
                System.out.println("Bidder Name: " + bid.getBidder().getBidder_name());
                System.out.println("Bidder Location: " + bid.getBidder().getLocation());
                System.out.println("Bidder Country: " + bid.getBidder().getCountry());
                System.out.println("Bidder Rating: " + bid.getBidder().getRating());
            }
        }
        else
            System.out.println("No bids");
    }

    private List<Bid> constructBidsList(XmlBids xml_bids) {
        List<XmlBid> Xml_Bids_list = xml_bids.getBids();    // List of XML_Bids

        if (Xml_Bids_list == null)
            return null;

        List<Bid> Bids_list = new ArrayList<>();            // List to Store the Bids

        for (XmlBid xml_bid : Xml_Bids_list){
            String bid_time = xml_bid.getTime();
            Date bid_time_d = DateConvert(bid_time);

            String bid_amount = xml_bid.getAmount().substring(1).replace(",", "");
            Double bid_amount_d = DoubleConvert(bid_amount);

            XmlBidder bidder_info = xml_bid.getBidder();

            String bidder_name = bidder_info.getBidder_name();
            String bidder_location = bidder_info.getLocation();
            String bidder_country = bidder_info.getCountry();
            Integer bidder_rating = bidder_info.getRating();

            Bid bid = new Bid(null, bidder_name, bidder_country, bidder_location, bidder_rating, bid_time_d, bid_amount_d);

            Bids_list.add(bid);
        }

        return Bids_list;
    }
}
