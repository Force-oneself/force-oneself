package com.quan.producer.core.context;

/**
 * @author Force-oneself
 * @description Holder
 * @date 2021-11-19
 **/
public interface Holder<T> {

    /**
     * 源数据
     *
     * @return 数据本身
     */
    T target();
}
