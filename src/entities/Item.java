package entities;

import java.util.Date;
import java.util.List;

public class Item {

    private Integer id;
    private String name;
    private Double currently;
    private Double first_bid;
    private Double buy_price;
    private String country;
    private String location;
    private Double latitude;
    private Double longitude;
    private Date creation;
    private Date starts;
    private Date ends;
    private String seller;
    private String desc;
    private List<String> categories;
    private List<Bid> bids;
    private Integer state;

    public Item(Integer id, String name, Double currently, Double first_bid, Double buy_price, String country, String location, Double latitude, Double longitude, Date creation, Date starts, Date ends, String seller, String desc, List<String> categories, List<Bid> bids) {
        this.id = id;
        this.name = name;
        this.currently = currently;
        this.first_bid = first_bid;
        this.buy_price = buy_price;
        this.country = country;
        this.location = location;
        this.latitude = latitude;
        this.longitude = longitude;
        this.creation = creation;
        this.starts = starts;
        this.ends = ends;
        this.seller = seller;
        this.desc = desc;
        this.categories = categories;
        this.bids = bids;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getCurrently() {
        return currently;
    }

    public void setCurrently(Double currently) {
        this.currently = currently;
    }

    public Double getFirst_bid() {
        return first_bid;
    }

    public void setFirst_bid(Double first_bid) {
        this.first_bid = first_bid;
    }

    public Double getBuy_price() {
        return buy_price;
    }

    public void setBuy_price(Double buy_price) {
        this.buy_price = buy_price;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Date getCreation() {
        return creation;
    }

    public void setCreation(Date creation) {
        this.creation = creation;
    }

    public Date getStarts() {
        return starts;
    }

    public void setStarts(Date starts) {
        this.starts = starts;
    }

    public Date getEnds() {
        return ends;
    }

    public void setEnds(Date ends) {
        this.ends = ends;
    }

    public String getSeller() {
        return seller;
    }

    public void setSeller(String seller) {
        this.seller = seller;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public List<String> getCategories() {
        return categories;
    }

    public void setCategories(List<String> categories) {
        this.categories = categories;
    }

    public List<Bid> getBids() {
        return bids;
    }

    public void setBids(List<Bid> bids) {
        this.bids = bids;
    }
}
