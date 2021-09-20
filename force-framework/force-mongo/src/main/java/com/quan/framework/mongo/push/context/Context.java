package com.quan.framework.mongo.push.context;

import com.quan.framework.mongo.push.prepare.Preparer;

import java.util.List;

/**
 * @author Force-oneself
 * @Description PushContext
 * @date 2021-09-15
 */
public interface Context {

    String getTenantId();

    List<Preparer> getPrepares();

}
