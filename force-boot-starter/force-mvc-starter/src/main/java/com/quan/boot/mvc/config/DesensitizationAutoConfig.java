package com.quan.boot.mvc.config;

import com.quan.boot.mvc.desensitization.DesensitizationAnnotationIntrospector;
import com.quan.boot.mvc.desensitization.Masker;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
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
    public Jackson2ObjectMapperBuilderCustomizer desensitizationJacksonCustomizer(List<Masker> maskers) {
        return builder -> builder.annotationIntrospector(new DesensitizationAnnotationIntrospector(maskers));
    }
}
