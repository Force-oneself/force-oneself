package com.quan.demo.freemarker.support.mysql;

import com.google.common.base.CaseFormat;
import com.quan.demo.freemarker.base.JavaDomainModel;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.stream.Collectors;

/**
 * @author Force-oneself
 * @description DomainSupport
 * @date 2022-03-18
 */
public class DomainSupport {

    private final static List<DomainProcessor> PROCESSORS = new ArrayList<>();

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
            model.setClassName(CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, model.getName()));
            // 下划线转驼峰: test_data ==> testData
            model.setName(CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, model.getName()));
            model.setClassType(CONVERTER.getOrDefault(model.getClassName(), model.getClassName()));
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
}
