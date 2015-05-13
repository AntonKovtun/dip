/**
 * 
 */
package com.sulin.frontend.config;

/**
 * @author Alexander Dukkardt
 * 
 */
public interface IFrontendProperties {

    public String getServerUrl();

    //
    public String getContextRoot();

    public String getResourcesServerUrl();

    public String getCookieDomain();

    public boolean expireOnBroserClose();



}
