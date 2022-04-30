package com.quan.demo.freemarker.support.mysql;

import com.quan.demo.freemarker.api.DataModel;
import com.quan.demo.freemarker.base.DefaultClassMeta;
import com.quan.demo.freemarker.base.DefaultFieldMeta;
import com.quan.demo.freemarker.enums.InternalKeyEnum;
import com.quan.demo.freemarker.support.mysql.meta.ColumnMeta;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author Force-oneself
 * @description DriverDataModel
 * @date 2022-01-03
 */
public interface DriverDataModel extends DataModel {

    /**
     * 驱动配置
     *
     * @return 驱动配置
     */
     DriverConfigHolder driverHolder();

    /**
     * 列的相关信息
     *
     * @return 列信息
     */
    default List<ColumnMeta> columnMetas() {
        DriverConfigHolder driverHolder = driverHolder();
        return DatabaseUtils.getColumnMeta(driverHolder.getTableName(),
                () -> DatabaseUtils.getConnection(driverHolder.getUrl(), driverHolder.getUsername(), driverHolder.getPassword()));
    }

    /**
     * MySQL 驱动返回表结构模型
     *
     * @return 模型
     */
    @Override
    default Object dataModel() {
        DriverConfigHolder driverHolder = this.driverHolder();
        String packageName = driverHolder.getBasePackage();
        String tableName = driverHolder.getTableName();
        List<ColumnMeta> columns = this.columnMetas();
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

        Map<String, Object> data = new HashMap<>(16);
        data.put(InternalKeyEnum.SYSTEM_ENV.getKey(), System.getenv());
        data.put(InternalKeyEnum.SYSTEM_PROPERTIES.getKey(), System.getProperties());
        data.put(InternalKeyEnum.ENTITY.getKey(), classMeta);
        return data;
    }

}
