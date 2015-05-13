package com.sulin.frontend.config;

import com.sulin.common.annotation.Dev;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * @author Alexander Dukkardt
 *
 */
@Configuration
@Dev
@PropertySource("classpath:frontend-dev.properties")
public class DevFrontendProperties extends AbstractFrontendProperties {

}
