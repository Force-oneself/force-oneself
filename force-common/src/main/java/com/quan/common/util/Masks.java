package com.quan.common.util;

import java.util.Arrays;
import java.util.stream.Stream;

/**
 * @author Force-oneself
 * @Description Masks 掩码工具处理类
 * @date 2021-09-24
 */
public final class Masks {

    /**
     * 计算相应位置掩码
     *
     * @param n 坐标 0开始
     * @return 掩码
     */
    public static long mask(int n) {
        if (n >= 63 || n < 0) {
            throw new RuntimeException("n must between 0 and 62");
        }
        return 1L << n;
    }

    /**
     * 计算多个掩码
     *
     * @param numbs 位置坐标
     * @return 掩码数组
     */
    public static long[] maskArray(int... numbs) {
        if (numbs == null || numbs.length == 0) {
            throw new RuntimeException("numbs must not null");
        }
        int len = numbs.length;
        long[] arr = new long[len];
        for (int i = 0; i < len; i++) {
            arr[i] = mask(numbs[i]);
        }
        return arr;
    }

    /**
     * 计算多个位置掩码
     *
     * @param numbs 坐标数组
     * @return 掩码
     */
    public static long maskMulti(int... numbs) {
        if (numbs == null || numbs.length == 0) {
            throw new RuntimeException("numbs must not null");
        }
        long mask = 0;
        for (int num : numbs) {
            mask += mask(num);
        }
        return mask;
    }

    /**
     * 数值是否包含掩码
     *
     * @param num  数值
     * @param mask 掩码
     * @return 是否
     */
    public static boolean in(long num, long mask) {
        long and = num & mask;
        return isMask(mask) && and > 0 && and <= mask;
    }

    /**
     * 数值是否等于掩码
     *
     * @param num  数值
     * @param mask 掩码
     * @return 是否
     */
    public static boolean eq(long num, long mask) {
        return isMask(mask) && (num & mask) == mask;
    }

    /**
     * 数值不等于掩码
     *
     * @param num  数值
     * @param mask 掩码
     * @return 是否
     */
    public static boolean non(long num, long mask) {
        return isMask(mask) && (num & mask) == 0;
    }

    /**
     * 掩码数值必须大于0
     *
     * @param mask 掩码
     * @return 是否
     */
    private static boolean isMask(long mask) {
        return mask > 0;
    }

    /**
     * 数值是否全匹配下标数组
     *
     * @param num   数值
     * @param index 下标数组
     * @return 是否
     */
    public static boolean eqAll(long num, int... index) {
        return Stream.of(index)
                .flatMapToInt(Arrays::stream)
                .allMatch(mask -> eq(num, mask(mask)));
    }

    /**
     * 数值是否不匹配下标数组
     *
     * @param num   数值
     * @param index 下标数组
     * @return 是否
     */
    public static boolean nonAll(long num, int... index) {
        return Stream.of(index)
                .flatMapToInt(Arrays::stream)
                .allMatch(mask -> non(num, mask(mask)));
    }

    /**
     * 数值是否包含下标数组
     *
     * @param num   数值
     * @param index 下标数组
     * @return 是否
     */
    public static boolean in(long num, int... index) {
        return Stream.of(index)
                .flatMapToInt(Arrays::stream)
                .anyMatch(mask -> in(num, mask(mask)));
    }

    /**
     * 数值是否包含多位置掩码
     *
     * @param num   数值
     * @param index 下标数组
     * @return 是否
     */
    public static boolean inMulti(long num, int... index) {
        return in(num, maskMulti(index));
    }

    /**
     * 数值是否全匹配多位置掩码
     *
     * @param num   数值
     * @param index 下标数组
     * @return 是否
     */
    public static boolean eqMulti(long num, int... index) {
        return eq(num, maskMulti(index));
    }

    /**
     * 数值是否不匹配多位置掩码
     *
     * @param num   数值
     * @param index 下标数组
     * @return 是否
     */
    public static boolean nonMulti(long num, int... index) {
        return non(num, maskMulti(index));
    }

}
