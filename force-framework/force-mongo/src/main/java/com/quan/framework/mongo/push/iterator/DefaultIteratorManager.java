package com.quan.framework.mongo.push.iterator;

import com.quan.framework.mongo.push.context.Context;

/**
 * @author Force-oneself
 * @Description DefaultIteratorManager
 * @date 2021-09-15
 */
public class DefaultIteratorManager<T> implements IteratorManager<T> {

    private IteratorAdapterComposite<T> composite;

    @Override
    public IteratorAdapter<T> getIteratorAdapter(Context context) {
        if (composite.support(context)) {
            throw new RuntimeException("未找到合适的数据迭代器");
        }
        return composite;
    }
}
