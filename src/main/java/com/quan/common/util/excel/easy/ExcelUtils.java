package com.quan.common.util.excel.easy;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.read.builder.ExcelReaderBuilder;

import java.io.InputStream;
import java.util.List;
import java.util.function.Consumer;

/**
 * @Description:
 * @Author heyq
 * @Date 2020-11-15
 **/
public class ExcelUtils extends EasyExcel {


    public static <T> ExcelReaderBuilder read(InputStream inputStream, Class<T> head, Integer pageSize, Consumer<List<T>> consumer) {
        return read(inputStream, head, new CustomizeConsumerListener<T>(pageSize, consumer));
    }
}
