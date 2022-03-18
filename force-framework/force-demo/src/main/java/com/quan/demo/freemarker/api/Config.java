package com.quan.demo.freemarker.api;

/**
 * @author Force-oneself
 * @description Config
 * @date 2022-03-18
 */
public interface Config {

    default String outPrefixPath() {
        return "";
    }

    default String templatePrefixPath() {
        return "";
    }

    default String basePackage() {
        return "";
    }
}
