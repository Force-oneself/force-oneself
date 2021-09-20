package com.quan.framework.mongo.push.filter;

import com.quan.framework.mongo.push.context.Context;
import com.quan.framework.mongo.push.entity.PushObject;

/**
 * @author Force-oneself
 * @Description FilterService
 * @date 2021-09-15
 */
public interface FilterService<T extends PushObject> {

    default boolean filter(T data, Context context) {
        return true;
    }
}
