package servlets;

import XMLentities.*;
import dao.ItemDAO;
import entities.Bid;
import entities.Item;

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
import java.util.ArrayList;
import java.util.DoubleSummaryStatistics;
import java.util.List;


@WebServlet("/LoadXml")
public class LoadXml extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {

            File file = new File("/home/agg/Desktop/TedExamples/test.xml");
            JAXBContext jaxbContext = JAXBContext.newInstance(XmlItems.class);

            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            XmlItems xml_items = (XmlItems) jaxbUnmarshaller.unmarshal(file);

            // Loop over the built XMLitems
            for (XmlItem xml_item : xml_items.getItems()){

                // Create the app entity using the xml_entity
                Item item = buildEntity(xml_item);

                System.out.println("===============================================");
                // Insert item to DB
//                ItemDAO dao = new ItemDAO(true);
//                dao.insertItem(item);
            }

            Marshaller marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.marshal(xml_items, response.getWriter());

        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    Item buildEntity(XmlItem xml_item){

        Integer id = xml_item.getId();
        String name = xml_item.getName();
        String currently = xml_item.getCurrently().substring(1);
        String buy_price = xml_item.getBuy_price();
        String first_bid = xml_item.getFirst_bid().substring(1);

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

        List<String> categories = xml_item.getCategories();

        XmlBids xml_bids = xml_item.getBids();
        List<XmlBid> Xml_Bids_list = xml_bids.getBids();    // List of XML_Bids
        List<Bid> Bids_list = new ArrayList<>();            // List to Store the Bids

        for (XmlBid xml_bid : Xml_Bids_list){
            String bid_time = xml_bid.getTime();
            String bid_amount = xml_bid.getAmount();

            XmlBidder bidder_info = xml_bid.getBidder();

            String bidder_name = bidder_info.getBidder_name();
            String bidder_location = bidder_info.getLocation();
            String bidder_country = bidder_info.getCountry();
        }

        if (buy_price != null)
            buy_price = buy_price.substring(1);
        else
            buy_price = "0.0";

        System.out.println("Item ID: " + id);
        System.out.println("Item Name: " + name);
        System.out.println("Current bid: " + currently);
        System.out.println("Buy Price: " + buy_price);
        System.out.println("First Bid: " + first_bid);
        System.out.println("Item Location: " + location);
        System.out.println("Item latitude: " + latitude);
        System.out.println("Item Longitude: " + longitude);
        System.out.println("Item StartDate: " + starts);
        System.out.println("Item EndDate: " + ends);
        System.out.println("Item Country: " + country);
        System.out.println("Item Description: " + description);
        System.out.println("Seller name: " + sellerName);
        for (int i = 0; i < categories.size(); i ++ )
            System.out.println("Item Category[" + i +"]: " + categories.get(i));

        return null;
    }
}
