package com.quan.demo.framework.spring.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import lombok.extern.slf4j.Slf4j;

import java.text.SimpleDateFormat;

/**
 * @Description: class Objs
 * @Author Force丶Oneself
 * @Date 2021-05-28
 **/
@Slf4j
public final class Objs {

    public final static ObjectMapper MAPPER;

    static {
        MAPPER = new ObjectMapper();
        // 忽略大小写
        MAPPER.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true);
        // 取消默认转换timestamps
        MAPPER.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        // 忽略空Bean转json的错误
        MAPPER.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        // 同意日期格式为："yyyy-MM-dd HH:mm:ss"
        MAPPER.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
        // 忽略 在json字符串中存在，但是java对象中不存在对应属性的情况，防止错误
        MAPPER.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        // 空字符作为空对象处理
        MAPPER.configure(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT, true);
        // 单个对象值处理成集合
        MAPPER.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
    }

    public static String prettyPrint(Object value) {
        try {
            return MAPPER.writeValueAsString(value);
        } catch (JsonProcessingException e) {
            log.error("对象打印失败", e);
        }
        return "null";
//        return JSON.toJSONString(value, SerializerFeature.PrettyFormat);
    }

    private Objs() {
    }

}
