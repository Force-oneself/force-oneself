package com.quan.demo.framework.producer.api.callback;

import com.quan.demo.framework.producer.api.context.CandidateHolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;

/**
 * @author Force-oneself
 * @description Callback
 * @date 2021-10-14
 **/
public interface Callback<T> extends Ordered {

    Logger log = LoggerFactory.getLogger(Callback.class);

    /**
     * 处理前回调
     *
     * @param holder 数据源
     */
    default void onBefore(CandidateHolder<T> holder) {
    }

    /**
     * 处理后回调
     *
     * @param holder 数据源
     */
    default void onAfter(CandidateHolder<T> holder) {

    }

    /**
     * 排序值
     *
     * @return 排序值
     */
    @Override
    default int getOrder() {
        return 100;
    }
}
