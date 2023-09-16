package com.quan.producer.core.adapter.store;

import com.quan.producer.core.base.UniqueName;
import com.quan.producer.core.context.CandidateHolder;
import org.springframework.core.Ordered;

/**
 * @author Force-oneself
 * @description StorageAdapter 存储器封装类
 * @date 2021-09-27
 **/
public interface StorageAdapter<T> extends Ordered, UniqueName {

    /**
     * 可支持数据的处理
     *
     * @param holder 源数据
     * @return 支持与否
     */
    default boolean support(CandidateHolder<T> holder) {
        return false;
    }

    /**
     * 存储处理
     *
     * @param holder 数据源
     */
    void store(CandidateHolder<T> holder);

    /**
     * 默认排序值
     *
     * @return 100
     */
    @Override
    default int getOrder() {
        return 100;
    }

    /**
     * 默认名称为Class name
     *
     * @return 名称
     */
    @Override
    default String uniqueName() {
        return getClass().getSimpleName();
    }
}
