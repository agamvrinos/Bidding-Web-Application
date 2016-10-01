package entities.XmlEntities;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement( name = "Bids" )
public class XmlBids {
    private List<XmlBid> bids;

    @XmlElement( name = "Bid" )
    public List<XmlBid> getBids() {
        return bids;
    }

    public void setBids(List<XmlBid> bids) {
        this.bids = bids;
    }
}
