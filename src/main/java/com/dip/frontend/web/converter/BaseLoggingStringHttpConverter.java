package com.dip.frontend.web.converter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.converter.StringHttpMessageConverter;

import java.io.IOException;

public class BaseLoggingStringHttpConverter extends StringHttpMessageConverter {
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @SuppressWarnings("rawtypes")
    @Override
    protected String readInternal(Class clazz, HttpInputMessage inputMessage)
            throws IOException {
        String s = super.readInternal(clazz, inputMessage);
        log.info("request (ThreadId={}):\n{}", new Object[] {
                Thread.currentThread().getId(), s });
        return s;
    }

}
