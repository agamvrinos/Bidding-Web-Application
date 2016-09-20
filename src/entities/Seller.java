package entities;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlType(propOrder = {"rating", "userID"})
@XmlRootElement(name = "Seller")
public class Seller {
    private Integer rating;
    private Integer userID;

    public Integer getRating() {
        return rating;
    }

    @XmlAttribute(name = "Rating", required = true)
    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public Integer getUserID() {
        return userID;
    }

    @XmlAttribute(name = "UserID", required = true)
    public void setUserID(Integer userID) {
        this.userID = userID;
    }
}
