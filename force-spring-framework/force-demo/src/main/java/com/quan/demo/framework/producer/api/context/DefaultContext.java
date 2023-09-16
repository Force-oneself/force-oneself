package com.quan.demo.framework.producer.api.context;

import com.quan.demo.framework.producer.api.adapter.iterator.IteratorAdapter;
import com.quan.demo.framework.producer.api.adapter.store.StorageAdapter;
import com.quan.demo.framework.producer.api.adapter.store.StorageMode;
import com.quan.demo.framework.producer.api.callback.Callback;
import org.springframework.core.annotation.AnnotationAwareOrderComparator;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.BiConsumer;
import java.util.function.BiPredicate;
import java.util.stream.Collectors;

/**
 * @author Force-oneself
 * @description DefaultContext
 * @date 2021-09-28
 **/
@SuppressWarnings("unchecked")
public class DefaultContext<T> implements Context<T> {

    /**
     * 存储器
     */
    private List<StorageAdapter<T>> storages = new ArrayList<>();

    /**
     * 迭代器
     */
    private List<IteratorAdapter<T>> iterators = new ArrayList<>();

    /**
     * 回调
     */
    private List<Callback<T>> callbacks = new ArrayList<>();

    /**
     * 过滤器
     */
    private BiPredicate<CandidateHolder<T>, Context<T>> filter = (holder, context) -> true;

    /**
     * 人群包
     */
    private List<String> throngIds = new ArrayList<>();

    /**
     * 存储模式
     */
    private StorageMode storageMode = StorageMode.PRIORITY;

    /**
     * 租户id
     */
    private List<String> tenantIds;

    /**
     * 自定义消费默认实现
     */
    private BiConsumer<CandidateHolder<T>, List<StorageAdapter<T>>> customizeStorage = (holder, storages) ->
            storages.stream()
                    .filter(storageAdapter -> storageAdapter.support(holder))
                    .forEach(storageAdapter -> storageAdapter.store(holder));

    /**
     * 上下文缓存需要传递的对象
     */
    private final Map<String, Object> cache = new ConcurrentHashMap<>(16);

    public static <T> DefaultContext<T> of() {
        return new DefaultContext<>();
    }

    public DefaultContext<T> filter(BiPredicate<CandidateHolder<T>, Context<T>> filter) {
        Assert.notNull(filter, "过滤器不能为空");
        this.filter = filter;
        return this;
    }

    public DefaultContext<T> storage(List<StorageAdapter<T>> storages) {
        Assert.notEmpty(storages, "存储器不能为空");
        this.storages = storages;
        AnnotationAwareOrderComparator.sort(this.storages);
        return this;
    }

    public DefaultContext<T> iterator(IteratorAdapter<T>... iterators) {
        Assert.notEmpty(iterators, "迭代器不能为空");
        this.iterators = Arrays.stream(iterators).collect(Collectors.toList());
        AnnotationAwareOrderComparator.sort(this.iterators);
        return this;
    }

    public DefaultContext<T> iterator(List<IteratorAdapter<T>> iterators) {
        Assert.notEmpty(iterators, "迭代器不能为空");
        this.iterators = iterators;
        AnnotationAwareOrderComparator.sort(this.iterators);
        return this;
    }

    public DefaultContext<T> callback(List<Callback<T>> callbacks) {
        Assert.notNull(callbacks, "回调不能为空");
        this.callbacks = callbacks;
        AnnotationAwareOrderComparator.sort(this.callbacks);
        return this;
    }

    public DefaultContext<T> throng(List<String> throngIds) {
        Assert.notEmpty(throngIds, "人群包列表不能为空");
        this.throngIds = throngIds;
        return this;
    }

    public DefaultContext<T> customizeStorage(BiConsumer<CandidateHolder<T>, List<StorageAdapter<T>>> customizeStorage) {
        Assert.notNull(customizeStorage, "自定义存储策略不能为空");
        this.customizeStorage = customizeStorage;
        return this;
    }

    public <O> DefaultContext<T> set(String name, O object) {
        Assert.notNull(object, "请检查参数值");
        this.setObject(name, object);
        return this;
    }

    public DefaultContext<T> storageMode(StorageMode storageMode) {
        Assert.notNull(storageMode, "存储模式不能为空");
        this.storageMode = storageMode;
        return this;
    }

    public DefaultContext<T> tenantIds(List<String> tenantIds) {
        Assert.notEmpty(tenantIds, "租户id不能为空");
        this.tenantIds = tenantIds.stream().distinct().collect(Collectors.toList());
        return this;
    }

    @Override
    public <O> O getObject(String name) {
        return (O) cache.get(name);
    }

    @Override
    public <O> void setObject(String name, O value) {
        cache.put(name, value);
    }

    @Override
    public List<IteratorAdapter<T>> iterators() {
        return iterators;
    }

    @Override
    public BiPredicate<CandidateHolder<T>, Context<T>> filter() {
        return filter;
    }

    @Override
    public List<StorageAdapter<T>> storages() {
        return storages;
    }

    @Override
    public List<String> throngIds() {
        return throngIds;
    }

    @Override
    public List<Callback<T>> callbacks() {
        return callbacks;
    }

    @Override
    public StorageMode storageMode() {
        return storageMode;
    }

    @Override
    public List<String> tenantIds() {
        Assert.notEmpty(tenantIds, "租户id不能为空");
        return tenantIds;
    }

    @Override
    public BiConsumer<CandidateHolder<T>, List<StorageAdapter<T>>> customizeStorage() {
        return customizeStorage;
    }
}
