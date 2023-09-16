package com.quan.demo.oss.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * OssProperties
 *
 * @author Force-oneself
 * @date 2023-01-13
 */
@ConfigurationProperties(prefix = "force.oss")
@Component
public class OssProperties {

    /**
     * 默认oss
     */
    private String defaultOss;
}
