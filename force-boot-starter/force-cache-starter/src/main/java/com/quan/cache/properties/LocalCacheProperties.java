package com.quan.cache.properties;

import com.quan.cache.support.local.policy.EvictionPolicy;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 本地缓存配置属性
 *
 * @author Force-oneself
 * @date 2025-01-04
 */
@ConfigurationProperties(prefix = "force.cache.local")
public class LocalCacheProperties {

    /**
     * 初始容量
     */
    private int initialCapacity = 100;

    /**
     * 最大容量
     */
    private int maximumSize = 10000;

    /**
     * 过期时间（分钟）
     */
    private int expireAfterMinutes = 30;

    /**
     * 淘汰策略
     */
    private EvictionPolicy evictionPolicy = EvictionPolicy.LRU;

    /**
     * 清理策略配置
     */
    private CleanupProperties cleanup = new CleanupProperties();

    public LocalCacheProperties() {
    }

    public int getInitialCapacity() {
        return this.initialCapacity;
    }

    public int getMaximumSize() {
        return this.maximumSize;
    }

    public int getExpireAfterMinutes() {
        return this.expireAfterMinutes;
    }

    public EvictionPolicy getEvictionPolicy() {
        return this.evictionPolicy;
    }

    public CleanupProperties getCleanup() {
        return this.cleanup;
    }

    public void setInitialCapacity(int initialCapacity) {
        this.initialCapacity = initialCapacity;
    }

    public void setMaximumSize(int maximumSize) {
        this.maximumSize = maximumSize;
    }

    public void setExpireAfterMinutes(int expireAfterMinutes) {
        this.expireAfterMinutes = expireAfterMinutes;
    }

    public void setEvictionPolicy(EvictionPolicy evictionPolicy) {
        this.evictionPolicy = evictionPolicy;
    }

    public void setCleanup(CleanupProperties cleanup) {
        this.cleanup = cleanup;
    }
}