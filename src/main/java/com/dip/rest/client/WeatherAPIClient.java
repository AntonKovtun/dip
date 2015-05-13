package com.sulin.rest.client;

import com.sulin.rest.response.RestResponse;

public interface WeatherAPIClient extends RestAPIClient {
    public <WeatherResponse extends RestResponse> WeatherResponse translate() throws Exception;
}
