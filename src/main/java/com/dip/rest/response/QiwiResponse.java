package com.dip.rest.response;

import com.dip.rest.response.bean.Bill;

public class QiwiResponse {
    private String result_code;
    private Bill bill;

    public String getResult_code() {
        return result_code;
    }

    public Bill getBill() {
        return bill;
    }

    public void setResult_code(String result_code) {
        this.result_code = result_code;
    }

    public void setBill(Bill bill) {
        this.bill = bill;
    }

    @Override
    public String toString() {
        return "QiwiResponse [result_code=" + result_code + ", bill=" + bill + "]";
    }

}