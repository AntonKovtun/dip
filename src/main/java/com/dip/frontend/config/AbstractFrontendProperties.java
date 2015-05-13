package com.sulin.frontend.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;

/**
 * @author Alexander Dukkardt
 * 
 */
public abstract class AbstractFrontendProperties implements IFrontendProperties {
    @Autowired
    private Environment env;

    @Override
    public String getServerUrl() {
        return env.getProperty("base.server.url");
    }

    @Override
    public String getResourcesServerUrl() {
        return env.getProperty("base.resourceServerUrl");
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.ssc.common.frontend.config.IBaseFrontendProperties#getContextRoot()
     */
    @Override
    public String getContextRoot() {
        return env.getProperty("frontend.contextRoot", "/");
    }

    @Override
    public String getCookieDomain() {
        return env.getProperty("sulin.auth.cookie.domain");
    }

    @Override
    public boolean expireOnBroserClose() {
        return env.getProperty("sulin.auth.cookie.expire.on.browser.close", Boolean.class, true);
    }



}
