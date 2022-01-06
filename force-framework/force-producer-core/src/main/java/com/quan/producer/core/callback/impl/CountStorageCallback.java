package com.quan.producer.core.callback.impl;

import com.quan.producer.core.adapter.store.StorageAdapter;
import com.quan.producer.core.callback.StorageCallback;
import com.quan.producer.core.context.CandidateHolder;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author Force-oneself
 * @description CountStorageCallback
 * @date 2021-10-14
 **/
public class CountStorageCallback<T> implements StorageCallback<T> {

    /**
     * 总人数的统计
     */
    private final AtomicLong count = new AtomicLong();

    /**
     * 实际存储器的统计
     */
    private final Map<String, AtomicLong> actualStoreCountMap = new HashMap<>(16);

    @Override
    public void end() {
        log.info("迭代过滤人数：{}", count);
        log.info("各存储器执行人数：{}", actualStoreCountMap);
    }

    @Override
    public void onAfter(CandidateHolder<T> holder) {
        count.incrementAndGet();
    }

    @Override
    public void actual(StorageAdapter<T> storeAdapter) {
        actualStoreCountMap.computeIfAbsent(storeAdapter.uniqueName(), key -> new AtomicLong()).incrementAndGet();
    }
}
