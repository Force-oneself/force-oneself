package com.quan.boot.mvc.config;

import com.quan.boot.mvc.constant.PropConstant;
import com.quan.boot.mvc.log.LoggerFilter;
import com.quan.boot.mvc.log.LoggerProperties;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.filter.CommonsRequestLoggingFilter;

import javax.servlet.DispatcherType;
import javax.servlet.ServletRequest;

/**
 * @author Force-oneself
 * @date 2023-03-01
 */
@Configuration(proxyBeanMethods = false)
@ConditionalOnClass(ServletRequest.class)
@EnableConfigurationProperties(LoggerProperties.class)
@ConditionalOnProperty(prefix = PropConstant.LOG, name = "enable", matchIfMissing = true)
public class LoggerAutoConfig {

    @Bean
    public FilterRegistrationBean<LoggerFilter> loggerFilter(LoggerProperties properties) {
        FilterRegistrationBean<LoggerFilter> registration = new FilterRegistrationBean<>();
        registration.setDispatcherTypes(DispatcherType.REQUEST);
        registration.setFilter(new LoggerFilter(properties));
        registration.addUrlPatterns("/*");
        registration.setName(LoggerFilter.class.getSimpleName());
        registration.setOrder(Ordered.HIGHEST_PRECEDENCE + 200);
        return registration;
    }

    @Bean
    public FilterRegistrationBean<CommonsRequestLoggingFilter> commonsRequestLoggingFilter(LoggerProperties properties) {
        FilterRegistrationBean<CommonsRequestLoggingFilter> registration = new FilterRegistrationBean<>();
        registration.setDispatcherTypes(DispatcherType.REQUEST);
        CommonsRequestLoggingFilter loggingFilter = new CommonsRequestLoggingFilter();
        loggingFilter.setIncludeClientInfo(true);
        loggingFilter.setIncludeHeaders(true);
        loggingFilter.setIncludePayload(true);
        loggingFilter.setIncludeQueryString(true);

        registration.setFilter(loggingFilter);
        registration.addUrlPatterns("/*");
        registration.setName(CommonsRequestLoggingFilter.class.getSimpleName());
        registration.setOrder(Ordered.HIGHEST_PRECEDENCE + 200);
        return registration;
    }

}
