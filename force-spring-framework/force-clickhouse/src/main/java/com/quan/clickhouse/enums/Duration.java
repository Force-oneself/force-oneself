package com.quan.clickhouse.enums;

public enum Duration {
    FREEHOLD(1),
    LEASEHOLD(2),
    UNKNOWN(0);

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
}
