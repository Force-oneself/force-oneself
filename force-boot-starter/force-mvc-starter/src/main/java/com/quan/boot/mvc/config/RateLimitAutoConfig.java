package com.quan.boot.mvc.config;

import com.quan.boot.mvc.limit.RateLimitFilter;
import com.quan.boot.mvc.limit.RateLimitProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;

import javax.servlet.DispatcherType;

/**
 * @author Force-oneself
 * @date 2023-09-24
 */
@Configuration(proxyBeanMethods = false)
@EnableConfigurationProperties(RateLimitProperties.class)
public class RateLimitAutoConfig {


    @Bean
    public FilterRegistrationBean<RateLimitFilter> rateLimitFilter(RateLimitProperties properties) {
        FilterRegistrationBean<RateLimitFilter> registration = new FilterRegistrationBean<>();
        registration.setDispatcherTypes(DispatcherType.REQUEST);
        registration.setFilter(new RateLimitFilter(properties));
        registration.addUrlPatterns("/*");
        registration.setName("rateLimitFilter");
        registration.setOrder(Ordered.HIGHEST_PRECEDENCE + 201);
        return registration;
    }
}
