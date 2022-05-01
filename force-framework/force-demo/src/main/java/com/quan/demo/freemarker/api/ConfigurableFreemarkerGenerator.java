package com.quan.demo.freemarker.api;

import com.quan.demo.freemarker.base.DefaultTemplateBear;
import freemarker.cache.ClassTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;

import java.util.Collection;
import java.util.stream.Collectors;

/**
 * @author Force-oneself
 * @description ConfigurableFreemarkerGenerator
 * @date 2022-03-17
 */
public interface ConfigurableFreemarkerGenerator<T extends TemplateConfig> extends FreemarkerGenerator {

    /**
     * 模板配置集
     *
     * @return Collection<TemplateConfig>
     */
    Collection<T> templateConfig();

    /**
     * 可配置模版集生成
     *
     * @return return
     */
    @Override
    default Collection<TemplateBear> templateBears() {
        Configuration config = new Configuration(Configuration.DEFAULT_INCOMPATIBLE_IMPROVEMENTS);
        config.setObjectWrapper(new DefaultObjectWrapper(Configuration.DEFAULT_INCOMPATIBLE_IMPROVEMENTS));
        this.configurationCustom(config);
        return this.templateConfig().stream()
                .peek(templateConfig -> templateConfigCustom(config, templateConfig))
                .map(templateConfig -> templateBear(config, templateConfig))
                .collect(Collectors.toList());
    }

    /**
     * 配置每个模版配置
     *
     * @param config         config
     * @param templateConfig templateConfig
     */
    default void templateConfigCustom(Configuration config, T templateConfig) {

    }

    /**
     * ignore
     *
     * @param config         freemarker Configuration
     * @param templateConfig TemplateConfig
     * @return TemplateBear
     */
    default TemplateBear templateBear(Configuration config, T templateConfig) {
        return new DefaultTemplateBear(config, templateConfig);
    }

    /**
     * Configuration 自定义配置可以实现获取外部模版类加载器的接口
     *
     * @param configuration configuration
     */
    default void configurationCustom(Configuration configuration) {
        configuration.setTemplateLoader(new ClassTemplateLoader(ConfigurableFreemarkerGenerator.class, "/ftl"));
    }
}
