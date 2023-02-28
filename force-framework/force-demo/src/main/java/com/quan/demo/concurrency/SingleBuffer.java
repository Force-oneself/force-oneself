package com.quan.demo.concurrency;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Force-oneself
 * @date 2023-02-28
 */
public class SingleBuffer<T> implements MemoryBuffer<T> {

    private final List<T> cache;
    private final int threshold;

    public SingleBuffer(int threshold) {
        this.cache = new ArrayList<>(threshold);
        this.threshold = threshold;
    }

    @Override
    public void write(T data) {
        cache.add(data);
    }


    @Override
    public boolean isFull() {
        return this.cache.size() >= this.threshold;
    }

    @Override
    public void flush() {
        if (cache.size() != threshold) {
            // throw new RuntimeException("并发安全");
        }
        System.out.println("阈值：" + threshold + " 数据刷盘: " + cache.size());
        this.cache.clear();
    }

}
