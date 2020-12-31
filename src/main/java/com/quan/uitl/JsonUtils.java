package com.quan.uitl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.parser.ParserConfig;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.util.TypeUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Json处理工具类
 *
 * @author wubingtao
 * <p>
 * created by wubingtao
 * Date: 2019/12/15
 */
public final class JsonUtils {

    private static final Logger logger = LoggerFactory.getLogger(JsonUtils.class);

    /**
     * 将对象转为Json字符串
     * 对象为空返回空字符串
     *
     * @param object 源对象
     * @return Json字符串
     */
    public static String toString(Object object) {
        return Objects.isNull(object) ? "" : JSON.toJSONString(object, SerializerFeature.WriteDateUseDateFormat);
    }

    /**
     * 将Json字符串转为指定类型对象
     *
     * @param jsonString Json字符串
     * @param clazz      目标对象类型
     * @return 转换后对象 Json字符串为空返回null
     */
    public static <T> T toObject(String jsonString, Class<T> clazz) {
        if (StringUtils.isBlank(jsonString)) {
            return ClassUtils.newInstant(clazz);
        }
        return JSON.parseObject(jsonString, clazz);
    }

    /**
     * 将Map转为对象
     *
     * @param map   Map对象
     * @param clazz 目标对象类型
     * @return 转换后对象
     */
    public static <T> T toObject(Map<?, ?> map, Class<T> clazz) {
        if (Objects.isNull(map)) {
            return ClassUtils.newInstant(clazz);
        }
        return TypeUtils.cast(map, clazz, ParserConfig.getGlobalInstance());
    }

    /**
     * 将Json字符串转为指定元素类型的列表
     *
     * @param jsonString
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> List<T> toList(String jsonString, Class<T> clazz) {
        if (StringUtils.isBlank(jsonString)) {
            return new ArrayList<>();
        }
        return JSON.parseArray(jsonString, clazz);
    }

    /**
     * 将对象转为类似Map的JSONObject实例
     *
     * @param object 源对象
     * @return 转换后JsonObject对象 对象为空 JsonObject属性为空
     */
    public static JSONObject toJsonObject(Object object) {
        if (Objects.isNull(object)) {
            return new JSONObject();
        }
        JSON json = (JSON) JSON.toJSON(object);
        if (json instanceof JSONObject) {
            return (JSONObject) json;
        }
        return new JSONObject();
    }

    public static JSONObject toJsonObject(String jsonString) {
        if (StringUtils.isBlank(jsonString)) {
            return new JSONObject();
        }
        return JSON.parseObject(jsonString);
    }

    /**
     * 将对象转为类似List的JSONArray实例
     *
     * @param object 源对象
     * @return 转换后JsonArray对象 对象为空 JsonArray元素为空
     */
    public static JSONArray toJsonArray(Object object) {
        if (Objects.isNull(object)) {
            return new JSONArray();
        }
        JSON json = (JSON) JSON.toJSON(object);
        if (json instanceof JSONArray) {
            return (JSONArray) json;
        }
        return new JSONArray();
    }

    public static JSONArray toJsonArray(String jsonString) {
        if (StringUtils.isBlank(jsonString)) {
            return new JSONArray();
        }
        return JSON.parseArray(jsonString);
    }

    /**
     * 将Json字符串List转为指定类型对象List
     *
     * @param list  Json字符串列表
     * @param clazz 目标对象类型
     * @return 目标类型对象列表
     */
    public static <T> List<T> castElementType(List<String> list, Class<T> clazz) {
        if (Objects.isNull(list)) {
            return null;
        }
        return list.stream().map(elementStr -> toObject(elementStr, clazz)).collect(Collectors.toList());
    }

    private JsonUtils() {
    }

}

