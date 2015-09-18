package ru.diemyst.entities.google;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by diemyst on 18.09.15.
 */
@XmlRootElement
public class Location {

    private String lat;

    private String lng;

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }
}
