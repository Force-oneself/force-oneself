package com.quan.boot.mvc.config;

import com.quan.boot.mvc.limit.DefaultRateLimitManager;
import com.quan.boot.mvc.limit.RateLimitFilter;
import com.quan.boot.mvc.limit.RateLimitManager;
import com.quan.boot.mvc.limit.RateLimitProperties;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
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
    public FilterRegistrationBean<RateLimitFilter> rateLimitFilter(RateLimitManager manager) {
        FilterRegistrationBean<RateLimitFilter> registration = new FilterRegistrationBean<>();
        registration.setDispatcherTypes(DispatcherType.REQUEST);
        registration.setFilter(new RateLimitFilter(manager));
        registration.addUrlPatterns("/*");
        registration.setName("rateLimitFilter");
        registration.setOrder(Ordered.HIGHEST_PRECEDENCE + 201);
        return registration;
    }

    @Bean
    @ConditionalOnMissingBean
    public DefaultRateLimitManager rateLimitManager(RateLimitProperties properties) {
        return new DefaultRateLimitManager(properties);
    }
}
