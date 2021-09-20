package com.quan.framework.mongo.push.filter;

import com.quan.framework.mongo.push.context.Context;
import com.quan.framework.mongo.push.entity.PushObject;

/**
 * @author Force-oneself
 * @Description FilterManager
 * @date 2021-09-15
 */
public interface FilterManager<T extends PushObject> {

    boolean filter(T data, Context context);

    void conditions(Context context);
}
