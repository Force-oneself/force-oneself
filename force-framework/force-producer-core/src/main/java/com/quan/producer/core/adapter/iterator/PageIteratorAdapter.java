package com.quan.producer.core.adapter.iterator;

import java.io.IOException;
import java.util.Iterator;

/**
 * @author Force-oneself
 * @description PageIteratorAdapter
 * @date 2021-10-20
 **/
public interface PageIteratorAdapter<T, R> extends WrapIteratorAdapter<T, R> {

    /**
     * 下一个源数据
     *
     * @return 存在与否
     */
    @Override
    default T nextTarget() {
        return iterator().next();
    }

    /**
     * 下一个
     *
     * @return 存在与否
     */
    @Override
    default boolean hasNext() {
        return (iterator() != null && iterator().hasNext()) || hasPage();
    }

    /**
     * 实现默认空实现（分页的实现暂不涉及流）
     *
     * @throws IOException IO流异常
     */
    @Override
    default void close() throws IOException {

    }

    /**
     * 是否还存在分页数据
     *
     * @return 是否
     */
    boolean hasPage();

    /**
     * 分页迭代器
     *
     * @return 迭代器
     */
    Iterator<T> iterator();
}
