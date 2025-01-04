package com.quan.oss.support;

import com.quan.oss.OssClient;
import com.quan.oss.OssClientRegistry;
import com.quan.oss.OssContext;
import com.quan.oss.OssSelector;
import com.quan.oss.properties.OssTenantProperties;

/**
 * 多租户OSS客户端选择器
 *
 * @author Force-oneself
 * @date 2025-01-01
 */
public class OssTenantSelector implements OssSelector {

    private final OssClientRegistry registry;

    private final OssTenantProperties tenantProperties;

    public OssTenantSelector(OssClientRegistry registry, OssTenantProperties tenantProperties) {
        this.registry = registry;
        this.tenantProperties = tenantProperties;
    }

    @Override
    public OssClient select(OssContext context) {
        String tenantName = context.attach("Tenant");
        return tenantProperties.isEnable() && tenantProperties.isMultiClient()
                ? registry.get(tenantName)
                : null;
    }
}
