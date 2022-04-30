package com.quan.demo.freemarker.support.mysql.meta;

import com.quan.demo.freemarker.api.SourceTemplateConfig;

/**
 * MysqlTemplateConfig
 *
 * @author Force-oneself
 * @date 2022-04-30
 */
public interface MysqlTemplateConfig extends SourceTemplateConfig {

    /**
     * 设置文件名
     *
     * @param fileName fileName
     */
    void setFileName(String fileName);
}
