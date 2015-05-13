package com.sulin.frontend.config;

import com.sulin.common.annotation.Local;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * @author Alexander Dukkardt
 *
 */
@Configuration
@Local
@PropertySource("classpath:frontend-local.properties")
public class LocalFrontendProperties extends AbstractFrontendProperties {

}
