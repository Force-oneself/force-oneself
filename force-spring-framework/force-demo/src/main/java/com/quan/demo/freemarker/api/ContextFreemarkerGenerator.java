package com.quan.demo.freemarker.api;

import freemarker.cache.ClassTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;

/**
 * ContextFreemarkerGenerator
 *
 * @author Force-oneself
 * @date 2022-04-29
 */
public interface ContextFreemarkerGenerator extends FreemarkerGenerator {

    Logger log = LoggerFactory.getLogger(ContextFreemarkerGenerator.class);

    /**
     * 默认实现
     */
    @Override
    default void generate() {
        context();
        FreemarkerGenerator.super.generate();
    }

    /**
     * 上下文
     *
     * @return 上下文
     */
    Context context();

    /**
     * 可配置模版集生成
     *
     * @return 模版
     */
    @Override
    default Collection<TemplateBear> templateBears() {
        Configuration config = new Configuration(Configuration.DEFAULT_INCOMPATIBLE_IMPROVEMENTS);
        config.setObjectWrapper(new DefaultObjectWrapper(Configuration.DEFAULT_INCOMPATIBLE_IMPROVEMENTS));
        this.configurationCustom(config);
        return this.customBears(config);
    }

    /**
     * 自定义生成模版承载实现
     *
     * @param config freemarker模版配置类
     * @return 模版载体
     */
    Collection<TemplateBear> customBears(Configuration config);

    /**
     * Configuration 自定义配置可以实现获取外部模版类加载器的接口
     *
     * @param configuration configuration
     */
    default void configurationCustom(Configuration configuration) {
        configuration.setTemplateLoader(new ClassTemplateLoader(ConfigurableFreemarkerGenerator.class, "/ftl"));
    }
}
