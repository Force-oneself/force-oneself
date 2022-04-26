package com.quan.framework.elasticsearch.api;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * OrderCondition.java
 *
 * @author Force-oneself
 * @date 2022-04-22 23:49
 */
@Getter
@Setter
@Accessors(chain = true)
public class OrderCondition<T> {

    private Boolean isAsc;
    private T orderField;

    public static <T> OrderCondition<T> order(boolean isAsc, T field) {
        OrderCondition<T> orderCondition = new OrderCondition<>();
        orderCondition.isAsc = isAsc;
        orderCondition.orderField = field;
        return orderCondition;
    }

    public static <T> OrderCondition<T> orderAsc(T field) {
        OrderCondition<T> orderCondition = new OrderCondition<>();
        orderCondition.isAsc = true;
        orderCondition.orderField = field;
        return orderCondition;
    }

    public static <T> OrderCondition<T> orderDesc(T field) {
        OrderCondition<T> orderCondition = new OrderCondition<>();
        orderCondition.isAsc = false;
        orderCondition.orderField = field;
        return orderCondition;
    }

}
