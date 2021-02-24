package com.quan.common.util.code.generate;

import com.quan.application.db.mysql.MySQLUtils;
import com.quan.common.util.StringUtils;
import com.quan.common.util.code.generate.bean.BeanDefinition;

import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @Description:
 * @Author heyq
 * @Date 2021-01-01
 **/
public class GeneratorDemo {

    public static void main(String[] args) throws SQLException {
        init("student", "com\\quan\\common\\util\\code\\generate\\Demo.java");
    }

    public static void init(String tableName, String codeFilePath) throws SQLException {
        // 获取原数据信息
        ResultSetMetaData metaData = MySQLUtils.getResultSetMetaData(tableName);
        BeanDefinition bean = new BeanDefinition();
        // 获取beanName
        String entityName = StringUtils.lineToHump(tableName);

        // 获取MySQL与Java类型映射关系
        Map<String, String> wrapper = MySQLUtils.getJavaWrapper();
        // 获取字段值
        List<BeanDefinition.Field> fields = new ArrayList<>();
        int size = metaData.getColumnCount();
        for (int i = 0; i < size; i++) {
            BeanDefinition.Field field = new BeanDefinition.Field();
            field.setName(StringUtils.lineToHump(metaData.getColumnName(i + 1)));
            field.setFullType(wrapper.get(metaData.getColumnTypeName(i + 1)));
            field.setType(field.getFullType().replaceAll("[A-Za-z]*\\.", ""));
            fields.add(field);
        }
        // 获取import的jar包
        Set<String> imports = fields.stream()
                .map(BeanDefinition.Field::getFullType)
                .filter(Objects::nonNull)
                // 默认包名无需显示引入
                .filter(type -> !type.startsWith("java.lang"))
                // 首字符为大写默认为java.lang下的类
                .filter(type -> !(type.charAt(0) >= 'A' && type.charAt(0) <= 'Z'))
                .collect(Collectors.toSet());

        String packageFullName = obtainCodePackageFullName(codeFilePath);

        bean.setPackageFullName(packageFullName);
        bean.setEntityName(entityName);
        bean.setImports(imports);
        bean.setFields(fields);
    }

    private static String obtainCodePackageFullName(String codeFilePath) {
        int indexOf = codeFilePath.lastIndexOf("\\");
        String fixedPackageName = codeFilePath.substring(0, indexOf);
        String packageFullName = fixedPackageName.replaceAll("\\\\", ".");
        return packageFullName;
    }
}
