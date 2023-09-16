package com.quan.framework.document.excel.easy;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.metadata.CellData;
import com.alibaba.excel.metadata.CellExtra;
import com.alibaba.excel.read.listener.ReadListener;
import com.alibaba.excel.util.ConverterUtils;

import java.util.Map;

/**
 * AnalysisReadListener.java
 *
 * @author Force-oneself
 * @date 2022-06-07
 */
public interface AnalysisReadListener<T> extends ReadListener<T> {

    /**
     * Returns the header as a map.Override the current method to receive header data.
     *
     * @param headMap 标题
     * @param context 上下文
     */
    @Override
    default void invokeHead(Map<Integer, CellData> headMap, AnalysisContext context) {
        invokeHeadMap(ConverterUtils.convertToStringMap(headMap, context), context);
    }

    /**
     * 执行标题的处理
     *
     * @param headMap 处理后的标题
     * @param context 上下文
     */
    default void invokeHeadMap(Map<Integer, String> headMap, AnalysisContext context) {
    }

    /**
     * The current method is called when extra information is returned
     *
     * @param extra   extra information
     * @param context analysis context
     */
    @Override
    default void extra(CellExtra extra, AnalysisContext context) {
    }

    /**
     * 当任何一个监听器进行错误报告时，所有监听器都会收到此方法。 如果这里抛出异常，整个读取将终止。
     *
     * @param exception 抛出的异常
     * @param context   上下文
     * @throws Exception 默认抛出
     */
    @Override
    default void onException(Exception exception, AnalysisContext context) throws Exception {
        throw exception;
    }

    /**
     * 是否继续读取, 默认ture
     *
     * @param context 上下文
     * @return boolean
     */
    @Override
    default boolean hasNext(AnalysisContext context) {
        return true;
    }
}
