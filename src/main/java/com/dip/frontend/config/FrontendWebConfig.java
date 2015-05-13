package com.dip.frontend.config;

import com.dip.frontend.web.converter.BaseLoggingStringHttpConverter;
import com.dip.frontend.web.interceptor.BaseLogInterceptor;
import com.dip.frontend.web.interceptor.SecurityInterceptor;
import com.dip.frontend.web.util.ExtendedResourceBundleMessageSource;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJacksonHttpMessageConverter;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import java.util.List;

@Configuration
@EnableWebMvc
@EnableAsync
@ComponentScan(basePackages = {"com.dip.frontend", "com.dip.rest"})
public class FrontendWebConfig extends WebMvcConfigurerAdapter {

    /*
     * (non-Javadoc)
     * 
     * @see
     * 
     * org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter
     * #addResourceHandlers(org.springframework.web.servlet.config.annotation.
     * ResourceHandlerRegistry)
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
    }

    @Bean
    public ViewResolver getViewResolver(){
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/WEB-INF/view/");
        resolver.setSuffix(".jsp");
        return resolver;
    }
    @Bean
    public MultipartResolver multipartResolver(){
        CommonsMultipartResolver resolver = new CommonsMultipartResolver();
        resolver.setMaxUploadSize(90000000l);
        return resolver;
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/");
    }

    @Bean
    public MessageSource messageSource() {
        ExtendedResourceBundleMessageSource ms = new ExtendedResourceBundleMessageSource();
        ms.setDefaultEncoding("UTF-8");
        ms.setCacheSeconds(600);
        ms.setFallbackToSystemLocale(false);
        ms.setBasenames(new String[] { "classpath:i18n/labels", "classpath:i18n/messages" });
        return ms;
    }

    /*
     * (non-Javadoc)
     *
     * @see
     *
     * org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter
     * #addInterceptors(org.springframework.web.servlet.config.annotation.
     * InterceptorRegistry)
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new BaseLogInterceptor());
        InterceptorRegistration securityInterceptor = registry
                .addInterceptor(securityInterceptor());
        securityInterceptor.addPathPatterns("/**");
    }
    @Bean
    public HandlerInterceptorAdapter securityInterceptor(){
        return new SecurityInterceptor();
    }

    @Override
    public void configureMessageConverters(
            List<HttpMessageConverter<?>> converters) {
        converters.add(new BaseLoggingStringHttpConverter());

        converters.add(new MappingJacksonHttpMessageConverter());
//        converters.add(new MappingJackson2HttpMessageConverter());
    }

}