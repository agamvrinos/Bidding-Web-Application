package entities.XmlEntities;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlType(propOrder = {"bidder", "time", "amount"})
@XmlRootElement( name = "Bid" )
public class XmlBid {

    private XmlBidder bidder;
    private String time;
    private String amount;

    public XmlBidder getBidder() {
        return bidder;
    }

    @XmlElement(name="Bidder")
    public void setBidder(XmlBidder bidder) {
        this.bidder = bidder;
    }

    public String getTime() {
        return time;
    }

    @XmlElement(name="Time")
    public void setTime(String time) {
        this.time = time;
    }

    public String getAmount() {
        return amount;
    }

    @XmlElement(name="Amount")
    public void setAmount(String amount) {
        this.amount = amount;
    }
}
