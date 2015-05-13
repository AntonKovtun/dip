package com.dip.rest.response.bean;

import java.io.Serializable;

public class Clouds implements Serializable {

    private Integer all;

    public Integer getAll() {
        return all;
    }

    public void setAll(Integer all) {
        this.all = all;
    }

    @Override
    public String toString() {
        return "Clouds [all=" + all + "]";
    }

}
