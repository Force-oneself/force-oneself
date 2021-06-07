package com.quan.demo.excel.easy;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.read.builder.ExcelReaderBuilder;

import java.io.InputStream;
import java.util.List;
import java.util.function.BiConsumer;

/**
 * @Description:
 * @Author heyq
 * @Date 2020-11-15
 **/
public class ExcelUtils extends EasyExcel {


    public static <T> ExcelReaderBuilder read(InputStream inputStream, Class<T> head,
                                              BiConsumer<List<T>, AnalysisContext> consumer) {
        return read(inputStream, head, new BatchableListener<>(new BatchableBuilder<T>().batch(consumer)));
    }
}
