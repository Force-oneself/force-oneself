package com.quan.framework.elasticsearch.enums.search;

import lombok.Getter;
import org.springframework.util.StringUtils;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Objects;

/**
 * ConditionType.java
 *
 * @author Force-oneself
 * @date 2022-04-22 23:49
 */
@Getter
public enum ConditionType implements Serializable {

    /**
     *
     */
    SMOOTH(10, "平滑条件", "直接条件"),
    NESTED(20, "嵌套条件", "嵌套条件语句"),

    ;

    private final Integer code;
    private final String name;
    private final String description;

    ConditionType(Integer code, String name, String description) {
        this.code = code;
        this.name = name;
        this.description = description;
    }

    private static ConditionType parse(Integer code) {
        if (Objects.isNull(code)) { return null; }
        return Arrays.stream(ConditionType.values())
                .filter(result -> Objects.equals(result.getCode(), code))
                .findFirst().orElseThrow(() -> new RuntimeException("条件类型不存在"));
    }

    private static ConditionType parse(String name) {
        if (StringUtils.isEmpty(name)) { return null; }
        return Arrays.stream(ConditionType.values())
                .filter(result -> Objects.equals(result.getName(), name))
                .findFirst().orElseThrow(() -> new RuntimeException("条件类型不存在"));
    }

}
