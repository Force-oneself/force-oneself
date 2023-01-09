package com.quan.demo.limit;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 滑动窗口限流器
 *
 * @author Force-oneself
 * @date 2023-01-07
 */
public class SlidingWindowRateLimiter implements RateLimiter {

    /**
     * 统计窗口时间(毫秒)
     */
    private final int windowLengthInMs;

    /**
     * 阈值
     */
    private final int capacity;

    /**
     * 窗口数量
     */
    private final int sampleCount;

    /**
     * 窗口信息
     */
    private final WindowWrap[] windowWraps;

    public SlidingWindowRateLimiter(int sampleCount, int intervalInMs, int capacity) {
        this.windowLengthInMs = intervalInMs / sampleCount;
        this.capacity = capacity;
        this.sampleCount = sampleCount;
        this.windowWraps = new WindowWrap[sampleCount];
        for (int i = 0; i < windowWraps.length; i++) {
            this.windowWraps[i] = new WindowWrap();
        }
    }


    @Override
    public synchronized boolean rateLimit() {
        long currentTimeMillis = System.currentTimeMillis();
        int currentIndex = (int) (currentTimeMillis % windowLengthInMs / (windowLengthInMs / sampleCount));
        // 2.  更新当前窗口计数 & 重置过期窗口计数
        int sum = 0;
        for (int i = 0; i < windowWraps.length; i++) {
            WindowWrap windowInfo = windowWraps[i];
            if ((currentTimeMillis - windowInfo.getStart()) > windowLengthInMs) {
                windowInfo.getCounter().set(0);
                windowInfo.setStart(currentTimeMillis);
            }
            if (currentIndex == i && windowInfo.getCounter().get() < capacity) {
                windowInfo.getCounter().incrementAndGet();
            }
            sum += windowInfo.getCounter().get();
        }
        return sum <= capacity;
    }

    /***
     * 滑动窗口桶信息
     *
     * @author Force-oneself
     * @date 2023-01-07
     */
    private static class WindowWrap {

        /**
         * 计数起始时间戳
         */
        private long start = System.currentTimeMillis();

        /**
         * 计数器
         */
        private final AtomicInteger counter = new AtomicInteger();


        public AtomicInteger getCounter() {
            return counter;
        }

        public long getStart() {
            return start;
        }

        public void setStart(long start) {
            this.start = start;
        }
    }
}
