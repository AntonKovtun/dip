package com.dip.rest.response;

public class QiwiResponseWrapper extends RestResponse {
    private QiwiResponse response;

    public QiwiResponse getResponse() {
        return response;
    }

    public void setResponse(QiwiResponse response) {
        this.response = response;
    }

}
