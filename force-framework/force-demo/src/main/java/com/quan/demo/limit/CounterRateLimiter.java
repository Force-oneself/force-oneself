package com.quan.demo.limit;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 本地统计器限流
 *
 * @author Force-oneself
 * @date 2023-01-07
 */
public class CounterRateLimiter implements RateLimiter {

    /**
     * 计数起始时间戳
     */
    private long start = System.currentTimeMillis();

    /**
     * 计数器
     */
    private final AtomicInteger counter = new AtomicInteger();

    /**
     * 统计窗口时间(毫秒)
     */
    private final long time;

    /**
     * 阈值
     */
    private final int threshold;


    public CounterRateLimiter(long time, int threshold) {
        this.time = time;
        this.threshold = threshold;
    }

    @Override
    public synchronized boolean rateLimit() {
        if (System.currentTimeMillis() - start > time) {
            start = System.currentTimeMillis();
            counter.set(0);
        }
        return counter.getAndIncrement() <= threshold;
    }
}
