package com.dip.rest.client.factory;

import com.dip.rest.client.RestAPIClient;
import com.dip.rest.constant.ClientProvider;

public abstract class AbstractAPIClientFactory<Client extends RestAPIClient> {
    public abstract Client getApiClient(ClientProvider provider) throws Exception;
}
