package com.quan.demo.freemarker.support.mysql;

import java.util.List;
import java.util.Map;

/**
 * MysqlGenerator
 *
 * @author Force-oneself
 * @date 2022-04-28
 */
public class MysqlPropertiesGenerator extends MysqlGenerator {

    public MysqlPropertiesGenerator(List<SimpleMysqlTemplateConfig> templateConfigs) {
        super(templateConfigs, driverHolder());
    }

    public static DriverConfigHolder driverHolder() {
        Map<String, String> config = DomainSupport.config();
        DriverConfigHolder holder = new DriverConfigHolder();
        holder.setDriver(config.get("driver"));
        holder.setUrl(config.get("url"));
        holder.setUsername(config.get("username"));
        holder.setPassword(config.get("password"));
        holder.setBasePackage(config.get("basePackage"));
        holder.setTableName(config.get("tableName"));
        holder.setPrefix(config.get("tableNamePrefix"));
        return holder;
    }

}
