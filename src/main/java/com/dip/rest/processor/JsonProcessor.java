package com.dip.rest.processor;

import org.codehaus.jackson.map.ObjectMapper;

import com.dip.rest.constant.ContentType;
import com.dip.rest.response.RestResponse;

class JsonProcessor extends AbstractProcessor {

    @Override
    public <ServiceResponse extends RestResponse> ServiceResponse readResponse(String responseBody,
            Class<ServiceResponse> clazz) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        mapper.readValue(responseBody, clazz);
        return mapper.readValue(responseBody, clazz);
    }

    @Override
    public String processRequestBody(Object postBody) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(postBody);
    }

    protected void setContentType() {
        this.contentType = ContentType.Json;
    }
}
