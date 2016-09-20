package XMLentities;

import javax.xml.bind.annotation.*;

@XmlType(propOrder = {"latitude", "longitude", "location"})
@XmlRootElement(name = "Location")
public class XmlLocation {
    private String location;
    private String latitude;
    private String longitude;

    public String getLocation() {
        return location;
    }

    @XmlValue
    public void setLocation(String location) {
        this.location = location;
    }

    public String getLatitude() {
        return latitude;
    }

    @XmlAttribute(name="Latitude")
    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    @XmlAttribute(name="Longitude")
    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }
}
