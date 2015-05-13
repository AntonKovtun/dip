package com.dip.rest.response.bean;

import java.io.Serializable;

public class Sys implements Serializable {

    private Integer type;
    private Integer id;
    private Double message;
    private String country;
    private Integer sunrise;
    private Integer sunset;

    public Integer getType() {
        return type;
    }

    public Integer getId() {
        return id;
    }

    public Double getMessage() {
        return message;
    }

    public String getCountry() {
        return country;
    }

    public Integer getSunrise() {
        return sunrise;
    }

    public Integer getSunset() {
        return sunset;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setMessage(Double message) {
        this.message = message;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setSunrise(Integer sunrise) {
        this.sunrise = sunrise;
    }

    public void setSunset(Integer sunset) {
        this.sunset = sunset;
    }

    @Override
    public String toString() {
        return "Sys [type=" + type + ", id=" + id + ", message=" + message + ", country=" + country + ", sunrise="
                + sunrise + ", sunset=" + sunset + "]";
    }

}
