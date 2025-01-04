package com.quan.oss;

/**
 * OSS资源对象
 *
 * @author Force-oneself
 * @date 2024-12-30
 */
public class OS {

    /**
     * 地址
     */
    private String url;

    /**
     * 节点
     */
    private String endpoint;

    /**
     * 桶
     */
    private String bucketName;

    /**
     * 区域
     */
    private String region;

    /**
     * 对象名
     */
    private String objectName;


    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

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

    public String getObjectName() {
        return objectName;
    }

    public void setObjectName(String objectName) {
        this.objectName = objectName;
    }
}
