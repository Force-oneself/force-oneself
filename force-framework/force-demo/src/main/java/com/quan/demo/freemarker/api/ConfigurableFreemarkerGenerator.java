package com.quan.demo.freemarker.api;

import freemarker.cache.ClassTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Collection;
import java.util.function.Supplier;
import java.util.stream.Collectors;

/**
 * @author Force-oneself
 * @description ConfigurableFreemarkerGenerator
 * @date 2022-03-17
 */
public interface ConfigurableFreemarkerGenerator extends FreemarkerGenerator {

    /**
     * 模板配置集
     *
     * @return Collection<TemplateConfig>
     */
    Collection<TemplateConfig> templateConfig();

    /**
     * 可配置模版集生成
     *
     * @return return
     */
    @Override
    default Collection<TemplateBear> templateHolders() {
        Configuration config = new Configuration(Configuration.DEFAULT_INCOMPATIBLE_IMPROVEMENTS);
        config.setObjectWrapper(new DefaultObjectWrapper(Configuration.DEFAULT_INCOMPATIBLE_IMPROVEMENTS));
        this.configurationCustom(config);
        return this.templateConfig().stream()
                .map(templateConfig -> templateBear(config, templateConfig))
                .collect(Collectors.toList());
    }

    /**
     * ignore
     *
     * @param config         freemarker Configuration
     * @param templateConfig TemplateConfig
     * @return TemplateBear
     */
    default TemplateBear templateBear(Configuration config, TemplateConfig templateConfig) {
        return new TemplateBear() {
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
        };
    }

    /**
     * Configuration 自定义配置可以实现获取外部模版类加载器的接口
     *
     * @param configuration configuration
     */
    default void configurationCustom(Configuration configuration) {
        configuration.setTemplateLoader(
                new ClassTemplateLoader(ConfigurableFreemarkerGenerator.class, "/ftl"));
    }
}
