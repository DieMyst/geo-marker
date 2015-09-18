package ru.diemyst.entities.google;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by diemyst on 18.09.15.
 */
@XmlRootElement
@JsonIgnoreProperties(ignoreUnknown = true)
public class Geometry {

    private Location location;

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }
}
