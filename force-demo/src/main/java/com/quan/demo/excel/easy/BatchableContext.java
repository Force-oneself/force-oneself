package com.quan.demo.excel.easy;

import com.alibaba.excel.context.AnalysisContext;

import java.util.List;
import java.util.function.BiConsumer;

/**
 * @Description: interface BatchableContext
 * @Author Force丶Oneself
 * @Date 2021-06-07
 **/
public interface BatchableContext<T> {

    int DEFAULT_CAPACITY = 1000;

    /**
     * 获取分批处理消费器
     *
     * @return 处理器消费
     */
    BiConsumer<List<T>, AnalysisContext> getBatchProcessor();

    /**
     * 获取每批次的容量
     * @return 容量
     */
    default int getCapacity() {
        return DEFAULT_CAPACITY;
    }

    /**
     * 启用分批处理
     * @return 是否启用
     */
    default boolean enable() {
        return true;
    }
}
