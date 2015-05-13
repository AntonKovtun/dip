package com.sulin.rest.processor;

import com.sulin.rest.constant.ContentProcessorType;

public class ProcessorFactory {

    public static AbstractProcessor getInstance(ContentProcessorType contentProcessorType) throws Exception {
        switch (contentProcessorType) {
        case Json:
            return new JsonProcessor();
        case Xml:
            return new XmlProcessor();
        case Qiwi:
            return new QiwiProcessor();
        default:
            throw new UnsupportedOperationException("Unknown content type. Cannot find processor");
        }
    }
}
