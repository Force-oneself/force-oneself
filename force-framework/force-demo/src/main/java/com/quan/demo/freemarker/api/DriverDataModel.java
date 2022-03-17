package com.quan.demo.freemarker.api;

import com.quan.demo.freemarker.DatabaseUtils;
import com.quan.demo.freemarker.base.JavaDomainModel;
import com.quan.demo.freemarker.enums.InternalKeyEnum;
import com.quan.demo.freemarker.meta.ColumnMeta;

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

    @Override
    default Object dataModel() {
        String tableName = "";
        List<ColumnMeta> columns = DatabaseUtils.getColumnMeta(tableName);
        JavaDomainModel domain = new JavaDomainModel();
        List<JavaDomainModel> feilds = columns.stream()
                .map(column -> {
                    JavaDomainModel model = new JavaDomainModel();
                    model.setClassName(column.name());
                    model.setClassType(column.typeName());
                    model.setDescribe(column.comment());
                    return model;
                })
                .collect(Collectors.toList());
        domain.setFields(feilds);
        domain.setClassName(tableName);


        Map<String, Object> data = new HashMap<>();
        data.put(InternalKeyEnum.SYSTEM.name(), System.getenv());
        data.put(InternalKeyEnum.CLASSPATH.name(), System.getProperties());
        data.put(InternalKeyEnum.ENTITY.name(), domain);
        return data;
    }
}
