package com.quan.demo.framework.spring.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

/**
 * @Description: class Objs
 * @Author Force丶Oneself
 * @Date 2021-05-28
 **/
@Slf4j
public final class Objs {

    public static ObjectMapper PRETTY_PRINTER = new ObjectMapper();

    public static String prettyPrint(Object value) {
        String print = "";
        try {
            return PRETTY_PRINTER.writeValueAsString(value);
        } catch (JsonProcessingException e) {
            log.info("对象格式化打印异常", e);
        }
        return print;
    }
    
    private Objs() {}

}
