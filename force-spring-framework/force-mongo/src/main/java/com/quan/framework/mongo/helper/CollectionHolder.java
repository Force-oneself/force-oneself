package com.quan.framework.mongo.helper;

import com.mongodb.client.MongoCollection;

/**
 * @author Force-oneself
 * @description CollectionHolder
 * @date 2022-01-25
 */
public interface CollectionHolder<T> {


    /**
     * 集合名
     *
     * @return java.lang.String
     */
    String collectionName();

    /**
     * 转换后的文档类型
     *
     * @return java.lang.Class<T>
     */
    Class<T> documentClass();

    /**
     * 委托的代理类
     *
     * @return com.mongodb.client.MongoCollection<T>
     */
    MongoCollection<T> delegate();

    /**
     * 方法配置
     * @return java.lang.String
     */
    MethodOptions methodOptions();
}
