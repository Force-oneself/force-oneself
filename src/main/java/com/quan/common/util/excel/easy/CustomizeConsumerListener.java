package com.quan.common.util.excel.easy;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

/**
 * @Description: 自定义消费监听
 * @Author heyq
 * @Date 2020-11-17
 **/
public class CustomizeConsumerListener<T> extends AnalysisEventListener<T> {

    private Integer pageSize;
    private List<T> dataList = new ArrayList<>();
    private Consumer<List<T>> consumer;

    public CustomizeConsumerListener(Integer pageSize, Consumer<List<T>> consumer) {
        this.pageSize = pageSize;
        this.consumer = consumer;
    }

    @Override
    public void invoke(T data, AnalysisContext context) {
        dataList.add(data);
        if (dataList.size() >= pageSize) {
            consumer.accept(dataList);
            dataList.clear();
        }
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
        consumer.accept(dataList);
    }
}
