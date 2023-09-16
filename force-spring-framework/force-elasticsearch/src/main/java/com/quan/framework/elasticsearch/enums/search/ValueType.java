package com.quan.framework.elasticsearch.enums.search;

import lombok.Getter;
import org.springframework.util.StringUtils;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;
import java.util.Objects;

/**
 * 数据类型
 *
 * @author wubingtao
 * <p>
 * created by wubingtao
 * Date: 2020/3/23
 */
@Getter
public enum ValueType implements Serializable {

    /**
     *
     */
    KEYWORD(0, "keyword", String.class, "keyword"),
    TEXT(1, "text", String.class, "text"),
    INTEGER(2, "integer", Integer.class, "integer"),
    LONG(3, "long", Long.class, "long"),
    FLOAT(4, "float", Float.class, "float"),
    DOUBLE(5, "double", Double.class, "double"),
    ENUM(6, "enum", String.class, "keyword"),
    DATE(7, "date", Date.class, "date"),
    BOOLEAN(8, "boolean", Boolean.class, "boolean"),
    IP(9, "ip", String.class, "keyword"),

    QR_CODE(9, "qr_code", String.class, "text"),

    OBJECT(10, "object", Object.class, "nested"),
    ;

    private final Integer code;
    private final String name;
    private final Class<?> javaType;
    private final String esType;

    ValueType(Integer code, String name, Class<?> javaType, String esType) {
        this.code = code;
        this.name = name;
        this.javaType = javaType;
        this.esType = esType;
    }

    public static ValueType parse(String name) {
        if (StringUtils.isEmpty(name)) { return null; }
        return Arrays.stream(ValueType.values())
                .filter(result -> Objects.equals(result.getName(), name))
                .findFirst().orElseThrow(() -> new RuntimeException("数据类型不存在"));
    }

    public static ValueType parse(Class<?> clazz) {
        if (Objects.isNull(clazz)) { return null; }
        return Arrays.stream(ValueType.values())
                .filter(result -> Objects.equals(result.getJavaType(), clazz))
                .findFirst().orElseThrow(() -> new RuntimeException("数据类型不存在"));
    }

    public static boolean isAvailableType(Class<?> clazz) {
        return Arrays.stream(ValueType.values()).map(ValueType::getJavaType).anyMatch(clazz::equals);
    }

}
