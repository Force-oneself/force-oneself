package com.quan.demo.freemarker.base;

import freemarker.template.Template;

import java.io.Writer;
import java.util.function.Supplier;

/**
 * @author Force-oneself
 * @description TemplateHolder
 * @date 2022-03-17
 */
public class TemplateHolder {

    protected Template template;

    protected Supplier<Writer> out;

    public TemplateHolder(Template template, Supplier<Writer> out) {
        this.template = template;
        this.out = out;
    }

    public Template getTemplate() {
        return template;
    }

    public void setTemplate(Template template) {
        this.template = template;
    }

    public Supplier<Writer> getOut() {
        return out;
    }

    public void setOut(Supplier<Writer> out) {
        this.out = out;
    }
}
