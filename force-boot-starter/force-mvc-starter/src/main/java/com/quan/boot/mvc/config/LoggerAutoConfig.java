package com.quan.boot.mvc.config;

import com.quan.boot.mvc.constant.PropConstant;
import com.quan.boot.mvc.log.LoggerFilter;
import com.quan.boot.mvc.log.LoggerProperties;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
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
@EnableConfigurationProperties(LoggerProperties.class)
@ConditionalOnProperty(prefix = PropConstant.LOG, name = "enable", matchIfMissing = true)
public class LoggerAutoConfig {

    @Bean
    public FilterRegistrationBean<LoggerFilter> loggerFilter(LoggerProperties properties) {
        FilterRegistrationBean<LoggerFilter> registration = new FilterRegistrationBean<>();
        registration.setDispatcherTypes(DispatcherType.REQUEST);
        registration.setFilter(new LoggerFilter(properties));
        registration.addUrlPatterns("/*");
        registration.setName("loggerFilter");
        registration.setOrder(Ordered.HIGHEST_PRECEDENCE + 200);
        return registration;
    }

}
