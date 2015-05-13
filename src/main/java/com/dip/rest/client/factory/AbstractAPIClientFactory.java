package com.sulin.rest.client.factory;

import com.sulin.rest.client.RestAPIClient;
import com.sulin.rest.constant.ClientProvider;

public abstract class AbstractAPIClientFactory<Client extends RestAPIClient> {
    public abstract Client getApiClient(ClientProvider provider) throws Exception;
}
