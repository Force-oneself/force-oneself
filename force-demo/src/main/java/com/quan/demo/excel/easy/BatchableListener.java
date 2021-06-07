package com.quan.demo.excel.easy;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;

/**
 * @Description: 自定义消费监听
 * @Author heyq
 * @Date 2020-11-17
 **/
public class BatchableListener<T> extends AnalysisEventListener<T> {

    private final List<T> dataList = new ArrayList<>();
    private final BatchableContext<T> batchableContext;


    public BatchableListener(BatchMapper<T> batchMapper) {
        this.batchableContext = new DefaultBatchableContext<>(batchMapper);
    }

    public BatchableListener(BatchableContext<T> batchableContext) {
        if (batchableContext == null) {
            throw new RuntimeException("BatchableContext can't be null");
        }
        this.batchableContext = batchableContext;
    }

    @Override
    public void invoke(T data, AnalysisContext context) {
        dataList.add(data);
        if (batchableContext.enable()
                && dataList.size() >= batchableContext.getCapacity()) {
            batchableContext.getBatchProcessor().accept(dataList, context);
            dataList.clear();
        }
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
        if (!dataList.isEmpty()) {
            batchableContext.getBatchProcessor().accept(dataList, context);
            dataList.clear();
        }
    }

    static class DefaultBatchableContext<T> implements BatchableContext<T> {

        private final BatchMapper<T> batchMapper;

        public DefaultBatchableContext(BatchMapper<T> batchMapper) {
            this.batchMapper = batchMapper;
        }

        @Override
        public BiConsumer<List<T>, AnalysisContext> getBatchProcessor() {
            return batchMapper::batch;
        }
    }
}
