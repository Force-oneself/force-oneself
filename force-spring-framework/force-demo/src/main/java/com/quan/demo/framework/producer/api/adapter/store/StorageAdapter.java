package com.quan.demo.framework.producer.api.adapter.store;

import com.quan.demo.framework.producer.api.context.CandidateHolder;
import org.springframework.core.Ordered;

/**
 * @author Force-oneself
 * @description StorageAdapter 存储器封装类
 * @date 2021-09-27
 **/
public interface StorageAdapter<T> extends Ordered {

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

}
