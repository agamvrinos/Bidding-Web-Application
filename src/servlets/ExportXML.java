package servlets;

import XMLentities.*;
import dao.ItemDAO;
import entities.Item;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;


@WebServlet("/ExportXML")
public class ExportXML extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //TODO: MONO ADMIN NA MPAINEI EDW
        Integer item_id = Integer.parseInt(request.getParameter("export"));

        ItemDAO dao = new ItemDAO(true);
        Item item = dao.getItemByID(item_id);

        //Create XMLItems
        XmlItems xmlItems = new XmlItems();
        xmlItems.setItems(new ArrayList<XmlItem>());

        //Create XMLItem
        XmlItem xml_item = new XmlItem();
        xmlItems.getItems().add(xml_item);

        //Fill it with info
        xml_item.setId(item.getId());
        xml_item.setName(item.getName());
        xml_item.setCategories(item.getCategories());
        xml_item.setCurrently("$" + String.valueOf(item.getCurrently()));
        xml_item.setBuy_price("$" + String.valueOf(item.getBuy_price()));
        xml_item.setFirst_bid("$" + String.valueOf(item.getFirst_bid()));
        xml_item.setTotal_offers(item.getTotal_offers());
        xml_item.setCountry(item.getCountry());
        xml_item.setDesc(item.getDesc());

        //Dates to Strings
        SimpleDateFormat sdf = new SimpleDateFormat("MMM-dd-yy hh:mm:ss");
        xml_item.setStarts(sdf.format(item.getStarts()));
        xml_item.setEnds(sdf.format(item.getEnds()));

        //Create Location XML
        XmlLocation xml_location = new XmlLocation();
        xml_item.setLocation(xml_location);

        //Get Location info
        xml_location.setLocation(item.getLocation());
        xml_location.setLatitude(item.getLatitude());
        xml_location.setLongitude(item.getLongitude());

        //Create Seller XML
        XmlSeller xml_seller = new XmlSeller();
        xml_item.setSeller(xml_seller);

        //Fill seller with info
        xml_seller.setSellerName(item.getSeller());
        xml_seller.setRating(dao.getUserRating(item.getSeller()));

        //Create Bids Xml object
        XmlBids xmlBids = new XmlBids();
        xml_item.setBids(xmlBids);
        xmlBids.setBids(new ArrayList<XmlBid>());

        //For every Bid
        for(int i=0; i< item.getBids().size(); i++){
            XmlBid xmlBid = new XmlBid();
            xmlBids.getBids().add(xmlBid);

            //bid xml info
            xmlBid.setAmount("$" + String.valueOf(item.getBids().get(i).getAmount()));
            xmlBid.setTime(sdf.format(item.getBids().get(i).getTime()));

            //bidder xml
            XmlBidder xmlBidder = new XmlBidder();
            xmlBid.setBidder(xmlBidder);

            //bidder info
            xmlBidder.setBidder_name(item.getBids().get(i).getUsername());
            xmlBidder.setLocation(item.getBids().get(i).getLocation());
            xmlBidder.setCountry(item.getBids().get(i).getCountry());
            xmlBidder.setRating(item.getBids().get(i).getRating());
        }
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(XmlItems.class);
            Marshaller marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            response.setContentType("text/xml");
            response.setHeader("Content-disposition","attachment; filename=item-" + item_id + ".xml");
            OutputStream out = response.getOutputStream();
            marshaller.marshal(xmlItems, out);
            out.flush();
        }
        catch (JAXBException ex){
            System.err.println("ERROR on ExportXML: " + ex.getMessage());
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect("index.jsp");
    }
}
