package com.quan.clickhouse.enums;

public enum Type {
    OTHER(0),
    TERRACED(1),
    SEMI_DETACHED(2),
    DETACHED(3),
    FLAT(4),
    ;

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

    public int getCode() {
        return this.ordinal();
    }
}
