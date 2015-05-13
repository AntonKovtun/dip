package com.dip.backend.config;

import com.dip.common.annotation.Local;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

@Configuration
@Local
@PropertySources(value={@PropertySource("classpath:backend-local.properties")})
public class LocalBackendProperties extends AbstractBackendProperties {
}
