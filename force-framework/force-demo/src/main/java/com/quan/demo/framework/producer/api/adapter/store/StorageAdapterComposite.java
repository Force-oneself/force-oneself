package com.quan.demo.framework.producer.api.adapter.store;


import com.quan.demo.framework.producer.api.context.CandidateHolder;
import com.quan.demo.framework.producer.api.context.Context;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

/**
 * @author Force-oneself
 * @description StorageAdapterComposite 存储器组合器封装类
 * @date 2021-10-11
 **/
public class StorageAdapterComposite<T> implements StorageAdapter<T> {

    private final List<StorageAdapter<T>> adapters;

    private final Context<T> context;

    /**
     * 模式匹配路由映射
     */
    private final Map<StorageMode, Consumer<CandidateHolder<T>>> modeProcessor = new HashMap<>(16);

    public StorageAdapterComposite(List<StorageAdapter<T>> adapters, Context<T> context) {
        this.adapters = adapters;
        this.context = context;
        init();
    }

    private void init() {
        // 优先级
        modeProcessor.put(StorageMode.PRIORITY,
                holder -> adapters.stream()
                        .filter(store -> store.support(holder))
                        // 查第一个及处理返回
                        .findFirst()
                        .ifPresent(store -> store.store(holder))
        );
        // 全部
        modeProcessor.put(StorageMode.ALL,
                holder -> adapters.stream()
                        .filter(store -> store.support(holder))
                        .forEach(store -> store.store(holder))
        );
        // 自定义
        modeProcessor.put(StorageMode.CUSTOMIZE, holder -> context.customizeStorage().accept(holder, adapters));
        // 默认值
        modeProcessor.put(StorageMode.DEFAULT, modeProcessor.get(StorageMode.PRIORITY));
        // 空值转默认
        modeProcessor.put(null, modeProcessor.get(StorageMode.PRIORITY));
    }

    @Override
    public void store(CandidateHolder<T> holder) {
        // 结束标识
        if (holder == null) {
            this.adapters.forEach(storageAdapter -> storageAdapter.store(null));
            return;
        }
        // 模式匹配执行消费
        modeProcessor.get(context.storageMode()).accept(holder);
    }
}
