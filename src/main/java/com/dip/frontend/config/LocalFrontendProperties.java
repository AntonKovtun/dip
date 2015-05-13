package com.dip.frontend.config;

import com.dip.common.annotation.Local;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;


@Configuration
@Local
@PropertySource("classpath:frontend-local.properties")
public class LocalFrontendProperties extends AbstractFrontendProperties {

}
