package com.quan.demo.freemarker.support.mysql;

import com.google.common.base.CaseFormat;
import com.quan.demo.freemarker.api.AbstractFreemarkerGenerator;
import com.quan.demo.freemarker.api.ClassMetaDefinition;
import com.quan.demo.freemarker.base.DefaultClassMeta;
import com.quan.demo.freemarker.base.DefaultFieldMeta;
import com.quan.demo.freemarker.support.mysql.meta.ColumnMeta;
import freemarker.template.Configuration;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * MysqlGenerator
 *
 * @author Force-oneself
 * @date 2022-04-28
 */
public class MysqlGenerator extends AbstractFreemarkerGenerator<SimpleMysqlTemplateConfig> {

    protected final DriverConfigHolder holder;

    public MysqlGenerator(List<SimpleMysqlTemplateConfig> templateConfigs, DriverConfigHolder holder) {
        super(templateConfigs);
        String tableName = holder.getTableName();
        String prefix = holder.getPrefix();
        int index = tableName.indexOf(prefix);
        String fileName = index < 0 ? tableName : tableName.substring(index + prefix.length());
        fileName = CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, fileName);
        holder.setFileName(fileName);
        this.holder = holder;
    }

    @Override
    protected ClassMetaDefinition metaDefinition() {
        DriverConfigHolder driverHolder = this.holder;
        String packageName = driverHolder.getBasePackage();
        String tableName = driverHolder.getTableName();
        List<ColumnMeta> columns = DatabaseUtils.getColumnMeta(tableName,
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
        classMeta.setType(holder.getFileName());
        DomainSupport.processMeta(classMeta, fieldMetas);
        return classMeta;
    }

    @Override
    public void templateConfigCustom(Configuration config, SimpleMysqlTemplateConfig templateConfig) {
        templateConfig.setFileName(holder.getFileName().concat(templateConfig.getTemplateFileNameSuffix()));
    }
}
