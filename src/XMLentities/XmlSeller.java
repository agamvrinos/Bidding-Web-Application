package XMLentities;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlType(propOrder = {"rating", "sellerName"})
@XmlRootElement(name = "Seller")
public class XmlSeller {
    private Integer rating;
    private String sellerName;

    @XmlAttribute(name = "Rating", required = true)
    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    @XmlAttribute(name = "UserID", required = true)
    public String getSellerName() {
        return sellerName;
    }

    public void setSellerName(String sellerName) {
        this.sellerName = sellerName;
    }
}
