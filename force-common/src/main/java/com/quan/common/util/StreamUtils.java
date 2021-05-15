package com.quan.common.util;

import java.util.stream.Stream;

/**
 * @author heyq
 * <p>
 * created by heyq
 * Date: 2019/12/15
 */
public final class StreamUtils {

    /**
     * 创建元素为给定对象的流
     *
     * @param object 给定填充对象
     * @return 包含给定对象并不限长度的流
     */
    public static <T> Stream<T> create(T object) {
        return Stream.iterate(object, t -> t);
    }

    /**
     * 创建元素为给定对象且指定长度的流
     *
     * @param object 给定填充对象
     * @param length 给定流长度
     * @return 包含给定对象并指定长度的流
     */
    public static <T> Stream<T> create(T object, int length) {
        return length <= 0 ? Stream.empty() : Stream.iterate(object, t -> t).limit(length);
    }

    /**
     * 将开始到结束的整数序列转为流
     *
     * @param start 开始整数
     * @param end   结束整数
     * @return 从开始整数到结束整理的数字序列流
     */
    public static Stream<Integer> createIntSequence(int start, int end) {
        return start > end ? Stream.empty() : Stream.iterate(start, t -> t + 1).limit(end - start + 1L);
    }

    /**
     * 生成长度为length的自然数序列流
     *
     * @param length 给定长度
     * @return 从0开始指定长度的数字序列流
     */
    public static Stream<Integer> createNatureIntSequence(int length) {
        return length <= 0 ? Stream.empty() : Stream.iterate(0, t -> t + 1).limit(length);
    }

    /**
     * 生成长度为length的正整数数序列流
     *
     * @param length 给定长度
     * @return 从1开始指定长度的数字序列流
     */
    public static Stream<Integer> createPositiveIntSequence(int length) {
        return length <= 0 ? Stream.empty() : Stream.iterate(1, t -> t + 1).limit(length);
    }

    private StreamUtils() {
    }

}

