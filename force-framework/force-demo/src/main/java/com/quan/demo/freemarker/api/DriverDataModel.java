package com.quan.demo.freemarker.api;

import com.quan.demo.freemarker.DatabaseUtils;
import com.quan.demo.freemarker.base.DriverConfigHolder;
import com.quan.demo.freemarker.base.JavaDomainModel;
import com.quan.demo.freemarker.enums.InternalKeyEnum;
import com.quan.demo.freemarker.meta.ColumnMeta;
import com.quan.demo.freemarker.support.mysql.DomainSupport;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author Force-oneself
 * @description JsonDataModel
 * @date 2022-01-03
 */
public interface DriverDataModel extends DataModel {

    default DriverConfigHolder driverHolder() {
        Map<String, String> config = DomainSupport.config();
        DriverConfigHolder holder = new DriverConfigHolder();
        holder.setDriver(config.get("driver"));
        holder.setUrl(config.get("url"));
        holder.setUsername(config.get("username"));
        holder.setPassword(config.get("password"));
        holder.setBasePackage(config.get("basePackage"));
        holder.setTableName(config.get("tableName"));
        return holder;
    }

    default List<ColumnMeta> columnMetas() {
        DriverConfigHolder driverHolder = driverHolder();
        return DatabaseUtils.getColumnMeta(driverHolder.getTableName(),
                () -> DatabaseUtils.getConnection(driverHolder.getUrl(), driverHolder.getUsername(), driverHolder.getPassword()));
    }

    @Override
    default Object dataModel() {
        DriverConfigHolder driverHolder = this.driverHolder();
        String packageName = driverHolder.getBasePackage();
        String tableName = driverHolder.getTableName();
        List<ColumnMeta> columns = this.columnMetas();
        JavaDomainModel domain = new JavaDomainModel();
        List<JavaDomainModel> feilds = columns.stream()
                .map(column -> {
                    JavaDomainModel model = new JavaDomainModel();
                    model.setClassName( column.name());
                    model.setName(column.name());
                    model.setClassType(column.typeName());
                    model.setDescribe(column.comment());
                    return model;
                })
                .collect(Collectors.toList());
        domain.setFields(feilds);
        domain.setPkg(packageName);
        domain.setClassName(tableName);
        domain.setClassType(domain.getPkg() + "." + domain.getClassName());
        domain.setName(tableName);
        DomainSupport.processModel(feilds);
        DomainSupport.processModel(domain);

        Map<String, Object> data = new HashMap<>();
        data.put(InternalKeyEnum.SYSTEM.name(), System.getenv());
        data.put(InternalKeyEnum.CLASSPATH.name(), System.getProperties());
        data.put(InternalKeyEnum.ENTITY.name(), domain);
        return data;
    }


}
