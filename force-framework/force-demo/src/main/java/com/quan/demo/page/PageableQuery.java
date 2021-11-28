package com.quan.demo.page;

/**
 * @author Force-oneself
 * @Description PageableQuery
 * @date 2021-11-22
 */
public interface PageableQuery<T> extends PageQuery<T>, Enable {

    @Override
    default boolean enable() {
        return true;
    }
}
