package XMLentities;


import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlType(propOrder = {"rating", "bidder_name", "location", "country"})
@XmlRootElement( name = "Bidder" )
public class XmlBidder {
    private Integer rating;
    private String bidder_name;
    private String location;
    private String country;

    @XmlAttribute(name = "Rating", required = true)
    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    @XmlAttribute(name = "UserID", required = true)
    public String getBidder_name() {
        return bidder_name;
    }

    public void setBidder_name(String bidder_name) {
        this.bidder_name = bidder_name;
    }

    @XmlElement(name = "Location")
    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @XmlElement(name = "Country")
    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
