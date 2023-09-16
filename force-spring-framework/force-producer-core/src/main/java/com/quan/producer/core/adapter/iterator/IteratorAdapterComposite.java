package com.quan.producer.core.adapter.iterator;

import com.quan.producer.core.context.CandidateHolder;
import com.quan.producer.core.context.Context;
import com.quan.producer.core.context.ContextConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.List;

/**
 * @author Force-oneself
 * @description IteratorAdapterComposite 迭代器组合器
 * @date 2021-09-28
 **/
public class IteratorAdapterComposite<T> implements IteratorAdapter<T> {

    private final static Logger log = LoggerFactory.getLogger(IteratorAdapterComposite.class);

    private final List<IteratorAdapter<T>> adapters;

    private IteratorAdapter<T> current;

    private int currentIndex = 0;

    private final long count;

    public IteratorAdapterComposite(List<IteratorAdapter<T>> adapters, Context<T> context) {
        this.adapters = adapters;
        this.current = adapters.get(0);
        count = adapters.stream().mapToLong(IteratorAdapter::count).sum();
        context.setObject(ContextConstants.ITERATOR_COUNT_TOTAL, count);
    }

    @Override
    public void close() throws IOException {
        current.close();
    }

    @Override
    public boolean hasNext() {
        boolean next = current.hasNext();

        if (next) {
            return true;
        } else if (currentIndex < adapters.size() - 1) {
            try {
                // 关闭当前游标
                current.close();
            } catch (IOException e) {
                log.error("游标关闭失败", e);
            }
            // 获取下一个
            current = adapters.get(++currentIndex);
            return current.hasNext();
        }
        return false;
    }

    @Override
    public CandidateHolder<T> next() {
        return current.next();
    }

    @Override
    public long count() {
        return count;
    }

}
