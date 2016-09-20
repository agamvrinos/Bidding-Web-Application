package XMLentities;


import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlType(propOrder = {"rating", "userID", "location", "country"})
@XmlRootElement( name = "Bidder" )
public class XmlBidder {
    private Integer rating;
    private String userID;
    private String location;
    private String country;

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

    public String getLocation() {
        return location;
    }

    @XmlElement(name = "Location")
    public void setLocation(String location) {
        this.location = location;
    }

    public String getCountry() {
        return country;
    }

    @XmlElement(name = "Country")
    public void setCountry(String country) {
        this.country = country;
    }
}
