package com.quan.demo.freemarker.api;

import com.quan.demo.freemarker.base.TemplateHolder;
import freemarker.template.TemplateException;

import java.io.IOException;
import java.io.Writer;
import java.util.Collection;

/**
 * @author Force-oneself
 * @description FreemarkerGenerator
 * @date 2022-01-03
 */
public interface FreemarkerGenerator extends Generator, DataModel {

    /**
     * 模版集
     *
     * @return java.util.Collection<com.quan.demo.freemarker.base.TemplateHolder>
     */
    Collection<TemplateHolder> templateHolders();

    /**
     * 生成代码
     */
    @Override
    default void generate() {
        templateHolders().forEach(templateHolder -> {
            try (Writer out = templateHolder.getOut().get()) {
                templateHolder.getTemplate().process(dataModel(), out);
                out.flush();
            } catch (TemplateException | IOException e) {
                throw new RuntimeException(e);
            }
        });

    }
}
