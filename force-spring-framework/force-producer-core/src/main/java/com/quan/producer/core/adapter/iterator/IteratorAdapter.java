package com.quan.producer.core.adapter.iterator;

import com.quan.producer.core.context.CandidateHolder;
import org.springframework.core.Ordered;

import java.io.Closeable;
import java.util.Iterator;

/**
 * @author Force-oneself
 * @description IteratorAdapter
 * @date 2021-09-27
 **/
public interface IteratorAdapter<T> extends Closeable, Iterator<CandidateHolder<T>>, Ordered {

    /**
     * 统计迭代数据量
     *
     * @return 数量
     */
    default long count() {
        return -1;
    }

    /**
     * 默认排序值
     *
     * @return 100
     */
    @Override
    default int getOrder() {
        return 100;
    }

}
