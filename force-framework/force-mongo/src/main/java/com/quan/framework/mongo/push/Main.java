package com.quan.framework.mongo.push;

import com.quan.framework.mongo.push.context.Context;
import com.quan.framework.mongo.push.entity.PushObject;
import com.quan.framework.mongo.push.filter.FilterManager;
import com.quan.framework.mongo.push.iterator.IteratorAdapter;
import com.quan.framework.mongo.push.iterator.IteratorManager;
import com.quan.framework.mongo.push.prepare.PrepareManager;
import com.quan.framework.mongo.push.store.StoreManager;

import java.io.IOException;

/**
 * @author Force-oneself
 * @Description Main
 * @date 2021-09-15
 */
public class Main<T extends PushObject> {

    private FilterManager<T> filterManager;

    private PrepareManager prepareManager;

    private IteratorManager<T> iteratorManager;

    private StoreManager storeManager;

    public void exec() throws IOException {
        // 创建上下文
        Context context = createContext();
        doExec(context);
    }

    private void doExec(Context context) throws IOException {
        // 前置准备
        prepareManager.prepare(context);
        // 迭代器拿数据
        try (IteratorAdapter<T> iteratorAdapter = iteratorManager.getIteratorAdapter(context)) {
            iteratorAdapter.forEachRemaining(obj -> {
                try {
                    // 过滤
                    if (filterManager.filter(obj, context)) {
                        // 存储
                        storeManager.store(obj, context);
                    }
                } catch (Throwable ignore) {

                }
            });
        }
    }

    private Context createContext() {
        return null;
    }
}
