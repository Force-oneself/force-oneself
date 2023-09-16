package com.quan.demo.page;

import java.util.Collection;

/**
 * Page.java
 *
 * @author Force-oneself
 * @date 2022-05-07 23:35
 */
public interface Page<T, R> {

    Collection<T> records();

    long count();

    long pages();

    PageQuery<R> query();
}
