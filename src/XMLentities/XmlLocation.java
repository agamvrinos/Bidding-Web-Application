package XMLentities;

import javax.xml.bind.annotation.*;

@XmlType(propOrder = {"latitude", "longitude", "location"})
@XmlRootElement(name = "Location")
public class XmlLocation {
    private String location;
    private Double latitude;
    private Double longitude;

    public String getLocation() {
        return location;
    }

    @XmlValue
    public void setLocation(String location) {
        this.location = location;
    }

    public Double getLatitude() {
        return latitude;
    }

    @XmlAttribute(name="Latitude")
    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    @XmlAttribute(name="Longitude")
    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }
}
