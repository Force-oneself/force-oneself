package com.quan.framework.mongo.push.store;

import com.quan.framework.mongo.push.context.Context;

/**
 * @author Force-oneself
 * @Description StoreManager
 * @date 2021-09-15
 */
public interface StoreManager {

    <T> void store(T data, Context context);
}
