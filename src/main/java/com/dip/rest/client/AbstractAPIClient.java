package com.sulin.rest.client;

import java.util.Deque;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.apache.log4j.Logger;

import com.sulin.rest.constant.ContentProcessorType;
import com.sulin.rest.processor.AbstractProcessor;
import com.sulin.rest.processor.ProcessorFactory;
import com.sulin.rest.response.RestResponse;
import com.sulin.rest.transport.HttpRequestWrapper;
import com.sulin.rest.transport.UrlWrapper;

public abstract class AbstractAPIClient {
    protected final Logger log = Logger.getLogger(this.getClass());
    protected ContentProcessorType contentProcessorType = ContentProcessorType.Json;
    protected static final String REST_BASE_URI = "base";
    protected Map<String, String> properties;

    private AbstractProcessor processor;

    protected abstract String getContentTypeProperty();

    protected abstract String getBaseUrl();

    @PostConstruct
    public void initAPIClient() throws Exception {
        setContentType();
        setDefaultUrl(getBaseUrl());
        processor = ProcessorFactory.getInstance(this.contentProcessorType);
    }

    private void setContentType() throws Exception {
        try {
            this.contentProcessorType = ContentProcessorType.valueOf(getContentTypeProperty());
        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
            throw new UnsupportedOperationException(
                    "Cannot initialized Content Type. Passed  Content Type is not found");
        }
    }

    /**
     * @return the properties
     */
    protected Map<String, String> getProperties() {
        return properties;
    }

    /**
     * @param properties
     *            the properties to set
     */
    protected void setProperties(Map<String, String> properties) {
        this.properties = properties;
    }

    /**
     * Returns the value of the property with the passed name.
     * 
     * @param name
     *            Name of desired property
     */
    protected String getProperty(String name) {
        return properties.get(name);
    }

    protected void setProperty(String key, String value) {
        if (properties == null) {
            properties = new HashMap<String, String>();
        }
        properties.put(key, value);
    }

    /**
     * 
     * @param defaultUrl
     */
    protected void setDefaultUrl(String defaultUrl) {
        setProperty(REST_BASE_URI, defaultUrl);
    }

    protected UrlWrapper getRequestUrl(Deque<String> reqParams, LinkedHashMap<String, String> queryParams) {
        UrlWrapper requestUrl = new UrlWrapper(getProperty(REST_BASE_URI));
        while (reqParams != null && !reqParams.isEmpty()) {
            requestUrl.addPathComponent(reqParams.pop());
        }
        String key = "";
        if (queryParams != null) {
            for (Iterator<String> it = queryParams.keySet().iterator(); it.hasNext();) {
                key = it.next();
                requestUrl.addQueryStringParameter(key, queryParams.get(key));
            }
        }
        return requestUrl;
    }

    protected <ServiceResponse extends RestResponse> ServiceResponse getResponse(Deque<String> reqParams,
            LinkedHashMap<String, String> queryParams, String method, Object postBody,
            Class<ServiceResponse> responseClass) throws Exception {
        return processor.readResponse(getResponse(reqParams, queryParams, method, postBody), responseClass);
    }

    protected String getResponse(Deque<String> reqParams, LinkedHashMap<String, String> queryParams, String method,
            Object postBody) throws Exception {
        return getResponse(reqParams, queryParams, method, postBody, getRequestHeaders());
    }

    protected String getResponse(Deque<String> reqParams, LinkedHashMap<String, String> queryParams, String method,
            Object postBody, HashMap<String, String> headers) throws Exception {
        HttpRequestWrapper request = getRequest(reqParams, queryParams, method, postBody, headers);
        signRequest(method, postBody, request.getUrl());
        return request.execute(addHeaders(request.getUrl()));
    }

    protected HttpRequestWrapper getRequest(Deque<String> reqParams, LinkedHashMap<String, String> queryParams,
            String method, Object postBody, HashMap<String, String> headers) throws Exception {
        UrlWrapper requestUrl = getRequestUrl(reqParams, queryParams);
        return getRequest(method, postBody, requestUrl, headers);
    }

    protected HttpRequestWrapper getRequest(String method, Object postBody, UrlWrapper requestUrl) throws Exception {
        return getRequest(method, postBody, requestUrl, null);
    }

    protected HttpRequestWrapper getRequest(String method, Object postBody, UrlWrapper requestUrl,
            HashMap<String, String> headers) throws Exception {
        return processor.getRequest(method, postBody, requestUrl, headers);
    }

    protected HashMap<String, String> getRequestHeaders() {
        return null;
    }

    /**
     * @return
     * @throws Exception
     */
    protected HashMap<String, String> addHeaders() throws Exception {
        return addHeaders(null);
    }

    /**
     * @param requestUrlContentType
     * @return
     * @throws Exception
     */
    protected HashMap<String, String> addHeaders(UrlWrapper requestUrl) throws Exception {
        return null;
    }

    protected void signRequest(String method, Object postBody, UrlWrapper requestUrl) throws Exception {
    }

}
