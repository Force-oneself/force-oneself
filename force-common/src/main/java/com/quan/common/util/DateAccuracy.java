package com.quan.common.util;

import java.io.Serializable;

/**
 * 日期时间精度枚举
 * 可用名称TimeUnit
 *
 * @author heyq
 * <p>
 * created by heyq
 * Date: 2019/12/17
 */
public enum DateAccuracy implements Serializable {

    YEAR(1, "年", ""),
    MONTH(2, "月", ""),
    WEEK(3, "周", ""),
    DAY(4, "日", ""),
    HOUR(5, "时", ""),
    MINUTE(6, "分", ""),
    SECOND(7, "秒", ""),
    MILLISECOND(8, "毫秒", ""),
    MICROSECOND(9, "微秒", ""),
    NANOSECOND(10, "纳秒", ""),
    ;

    private Integer code;
    private String name;
    private String description;

    DateAccuracy(Integer code, String name, String description) {
        this.code = code;
        this.name = name;
        this.description = description;
    }

    public Integer getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

}

