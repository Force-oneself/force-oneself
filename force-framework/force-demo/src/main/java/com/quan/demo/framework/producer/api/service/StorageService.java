package com.quan.demo.framework.producer.api.service;

import com.quan.demo.framework.producer.api.adapter.store.StorageAdapter;
import com.quan.demo.framework.producer.api.callback.StorageCallback;
import com.quan.demo.framework.producer.api.context.CandidateHolder;
import com.quan.demo.framework.producer.api.context.Context;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Force-oneself
 * @description StorageManager
 * @date 2021-09-27
 **/
public interface StorageService<T> {

    /**
     * 存储
     *
     * @param holder  候选者
     * @param context 上下文
     */
    default void store(CandidateHolder<T> holder, Context<T> context) {
        // 筛选出存储回调
        final List<StorageCallback<T>> callbacks = context.callbacks().stream()
                .filter(callback -> callback instanceof StorageCallback)
                .map(callback -> (StorageCallback<T>) callback)
                .collect(Collectors.toList());

        if (holder != null) {
            // 前置回调
            callbacks.forEach(callback -> callback.onBefore(holder));
        }
        // 存储执行
        storageAdapter().store(holder);
        // 为null说明需要执行结余操作
        if (holder == null) {
            // 结余回调
            callbacks.forEach(StorageCallback::end);
        } else {
            // 后置回调
            callbacks.forEach(callback -> callback.onAfter(holder));
        }
    }

    /**
     * 返回支持存储的适配
     *
     * @return 适配器
     */
    StorageAdapter<T> storageAdapter();
}
