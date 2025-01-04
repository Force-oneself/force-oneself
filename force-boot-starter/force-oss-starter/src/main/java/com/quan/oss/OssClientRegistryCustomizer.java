package com.quan.oss;

/**
 * Description
 *
 * @author Force-oneself
 * @date 2025-01-01
 */
@FunctionalInterface
public interface OssClientRegistryCustomizer {

    /**
     * 自定义拓展注册实现
     *
     * @param registry OSS Client注册中心
     */
    void customize(OssClientRegistry registry);
}
