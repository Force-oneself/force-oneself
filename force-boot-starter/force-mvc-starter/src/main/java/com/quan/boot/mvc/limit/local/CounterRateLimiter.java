package com.quan.boot.mvc.limit.local;

import com.quan.boot.mvc.limit.RateLimiter;
import jdk.internal.util.xml.impl.Pair;

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
    private final AtomicInteger counter = new AtomicInteger(0);

    /**
     * 统计窗口时间(毫秒)
     */
    private final long time;

    /**
     * 阈值
     */
    private final int capacity;


    public CounterRateLimiter(long time, int capacity) {
        this.time = time;
        this.capacity = capacity;
    }

    @Override
    public synchronized boolean rateLimit() {
        long current = System.currentTimeMillis();
        if (current - start >= time) {
            start = current;
            counter.set(0);
        }
        return counter.incrementAndGet() <= capacity;
    }
}
