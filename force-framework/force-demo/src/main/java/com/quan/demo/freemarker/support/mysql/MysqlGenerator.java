package com.quan.demo.freemarker.support.mysql;

import com.quan.demo.freemarker.api.ConfigurableFreemarkerGenerator;
import freemarker.template.Configuration;

import java.util.List;
import java.util.Map;

/**
 * MysqlGenerator
 *
 * @author Force-oneself
 * @date 2022-04-28
 */
public class MysqlGenerator implements DriverDataModel, ConfigurableFreemarkerGenerator<SimpleMysqlTemplateConfig> {

    private List<SimpleMysqlTemplateConfig> templateConfigs;

    protected String tableName;

    @Override
    public DriverConfigHolder driverHolder() {
        Map<String, String> config = DomainSupport.config();
        DriverConfigHolder holder = new DriverConfigHolder();
        holder.setDriver(config.get("driver"));
        holder.setUrl(config.get("url"));
        holder.setUsername(config.get("username"));
        holder.setPassword(config.get("password"));
        holder.setBasePackage(config.get("basePackage"));
        holder.setTableName(config.get("tableName"));
        this.tableName = holder.getTableName();
        return holder;
    }

    @Override
    public List<SimpleMysqlTemplateConfig> templateConfig() {
        return this.templateConfigs;
    }

    @Override
    public void templateConfigCustom(Configuration config, SimpleMysqlTemplateConfig templateConfig) {
        templateConfig.setFileName(tableName);
    }

    public void setTemplateConfigs(List<SimpleMysqlTemplateConfig> templateConfigs) {
        this.templateConfigs = templateConfigs;
    }



}
