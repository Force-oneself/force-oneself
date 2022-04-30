package com.quan.demo.freemarker.support.mysql;

import com.google.common.base.CaseFormat;
import com.quan.demo.freemarker.base.DefaultClassMeta;
import com.quan.demo.freemarker.base.DefaultFieldMeta;
import com.quan.demo.freemarker.base.JavaDomainModel;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author Force-oneself
 * @description DomainSupport
 * @date 2022-03-18
 */
public class DomainSupport {

    private final static List<DomainProcessor> PROCESSORS = new ArrayList<>();
    public static final String JDK_PKG = "java.lang";

    private final static Map<String, String> CONVERTER;

    private final static Map<String, String> DRIVER_CONFIG;

    static {

        Properties props = new Properties();
        // 获取properties文件的流对象
        InputStream in = DomainSupport.class.getClassLoader().getResourceAsStream("mysql-java-wrapper.properties");
        try {
            props.load(in);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        CONVERTER = props.entrySet().stream()
                .collect(Collectors.toMap(obj -> obj.getKey().toString(), obj -> obj.getValue().toString()));

        PROCESSORS.add(model -> {
            // 下划线转驼峰: test_data ==> TestData
            model.setClassName(CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, model.getClassName()));
            // 下划线转驼峰: test_data ==> testData
            model.setName(CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, model.getName()));
            model.setClassType(CONVERTER.getOrDefault(model.getClassType(), model.getClassType()));
        });

        Properties config = new Properties();
        // 获取properties文件的流对象
        InputStream configIn = DomainSupport.class.getClassLoader().getResourceAsStream("freemarker.properties");
        try {
            config.load(configIn);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        DRIVER_CONFIG = config.entrySet().stream()
                .collect(Collectors.toMap(obj -> obj.getKey().toString(), obj -> obj.getValue().toString()));
    }

    public static void processModel(JavaDomainModel model) {
        PROCESSORS.forEach(processor -> processor.process(model));
    }

    public static void processModel(List<JavaDomainModel> models) {
        PROCESSORS.forEach(processor -> models.forEach(processor::process));
    }

    public static Map<String, String> config() {
        return DRIVER_CONFIG;
    }

    public static void processMeta(DefaultClassMeta classMeta, Set<DefaultFieldMeta> fieldMetas) {
        Set<String> imports = new HashSet<>();
        fieldMetas.forEach(field -> {
            // 数据库类型与java类型的映射
            String reference = CONVERTER.getOrDefault(field.getType(), field.getType());
            if (reference.startsWith(JDK_PKG)) {
                field.setPkg(JDK_PKG);
                reference = reference.substring(JDK_PKG.length() + 1);
            } else {
                int endIndex = reference.lastIndexOf('.');
                if (endIndex > -1) {
                    imports.add(reference);
                    field.setPkg(reference.substring(0, endIndex));
                    reference = reference.substring(endIndex + 1);
                } else {
                    field.setPkg(JDK_PKG);
                }
            }
            field.setType(reference);
            // 下划线转驼峰: test_data ==> testData
            field.setName(CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, field.getName()));
            // 下划线转驼峰: test_data ==> TestData
            field.setType(CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, field.getType()));
        });
        classMeta.setImports(imports);
        // 下划线转驼峰: test_data ==> TestData
        classMeta.setType(CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, classMeta.getType()));
    }
}
