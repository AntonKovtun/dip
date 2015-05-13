package com.dip.frontend.config;

import com.dip.common.annotation.Dev;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;


@Configuration
@Dev
@PropertySource("classpath:frontend-dev.properties")
public class DevFrontendProperties extends AbstractFrontendProperties {

}
