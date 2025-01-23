package com.quan.cache.properties;

import com.quan.cache.support.local.policy.CleanupPolicy;

/**
 * 清理策略配置
 *
 * @author Force-oneself
 * @date 2025-01-04
 */
public class CleanupProperties {

    /**
     * 清理策略
     */
    private CleanupPolicy policy = CleanupPolicy.SCHEDULED;

    /**
     * 定时清理间隔（秒）
     */
    private int intervalSeconds = 300;

    /**
     * 触发清理的容量比例（0-1）
     */
    private double cleanupRatio = 0.75;

    /**
     * 每次清理的最大条目数
     */
    private int batchSize = 100;

    /**
     * 空闲检测时间（秒）
     */
    private int idleSeconds = 1800;

    public CleanupProperties() {
    }

    public CleanupPolicy getPolicy() {
        return this.policy;
    }

    public int getIntervalSeconds() {
        return this.intervalSeconds;
    }

    public double getCleanupRatio() {
        return this.cleanupRatio;
    }

    public int getBatchSize() {
        return this.batchSize;
    }

    public int getIdleSeconds() {
        return this.idleSeconds;
    }

    public void setPolicy(CleanupPolicy policy) {
        this.policy = policy;
    }

    public void setIntervalSeconds(int intervalSeconds) {
        this.intervalSeconds = intervalSeconds;
    }

    public void setCleanupRatio(double cleanupRatio) {
        this.cleanupRatio = cleanupRatio;
    }

    public void setBatchSize(int batchSize) {
        this.batchSize = batchSize;
    }

    public void setIdleSeconds(int idleSeconds) {
        this.idleSeconds = idleSeconds;
    }

}