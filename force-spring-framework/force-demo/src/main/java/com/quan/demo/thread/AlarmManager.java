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
     * 动态线程池配置属性
     */
    private final DynamicThreadPoolProperties properties;

    /**
     * 告警通知器
     */
    private final AlarmNotifier alarmNotifier;

    public AlarmManager(DynamicThreadPoolProperties properties, AlarmNotifier alarmNotifier) {
        this.properties = properties;
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
     * 发送告警通知
     *
     * @param metrics 线程池监控指标
     */
    public void sendAlarm(ThreadPoolMetrics metrics) {
        String name = metrics.getName();
        Map<String, ThreadPoolProperties> threadPools = properties.getThreadPools();
        if (threadPools == null || !threadPools.containsKey(name)) {
            log.info("Thread pool " + name + " not found in properties, skipping alarm.");
            return;
        }
        // 使用告警通知器发送告警
        alarmNotifier.send(metrics);
    }

    /**
     * 监控线程池状态
     */
    private void monitorThreadPools() {
        for (Map.Entry<String, ThreadPoolExecutor> entry : monitoredThreadPools.entrySet()) {
            String name = entry.getKey();
            ThreadPoolExecutor tpe = entry.getValue();
            // 创建新的ThreadPoolMetrics实例
            ThreadPoolMetrics metrics = new ThreadPoolMetrics(name, tpe);
            this.sendAlarm(metrics);

            // 监控线程活跃数量与核心线程数的比值
            // int activeCount = metrics.getActiveCount();
            // int corePoolSize = metrics.getCorePoolSize();
            // double threadRatio = (double) activeCount / corePoolSize;
            // if (threadRatio > 1.5) { // 假设阈值为1.5
            //     sendAlarm(metrics);
            // }
            //
            // // 监控队列大小与阻塞任务数量的比值
            // int queueSize = metrics.getQueueSize();
            // int blockingTaskCount = (int) (metrics.getTaskCount() - metrics.getCompletedTaskCount());
            // double queueRatio = (double) queueSize / blockingTaskCount;
            // if (queueRatio > 0.5) { // 假设阈值为0.5
            //     sendAlarm(metrics);
            // }
        }
    }

    public void start() {
        // 启动定时监控任务
        scheduledExecutorService.scheduleAtFixedRate(this::monitorThreadPools, 0, 1, TimeUnit.MINUTES);
    }
}