package com.quan.framework.mongo.push.filter;

import com.quan.framework.mongo.push.context.Context;
import com.quan.framework.mongo.push.entity.PushObject;

import java.util.List;

/**
 * @author Force-oneself
 * @Description DefaultFilterManager
 * @date 2021-09-15
 */
public class DefaultFilterManager<T extends PushObject> implements FilterManager<T> {

    private FilterService<T> filterService;

    private List<LogicOperation<T, Context>> operations;

    @Override
    public boolean filter(T data, Context context) {
        return false;
    }

    @Override
    public void conditions(Context context) {
        
    }
}
