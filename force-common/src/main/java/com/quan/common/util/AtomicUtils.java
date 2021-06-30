package com.quan.common.util;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Description:
 * @Author heyq
 * @Date 2021-03-29
 **/
public class AtomicUtils {

    public static AtomicInteger atomic = new AtomicInteger(1);

    public static void print(String message) {
        System.err.println(atomic.getAndIncrement() + " ===> " + message);
    }
}
