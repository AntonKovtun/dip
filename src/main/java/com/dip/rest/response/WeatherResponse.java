package com.dip.rest.response;

import java.util.List;

import com.dip.rest.response.bean.Clouds;
import com.dip.rest.response.bean.Coord;
import com.dip.rest.response.bean.Main;
import com.dip.rest.response.bean.Rain;
import com.dip.rest.response.bean.Sys;
import com.dip.rest.response.bean.WeatherData;
import com.dip.rest.response.bean.Wind;

public class WeatherResponse extends RestResponse {
    private Coord coord;
    private Sys sys;
    private List<WeatherData> weather;
    private String base;
    private Main main;
    private Wind wind;
    private Rain rain;
    private Clouds clouds;
    private Integer dt;
    private Integer id;
    private String name;
    private Integer cod;

    public Coord getCoord() {
        return coord;
    }

    public Sys getSys() {
        return sys;
    }

    public List<WeatherData> getWeather() {
        return weather;
    }

    public String getBase() {
        return base;
    }

    public Main getMain() {
        return main;
    }

    public Wind getWind() {
        return wind;
    }

    public Rain getRain() {
        return rain;
    }

    public Clouds getClouds() {
        return clouds;
    }

    public Integer getDt() {
        return dt;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getCod() {
        return cod;
    }

    public void setCoord(Coord coord) {
        this.coord = coord;
    }

    public void setSys(Sys sys) {
        this.sys = sys;
    }

    public void setWeather(List<WeatherData> weather) {
        this.weather = weather;
    }

    public void setBase(String base) {
        this.base = base;
    }

    public void setMain(Main main) {
        this.main = main;
    }

    public void setWind(Wind wind) {
        this.wind = wind;
    }

    public void setRain(Rain rain) {
        this.rain = rain;
    }

    public void setClouds(Clouds clouds) {
        this.clouds = clouds;
    }

    public void setDt(Integer dt) {
        this.dt = dt;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCod(Integer cod) {
        this.cod = cod;
    }

    @Override
    public String toString() {
        return "WeatherResponse [coord=" + coord + ", sys=" + sys + ", weather=" + weather + ", base=" + base
                + ", main=" + main + ", wind=" + wind + ", rain=" + rain + ", clouds=" + clouds + ", dt=" + dt
                + ", id=" + id + ", name=" + name + ", cod=" + cod + "]";
    }

}