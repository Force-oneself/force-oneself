package com.quan.producer.core.adapter.store;

import com.quan.producer.core.callback.StorageCallback;
import com.quan.producer.core.context.CandidateHolder;
import com.quan.producer.core.context.Context;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author Force-oneself
 * @description StorageAdapterComposite 存储器组合器封装类
 * @date 2021-10-11
 **/
public class StorageAdapterComposite<T> implements StorageAdapter<T> {

    private final List<StorageAdapter<T>> adapters;

    private final Context<T> context;

    private final List<StorageCallback<T>> callbacks;

    /**
     * 模式匹配路由映射
     */
    private final Map<StorageMode, Function<CandidateHolder<T>, List<StorageAdapter<T>>>> modeProcessor = new HashMap<>(16);

    public StorageAdapterComposite(List<StorageAdapter<T>> adapters, Context<T> context) {
        Assert.isTrue(Objects.nonNull(adapters), "请选择合适的存储器");
        this.adapters = adapters;
        this.context = context;
        this.callbacks = context.callbacks().stream()
                .filter(callback -> callback instanceof StorageCallback)
                .map(callback -> (StorageCallback<T>) callback)
                .collect(Collectors.toList());
        init();
    }

    private void init() {
        // 优先级
        modeProcessor.put(StorageMode.PRIORITY,
                holder -> adapters.stream()
                        .filter(store -> store.support(holder))
                        // 查第一个及处理返回
                        .findFirst()
                        .map(Collections::singletonList)
                        .orElse(null)
        );
        // 全部
        modeProcessor.put(StorageMode.ALL,
                holder -> adapters.stream()
                        .filter(store -> store.support(holder))
                        .collect(Collectors.toList())
        );
        // 自定义
        modeProcessor.put(StorageMode.CUSTOMIZE, holder -> context.customizeStorage().apply(holder, adapters));
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
        List<StorageAdapter<T>> actual = modeProcessor.get(context.storageMode()).apply(holder);
        if (!CollectionUtils.isEmpty(actual)) {
            actual.stream()
                    .peek(store -> store.store(holder))
                    .forEach(store -> callbacks.forEach(callback -> callback.actual(store)));
        }
    }

}
