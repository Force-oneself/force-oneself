package com.quan.demo.freemarker.support.mysql;

import com.quan.demo.freemarker.base.JavaDomainModel;

/**
 * @author Force-oneself
 * @description DomainProcessor
 * @date 2022-03-18
 */
@FunctionalInterface
public interface DomainProcessor {

    /**
     * 处理
     * @param model model
     */
    void process(JavaDomainModel model);

}
