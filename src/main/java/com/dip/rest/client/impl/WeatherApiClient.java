package com.dip.rest.client.impl;

import com.dip.rest.client.AbstractAPIClient;

public abstract class WeatherApiClient extends AbstractAPIClient {
    private String baseUrl = "http://api.openweathermap.org";

    protected String getBaseUrl() {
        return baseUrl;
    }

}
