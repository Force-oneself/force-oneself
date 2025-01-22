package com.quan.demo.thread;

import lombok.ToString;

import java.util.concurrent.ThreadPoolExecutor;

/**
 * 线程池指标类
 *
 * @author Force-oneself
 * @date 2025-01-20
 */
@ToString
public class ThreadPoolMetrics {

    /**
     * 线程池执行器
     */
    private final ThreadPoolExecutor threadPoolExecutor;

    /**
     * 线程池名称
     */
    private final String name;

    /**
     * 活跃线程数
     */
    private int activeCount;

    /**
     * 核心线程数
     */
    private int corePoolSize;

    /**
     * 最大线程数
     */
    private int maximumPoolSize;

    /**
     * 线程池中任务总数
     */
    private long taskCount;

    /**
     * 已完成任务数
     */
    private long completedTaskCount;

    /**
     * 阻塞队列大小
     */
    private int queueSize;

    public ThreadPoolMetrics(String name, ThreadPoolExecutor threadPoolExecutor) {
        this.name = name;
        this.threadPoolExecutor = threadPoolExecutor;
        // 调用recordMetrics方法记录初始指标
        this.recordMetrics();
    }

    /**
     * 获取线程池名称
     *
     * @return 线程池名称
     */
    public String getName() {
        return name;
    }

    /**
     * 获取线程池执行器
     *
     * @return 线程池执行器
     */
    public ThreadPoolExecutor getThreadPoolExecutor() {
        return threadPoolExecutor;
    }

    /**
     * 获取活跃线程数
     *
     * @return 活跃线程数
     */
    public int getActiveCount() {
        return activeCount;
    }

    /**
     * 获取核心线程数
     *
     * @return 核心线程数
     */
    public int getCorePoolSize() {
        return corePoolSize;
    }

    /**
     * 获取最大线程数
     *
     * @return 最大线程数
     */
    public int getMaximumPoolSize() {
        return maximumPoolSize;
    }

    /**
     * 获取线程池中任务总数
     *
     * @return 任务总数
     */
    public long getTaskCount() {
        return taskCount;
    }

    /**
     * 获取已完成任务数
     *
     * @return 已完成任务数
     */
    public long getCompletedTaskCount() {
        return completedTaskCount;
    }

    /**
     * 获取阻塞队列大小
     *
     * @return 阻塞队列大小
     */
    public int getQueueSize() {
        return queueSize;
    }

    /**
     * 记录当时的线程池指标
     */
    public void recordMetrics() {
        this.activeCount = threadPoolExecutor.getActiveCount();
        this.corePoolSize = threadPoolExecutor.getCorePoolSize();
        this.maximumPoolSize = threadPoolExecutor.getMaximumPoolSize();
        this.taskCount = threadPoolExecutor.getTaskCount();
        this.completedTaskCount = threadPoolExecutor.getCompletedTaskCount();
        this.queueSize = threadPoolExecutor.getQueue().size();
    }
}