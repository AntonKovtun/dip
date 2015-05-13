package com.sulin.rest.client.factory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.sulin.rest.client.WeatherAPIClient;
import com.sulin.rest.constant.ClientProvider;

@Service
public class WeatherApiClientFactory extends AbstractAPIClientFactory<WeatherAPIClient> {
    @Autowired
    @Qualifier("weatherClient")
    private WeatherAPIClient weatherApiClient;

    @Override
    public WeatherAPIClient getApiClient(ClientProvider provider) throws Exception {
        switch (provider) {
        case Openweathermap:
            return weatherApiClient;
        default:
            throw new UnsupportedOperationException("Unsupported API provider: " + provider);
        }
    }
}
