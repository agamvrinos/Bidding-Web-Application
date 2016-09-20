package entities;

import java.util.List;
import javax.xml.bind.annotation.*;

@XmlType(propOrder = {"id", "name", "categories", "currently", "buy_price", "first_bid", "total_offers",
        "location", "country", "starts", "ends", "seller", "desc"})
@XmlRootElement( name = "Item" )
public class Item {

    private Integer id;
    private String name;
    private List<String> categories;
    private String currently;
    private String buy_price;
    private String first_bid;
    private Integer total_offers;

    private Location location;
    private String country;
    private String starts;
    private String ends;
    private String seller;
    private String desc;

    private String creation;
    private List<Bid> bids;
    private Integer state;
    private String image;

    //TODO: I changed Dates from Date to String in order to parse it
    //TODO: Need to change the String type of seller and make it of type Seller
    public Item(Integer id, String name, String currently, String first_bid, String buy_price, String country, Location location, String creation, String starts, String ends, String seller, String desc, List<String> categories, List<Bid> bids, Integer state, String image, Integer total_offers) {
        this.id = id;
        this.name = name;
        this.currently = currently;
        this.first_bid = first_bid;
        this.buy_price = buy_price;
        this.country = country;
        this.location = location;
        this.creation = creation;
        this.starts = starts;
        this.ends = ends;
        this.seller = seller;
        this.desc = desc;
        this.categories = categories;
        this.bids = bids;
        this.state = state;
        this.image = image;
        this.total_offers = total_offers;
    }

    public Item() {
    }

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

    @XmlTransient
    public List<Bid> getBids() {
        return bids;
    }

    public void setBids(List<Bid> bids) {
        this.bids = bids;
    }

    @XmlElement(name = "Location")
    public Location getLocation() {
        return location;
    }
    public void setLocation(Location location) {
        this.location = location;
    }

    @XmlElement(name = "Country")
    public String getCountry() {
        return country;
    }
    public void setCountry(String country) {
        this.country = country;
    }

    @XmlTransient
    public String getCreation() {
        return creation;
    }

    public void setCreation(String creation) {
        this.creation = creation;
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
    public String getSeller() {
        return seller;
    }
    public void setSeller(String seller) {
        this.seller = seller;
    }

    @XmlElement(name = "Description")
    public String getDesc() {
        return desc;
    }
    public void setDesc(String desc) {
        this.desc = desc;
    }

    @XmlTransient
    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    @XmlTransient
    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}

