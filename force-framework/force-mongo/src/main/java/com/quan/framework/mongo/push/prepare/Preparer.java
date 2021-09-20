package com.quan.framework.mongo.push.prepare;

import com.quan.framework.mongo.push.context.Context;

/**
 * @author Force-oneself
 * @Description Preparer
 * @date 2021-09-15
 */
public interface Preparer {

    default void prepare(Context context) {

    }
}
