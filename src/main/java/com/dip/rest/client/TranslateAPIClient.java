package com.sulin.rest.client;

import com.sulin.rest.response.RestResponse;

public interface TranslateAPIClient extends RestAPIClient {
    public <TranslateResponse extends RestResponse> TranslateResponse translate(String text, String sourceLang,
            String targetLang) throws Exception;
}
