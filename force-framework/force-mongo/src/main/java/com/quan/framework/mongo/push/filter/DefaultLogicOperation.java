package com.quan.framework.mongo.push.filter;

import com.quan.framework.mongo.push.context.Context;
import com.quan.framework.mongo.push.entity.PushObject;

/**
 * @author Force-oneself
 * @Description DefaultLogicOperation
 * @date 2021-09-15
 */
public class DefaultLogicOperation<T extends PushObject> implements LogicOperation<T, Context> {

    @Override
    public boolean test(T t, Context context) {
        return false;
    }
}
