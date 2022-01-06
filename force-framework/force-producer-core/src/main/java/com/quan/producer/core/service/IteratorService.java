package com.quan.producer.core.service;

import com.quan.producer.core.adapter.iterator.IteratorAdapter;
import com.quan.producer.core.context.CandidateHolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.function.Consumer;

/**
 * @author Force-oneself
 * @description IteratorManager
 * @date 2021-09-27
 **/
public interface IteratorService<T> {

    Logger log = LoggerFactory.getLogger(IteratorService.class);

    /**
     * 迭代消费
     *
     * @param consumer 外部提供消费者
     */
    default void iterator(Consumer<CandidateHolder<T>> consumer) {
        try (IteratorAdapter<T> adapter = iterator()) {
            adapter.forEachRemaining(consumer);
        } catch (IOException e) {
            throw new RuntimeException("数据迭代异常", e);
        }
    }

    /**
     * 迭代适配器
     *
     * @return 适配器
     */
    IteratorAdapter<T> iterator();

}
