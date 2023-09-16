package com.quan.demo.concurrency;

/**
 * SegmentBuffer
 *
 * @author Force-oneself
 * @date 2022-09-14
 */
public interface SegmentBuffer<T, S extends MemoryBuffer<T>> extends MemoryBuffer<T> {

    /**
     * 路由选择缓存块
     *
     * @return 缓存块
     */
    S choose();

    /**
     * 交换缓存块
     */
    void exchange();

    @Override
    default void write(T data) {
        S slice = this.choose();
        boolean exchange = false;
        synchronized (slice) {
            if (slice.isFull()) {
                slice = this.choose();
            }
            slice.write(data);
            if (slice.isFull()) {
                this.exchange();
                exchange = true;
            }
        }
        if (exchange) {
            synchronized (slice) {
                slice.flush();
            }
        }
    }

}
