package com.quan.tools.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.quan.tools.jackson.JacksonObjectMapper;

/**
 * json数据格式化工具类
 *
 * @author Force-oneself
 * @date 2022-10-18
 */
public class JsonUtils {

    private static final ObjectMapper INSTANCE = new JacksonObjectMapper();

    private JsonUtils() {
    }

    /**
     * json反序列化成对象
     *
     * @param json  json字符串
     * @param clazz 对象class
     * @return /
     */
    public static <T> T toObject(String json, Class<T> clazz) {
        try {
            return getInstance().readValue(json, clazz);
        } catch (Exception e) {
            throw new RuntimeException("Error converting JSON to object", e);
        }
    }


    /**
     * 将对象序列化成json字符串
     *
     * @param value javaBean
     * @return jsonString json字符串
     */
    public static <T> String toJson(T value) {
        try {
            return getInstance().writeValueAsString(value);
        } catch (Exception e) {
            throw new RuntimeException("Error converting JSON to object", e);
        }
    }

    /**
     * 实现JSON序列化的 jackson 单例对象
     *
     * @return /
     */
    private static ObjectMapper getInstance() {
        return INSTANCE;
    }
}
