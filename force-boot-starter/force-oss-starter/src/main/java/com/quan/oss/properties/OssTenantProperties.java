package com.quan.oss.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Map;

/**
 * 多租户OSS配置
 *
 * @author Force-oneself
 * @date 2025-01-01
 */
@ConfigurationProperties(prefix = OssTenantProperties.PREFIX)
public class OssTenantProperties {

    public static final String PREFIX = "force.oss.tenant";

    /**
     * 多租户OSS配置是否开启
     */
    private boolean enable;

    /**
     * 多客户端
     */
    private boolean multiClient;

    /**
     * 各个租户配置Client Key信息
     */
    private Map<String, String> clients;


    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    public boolean isMultiClient() {
        return multiClient;
    }

    public void setMultiClient(boolean multiClient) {
        this.multiClient = multiClient;
    }

    public Map<String, String> getClients() {
        return clients;
    }

    public void setClients(Map<String, String> clients) {
        this.clients = clients;
    }
}
