package ru.diemyst.entities;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by diemyst on 18.09.15.
 */
@Entity
@XmlRootElement
@NamedQuery(name = "Point.getAll", query = "SELECT c from Point c")
public class Point {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column
    private String name;

    @Column
    private Double latitude;

    @Column
    private Double longtitude;

    public Point() {
    }

    public Point(String name, Double latitude, Double longtitude) {
        this.name = name;
        this.latitude = latitude;
        this.longtitude = longtitude;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongtitude() {
        return longtitude;
    }

    public void setLongtitude(Double longtitude) {
        this.longtitude = longtitude;
    }
}
