package com.quan.boot.mvc.limit.local;

import com.quan.boot.mvc.limit.RateLimiter;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 滑动窗口限流(基于队列实现)
 *
 * @author Force-oneself
 * @date 2023-10-10
 */
public class SlidingWindowRateLimiter implements RateLimiter {

    /**
     * 窗口存储访问时间戳
     */
    private final Queue<Long> windowWraps = new LinkedList<>();

    /**
     * 阈值
     */
    private final int capacity;

    /**
     * 窗口大小
     */
    private final long windowLengthInMs;

    public SlidingWindowRateLimiter(long windowLengthInMs, int capacity) {
        this.capacity = capacity;
        this.windowLengthInMs = windowLengthInMs;
    }

    @Override
    public synchronized boolean rateLimit() {
        long current = System.currentTimeMillis();
        while (!windowWraps.isEmpty() && windowWraps.peek() <= current - windowLengthInMs) {
            // 不在窗口内的弹出队列
            windowWraps.poll();
        }
        return windowWraps.size() < capacity && windowWraps.add(current);
    }
}
