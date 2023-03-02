package com.quan.framework.spring.mvc.log;

import com.quan.framework.spring.mvc.filter.RepeatableWrapperFilter;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

import javax.servlet.Filter;

/**
 * @author Force-oneself
 * @date 2023-03-01
 */
@Configuration
@EnableConfigurationProperties(ServletLoggerProperties.class)
public class ServletLoggerConfig {

    @Bean
    @Order(Ordered.HIGHEST_PRECEDENCE + 100)
    public Filter repeatableWrapperFilter() {
        return new RepeatableWrapperFilter();
    }

    @Bean
    @Order(Ordered.HIGHEST_PRECEDENCE + 200)
    public Filter loggerFilter(ServletLoggerProperties properties) {
        return new LoggerFilter(properties);
    }
}
