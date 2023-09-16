package com.quan.producer.core.service;

import com.quan.producer.core.context.CandidateHolder;
import com.quan.producer.core.context.Context;

import java.util.function.BiPredicate;

/**
 * @author Force-oneself
 * @description FilterManager
 * @date 2021-09-27
 **/
public interface FilterService<T> {

    /**
     * 过滤群推对象
     *
     * @param holder  候选者
     * @param context 上下文
     * @return 是否过滤
     */
    default boolean filter(CandidateHolder<T> holder, Context<T> context) {
        return filter().test(holder, context);
    }

    /**
     * 返回过滤断言
     *
     * @return 断言
     */
    default BiPredicate<CandidateHolder<T>, Context<T>> filter() {
        return (holder, context) -> true;
    }
}
