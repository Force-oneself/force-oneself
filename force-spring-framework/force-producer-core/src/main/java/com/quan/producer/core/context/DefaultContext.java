package com.quan.producer.core.context;

import com.quan.producer.core.adapter.iterator.IteratorAdapter;
import com.quan.producer.core.adapter.store.StorageAdapter;
import com.quan.producer.core.adapter.store.StorageMode;
import com.quan.producer.core.callback.Callback;
import org.springframework.core.annotation.AnnotationAwareOrderComparator;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.BiFunction;
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
    private BiFunction<CandidateHolder<T>, List<StorageAdapter<T>>, List<StorageAdapter<T>>> customizeStorage
            = (holder, storages) -> storages.stream()
            .filter(storageAdapter -> storageAdapter.support(holder))
            .collect(Collectors.toList());

    /**
     * 上下文缓存需要传递的对象
     */
    private final Map<String, Object> cache = new ConcurrentHashMap<>(16);

    public static <T> DefaultContext<T> of() {
        return new DefaultContext<>();
    }

    public DefaultContext<T> filter(BiPredicate<CandidateHolder<T>, Context<T>> filter) {
        Assert.isTrue(Objects.nonNull(filter), "过滤器不能为空");
        this.filter = filter;
        return this;
    }

    public DefaultContext<T> storage(List<StorageAdapter<T>> storages) {
        Assert.isTrue(Objects.nonNull(storages) && !storages.isEmpty(), "存储器不能为空");
        this.storages = storages;
        AnnotationAwareOrderComparator.sort(this.storages);
        return this;
    }

    public DefaultContext<T> iterator(List<IteratorAdapter<T>> iterators) {
        Assert.isTrue(Objects.nonNull(iterators) && !iterators.isEmpty(), "迭代器不能为空");
        this.iterators = iterators;
        AnnotationAwareOrderComparator.sort(this.iterators);
        return this;
    }

    public DefaultContext<T> callback(List<Callback<T>> callbacks) {
        Assert.isTrue(Objects.nonNull(callbacks), "回调不能为空");
        this.callbacks = callbacks;
        AnnotationAwareOrderComparator.sort(this.callbacks);
        return this;
    }

    public DefaultContext<T> throng(List<String> throngIds) {
        Assert.isTrue(Objects.nonNull(throngIds) && !throngIds.isEmpty(), "人群包列表不能为空");
        this.throngIds = throngIds;
        return this;
    }

    public DefaultContext<T> customizeStorage(
            BiFunction<CandidateHolder<T>, List<StorageAdapter<T>>, List<StorageAdapter<T>>> customizeStorage) {
        Assert.isTrue(Objects.nonNull(customizeStorage), "自定义存储策略不能为空");
        this.customizeStorage = customizeStorage;
        return this;
    }

    public <O> DefaultContext<T> set(String name, O object) {
        Assert.isTrue(StringUtils.hasText(name) && Objects.nonNull(object), "请检查参数值");
        this.setObject(name, object);
        return this;
    }

    public DefaultContext<T> storageMode(StorageMode storageMode) {
        Assert.isTrue(Objects.nonNull(storageMode), "存储模式不能为空");
        this.storageMode = storageMode;
        return this;
    }

    public DefaultContext<T> tenantIds(List<String> tenantIds) {
        Assert.isTrue(Objects.nonNull(tenantIds) && !tenantIds.isEmpty(), "租户id不能为空");
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
        return tenantIds;
    }

    @Override
    public BiFunction<CandidateHolder<T>, List<StorageAdapter<T>>, List<StorageAdapter<T>>> customizeStorage() {
        return customizeStorage;
    }
}
