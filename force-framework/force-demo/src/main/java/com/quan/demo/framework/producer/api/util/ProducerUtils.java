package com.quan.demo.framework.producer.api.util;


import com.quan.demo.framework.producer.api.adapter.iterator.IteratorAdapter;
import com.quan.demo.framework.producer.api.context.CandidateHolder;
import com.quan.demo.framework.producer.api.context.Context;
import com.quan.demo.framework.producer.api.context.ContextAware;

import java.util.Collection;

/**
 * @author Force-oneself
 * @description ContextAwareUtils
 * @date 2021-11-23
 **/
@SuppressWarnings("unchecked")
public final class ProducerUtils {

    public static <T> void setContext(Context<T> context) {
        setContext(context, context.iterators());
        setContext(context, context.callbacks());
        setContext(context, context.storages());
        setContext(context, context.filter());
    }

    public static <T> void setContext(Context<T> context, Collection<?> aware) {
        aware.stream()
                .filter(obj -> obj instanceof ContextAware)
                .map(obj -> (ContextAware<T>) obj)
                .forEach(contextAware -> contextAware.setContext(context));
    }

    public static <T> void setContext(Context<T> context, Object obj) {
        if (obj instanceof ContextAware) {
            ContextAware<T> aware = (ContextAware<T>) obj;
            aware.setContext(context);
        }
    }

    public static <T> IteratorAdapter<T> emptyIterator() {
        return new IteratorAdapter<T>() {
            @Override
            public void close() {

            }

            @Override
            public boolean hasNext() {
                return false;
            }

            @Override
            public CandidateHolder<T> next() {
                return null;
            }
        };
    }
}
