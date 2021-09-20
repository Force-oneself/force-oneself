package com.quan.framework.mongo.push.iterator;

import com.quan.framework.mongo.push.context.Context;

/**
 * @author Force-oneself
 * @Description IteratorManager
 * @date 2021-09-15
 */
public interface IteratorManager<T> {

    /**
     * 获取迭代器
     *
     * @param context 上下文
     * @return 数据迭代器
     */
    IteratorAdapter<T> getIteratorAdapter(Context context);
}
