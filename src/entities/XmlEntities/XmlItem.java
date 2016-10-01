package entities.XmlEntities;

import java.util.List;
import javax.xml.bind.annotation.*;

@XmlType(propOrder = {"id", "name", "categories", "currently", "buy_price", "first_bid", "total_offers",
                        "bids", "location", "country", "starts", "ends", "seller", "desc"})
@XmlRootElement( name = "Item" )
public class XmlItem {

    private Integer id;
    private String name;
    private List<String> categories;
    private String currently;
    private String buy_price;
    private String first_bid;
    private Integer total_offers;
    private XmlBids bids;

    private XmlLocation location;
    private String country;
    private String starts;
    private String ends;
    private XmlSeller seller;
    private String desc;


    @XmlAttribute(name = "ItemID", required = true)
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }

    @XmlElement(name = "Name")
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    @XmlElement(name = "Category")
    public List<String> getCategories() {
        return categories;
    }
    public void setCategories(List<String> categories) {
        this.categories = categories;
    }

    @XmlElement(name = "Currently")
    public String getCurrently() {
        return currently;
    }
    public void setCurrently(String currently) {
        this.currently = currently;
    }

    @XmlElement(name = "Buy_Price")
    public String getBuy_price() {
        return buy_price;
    }
    public void setBuy_price(String buy_price) {
        this.buy_price = buy_price;
    }

    @XmlElement(name = "First_Bid")
    public String getFirst_bid() {
        return first_bid;
    }
    public void setFirst_bid(String first_bid) {
        this.first_bid = first_bid;
    }

    @XmlElement(name = "Number_of_Bids")
    public Integer getTotal_offers() {
        return total_offers;
    }
    public void setTotal_offers(Integer total_offers) {
        this.total_offers = total_offers;
    }

    @XmlElement(name = "Bids")
    public XmlBids getBids() {
        return bids;
    }
    public void setBids(XmlBids bids) {
        this.bids = bids;
    }

    @XmlElement(name = "Location")
    public XmlLocation getLocation() {
        return location;
    }
    public void setLocation(XmlLocation location) {
        this.location = location;
    }

    @XmlElement(name = "Country")
    public String getCountry() {
        return country;
    }
    public void setCountry(String country) {
        this.country = country;
    }

    @XmlElement(name = "Started")
    public String getStarts() {
        return starts;
    }
    public void setStarts(String starts) {
        this.starts = starts;
    }

    @XmlElement(name = "Ends")
    public String getEnds() {
        return ends;
    }
    public void setEnds(String ends) {
        this.ends = ends;
    }

    @XmlElement(name = "Seller")
    public XmlSeller getSeller() {
        return seller;
    }
    public void setSeller(XmlSeller seller) {
        this.seller = seller;
    }

    @XmlElement(name = "Description")
    public String getDesc() {
        return desc;
    }
    public void setDesc(String desc) {
        this.desc = desc;
    }

}

