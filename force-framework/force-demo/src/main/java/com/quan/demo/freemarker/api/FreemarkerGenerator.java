package com.quan.demo.freemarker.api;

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
     * 模版生成器集合
     *
     * @return return
     */
    Collection<TemplateBear> templateHolders();

    /**
     * 生成代码的模版方法
     */
    @Override
    default void generate() {
        this.templateHolders().forEach(templateHolder -> {
            try (Writer out = templateHolder.out().get()) {
                templateHolder.template().get().process(this.dataModel(), out);
                out.flush();
            } catch (TemplateException | IOException e) {
                throw new RuntimeException(e);
            }
        });
    }
}
