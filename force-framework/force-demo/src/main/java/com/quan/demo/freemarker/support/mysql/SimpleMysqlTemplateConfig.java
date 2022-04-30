package com.quan.demo.freemarker.support.mysql;

import com.quan.demo.freemarker.base.SimpleTemplateConfig;
import com.quan.demo.freemarker.support.mysql.meta.MysqlTemplateConfig;

/**
 * @author Force-oneself
 * @description TemplateConfig
 * @date 2022-03-17
 */
public class SimpleMysqlTemplateConfig extends SimpleTemplateConfig implements MysqlTemplateConfig {

    @Override
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}

