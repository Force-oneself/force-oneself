package com.quan.demo.concurrency;

/**
 * MemoryBuffer
 *
 * @author Force-oneself
 * @date 2022-09-14
 */
public interface MemoryBuffer<T> {

    /**
     * 写入数据
     *
     * @param data 数据
     */
    void write(T data);

    /**
     * 读取数据
     *
     * @return 数据
     */
    // T read();

    /**
     * 缓存已满
     *
     * @return /
     */
    boolean isFull();

    /**
     * 刷盘
     */
    void flush();
}
