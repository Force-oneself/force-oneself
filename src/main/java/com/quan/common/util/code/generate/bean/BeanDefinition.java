package com.quan.common.util.code.generate.bean;

import lombok.Data;

import java.util.List;
import java.util.Set;

/**
 * @Description:
 * @Author heyq
 * @Date 2021-02-08
 **/
@Data
public class BeanDefinition {

    private String entityName;
    private String packageFullName;
    private List<Field> fields;
    private Set<String> imports;

    @Data
    public static class Field {
        private String name;
        private String fullType;
        private String type;
    }
}
