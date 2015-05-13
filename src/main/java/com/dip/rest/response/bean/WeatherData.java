package com.dip.rest.response.bean;

import java.io.Serializable;

public class WeatherData implements Serializable {

    private Integer id;
    private String main;
    private String description;
    private String icon;

    public Integer getId() {
        return id;
    }

    public String getMain() {
        return main;
    }

    public String getDescription() {
        return description;
    }

    public String getIcon() {
        return icon;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setMain(String main) {
        this.main = main;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    @Override
    public String toString() {
        return "WeatherData [id=" + id + ", main=" + main + ", description=" + description + ", icon=" + icon + "]";
    }

}
