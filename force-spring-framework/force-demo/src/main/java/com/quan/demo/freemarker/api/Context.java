package com.quan.demo.freemarker.api;

import java.util.Collection;

/**
 * Context
 *
 * @author Force-oneself
 * @date 2022-04-29
 */
public interface Context {

    /**
     * 模板配置
     *
     * @return 模板配置
     */
    Collection<TemplateConfig> templateConfig();


}
