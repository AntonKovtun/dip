package com.dip.rest.client.factory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.dip.rest.client.QiwiAPIClient;
import com.dip.rest.constant.ClientProvider;

@Service
public class QiwiApiClientFactory extends AbstractAPIClientFactory<QiwiAPIClient> {
    @Autowired
    @Qualifier("qiwiClient")
    private QiwiAPIClient qiwiApiClient;

    @Override
    public QiwiAPIClient getApiClient(ClientProvider provider) throws Exception {
        switch (provider) {
        case QiwiWallet:
            return qiwiApiClient;
        default:
            throw new UnsupportedOperationException("Unsupported API provider: " + provider);
        }
    }
}
