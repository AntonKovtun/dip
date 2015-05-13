package com.sulin.rest.client.impl;

import java.net.URLEncoder;

import com.sulin.rest.client.AbstractAPIClient;

public abstract class QiwiApiClient extends AbstractAPIClient {
    private String baseUrl = "https://w.qiwi.com";

    protected String getBaseUrl() {
        return baseUrl;
    }

    protected void addRequestParameter(StringBuilder paramBuilder, String name, Object value, boolean encodeFlag)
            throws Exception {
        if (paramBuilder == null)
            return;

        if (paramBuilder.length() > 0)
            paramBuilder.append("&");

        paramBuilder.append(name).append("=");

        if (encodeFlag)
            paramBuilder.append(URLEncoder.encode(value.toString(), "UTF-8"));
        else
            paramBuilder.append(value);

    }

    protected void addRequestParameter(StringBuilder paramBuilder, String name, Double value, boolean encodeFlag)
            throws Exception {
        String strValue = String.valueOf(value);
        this.addRequestParameter(paramBuilder, name, strValue, encodeFlag);
    }
}
