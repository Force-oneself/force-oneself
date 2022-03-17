package com.quan.demo.freemarker.api;

import com.quan.demo.freemarker.base.TemplateConfigHolder;
import com.quan.demo.freemarker.base.TemplateHolder;
import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.Collection;
import java.util.function.Consumer;
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
     * @return java.util.Collection<com.quan.demo.freemarker.base.TemplateConfigHolder>
     */
    Collection<TemplateConfigHolder> configHolders();

    /**
     * 可配置模版集生成
     *
     * @return java.util.Collection<com.quan.demo.freemarker.base.TemplateHolder>
     */
    default Collection<TemplateHolder> templateHolders() {
        Configuration config = new Configuration(Configuration.DEFAULT_INCOMPATIBLE_IMPROVEMENTS);
        config.setObjectWrapper(new DefaultObjectWrapper(Configuration.DEFAULT_INCOMPATIBLE_IMPROVEMENTS));
        final TemplateGlobalConfig globalConfig = globalConfig();
        if (globalConfig.customizeConfig() != null) {
            globalConfig.customizeConfig().accept(config);
        }
        return configHolders().stream()
                .map(configHolder -> {
                    try {
                        String templatePath = globalConfig.templatePrefixPath() + configHolder.getTemplatePath();
                        return new TemplateHolder(config.getTemplate(templatePath, configHolder.getEncoding()),
                                () -> {
                                    try {
                                        return new BufferedWriter(new OutputStreamWriter(
                                                new FileOutputStream(globalConfig.outPrefixPath() + configHolder.getOutPath()),
                                                StandardCharsets.UTF_8));
                                    } catch (FileNotFoundException e) {
                                        throw new RuntimeException(e);
                                    }
                                });
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                })
                .collect(Collectors.toList());
    }

    /**
     * 全局配置
     *
     * @return com.quan.demo.freemarker.api.TemplateGlobalConfig
     */
    default TemplateGlobalConfig globalConfig() {
        return new TemplateGlobalConfig() {
            @Override
            public Consumer<Configuration> customizeConfig() {
                return TemplateGlobalConfig.super.customizeConfig();
            }
        };
    }
}
