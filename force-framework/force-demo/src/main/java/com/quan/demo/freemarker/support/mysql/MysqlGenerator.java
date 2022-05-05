package com.quan.demo.freemarker.support.mysql;

import com.quan.demo.freemarker.api.ClassMetaDefinition;
import com.quan.demo.freemarker.api.AbstractFreemarkerGenerator;
import com.quan.demo.freemarker.base.DefaultClassMeta;
import com.quan.demo.freemarker.base.DefaultFieldMeta;
import com.quan.demo.freemarker.enums.StringPool;
import com.quan.demo.freemarker.support.mysql.meta.ColumnMeta;
import freemarker.template.Configuration;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * MysqlGenerator
 *
 * @author Force-oneself
 * @date 2022-04-28
 */
public class MysqlGenerator extends AbstractFreemarkerGenerator<SimpleMysqlTemplateConfig> {

    protected String tableName;

    protected String prefix = StringPool.EMPTY;

    protected String fileName = StringPool.EMPTY;

    public MysqlGenerator(List<SimpleMysqlTemplateConfig> templateConfigs) {
        super(templateConfigs);
    }

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
        this.prefix = config.get("tableNamePrefix");
        int index = tableName.indexOf(prefix);
        if (index < 0) {
            this.fileName = this.tableName;
        } else {
            this.fileName = tableName.substring(index + prefix.length());
        }
        return holder;
    }

    @Override
    protected ClassMetaDefinition metaDefinition() {
        DriverConfigHolder driverHolder = this.driverHolder();
        String packageName = driverHolder.getBasePackage();
        String tableName = driverHolder.getTableName();
        List<ColumnMeta> columns = DatabaseUtils.getColumnMeta(driverHolder.getTableName(),
                () -> DatabaseUtils.getConnection(driverHolder.getUrl(), driverHolder.getUsername(), driverHolder.getPassword()));
        DefaultClassMeta classMeta = new DefaultClassMeta();
        Set<DefaultFieldMeta> fieldMetas = columns.stream()
                .map(columnMeta -> {
                    DefaultFieldMeta fieldMeta = new DefaultFieldMeta();
                    fieldMeta.setName(columnMeta.name());
                    fieldMeta.setDescribe(columnMeta.comment());
                    fieldMeta.setType(columnMeta.typeName());
                    return fieldMeta;
                })
                .collect(Collectors.toSet());
        classMeta.setFields(fieldMetas);
        classMeta.setPkg(packageName);
        classMeta.setType(tableName);
        DomainSupport.processMeta(classMeta, fieldMetas);
        return classMeta;
    }

    @Override
    public void templateConfigCustom(Configuration config, SimpleMysqlTemplateConfig templateConfig) {
        templateConfig.setFileName(fileName);
    }
}
