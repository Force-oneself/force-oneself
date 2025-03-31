package com.quan.boot.mvc.config;

import com.quan.boot.mvc.constant.PropConstant;
import com.quan.boot.mvc.repeat.ContentCachingWrapperFilter;
import com.quan.boot.mvc.repeat.RepeatableWrapperFilter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;

import javax.servlet.DispatcherType;
import javax.servlet.ServletRequest;

/**
 * @author Force-oneself
 * @date 2023-08-17
 */
@Configuration(proxyBeanMethods = false)
@ConditionalOnClass(ServletRequest.class)
@ConditionalOnProperty(prefix = PropConstant.REPEAT, name = "enable", matchIfMissing = true)
public class RepeatableAutoConfig {

    @Bean
    public FilterRegistrationBean<RepeatableWrapperFilter> repeatableWrapperFilter() {
        FilterRegistrationBean<RepeatableWrapperFilter> registration = new FilterRegistrationBean<>();
        registration.setDispatcherTypes(DispatcherType.REQUEST);
        registration.setFilter(new RepeatableWrapperFilter());
        registration.addUrlPatterns("/*");
        registration.setName(RepeatableWrapperFilter.class.getSimpleName());
        registration.setOrder(Ordered.HIGHEST_PRECEDENCE + 100);
        return registration;
    }

    @Bean
    public FilterRegistrationBean<ContentCachingWrapperFilter> contentCachingWrapperFilter() {
        FilterRegistrationBean<ContentCachingWrapperFilter> registration = new FilterRegistrationBean<>();
        registration.setDispatcherTypes(DispatcherType.REQUEST);
        registration.setFilter(new ContentCachingWrapperFilter());
        registration.addUrlPatterns("/*");
        registration.setName(ContentCachingWrapperFilter.class.getSimpleName());
        registration.setOrder(Ordered.HIGHEST_PRECEDENCE + 99);
        return registration;
    }
}
