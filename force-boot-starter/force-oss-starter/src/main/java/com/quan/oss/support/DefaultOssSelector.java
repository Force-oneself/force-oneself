package com.quan.oss.support;

import com.quan.oss.OssClient;
import com.quan.oss.OssClientRegistry;
import com.quan.oss.OssContext;
import com.quan.oss.OssSelector;
import com.quan.oss.properties.OssProperties;

/**
 * 默认实现OSS客户端选择器
 *
 * @author Force-oneself
 * @date 2025-01-01
 */
public class DefaultOssSelector implements OssSelector {

    /**
     * OSS注册中心
     */
    private final OssClientRegistry registry;

    /**
     * OSS配置
     */
    private final OssProperties properties;

    public DefaultOssSelector(OssClientRegistry registry, OssProperties properties) {
        this.registry = registry;
        this.properties = properties;
    }

    @Override
    public OssClient select(OssContext context) {
        return registry.get(properties.getDefaultName());
    }
}
