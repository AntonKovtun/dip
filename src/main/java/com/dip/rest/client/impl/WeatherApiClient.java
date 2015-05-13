package com.sulin.rest.client.impl;

import com.sulin.rest.client.AbstractAPIClient;

public abstract class WeatherApiClient extends AbstractAPIClient {
    private String baseUrl = "http://api.openweathermap.org";

    protected String getBaseUrl() {
        return baseUrl;
    }

}
