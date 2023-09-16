package com.quan.boot.mvc.config;

import com.quan.boot.mvc.desensitization.DesensitizationPostProcessor;
import com.quan.boot.mvc.desensitization.Operation;
import com.quan.boot.mvc.desensitization.Desensitization;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import java.util.List;

/**
 * DesensitizationAutoConfiguration
 *
 * @author Force-oneself
 * @date 2022-07-03
 */
@Configuration(proxyBeanMethods = false)
@ConditionalOnClass({Jackson2ObjectMapperBuilder.class})
public class DesensitizationAutoConfig {

    @Bean
    @ConditionalOnMissingBean
    public DesensitizationPostProcessor desensitizationPostProcessor(List<Operation> operations) {
        return new DesensitizationPostProcessor(operations);
    }

    @Bean
    public Operation noOperation() {
        return new Desensitization.NoOperation();
    }

    @Bean
    public Operation operation() {
        return new SimpleOperation();
    }

    public static class SimpleOperation implements Operation {

        @Override
        public String mask(String content, String maskChar) {
            return "*";
        }
    }
}
