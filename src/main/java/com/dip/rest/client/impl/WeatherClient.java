package com.dip.rest.client.impl;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedHashMap;

import org.springframework.stereotype.Service;

import com.dip.rest.client.WeatherAPIClient;
import com.dip.rest.response.WeatherResponse;

@Service("weatherClient")
public class WeatherClient extends WeatherApiClient implements WeatherAPIClient {

    @Override
    public WeatherResponse translate() throws Exception {
        // TODO Auto-generated method stub
        Deque<String> reqParams = new ArrayDeque<String>();
        reqParams.push("data/2.5/weather");

        LinkedHashMap<String, String> queryParams = new LinkedHashMap<String, String>();
        queryParams.put("q", "London");

        return getResponse(reqParams, queryParams, "GET", null, WeatherResponse.class);
    }

    @Override
    protected String getContentTypeProperty() {
        // TODO Auto-generated method stub
        return "Json";
    }

    // protected HashMap<String, String> getRequestHeaders() {
    // HashMap<String, String> hearders = new HashMap<String, String>();
    // hearders.put("Authorization:", "");
    // return hearders;
    // }

}
