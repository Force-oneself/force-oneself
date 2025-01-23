package com.quan.demo.thread;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.concurrent.*;

/**
 * 告警管理类
 *
 * @author Force-oneself
 * @date 2025-01-20
 */
public class AlarmManager {

    /**
     * 日志记录器
     */
    private static final Logger log = LoggerFactory.getLogger(AlarmManager.class);

    /**
     * 已注册告警监控的线程池集合
     */
    private final Map<String, ThreadPoolExecutor> monitoredThreadPools = new ConcurrentHashMap<>();

    /**
     * 启动定时监控任务
     */
    private final ScheduledExecutorService scheduledExecutorService = new ScheduledThreadPoolExecutor(1);

    /**
     * 告警通知器
     */
    private final AlarmNotifier alarmNotifier;

    public AlarmManager(AlarmNotifier alarmNotifier) {
        this.alarmNotifier = alarmNotifier;
    }

    /**
     * 注册线程池监控
     *
     * @param name               线程池名称
     * @param threadPoolExecutor 线程池执行器
     */
    public void register(String name, ThreadPoolExecutor threadPoolExecutor) {
        monitoredThreadPools.put(name, threadPoolExecutor);
    }

    /**
     * 启动告警监控
     */
    public void start() {
        // 启动定时监控任务
        scheduledExecutorService.scheduleAtFixedRate(() -> monitoredThreadPools.entrySet()
                .stream()
                // 创建新的ThreadPoolMetrics实例
                .map(entry -> new ThreadPoolMetrics(entry.getKey(), entry.getValue()))
                // 记录日志
                .peek(metrics -> log.info("Thread pool metrics: {}", metrics))
                // 通知
                .forEach(alarmNotifier::notify), 0, 1, TimeUnit.MINUTES);
    }
}