package com.quan.demo.freemarker.base;

import com.quan.demo.freemarker.api.TemplateBear;
import com.quan.demo.freemarker.api.TemplateConfig;
import freemarker.template.Configuration;
import freemarker.template.Template;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.function.Supplier;

/**
 * DefaultTemplateBear
 *
 * @author Force-oneself
 * @date 2022-04-29
 */
public class DefaultTemplateBear implements TemplateBear {

    protected final Configuration config;

    protected final TemplateConfig templateConfig;


    public DefaultTemplateBear(Configuration config, TemplateConfig templateConfig) {
        this.config = config;
        this.templateConfig = templateConfig;
    }

    @Override
    public Supplier<Template> template() {
        return () -> {
            try {
                return config.getTemplate(templateConfig.templatePath(), templateConfig.encoding());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        };
    }

    @Override
    public Supplier<Writer> out() {
        return () -> {
            try {
                return new BufferedWriter(new OutputStreamWriter(new FileOutputStream(templateConfig.outPath()),
                        StandardCharsets.UTF_8));
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        };
    }

}
