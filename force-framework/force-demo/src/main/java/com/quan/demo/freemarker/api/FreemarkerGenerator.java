package com.quan.demo.freemarker.api;

import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

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
    Collection<TemplateBear> templateBears();

    /**
     * 生成代码的模版方法
     */
    @Override
    @SuppressWarnings("unchecked")
    default void generate() {
        List<Object> models = new ArrayList<>();
        final Object dataModel = this.dataModel();
        if (dataModel instanceof Collection) {
            models.addAll(((Collection<Object>) dataModel));
        } else {
            models.add(dataModel);
        }
        final Collection<TemplateBear> bears = this.templateBears();
        models.forEach(model -> bears.forEach(bear -> {
            try (Writer out = bear.out().get()) {
                Template template = bear.template().get();
                template.process(model, out);
                out.flush();
            } catch (TemplateException | IOException e) {
                throw new RuntimeException(e);
            }
        }));
    }

}
