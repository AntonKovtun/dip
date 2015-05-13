package com.sulin.backend.config;

import com.sulin.common.annotation.Local;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

/**
 * Created with IntelliJ IDEA.
 * User: alexander
 * Date: 10/27/13
 * Time: 7:08 PM
 * To change this template use File | Settings | File Templates.
 */
@Configuration
@Local
@PropertySources(value={@PropertySource("classpath:backend-local.properties")})
public class LocalBackendProperties extends AbstractBackendProperties {
}
