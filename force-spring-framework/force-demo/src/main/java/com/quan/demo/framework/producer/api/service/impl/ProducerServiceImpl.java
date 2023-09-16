package com.quan.demo.framework.producer.api.service.impl;

import com.quan.demo.framework.producer.api.adapter.iterator.IteratorAdapter;
import com.quan.demo.framework.producer.api.adapter.iterator.IteratorAdapterComposite;
import com.quan.demo.framework.producer.api.adapter.store.StorageAdapter;
import com.quan.demo.framework.producer.api.adapter.store.StorageAdapterComposite;
import com.quan.demo.framework.producer.api.context.Context;
import com.quan.demo.framework.producer.api.service.FilterService;
import com.quan.demo.framework.producer.api.service.IteratorService;
import com.quan.demo.framework.producer.api.service.ProducerService;
import com.quan.demo.framework.producer.api.service.StorageService;

import java.util.List;

/**
 * @author Force-oneself
 * @description GroupPushServiceImpl
 * @date 2021-09-28
 **/
public class ProducerServiceImpl<T> implements ProducerService<T> {

    @Override
    public StorageService<T> storage(Context<T> context) {
        // 存储器组合器
        StorageAdapterComposite<T> adapterComposite = new StorageAdapterComposite<>(storageAdapters(context), context);
        // 构建存储服务
        return new StorageServiceImpl<>(adapterComposite);
    }

    private List<StorageAdapter<T>> storageAdapters(Context<T> context) {
        return context.storages();
    }

    @Override
    public FilterService<T> filter(Context<T> context) {
        return new FilterServiceImpl<>(context);
    }

    @Override
    public IteratorService<T> iterator(Context<T> context) {
        // 迭代器组合器
        IteratorAdapterComposite<T> iteratorComposite = new IteratorAdapterComposite<>(iteratorAdapters(context), context);
        // 构建service
        return new IteratorServiceImpl<>(iteratorComposite, context);
    }

    private List<IteratorAdapter<T>> iteratorAdapters(Context<T> context) {
        return context.iterators();
    }

}
