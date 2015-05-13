package com.dip.frontend.config;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.ServletContextAware;

import com.dip.frontend.constant.CommonWebConstant;

@Component
public class AppConfigPropagator implements ServletContextAware {

    @Autowired
    private IFrontendProperties baseFrontendProperties;

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.springframework.web.context.ServletContextAware#setServletContext
     * (javax.servlet.ServletContext)
     */
    @Override
    public void setServletContext(ServletContext servletContext) {
        servletContext.setAttribute(CommonWebConstant.serverUrl.name(), baseFrontendProperties.getServerUrl());
        servletContext.setAttribute(CommonWebConstant.resourceServerUrl.name(),
                baseFrontendProperties.getResourcesServerUrl());
        servletContext.setAttribute(CommonWebConstant.contextRoot.name(), baseFrontendProperties.getContextRoot());
    }

}
