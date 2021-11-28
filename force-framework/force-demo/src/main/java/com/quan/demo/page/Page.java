package com.quan.demo.page;

import java.util.Collection;

/**
 * @author Force-oneself
 * @Description Page
 * @date 2021-11-22
 */
public interface Page<T, R> {

    Collection<T> records();

    long count();

    long pages();

    PageQuery<R> query();
}
