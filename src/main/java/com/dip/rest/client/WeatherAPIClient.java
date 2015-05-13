package com.dip.rest.client;

import com.dip.rest.response.RestResponse;

public interface WeatherAPIClient extends RestAPIClient {
    public <WeatherResponse extends RestResponse> WeatherResponse translate() throws Exception;
}
