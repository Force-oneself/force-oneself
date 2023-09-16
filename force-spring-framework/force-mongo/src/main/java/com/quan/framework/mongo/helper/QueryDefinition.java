package com.quan.framework.mongo.helper;

import org.springframework.data.mongodb.core.query.Query;

import java.util.function.Supplier;

/**
 * @author Force-oneself
 * @Description QueryDefinition
 * @date 2021-08-20
 */
public interface QueryDefinition<T> extends Supplier<Query> {

    /**
     * 返回Document的实体Class
     *
     * @return class
     */
    default Class<T> queryClass() {
        throw new UnsupportedOperationException("请子类实现");
    }

    /**
     * 查询条件
     *
     * @return 自定Query
     */
    @Override
    default Query get() {
        return new Query();
    }


}
