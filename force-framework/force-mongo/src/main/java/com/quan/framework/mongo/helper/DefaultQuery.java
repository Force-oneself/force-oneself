package com.quan.framework.mongo.helper;

import org.springframework.data.mongodb.core.query.Query;

/**
 * @author Force-oneself
 * @Description DefaultQuery
 * @date 2021-08-20
 */
public class DefaultQuery<T> implements PageableQuery<T>{

    private int size = 10;

    private int current = 1;

    private boolean enable = true;

    private Query query;

    private Class<T> entityClass;

    public DefaultQuery() {
    }

    public DefaultQuery(int current, int size) {
        this.size = size;
        this.current = current;
    }

    public DefaultQuery(Query query, Class<T> entityClass) {
        this.query = query;
        this.entityClass = entityClass;
    }

    public static <T> DefaultQuery<T> of(Query query, Class<T> entityClass) {
        return new DefaultQuery<>(query, entityClass);
    }

    public DefaultQuery<T> size(int size) {
        this.size = size;
        return this;
    }

    public DefaultQuery<T> current(int current) {
        this.current = current;
        return this;
    }


    public DefaultQuery<T> enable(boolean enable) {
        this.enable = enable;
        return this;
    }

    public DefaultQuery<T> query(Query query) {
        this.query = query;
        return this;
    }

    public DefaultQuery<T> entityClass(Class<T> entityClass) {
        this.entityClass = entityClass;
        return this;
    }

    @Override
    public int current() {
        return current;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean enable() {
        return enable;
    }

    @Override
    public Query get() {
        return query;
    }

    @Override
    public Class<T> queryClass() {
        return entityClass;
    }
}
