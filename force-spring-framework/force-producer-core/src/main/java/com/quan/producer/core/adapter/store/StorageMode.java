package com.quan.producer.core.adapter.store;

/**
 * @author Force-oneself
 * @description StorageMode
 * @date 2021-10-19
 **/
public enum StorageMode {

    /**
     * 对所有存储器进行适配存储
     */
    ALL,
    /**
     * 对符合的存储器优先级排序，存在多个符合的存储器时，仅执行优先级最高的
     */
    PRIORITY,
    /**
     * 默认优先级
     */
    DEFAULT,
    /**
     * 自定义
     */
    CUSTOMIZE
}
