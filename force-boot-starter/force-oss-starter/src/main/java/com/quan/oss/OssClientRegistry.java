package com.quan.oss;

import org.springframework.beans.factory.DisposableBean;

/**
 * OSS 注册中心
 *
 * @author Force-oneself
 * @date 2025-01-01
 */
public interface OssClientRegistry extends AutoCloseable, DisposableBean {

    /**
     * 注册OSS Client
     *
     * @param key    client key
     * @param client OSS Client
     */
    void register(String key, OssClient client);

    /**
     * 获取OSS Client
     *
     * @param key client key
     * @return OSS Client
     */
    OssClient get(String key);
}
