package com.quan.demo.thread;

/**
 * 告警通知接口
 *
 * @author Force-oneself
 * @date 2025-01-20
 */
public interface AlarmNotifier {

    /**
     * 发送告警通知
     *
     * @param metrics 线程池监控指标
     */
    void send(ThreadPoolMetrics metrics);
}