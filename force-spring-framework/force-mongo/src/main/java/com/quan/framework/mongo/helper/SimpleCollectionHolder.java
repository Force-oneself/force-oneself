package com.quan.framework.mongo.helper;

import com.mongodb.client.MongoCollection;

/**
 * @author Force-oneself
 * @description SimpleCollectionHolder
 * @date 2022-01-25
 */
public class SimpleCollectionHolder<T> implements CollectionHolder<T> {

    private final String collectionName;
    private final Class<T> documentClass;
    private final MongoCollection<T> delegate;
    private final MethodOptions methodOptions;

    public SimpleCollectionHolder(String collectionName, Class<T> documentClass, MongoCollection<T> delegate,
                                  MethodOptions methodOptions) {
        this.collectionName = collectionName;
        this.documentClass = documentClass;
        this.delegate = delegate;
        this.methodOptions = methodOptions;
    }

    @Override
    public String collectionName() {
        return this.collectionName;
    }

    @Override
    public Class<T> documentClass() {
        return this.documentClass;
    }

    @Override
    public MongoCollection<T> delegate() {
        return this.delegate;
    }

    @Override
    public MethodOptions methodOptions() {
        return this.methodOptions;
    }
}
