package com.quan.framework.document.excel.easy;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.read.builder.ExcelReaderBuilder;

import java.io.InputStream;
import java.util.List;
import java.util.function.BiConsumer;

/***
 * ExcelUtils.java
 *
 * @author Force-oneself
 * @date 2020-05-12
 */
public class ExcelUtils extends EasyExcel {

    /**
     * 批量获取Excel数据
     *
     * @param inputStream 输入流
     * @param head        标题
     * @param consumer    消费分批数据的
     * @param <T>         消费的数据类型
     * @return Excel的构造器
     */
    public static <T> ExcelReaderBuilder batchRead(InputStream inputStream, Class<T> head,
                                                   BiConsumer<List<T>, AnalysisContext> consumer) {
        return read(inputStream, head, new BatchListener<>(new BatchBuilder<T>().batch(consumer)));
    }

    /**
     * 批量获取Excel数据
     *
     * @param inputStream 输入流
     * @param head        标题
     * @param builder     消费的条件构造器
     * @param <T>         消费的数据类型
     * @return Excel的构造器
     */
    public static <T> ExcelReaderBuilder batchRead(InputStream inputStream, Class<T> head, BatchBuilder<T> builder) {
        return read(inputStream, head, new BatchListener<>(builder));
    }
}
