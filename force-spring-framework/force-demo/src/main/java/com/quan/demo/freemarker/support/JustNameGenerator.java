package com.quan.demo.freemarker.support;

import com.quan.demo.freemarker.api.AbstractFreemarkerGenerator;
import com.quan.demo.freemarker.api.ClassMetaDefinition;
import com.quan.demo.freemarker.base.DefaultClassMeta;
import com.quan.demo.freemarker.base.DefaultTemplateConfig;
import com.quan.demo.freemarker.enums.StringPool;
import freemarker.template.Configuration;

import java.util.List;

/**
 * JustNameGenerator
 *
 * @author Force-oneself
 * @date 2022-05-05
 */
public class JustNameGenerator extends AbstractFreemarkerGenerator<DefaultTemplateConfig> {

    private String className = StringPool.EMPTY;
    private String pkg = StringPool.EMPTY;

    public JustNameGenerator(List<DefaultTemplateConfig> templateConfigs) {
        super(templateConfigs);
    }

    @Override
    protected ClassMetaDefinition metaDefinition() {
        DefaultClassMeta classMeta = new DefaultClassMeta();
        classMeta.setPkg(pkg);
        classMeta.setType(className);
        return classMeta;
    }

    @Override
    public void templateConfigCustom(Configuration config, DefaultTemplateConfig templateConfig) {
        templateConfig.setFileName(className.concat(templateConfig.getTemplateFileNameSuffix()));
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getPkg() {
        return pkg;
    }

    public void setPkg(String pkg) {
        this.pkg = pkg;
    }
}
