package com.quan.framework.spring.mvc.log;

import com.quan.framework.spring.mvc.filter.RepeatableWrapperFilter;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;

import javax.servlet.DispatcherType;

/**
 * @author Force-oneself
 * @date 2023-03-01
 */
@Configuration
@EnableConfigurationProperties(ServletLoggerProperties.class)
public class ServletLoggerAutoConfig {

    @Bean
    public FilterRegistrationBean<LoggerFilter> loggerFilter(ServletLoggerProperties properties) {
        FilterRegistrationBean<LoggerFilter> registration = new FilterRegistrationBean<>();
        registration.setDispatcherTypes(DispatcherType.REQUEST);
        registration.setFilter(new LoggerFilter(properties));
        registration.addUrlPatterns("/*");
        registration.setName("loggerFilter");
        registration.setOrder(Ordered.HIGHEST_PRECEDENCE + 200);
        return registration;
    }

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
