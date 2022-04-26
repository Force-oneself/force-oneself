package com.quan.framework.elasticsearch.enums.search;

import lombok.Getter;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Objects;

/**
 * MatchType.java
 *
 * @author Force-oneself
 * @date 2022-04-22 23:49
 */
@Getter
public enum MatchType implements Serializable {

    EQUAL(10, "="),
    NOT_EQUAL(11, "!="),

    GREATER_THAN(20, ">"),
    GREATER_EQUAL(21, ">="),
    LESS_THAN(22, "<"),
    LESS_EQUAL(23, "<="),

    LIKE(30, "like"),
    NOT_LIKE(31, "not like"),
    LIKE_LEFT(32, "like left"),
    LIKE_RIGHT(33, "like right"),

    IN(40, "in"),
    NOT_IN(41, "not in"),

    BETWEEN(50, "between"),
    NOT_BETWEEN(51, "not between"),

    IS_NULL(60, "is null"),
    NOT_NULL(61, "not null"),

    ;

    private final Integer code;
    private final String name;

    MatchType(Integer code, String name) {
        this.code = code;
        this.name = name;
    }

    private static MatchType parse(Integer code) {
        if (Objects.isNull(code)) { return null; }
        return Arrays.stream(MatchType.values())
                .filter(result -> Objects.equals(result.getCode(), code))
                .findFirst().orElseThrow(() -> new RuntimeException("匹配类型不存在"));
    }

    private static MatchType parse(String name) {
        if (Objects.isNull(name)) { return null; }
        return Arrays.stream(MatchType.values())
                .filter(result -> Objects.equals(result.getName(), name))
                .findFirst().orElseThrow(() -> new RuntimeException("匹配类型不存在"));
    }

}
