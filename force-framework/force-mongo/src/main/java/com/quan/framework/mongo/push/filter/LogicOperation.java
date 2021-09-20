package com.quan.framework.mongo.push.filter;

import java.util.function.BiPredicate;

/**
 * @author Force-oneself
 * @Description LogicOperation 逻辑操作组合接口
 * @date 2021-09-15
 */
public interface LogicOperation<T, U> extends BiPredicate<T, U> {

}
