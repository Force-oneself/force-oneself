package com.quan.demo.page;

/**
 * PageableQuery.java
 *
 * @author Force-oneself
 * @date 2022-05-07 23:34
 */
public interface PageableQuery<T> extends PageQuery<T>, Enable {

    @Override
    default boolean enable() {
        return true;
    }
}
