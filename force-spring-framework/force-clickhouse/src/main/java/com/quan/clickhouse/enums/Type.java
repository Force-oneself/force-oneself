package com.quan.clickhouse.enums;

import com.quan.clickhouse.entity.PricePaid;

// 枚举类型
public enum Type {
    TERRACED(1),
    SEMI_DETACHED(2),
    DETACHED(3),
    FLAT(4),
    OTHER(0);

    private final int value;

    Type(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static Type fromValue(int value) {
        for (Type type : values()) {
            if (type.getValue() == value) {
                return type;
            }
        }
        return OTHER;
    }
}
