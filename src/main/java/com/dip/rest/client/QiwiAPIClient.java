package com.dip.rest.client;

import com.dip.rest.response.QiwiResponseWrapper;

public interface QiwiAPIClient extends RestAPIClient {
    public QiwiResponseWrapper bill(String storeId, String userPhoneNumber, Double amount, String ccy, String comment,
            String lifetime, String pay_source, String prv_name, String billId) throws Exception;

    public QiwiResponseWrapper billRequest(String storeId, String billId) throws Exception;

    // public HashMap<String, String> getRequestHeaders(String apiId, String
    // passwordApiId) throws Exception;

    // public HashMap<String, String> getRequestHeaders(String apiId, String
    // passwordApiId);
}
