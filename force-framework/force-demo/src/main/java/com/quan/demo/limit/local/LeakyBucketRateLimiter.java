package com.quan.demo.limit.local;

import com.quan.demo.limit.RateLimiter;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 漏桶限流器
 *
 * @author Force-oneself
 * @date 2023-01-09
 */
public class LeakyBucketRateLimiter implements RateLimiter {

    /**
     * 桶的容量
     */
    private final int capacity;

    /**
     * 木桶剩余的水滴的量(初始化的时候的空的桶)
     */
    private final AtomicInteger water = new AtomicInteger(0);

    /**
     * 水滴的流出的速率,这个可以在 构造方法种设置,比如每秒10个请求.
     */
    private final int outRate;

    /**
     * 记录上次成功接收到请求的时间
     * 用于计算当前系统时间减去上次请求时间 乘以outrate 所处理的请求数.
     */
    private long receivedTime;

    public LeakyBucketRateLimiter(int capacity, int outRate) {
        this.capacity = capacity;
        this.outRate = outRate;
    }

    @Override
    public synchronized boolean rateLimit() {
        // 如果是空桶,则直接将
        if (water.get() == 0) {
            receivedTime = System.currentTimeMillis();
            water.addAndGet(1);
            return true;
        }

        // 先计算下上成功接受到当前时间已经流出的记录数
        int outNum = ((int) ((System.currentTimeMillis() - receivedTime) / 1000)) * outRate;
        int waterLeft = water.get() - outNum;
        water.set(Math.max(0, waterLeft));
        // 重新更新leakTimeStamp
        // outNum是否大于0
        if (outNum > 0) {
            receivedTime = System.currentTimeMillis();
        }

        // 尝试加水,并且水还未满
        if ((water.get()) < capacity) {
            water.addAndGet(1);
            return true;
        } else {
            // 水满，拒绝加水
            return false;
        }
    }
}
