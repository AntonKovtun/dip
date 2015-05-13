package com.sulin.backend.config;

import com.sulin.common.annotation.Dev;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

/**
 * @author Alexander Dukkardt
 *
 */
@Configuration
@Dev
@PropertySources(value={@PropertySource("classpath:backend-dev.properties")})
public class DevBackendProperties extends AbstractBackendProperties {

}
