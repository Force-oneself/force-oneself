package com.quan.framework.document.excel.easy;

import com.alibaba.excel.context.AnalysisContext;

import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;

/**
 * BatchBuilder.java
 *
 * @author Force-oneself
 * @date 2022-06-07
 */
public class BatchBuilder<T> {

    private BiConsumer<List<T>, AnalysisContext> batchProcessor;
    private int capacity = 1000;
    private boolean enable = true;
    private BiFunction<T, AnalysisContext, T> pre;

    public BatchBuilder<T> batch(BiConsumer<List<T>, AnalysisContext> batchProcessor) {
        this.batchProcessor = batchProcessor;
        return this;
    }

    public BatchBuilder<T> pre(BiFunction<T, AnalysisContext, T> preProcessor) {
        this.pre = preProcessor;
        return this;
    }

    public BatchBuilder<T> capacity(int capacity) {
        this.capacity = capacity;
        return this;
    }

    public BatchBuilder<T> enable(boolean enable) {
        this.enable = enable;
        return this;
    }

    public BiFunction<T, AnalysisContext, T> getPre() {
        return pre;
    }

    public BiConsumer<List<T>, AnalysisContext> getBatchProcessor() {
        return batchProcessor;
    }

    public int getCapacity() {
        return capacity;
    }

    public boolean isEnable() {
        return enable;
    }
}

