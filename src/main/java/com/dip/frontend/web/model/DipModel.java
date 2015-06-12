package com.dip.frontend.web.model;

public class DipModel extends AbstractDataModel {
    private static final long serialVersionUID = 1L;

    private String size;
    private String numberOperationsGa;
    private String numberOperationsEa;
    private String sdnCount;
    private String populationCount;
    private String lenghHromosom;

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getNumberOperationsGa() {
        return numberOperationsGa;
    }

    public void setNumberOperationsGa(String numberOperationsGa) {
        this.numberOperationsGa = numberOperationsGa;
    }

    public String getNumberOperationsEa() {
        return numberOperationsEa;
    }

    public void setNumberOperationsEa(String numberOperationsEa) {
        this.numberOperationsEa = numberOperationsEa;
    }

    public String getSdnCount() {
        return sdnCount;
    }

    public void setSdnCount(String sdnCount) {
        this.sdnCount = sdnCount;
    }

    public String getPopulationCount() {
        return populationCount;
    }

    public void setPopulationCount(String populationCount) {
        this.populationCount = populationCount;
    }

    public String getLenghHromosom() {
        return lenghHromosom;
    }

    public void setLenghHromosom(String lenghHromosom) {
        this.lenghHromosom = lenghHromosom;
    }
}
