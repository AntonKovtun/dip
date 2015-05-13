package com.dip.rest.client.impl;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dip.backend.config.IBackendProperties;
import com.dip.rest.client.QiwiAPIClient;
import com.dip.rest.response.QiwiResponseWrapper;

@Service("qiwiClient")
public class QiwiClient extends QiwiApiClient implements QiwiAPIClient {

    @Autowired
    private IBackendProperties backendProperties;

    private Boolean useAcceptEncodingGzip = false;

    @Override
    public QiwiResponseWrapper bill(String storeId, String userPhoneNumber, Double amount, String ccy, String comment,
            String lifetime, String pay_source, String prv_name, String billId) throws Exception {

        // TODO Auto-generated method stub
        Deque<String> reqParams = new ArrayDeque<String>();
        reqParams.push("api/v2/prv/" + storeId + "/bills/" + billId);

        // UUID.randomUUID();

        StringBuilder req = new StringBuilder();
        addRequestParameter(req, "user", String.format("tel:%s", userPhoneNumber), true);
        addRequestParameter(req, "amount", amount, false);
        addRequestParameter(req, "ccy", ccy, false);
        addRequestParameter(req, "comment", comment, false);
        addRequestParameter(req, "lifetime", lifetime, true);
        // addRequestParameter(req, "pay_source", pay_source, false);
        addRequestParameter(req, "prv_name", prv_name, false);

        return getResponse(reqParams, null, "PUT", req.toString(), QiwiResponseWrapper.class);
    }

    @Override
    public QiwiResponseWrapper billRequest(String storeId, String billId) throws Exception {

        Deque<String> reqParams = new ArrayDeque<String>();
        reqParams.push("api/v2/prv/" + storeId + "/bills/" + billId);

        return getResponse(reqParams, null, "GET", null, QiwiResponseWrapper.class);
    }

    @Override
    protected String getContentTypeProperty() {
        // TODO Auto-generated method stub
        return "Qiwi";
    }

    protected HashMap<String, String> getRequestHeaders() {
        HashMap<String, String> headers = new HashMap<String, String>();

        byte[] encodedBytes = Base64.encodeBase64((backendProperties.getQiwiLogin() + ":" + backendProperties
                .getQiwiPassword()).getBytes());
        headers.put("Authorization", "Basic " + new String(encodedBytes));
        headers.put("Accept", "text/json");
        headers.put("Content-Type", "application/x-www-form-urlencoded; charset=utf-8");

        return headers;
    }

    public Boolean getUseAcceptEncodingGzip() {
        return useAcceptEncodingGzip;
    }

    public void setUseAcceptEncodingGzip(Boolean useAcceptEncodingGzip) {
        this.useAcceptEncodingGzip = useAcceptEncodingGzip;
    }

}
