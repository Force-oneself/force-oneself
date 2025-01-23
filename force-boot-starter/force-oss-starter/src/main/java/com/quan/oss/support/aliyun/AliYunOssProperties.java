package com.quan.oss.support.aliyun;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Description
 *
 * @author Force-oneself
 * @date 2025-01-01
 */
@ConfigurationProperties(prefix = "force.oss.aliyun")
public class AliYunOssProperties {

    /**
     * Endpoint以华东1（杭州）为例，其它Region请按实际情况填写。
     */
    private String endpoint = "https://oss-cn-hangzhou.aliyuncs.com";

    /**
     * 填写Bucket名称，例如examplebucket。
     */
    private String bucketName = "bucket";

    /**
     * 填写Bucket所在地域。以华东1（杭州）为例，Region填写为cn-hangzhou。
     */
    private String region = "cn-hangzhou";

    /**
     * 访问密钥
     */
    private String accessKey;

    /**
     * 安全密钥
     */
    private String secretKey;


    public String getEndpoint() {
        return endpoint;
    }

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    public String getBucketName() {
        return bucketName;
    }

    public void setBucketName(String bucketName) {
        this.bucketName = bucketName;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    public String getAccessKey() {
        return accessKey;
    }

    public void setAccessKey(String accessKey) {
        this.accessKey = accessKey;
    }
}
