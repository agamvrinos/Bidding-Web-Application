package entities;

import java.util.Date;

public class Bid {

    private String userID;
    private Float rating;
    private String country;
    private String location;
    private Date time;
    private Double amount;

    public Bid(String userID, Float rating, String country, String location, Date time, Double amount) {
        this.userID = userID;
        this.rating = rating;
        this.country = country;
        this.location = location;
        this.time = time;
        this.amount = amount;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public Float getRating() {
        return rating;
    }

    public void setRating(Float rating) {
        this.rating = rating;
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
}
