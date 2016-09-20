package XMLentities;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;


@XmlRootElement( name = "Items" )
public class XmlItems {
    private List<XmlItem> items;


    public List<XmlItem> getItems() {
        return items;
    }

    @XmlElement( name = "Item" )
    public void setItems(List<XmlItem> items) {
        this.items = items;
    }
}
