package com.quan.uitl.excel.easy;

import com.alibaba.excel.context.AnalysisContext;

import java.util.function.Consumer;

/**
 * @Description:
 * @Author heyq
 * @Date 2020-11-17
 **/
public interface Processor<T> {

    void process(AnalysisContext context, Consumer<T> consumer);
}
