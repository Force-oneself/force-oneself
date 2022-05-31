package com.quan.framework.elasticsearch.demo.api;

/**
 * Matcher
 *
 * @author Force-oneself
 * @date 2022-05-31
 */
public interface Matcher<P, V, M> {

    P param();

    V value();

    M match();
}
