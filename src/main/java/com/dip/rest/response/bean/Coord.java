package com.sulin.rest.response.bean;

import java.io.Serializable;

public class Coord implements Serializable {

    private Double lon;
    private Double lat;

    public Double getLon() {
        return lon;
    }

    public Double getLat() {
        return lat;
    }

    public void setLon(Double lon) {
        this.lon = lon;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    @Override
    public String toString() {
        return "Coord [lon=" + lon + ", lat=" + lat + "]";
    }

}
