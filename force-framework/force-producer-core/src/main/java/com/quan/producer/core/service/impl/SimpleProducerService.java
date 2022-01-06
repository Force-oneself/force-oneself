package com.quan.producer.core.service.impl;

import com.quan.producer.core.adapter.iterator.IteratorAdapterComposite;
import com.quan.producer.core.adapter.store.StorageAdapterComposite;
import com.quan.producer.core.context.Context;
import com.quan.producer.core.service.FilterService;
import com.quan.producer.core.service.IteratorService;
import com.quan.producer.core.service.ProducerService;
import com.quan.producer.core.service.StorageService;

/**
 * @author Force-oneself
 * @description ProducerServiceImpl
 * @date 2021-09-28
 **/
public interface SimpleProducerService<T> extends ProducerService<T> {

    /**
     * 存储器服务默认实现
     *
     * @param context 上下文
     * @return 存储器服务
     */
    @Override
    default StorageService<T> storage(Context<T> context) {
        // 存储器组合器
        StorageAdapterComposite<T> adapterComposite = new StorageAdapterComposite<>(context.storages(), context);
        // 构建存储服务
        return new StorageServiceImpl<>(adapterComposite);
    }

    /**
     * 过滤器服务默认实现
     *
     * @param context 上下文
     * @return 过滤器服务
     */
    @Override
    default FilterService<T> filter(Context<T> context) {
        return new FilterServiceImpl<>(context);
    }

    /**
     * 迭代器服务默认实现
     *
     * @param context 上下文
     * @return 迭代器服务
     */
    @Override
    default IteratorService<T> iterator(Context<T> context) {
        // 迭代器组合器
        IteratorAdapterComposite<T> iteratorComposite = new IteratorAdapterComposite<>(context.iterators(), context);
        // 构建service
        return new IteratorServiceImpl<>(iteratorComposite, context);
    }
}
