package com.quan.demo.freemarker.api;

/**
 * @author Force-oneself
 * @description DataModel
 * @date 2022-01-03
 */
@FunctionalInterface
public interface DataModel {

    /**
     * freemarker 数据模型
     *
     * @return 模型
     */
    Object dataModel();
}
