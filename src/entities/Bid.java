package entities;

import java.util.Date;

public class Bid {

    private Integer bidID;

    private String username;
    private String country;
    private String location;
    private Integer rating;

    private Date time;
    private Double amount;

    public Bid(Integer bidID, String username, String country, String location, Integer rating, Date time, Double amount) {
        this.bidID = bidID;
        this.username = username;
        this.country = country;
        this.location = location;
        this.rating = rating;
        this.time = time;
        this.amount = amount;
    }

    public Integer getBidID() {
        return bidID;
    }

    public void setBidID(Integer bidID) {
        this.bidID = bidID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }
}
