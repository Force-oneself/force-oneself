package com.quan.producer.core.service.impl;

import com.quan.producer.core.context.CandidateHolder;
import com.quan.producer.core.context.Context;
import com.quan.producer.core.service.FilterService;

import java.util.function.BiPredicate;

/**
 * @author Force-oneself
 * @description FilterServiceImpl
 * @date 2021-09-28
 **/
public class FilterServiceImpl<T> implements FilterService<T> {

    private final Context<T> context;

    private BiPredicate<CandidateHolder<T>, Context<T>> filter = (holder, context) -> true;

    public FilterServiceImpl(Context<T> context) {
        this.context = context;
        if (context.filter() != null) {
            this.filter = context.filter();
        }
    }

    @Override
    public BiPredicate<CandidateHolder<T>, Context<T>> filter() {
        return filter;
    }
}
