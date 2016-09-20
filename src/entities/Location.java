package entities;

import javax.xml.bind.annotation.*;

@XmlType(propOrder = {"latitude", "longitude", "location"})
@XmlRootElement(name = "Location")
public class Location {
    private String location;
    private Double latitude;
    private Double longitude;

    public Location(String location, Double latitude, Double longitude) {
        this.location = location;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public Location(){}

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
