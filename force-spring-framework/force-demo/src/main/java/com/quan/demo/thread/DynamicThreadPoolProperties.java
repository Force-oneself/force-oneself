package com.quan.demo.thread;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;

import java.util.Map;

/**
 * Description
 *
 * @author Force-oneself
 * @date 2025-01-20
 */
@Getter
@Setter
@ConfigurationProperties(prefix = DynamicThreadPoolProperties.PREFIX)
public class DynamicThreadPoolProperties {

    public final static String PREFIX = "force.dynamic.tp";

    @NestedConfigurationProperty
    private Map<String, ThreadPoolProperties> threadPools;
}
