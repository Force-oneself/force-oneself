package com.quan.demo.freemarker.api;

import freemarker.template.Configuration;

import java.util.function.Consumer;

/**
 * @author Force-oneself
 * @description TemplateGlobalConfig
 * @date 2022-03-17
 */
public interface TemplateGlobalConfig {

    /**
     * 自定义配置
     *
     * @return java.util.function.Consumer<freemarker.template.Configuration>
     */
    default Consumer<Configuration> customizeConfig() {
        return null;
    }

    default String outPrefixPath() {
        return "";
    }

    default String templatePrefixPath() {
        return "";
    }

}
