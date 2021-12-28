package com.quan.demo.freemarker;

/**
 * @author Force-oneself
 * @description Generater
 * @date 2021-12-27
 */
@FunctionalInterface
public interface Generator {

    /**
     * 生成代码
     *
     * @param data data
     */
    void generate(DataHolder data);
}
