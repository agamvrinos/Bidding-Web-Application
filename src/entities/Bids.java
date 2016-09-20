package entities;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement( name = "Bids" )
public class Bids {
    private List<Bid> bids;

    public List<Bid> getBids() {
        return bids;
    }

    @XmlElement( name = "Bid" )
    public void setBids(List<Bid> bids) {
        this.bids = bids;
    }
}
