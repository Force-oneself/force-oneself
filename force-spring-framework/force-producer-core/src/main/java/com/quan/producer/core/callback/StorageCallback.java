package com.quan.producer.core.callback;

import com.quan.producer.core.adapter.store.StorageAdapter;

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

    /**
     * 实际执行存储的回调
     *
     * @param storeAdapter 实际调用存储器
     */
    default void actual(StorageAdapter<T> storeAdapter) {

    }
}
