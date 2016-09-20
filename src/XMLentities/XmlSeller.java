package XMLentities;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlType(propOrder = {"rating", "userID"})
@XmlRootElement(name = "Seller")
public class XmlSeller {
    private Integer rating;
    private String userID;

    public Integer getRating() {
        return rating;
    }

    @XmlAttribute(name = "Rating", required = true)
    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public String getUserID() {
        return userID;
    }

    @XmlAttribute(name = "UserID", required = true)
    public void setUserID(String userID) {
        this.userID = userID;
    }
}
