package com.quan.framework.elasticsearch.enums.search;

import lombok.Getter;
import org.springframework.util.StringUtils;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Objects;

/**
 * SearchType.java
 *
 * @author Force-oneself
 * @date 2022-04-22 23:49
 */
@Getter
public enum SearchType implements Serializable {

    /**
     *
     */
    AND(10, "and"),
    OR(20, "or"),
    ;

    private final Integer code;
    private final String name;

    SearchType(Integer code, String name) {
        this.code = code;
        this.name = name;
    }

    private static SearchType parse(Integer code) {
        if (Objects.isNull(code)) { return null; }
        return Arrays.stream(SearchType.values())
                .filter(result -> Objects.equals(result.getCode(), code))
                .findFirst().orElseThrow(() -> new RuntimeException("查找类型不存在"));
    }

    private static SearchType parse(String name) {
        if (StringUtils.isEmpty(name)) { return null; }
        return Arrays.stream(SearchType.values())
                .filter(result -> Objects.equals(result.getName(), name))
                .findFirst().orElseThrow(() -> new RuntimeException("查找类型不存在"));
    }

}
