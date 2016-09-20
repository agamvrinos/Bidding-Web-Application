package entities;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement( name = "Items" )
public class Items {
    private List<Item> items;

    public List<Item> getItems() {
        return items;
    }

    @XmlElement( name = "Item" )
    public void setItems(List<Item> items) {
        this.items = items;
    }
}