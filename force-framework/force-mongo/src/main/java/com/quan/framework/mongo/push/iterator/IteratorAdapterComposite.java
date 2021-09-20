package com.quan.framework.mongo.push.iterator;

import com.quan.framework.mongo.push.context.Context;
import org.springframework.util.CollectionUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Force-oneself
 * @Description IteratorAdapterComposite
 * @date 2021-09-15
 */
public class IteratorAdapterComposite<T> implements IteratorAdapter<T> {

    private final List<IteratorAdapter<T>> adapters;

    private IteratorAdapter<T> current;

    private int currentIndex = 0;

    private List<IteratorAdapter<T>> supportAdapters = new ArrayList<>();

    public IteratorAdapterComposite(List<IteratorAdapter<T>> adapters) {
        this.adapters = adapters;
    }

    @Override
    public void close() throws IOException {
        for (IteratorAdapter<T> adapter : supportAdapters) {
            adapter.close();
        }
    }

    @Override
    public boolean hasNext() {
        boolean next = current.hasNext();
        if (!next && currentIndex == supportAdapters.size() - 1) {
            currentIndex++;
            current = supportAdapters.get(currentIndex);
            return current.hasNext();
        }
        return false;
    }

    @Override
    public T next() {
        return current.next();
    }

    @Override
    public boolean support(Context context) {
        if (CollectionUtils.isEmpty(adapters)) {
            return false;
        }
        List<IteratorAdapter<T>> support = adapters.stream()
                .filter(adapter -> adapter.support(context)).collect(Collectors.toList());
        boolean exit = !CollectionUtils.isEmpty(support);
        if (exit) {
            this.supportAdapters = support;
            currentIndex = 0;
            this.current = supportAdapters.get(this.currentIndex);
        }
        return exit;
    }
}
