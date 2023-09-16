package com.quan.framework.mongo.helper;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Force-oneself
 * @description SimpleMethodOptions
 * @date 2022-01-25
 */
public class SimpleMethodOptions implements MethodOptions {

    private final MethodType methodType;

    private final Map<Integer, Object> params = new HashMap<>(16);

    private final AtomicInteger index = new AtomicInteger(0);


    public SimpleMethodOptions(MethodType methodType) {
        this.methodType = methodType;
    }

    public static SimpleMethodOptions of(MethodType methodType) {
        return new SimpleMethodOptions(methodType);
    }

    @Override
    public MethodType methodType() {
        return this.methodType;
    }

    @Override
    public Map<Integer, Object> params() {
        return this.params;
    }

    public SimpleMethodOptions param(Object param) {
        this.params.put(index.incrementAndGet(), param);
        return this;
    }
}
