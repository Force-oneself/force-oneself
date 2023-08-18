package com.quan.boot.mvc.config;

import com.quan.boot.mvc.repeat.RepeatableWrapperFilter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;

import javax.servlet.DispatcherType;

/**
 * @author Force-oneself
 * @date 2023-08-17
 */
@Configuration(proxyBeanMethods = false)
@ConditionalOnProperty(prefix = "force.servlet.repeat", name = "enable", matchIfMissing = true)
public class RepeatableAutoConfig {

    @Bean
    public FilterRegistrationBean<RepeatableWrapperFilter> repeatableWrapperFilter() {
        FilterRegistrationBean<RepeatableWrapperFilter> registration = new FilterRegistrationBean<>();
        registration.setDispatcherTypes(DispatcherType.REQUEST);
        registration.setFilter(new RepeatableWrapperFilter());
        registration.addUrlPatterns("/*");
        registration.setName("repeatableWrapperFilter");
        registration.setOrder(Ordered.HIGHEST_PRECEDENCE + 100);
        return registration;
    }
}
