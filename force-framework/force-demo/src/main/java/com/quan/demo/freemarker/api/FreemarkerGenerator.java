package com.quan.demo.freemarker.api;

import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.Writer;

/**
 * @author Force-oneself
 * @description FreemarkerGenerator
 * @date 2022-01-03
 */
public interface FreemarkerGenerator extends Generator, DataModel {

    Logger LOGGER = LoggerFactory.getLogger(FreemarkerGenerator.class);

    /**
     * 字节输出流
     *
     * @return java.io.Writer
     */
    Writer out();

    /**
     * 模版
     *
     * @return freemarker.template.Template
     */
    Template template();


    /**
     * 生成代码
     */
    @Override
    default void generate() {
        try (Writer out = out()) {
            template().process(dataModel(), out);
            out.flush();
        } catch (TemplateException | IOException e) {
            LOGGER.error("生成失败", e);
        }
    }
}
