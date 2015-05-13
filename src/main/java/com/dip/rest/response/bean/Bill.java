package com.dip.rest.response.bean;

import java.io.Serializable;
import java.util.Date;

public class Bill implements Serializable {

    private String bill_id;
    private String user;
    private Double amount;
    private String ccy;
    private String comment;
    private Date lifetime;

    private String status;
    private String error;

    public String getBill_id() {
        return bill_id;
    }

    public String getUser() {
        return user;
    }

    public Double getAmount() {
        return amount;
    }

    public String getCcy() {
        return ccy;
    }

    public String getComment() {
        return comment;
    }

    public Date getLifetime() {
        return lifetime;
    }

    public String getStatus() {
        return status;
    }

    public String getError() {
        return error;
    }

    public void setBill_id(String bill_id) {
        this.bill_id = bill_id;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public void setCcy(String ccy) {
        this.ccy = ccy;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void setLifetime(Date lifetime) {
        this.lifetime = lifetime;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setError(String error) {
        this.error = error;
    }

    @Override
    public String toString() {
        return "Bill [bill_id=" + bill_id + ", user=" + user + ", amount=" + amount + ", ccy=" + ccy + ", comment="
                + comment + ", lifetime=" + lifetime + ", status=" + status + ", error=" + error + "]";
    }

}
