package com.quan.clickhouse.enums;

public enum Duration {
    UNKNOWN(0),
    FREEHOLD(1),
    LEASEHOLD(2),
    ;

    private final int value;

    Duration(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static Duration fromValue(int value) {
        for (Duration duration : values()) {
            if (duration.getValue() == value) {
                return duration;
            }
        }
        return UNKNOWN;
    }

    public int getCode() {
        return this.ordinal();
    }

}
