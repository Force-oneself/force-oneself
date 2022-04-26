package com.quan.framework.elasticsearch.enums.search;

import lombok.Getter;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Objects;

/**
 * Aggregator
 *
 * @author Force-oneself
 * @date 2022-04-23
 */
@Getter
public enum Aggregator implements Serializable {

    /**
     *
     */
    COUNT(11, "count", "计数"),

    MIN(21, "min", "最小值"),
    AVERAGE(22, "average", "平均值"),
    MAX(23, "max", "最大值"),
    SUM(24, "sum", "累计值"),
    ;

    private final Integer code;
    private final String name;
    private final String description;

    Aggregator(Integer code, String name, String description) {
        this.code = code;
        this.name = name;
        this.description = description;
    }

    public static Aggregator parse(Integer code) {
        if (Objects.isNull(code)) { return null; }
        return Arrays.stream(Aggregator.values())
                .filter(result -> Objects.equals(result.getCode(), code))
                .findFirst().orElseThrow(() -> new RuntimeException("聚合器类型不存在"));
    }
}
