package com.dip.frontend.web.util;

import org.springframework.context.support.ReloadableResourceBundleMessageSource;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;


public class ExtendedResourceBundleMessageSource extends ReloadableResourceBundleMessageSource {

    public Map<String, String> getMessagesMap(Locale locale){
        Map<String, String> messageMap = new HashMap<String, String>();

        PropertiesHolder propertiesHolder = this.getMergedProperties(locale);
        if(propertiesHolder!=null && propertiesHolder.getProperties()!=null){
            Enumeration<String> keys = (Enumeration<String>)propertiesHolder.getProperties().propertyNames();
            if(keys!=null){
                while(keys.hasMoreElements()){
                    String key = keys.nextElement();
                    messageMap.put(key, propertiesHolder.getProperties().getProperty(key));
                }
            }
        }
        return messageMap;
    }
}
