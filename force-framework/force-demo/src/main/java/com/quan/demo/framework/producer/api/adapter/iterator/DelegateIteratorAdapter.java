package com.quan.demo.framework.producer.api.adapter.iterator;

import com.quan.demo.framework.producer.api.context.CandidateHolder;

import java.io.IOException;

/**
 * @author Force-oneself
 * @description DelegateIteratorAdapter 委派实际的迭代器处理，针对的是不同条件不同迭代的业务场景
 * @date 2021-10-18
 **/
public interface DelegateIteratorAdapter<T> extends IteratorAdapter<T> {

    /**
     * 委托给实际的去处理
     *
     * @throws IOException 正常的IO流异常
     */
    @Override
    default void close() throws IOException {
        delegate().close();
    }

    /**
     * 存在下一个
     *
     * @return 是否
     */
    @Override
    default boolean hasNext() {
        return delegate().hasNext();
    }

    /**
     * 下一个数据
     *
     * @return 数据
     */
    @Override
    default CandidateHolder<T> next() {
        return delegate().next();
    }

    /**
     * 委托统计值
     *
     * @return 统计值
     */
    @Override
    default long count() {
        return delegate().count();
    }

    /**
     * 实际委托的排序值
     *
     * @return 排序值
     */
    @Override
    default int getOrder() {
        return delegate().getOrder();
    }

    /**
     * 委派的迭代器处理
     *
     * @return 实际处理的迭代器
     */
    IteratorAdapter<T> delegate();
}
