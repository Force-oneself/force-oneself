package com.quan.framework.mongo.helper;

import java.util.Map;

/**
 * @author Force-oneself
 * @description MethodHolder
 * @date 2022-01-25
 */
public interface MethodOptions {

    /**
     * 方法类型
     * @return java.lang.String
     */
    MethodType methodType();

    /**
     * 参数列表
     * @return java.util.Map<java.lang.Integer,java.lang.Object>
     */
    Map<Integer, Object> params();

    @SuppressWarnings("unchecked")
    default <T> T find(Class<T> param) {
        return (T) params().values().stream()
                .filter(obj ->  param.isAssignableFrom(obj.getClass()))
                .findFirst()
                .orElse(null);
    }


    default boolean isExist(Class<?> param) {
        return params().values().stream().anyMatch(obj -> param.isAssignableFrom(obj.getClass()));
    }

    default boolean isExist(int index, Class<?> param) {
        Map<Integer, Object> params = params();
        return params.containsKey(index) &&  param.isAssignableFrom(params.get(index).getClass());
    }
}
