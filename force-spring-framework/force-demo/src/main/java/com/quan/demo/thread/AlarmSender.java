package com.quan.demo.thread;

/**
 * 告警发送器接口
 *
 * @author Force-oneself
 * @date 2025-01-23
 */
public interface AlarmSender {

    /**
     * 发送告警
     *
     * @param metrics 线程池监控信息
     */
    void send(ThreadPoolMetrics metrics);
}
