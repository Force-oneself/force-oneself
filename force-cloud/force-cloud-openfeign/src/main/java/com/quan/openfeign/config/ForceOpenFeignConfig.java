package com.quan.openfeign.config;

import feign.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cloud.netflix.ribbon.SpringClientFactory;
import org.springframework.cloud.openfeign.*;
import org.springframework.cloud.openfeign.ribbon.CachingSpringLoadBalancerFactory;
import org.springframework.cloud.openfeign.ribbon.LoadBalancerFeignClient;
import org.springframework.cloud.openfeign.support.SpringMvcContract;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.support.DefaultConversionService;

import javax.servlet.http.HttpServletResponse;
import java.nio.charset.StandardCharsets;
import java.util.*;

/**
 * ForceOpenFeignConfig
 *
 * @author Force-oneself
 * @date 2022-11-25
 */
@Configuration(proxyBeanMethods = false)
public class ForceOpenFeignConfig {


    @Configuration(proxyBeanMethods = false)
    @ConditionalOnClass(name = "feign.hystrix.HystrixFeign")
    @ConditionalOnProperty(value = "feign.hystrix.enabled", havingValue = "true",
            matchIfMissing = true)
    protected static class HystrixFeignTargeterConfiguration {

        @Bean
        @ConditionalOnMissingBean
        public Targeter feignTargeter() {
            return new ForceHystrixTargeter();
        }

    }

    @Bean
    public Contract feignContract(ObjectProvider<ConversionService> objectProvider,
                                  List<AnnotatedParameterProcessor> annotatedParameterProcessors) {
        ConversionService conversionService = objectProvider.getIfAvailable(DefaultConversionService::new);
        return new SpringMvcContract(annotatedParameterProcessors, conversionService);
    }

    @Bean
    public LoadBalancerFeignClient feignClient(CachingSpringLoadBalancerFactory cachingFactory,
                                               SpringClientFactory clientFactory) {

        return new LoadBalancerFeignClient(new Client.Default(null, null), cachingFactory,
                clientFactory) {
            public  final Logger log = LoggerFactory.getLogger(ForceOpenFeignConfig.class);

            @Override
            public Response execute(Request request, Request.Options options) {
                Map<String, Collection<String>> headers = new HashMap<>(2);
                headers.put("content-type", Collections.singletonList("application/json"));
                headers.put("transfer-encoding", Collections.singletonList("chunked"));

                Response.Builder builder = Response.builder()
                        .status(200).headers(headers)
                        .request(request);
                try {
                    Response resp = super.execute(request, options);
                    if (Objects.equals(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, resp.status())) {
                        log.error("服务内部错误, req: {}, data: {}", request.url(),
                                new String(Optional.ofNullable(request.body()).orElse(new byte[]{}), StandardCharsets.UTF_8));
                        return builder.body("服务错误".getBytes()).build();
                    }
                    return resp;
                } catch (Exception e) {
                    log.error("服务不可用, req: {}, data: {}", request.url(),
                            new String(Optional.ofNullable(request.body()).orElse(new byte[]{}), StandardCharsets.UTF_8));
                    return builder.body(("服务不可用").getBytes()).build();
                }
            }
        };
    }


}
