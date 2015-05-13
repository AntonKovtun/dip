package com.dip.backend.config;

import com.dip.common.annotation.Dev;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

@Configuration
@Dev
@PropertySources(value={@PropertySource("classpath:backend-dev.properties")})
public class DevBackendProperties extends AbstractBackendProperties {

}
