package com.quan.oss.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * OSS 配置类
 *
 * @author Force-oneself
 * @date 2024-12-29
 */
@ConfigurationProperties(prefix = OssProperties.PREFIX)
public class OssProperties {

    /**
     * OSS配置前缀
     */
    public static final String PREFIX = "force.oss";

    /**
     * 是否开启OSS配置
     */
    private boolean enable;

    /**
     * 默认OSS配置名称
     */
    private String defaultName;

    /**
     * 桶名称
     */
    private String bucketName;


    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    public String getDefaultName() {
        return defaultName;
    }

    public void setDefaultName(String defaultName) {
        this.defaultName = defaultName;
    }

    public String getBucketName() {
        return bucketName;
    }

    public void setBucketName(String bucketName) {
        this.bucketName = bucketName;
    }
}
