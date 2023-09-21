package com.quan.tools.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.quan.tools.Exceptions;
import com.quan.tools.Toolkit;
import com.quan.tools.constant.StringPool;
import com.quan.tools.jackson.JacksonObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * json数据格式化工具类
 *
 * @author Force-oneself
 * @date 2022-10-18
 */
public class JsonUtils {

    private final static Logger LOGGER = LoggerFactory.getLogger(JsonUtils.class);

    private JsonUtils() {
    }


    /**
     * 将对象序列化成json字符串
     *
     * @param value javaBean
     * @return jsonString json字符串
     */
    public static <T> String json(T value) {
        try {
            return getInstance().writeValueAsString(value);
        } catch (Exception e) {
            throw Exceptions.unchecked(e);
        }
    }

    /**
     * 将对象序列化成 json byte 数组
     *
     * @param object javaBean
     * @return jsonString json字符串
     */
    public static <T> byte[] bytes(T object) {
        try {
            return getInstance().writeValueAsBytes(object);
        } catch (JsonProcessingException e) {
            throw Exceptions.unchecked(e);
        }
    }

    /**
     * 将json反序列化成List对象
     *
     * @param content      content
     * @param valueTypeRef class
     * @param <T>          T 泛型标记
     * @return List<T>
     */
    public static <T> List<T> parseArray(String content, Class<T> valueTypeRef) {
        if (!StringUtils.startsWithIgnoreCase(content, StringPool.LEFT_SQ_BRACKET)
                && !StringUtils.endsWithIgnoreCase(content, StringPool.RIGHT_SQ_BRACKET)) {
            content = StringPool.LEFT_SQ_BRACKET + content + StringPool.RIGHT_SQ_BRACKET;
        }
        try {
            List<Map<String, Object>> contentMap = getInstance().readValue(content, new TypeReference<List<Map<String, Object>>>() {
            });
            return contentMap.stream()
                    .map(obj -> getInstance().convertValue(obj, valueTypeRef))
                    .collect(Collectors.toList());
        } catch (IOException e) {
            throw Exceptions.unchecked(e);
        }
    }

    /**
     * 将json byte 数组反序列化成对象
     *
     * @param content   json bytes
     * @param valueType class
     * @param <T>       T 泛型标记
     * @return Bean
     */
    public static <T> T parse(byte[] content, Class<T> valueType) {
        if (Objects.isNull(content) || content.length < 1) {
            return null;
        }
        try {
            return getInstance().readValue(content, valueType);
        } catch (IOException e) {
            throw Exceptions.unchecked(e);
        }
    }

    /**
     * 将json反序列化成对象
     *
     * @param jsonString jsonString
     * @param valueType  class
     * @param <T>        T 泛型标记
     * @return Bean
     */
    public static <T> T parse(String jsonString, Class<T> valueType) {
        if (StringUtils.isBlank(jsonString)) {
            return null;
        }
        try {
            return getInstance().readValue(jsonString, valueType);
        } catch (IOException e) {
            throw Exceptions.unchecked(e);
        }
    }

    /**
     * 将json反序列化成对象
     *
     * @param in        InputStream
     * @param valueType class
     * @param <T>       T 泛型标记
     * @return Bean
     */
    public static <T> T parse(InputStream in, Class<T> valueType) {
        if (in == null) {
            return null;
        }
        try {
            return getInstance().readValue(in, valueType);
        } catch (IOException e) {
            throw Exceptions.unchecked(e);
        }
    }

    /**
     * 将json反序列化成对象
     *
     * @param content bytes
     * @param <T>     T 泛型标记
     * @return Bean
     */
    public static <T> T parse(byte[] content) {
        if (Toolkit.isEmpty(content)) {
            return null;
        }
        try {
            return getInstance().readValue(content, new TypeReference<T>() {
            });
        } catch (IOException e) {
            throw Exceptions.unchecked(e);
        }
    }

    /**
     * 将json反序列化成对象
     *
     * @param jsonString jsonString
     * @param <T>        T 泛型标记
     * @return Bean
     */
    public static <T> T parse(String jsonString) {
        if (StringUtils.isBlank(jsonString)) {
            return null;
        }
        try {
            return getInstance().readValue(jsonString, new TypeReference<T>() {
            });
        } catch (IOException e) {
            throw Exceptions.unchecked(e);
        }
    }

    /**
     * 将json反序列化成对象
     *
     * @param in  InputStream
     * @param <T> T 泛型标记
     * @return Bean
     */
    public static <T> T parse(InputStream in) {
        if (in == null) {
            return null;
        }
        try {
            return getInstance().readValue(in, new TypeReference<T>() {
            });
        } catch (IOException e) {
            throw Exceptions.unchecked(e);
        }
    }

    /**
     * 实现JSON序列化的 jackson 单例对象
     *
     * @return /
     */
    private static ObjectMapper getInstance() {
        return JacksonHolder.INSTANCE;
    }

    private static class JacksonHolder {
        private static final ObjectMapper INSTANCE = new JacksonObjectMapper();
    }
}
