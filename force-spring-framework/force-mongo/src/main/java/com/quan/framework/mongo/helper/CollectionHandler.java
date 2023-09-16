package com.quan.framework.mongo.helper;

/**
 * @author Force-oneself
 * @description Options
 * @date 2022-01-25
 */
public interface CollectionHandler {


    /**
     * 是否支持处理
     *
     * @param holder holder
     * @return boolean
     */
    default boolean support(CollectionHolder<?> holder) {
        return false;
    }

    /**
     * collection处理
     *
     * @param holder holder
     */
    void handle(CollectionHolder<?> holder);
}
