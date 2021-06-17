package com.quan.framework.document.excel.easy;

import com.alibaba.excel.context.AnalysisContext;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;

/**
 * @author Force-oneself
 * @Description BatchableBuilder.java
 * @date 2021-06-07
 */
public class BatchableBuilder<T> {

    private BiConsumer<List<T>, AnalysisContext> batchProcessor;
    private int capacity = 1000;
    private boolean enable = true;
    private BiFunction<T, AnalysisContext, T> pre;

    public BatchableBuilder<T> batch(@NotNull BiConsumer<List<T>, AnalysisContext> batchProcessor) {
        this.batchProcessor = batchProcessor;
        return this;
    }

    public BatchableBuilder<T> pre(@NotNull BiFunction<T, AnalysisContext, T> preProcessor) {
        this.pre = preProcessor;
        return this;
    }

    public BatchableBuilder<T> capacity(int capacity) {
        this.capacity = capacity;
        return this;
    }

    public BatchableBuilder<T> enable(boolean enable) {
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

