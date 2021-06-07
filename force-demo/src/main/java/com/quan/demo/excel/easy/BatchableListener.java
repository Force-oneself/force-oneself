package com.quan.demo.excel.easy;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;

/**
 * @Description: 可实现批量处理的监听器
 * @Author heyq
 * @Date 2020-11-17
 **/
public class BatchableListener<T> extends AnalysisEventListener<T> {

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    /**
     * 存储批量数据
     */
    private final List<T> dataList = new ArrayList<>();

    /**
     * 构造条件的builder
     */
    private final BatchableBuilder<T> builder;


    public BatchableListener(@NotNull BatchableBuilder<T> builder) {
        this.builder = builder;
    }

    /**
     * 定义公共大体一致的模版
     *
     * @param data    每一行的数据
     * @param context 上下文
     */
    @Override
    public void invoke(T data, AnalysisContext context) {
        // 前置处理验证实体等，返回null可以过滤掉
        T preData = builder.getPre().apply(data, context);
        if (Objects.isNull(preData)) {
            return;
        }
        dataList.add(preData);
        if (builder.isEnable() && dataList.size() >= builder.getCapacity()) {
            builder.getBatchProcessor().accept(dataList, context);
            dataList.clear();
        }
    }

    /**
     * 完成后的操作
     *
     * @param context 上下文
     */
    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
        if (!dataList.isEmpty()) {
            builder.getBatchProcessor().accept(dataList, context);
            dataList.clear();
        }
        LOGGER.info("Excel读取完成");
    }
}
