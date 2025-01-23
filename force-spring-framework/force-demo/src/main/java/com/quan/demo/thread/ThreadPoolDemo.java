package com.quan.demo.thread;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Description
 *
 * @author Force-oneself
 * @date 2025-01-08
 */
public class ThreadPoolDemo {


    public static void main(String[] args) {

        boolean change = new Random().nextDouble() > 0.5;

        ThreadPoolExecutor tpe = new ThreadPoolExecutor(
                1, 1,
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<>()
        );

        // 正在执行的线程数量
        tpe.getActiveCount();
        // 核心线程数
        tpe.getCorePoolSize();
        // 最大线程数
        tpe.getMaximumPoolSize();
        // 当前池中线程数
        tpe.getPoolSize();
        // 历史最大线程数
        tpe.getLargestPoolSize();
        // 执行的任务总数
        tpe.getTaskCount();
        // 完成的任务数
        tpe.getCompletedTaskCount();
        // 空闲时间
        tpe.getKeepAliveTime(TimeUnit.MILLISECONDS);
        // 阻塞队列
        BlockingQueue<Runnable> queue = tpe.getQueue();
        // 队列大小
        int queueSize = queue.size();
        // 剩余容量
        int remainingCapacity = queue.remainingCapacity();
        // 拒绝策略
        tpe.getRejectedExecutionHandler();
        // 线程工厂
        tpe.getThreadFactory();

    }


    /**
     * 监控指标
     */
    @Getter
    @Setter
    private static class MonitorMetrics {

        /**
         * 线程池名称
         */
        private String name;
        /**
         * 核心线程数
         */
        private int coreSize;
        /**
         * 最大线程数
         */
        private int maxSize;
        /**
         * 队列大小
         */
        private int queueSize;
        /**
         * 活跃线程数
         */
        private int activeSize;
        /**
         * 完成任务数
         */
        private int completeSize;
        /**
         * 拒绝任务数
         */
        private int rejectSize;
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


}
