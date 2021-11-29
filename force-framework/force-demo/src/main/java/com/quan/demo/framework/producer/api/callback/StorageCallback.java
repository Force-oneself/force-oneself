package com.quan.demo.framework.producer.api.callback;

/**
 * @author Force-oneself
 * @description StorageCallback
 * @date 2021-10-14
 **/
public interface StorageCallback<T> extends Callback<T> {

    /**
     * 存储结束回调
     */
    default void end() {

    }
}
