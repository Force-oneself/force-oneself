package com.quan.common.util;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

/**
 * @Description:
 * @Author heyq
 * @Date 2021-03-29
 **/
public class AtomicUtils {

    public static AtomicInteger atomic = new AtomicInteger(1);
    public static Map<Integer, String> step = new LinkedHashMap<>(32);

    public static void print(String message) {
        step.put(atomic.getAndIncrement(), message);
    }

    public static void stop() {
        System.err.println(step.entrySet().stream()
                .map(entry -> String.format("%d : %s", entry.getKey(), entry.getValue()))
                .collect(Collectors.joining("\n"))
        );
    }
}
