package com.dip.common.dto;

import com.dip.common.constant.Status;
import com.dip.common.constant.UserType;

import java.util.Date;
import java.util.List;

public class DipDto {
    private String id;
    private String size;
    private String rowCount;
    private String colCount;
    private List<String> population;
    private int[][] matrix;
    private int[][] matrixSm;

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public List<String> getPopulation() {
        return population;
    }

    public void setPopulation(List<String> population) {
        this.population = population;
    }

    public int[][] getMatrix() {
        return matrix;
    }

    public void setMatrix(int[][] matrix) {
        this.matrix = matrix;
    }

    public String getColCount() {
        return colCount;
    }

    public void setColCount(String colCount) {
        this.colCount = colCount;
    }

    public String getRowCount() {
        return rowCount;
    }

    public void setRowCount(String rowCount) {
        this.rowCount = rowCount;
    }

    public int[][] getMatrixSm() {
        return matrixSm;
    }

    public void setMatrixSm(int[][] matrixSm) {
        this.matrixSm = matrixSm;
    }

    public DipDto() {
    }

    public DipDto(String id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DipDto userDto = (DipDto) o;

        if (id != null ? !id.equals(userDto.id) : userDto.id != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
