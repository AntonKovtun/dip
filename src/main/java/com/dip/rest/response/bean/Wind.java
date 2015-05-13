package com.dip.rest.response.bean;

import java.io.Serializable;

public class Wind implements Serializable {

    private Double speed;
    private Integer deg;
    private Integer var_beg;
    private Integer var_end;

    public Double getSpeed() {
        return speed;
    }

    public Integer getDeg() {
        return deg;
    }

    public Integer getVar_beg() {
        return var_beg;
    }

    public Integer getVar_end() {
        return var_end;
    }

    public void setSpeed(Double speed) {
        this.speed = speed;
    }

    public void setDeg(Integer deg) {
        this.deg = deg;
    }

    public void setVar_beg(Integer var_beg) {
        this.var_beg = var_beg;
    }

    public void setVar_end(Integer var_end) {
        this.var_end = var_end;
    }

    @Override
    public String toString() {
        return "Wind [speed=" + speed + ", deg=" + deg + ", var_beg=" + var_beg + ", var_end=" + var_end + "]";
    }

}
