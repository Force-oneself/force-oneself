package com.quan.demo.framework.producer.api.callback.impl;

import com.quan.demo.framework.producer.api.callback.StorageCallback;
import com.quan.demo.framework.producer.api.context.CandidateHolder;

import java.util.concurrent.atomic.LongAdder;

/**
 * @author Force-oneself
 * @description CountStorageCallback
 * @date 2021-10-14
 **/
public class CountStorageCallback<T> implements StorageCallback<T> {

    private final LongAdder count = new LongAdder();

    @Override
    public void end() {
        log.info("群推人数：{}", count);
    }

    @Override
    public void onAfter(CandidateHolder<T> holder) {
        count.increment();
    }
}
