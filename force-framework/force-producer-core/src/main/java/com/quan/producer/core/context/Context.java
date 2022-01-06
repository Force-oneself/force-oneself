package com.quan.producer.core.context;

import com.quan.producer.core.adapter.iterator.IteratorAdapter;
import com.quan.producer.core.adapter.store.StorageAdapter;
import com.quan.producer.core.adapter.store.StorageMode;
import com.quan.producer.core.callback.Callback;

import java.util.List;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;

/**
 * @author Force-oneself
 * @description GroupPushContext
 * @date 2021-09-27
 **/
public interface Context<T> {

    /**
     * 根据key获取全局对象
     *
     * @param name 对象key
     * @return 管局对象
     */
    <O> O getObject(String name);

    /**
     * 设置对象
     *
     * @param name  名称key
     * @param value 对象属性
     */
    <O> void setObject(String name, O value);

    /**
     * 获取迭代器
     *
     * @return 迭代器列表
     */
    List<IteratorAdapter<T>> iterators();

    /**
     * 过滤器
     *
     * @return 过滤器实现
     */
    BiPredicate<CandidateHolder<T>, Context<T>> filter();

    /**
     * 获取存储器
     *
     * @return 存储器列表
     */
    List<StorageAdapter<T>> storages();

    /**
     * 人群包ids
     *
     * @return ids
     */
    List<String> throngIds();

    /**
     * 获取回调列表
     *
     * @return 回调列表
     */
    List<Callback<T>> callbacks();

    /**
     * 选择存储模式
     *
     * @return 模式
     */
    default StorageMode storageMode() {
        return StorageMode.PRIORITY;
    }

    /**
     * 当前租户拥有的租户集
     *
     * @return 租户id
     */
    List<String> tenantIds();

    /**
     * 自定义存储实现
     *
     * @return 消费实现
     */
    BiFunction<CandidateHolder<T>, List<StorageAdapter<T>>, List<StorageAdapter<T>>> customizeStorage();
}
