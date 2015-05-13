package com.dip.rest.response.bean;

import java.io.Serializable;

public class Rain implements Serializable {

    private Double h_1;

    public Double getH_1() {
        return h_1;
    }

    public void setH_1(Double h_1) {
        this.h_1 = h_1;
    }

    @Override
    public String toString() {
        return "Rain [h_1=" + h_1 + "]";
    }

}
