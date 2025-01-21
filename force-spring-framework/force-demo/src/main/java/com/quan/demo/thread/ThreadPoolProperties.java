package com.quan.demo.thread;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Description
 *
 * @author Force-oneself
 * @date 2025-01-20
 */
@Getter
@Setter
public class ThreadPoolProperties {

    /**
     * 线程池名称
     */
    private String name;

    /**
     * 核心线程数
     */
    private int corePoolSize;

    /**
     * 最大线程数
     */
    private int maximumPoolSize;

    /**
     * 队列大小
     */
    private int queueSize;

    /**
     * 线程存活时间
     */
    private long keepAliveTime;

    /**
     * 拒绝策略
     */
    private String rejectPolicy;

    /**
     * 线程工厂
     */
    private String threadFactory;

    /**
     * 告警通知
     */
    private String alarmNotify;
}
