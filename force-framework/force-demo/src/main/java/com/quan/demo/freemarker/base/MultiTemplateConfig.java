package com.quan.demo.freemarker.base;

import com.quan.demo.freemarker.enums.StringPool;

import java.util.ArrayList;
import java.util.List;

/**
 * MultiTemplateConfig
 *
 * @author Force-oneself
 * @date 2022-05-07
 */
public class MultiTemplateConfig {

   private final List<DefaultTemplateConfig> templateConfigs = new ArrayList<>();

   private String baseOutPrefixPath = StringPool.EMPTY;
   private String baseTemplatePrefixPath = StringPool.EMPTY;

    public MultiTemplateConfig(String baseOutPrefixPath, String baseTemplatePrefixPath) {
        this.baseOutPrefixPath = baseOutPrefixPath;
        this.baseTemplatePrefixPath = baseTemplatePrefixPath;
    }

    public MultiTemplateConfig() {
    }

    public MultiTemplateConfig add(String templateName) {
        DefaultTemplateConfig config = new DefaultTemplateConfig();
        config.setTemplatePrefixPath(baseTemplatePrefixPath);
        config.setOutPrefixPath(baseOutPrefixPath);
        config.setTemplateFileName(templateName);
        this.templateConfigs.add(config);
        return this;
    }

    public List<DefaultTemplateConfig> configs() {
        return this.templateConfigs;
    }
}
