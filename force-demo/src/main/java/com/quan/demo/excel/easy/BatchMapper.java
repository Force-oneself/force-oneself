package com.quan.demo.excel.easy;

import com.alibaba.excel.context.AnalysisContext;

import java.util.List;

/**
 *
 * @Description: interface BatchMapper
 * @Author Force丶Oneself
 * @Date 2021-06-07
 **/

public interface BatchMapper<T> {

    /**
     * 分批执行处理
     *
     * @param data 分批数据
     * @param context 上下文
     */
    void batch(List<T> data, AnalysisContext context);
}
