package com.quan.common.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.json.JsonReadFeature;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.quan.common.bean.R;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.lang.reflect.Constructor;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.util.*;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;

/**
 * @Description: class Objs
 * @Author Force丶Oneself
 * @Date 2021-05-28
 **/
public class Objs {

    private static final Logger log = LoggerFactory.getLogger(Objs.class);

    public final static ObjectMapper MAPPER = new ObjectMapper();

    private Objs() {
    }

    static {
        initMapper();
    }

    private static void initMapper() {
        // 忽略大小写
        MAPPER.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true);
        // 取消默认转换timestamps
        MAPPER.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        // 忽略空Bean转json的错误
        MAPPER.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        // 忽略 在json字符串中存在，但是java对象中不存在对应属性的情况，防止错误
        MAPPER.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        // 空字符作为空对象处理
        MAPPER.configure(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT, true);
        // 单个对象值处理成集合
        MAPPER.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
        // 配置枚举转为String
        MAPPER.configure(SerializationFeature.WRITE_ENUMS_USING_TO_STRING, true);
        // 配置String转为枚举
        MAPPER.configure(DeserializationFeature.READ_ENUMS_USING_TO_STRING, true);
        // 去掉默认的时间格式戳格式
        MAPPER.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        // 单引号
        MAPPER.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
        // 允许JSON字符串包含引号控制符（值小于32的ASCII字符，包含制表符和换行符）
        MAPPER.configure(JsonReadFeature.ALLOW_UNESCAPED_CONTROL_CHARS.mappedFeature(), true);
        MAPPER.configure(JsonReadFeature.ALLOW_BACKSLASH_ESCAPING_ANY_CHARACTER.mappedFeature(), true);
        // 单引号处理
        MAPPER.configure(JsonReadFeature.ALLOW_SINGLE_QUOTES.mappedFeature(), true);

        // 统一日期格式为："yyyy-MM-dd HH:mm:ss"
        MAPPER.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA));
        // 设置null字段去除序列化
        MAPPER.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        // 设置地点为中国
        MAPPER.setLocale(Locale.CHINA);
        // 设置上海的时区
        MAPPER.setTimeZone(TimeZone.getTimeZone(ZoneId.systemDefault()));
    }

    /**
     * 格式化打印对象,主要用作代码日志
     * 注意：jackson中存在bug, 打印循环依赖的对象时会StackOverflowError
     * 解决方案：自己百度加注解解决，打印框架里的对象时无解，需要自己排除
     *
     * @param value 格式化对象
     * @return 格式化后json字符
     */
    public static String prettyPrint(Object value) {
        try {
            return MAPPER.writeValueAsString(value);
        } catch (JsonProcessingException e) {
            log.error("格式转换异常", e);
            return "{ \"error\": \"" + e.getMessage() + "\" }";
        }
    }

    /**
     * 序列化
     *
     * @param object 对象
     * @return 字节数组
     */
    public static byte[] serialize(Object object) {
        try (ByteArrayOutputStream bas = new ByteArrayOutputStream();
             ObjectOutputStream oos = new ObjectOutputStream(bas)) {
            // 序列化
            oos.writeObject(object);
            return bas.toByteArray();
        } catch (Exception e) {
            log.error("序列化失败", e);
            throw new RuntimeException("序列化失败", e);
        }
    }

    /**
     * 反序列化
     *
     * @param bytes 字节数组
     * @return 对象
     */
    public static Object universalize(byte[] bytes) {
        try (ByteArrayInputStream bas = new ByteArrayInputStream(bytes)) {
            // 反序列化
            return new ObjectInputStream(bas).readObject();
        } catch (Exception e) {
            log.error("反序列失败", e);
            throw new RuntimeException("反序列化失败", e);
        }
    }

    /**
     * 获取目录下符合条件的文件
     *
     * @param file   目录路径
     * @param filter 过滤条件
     * @return 符合条件的文件集合
     */
    public static List<File> matchFiles(File file, Predicate<File> filter) {
        return Arrays.stream(Objects.requireNonNull(file.listFiles(File::isFile)))
                .filter(filter)
                .collect(Collectors.toList());
    }

    /**
     * 获取目录下符合后缀式的文件
     *
     * @param file     目录路径
     * @param endsWith 后缀式字符
     * @return 符合后缀式的文件集合
     */
    public static List<File> endsWithFiles(File file, String endsWith) {
        return matchFiles(file, endFile -> endFile.getName().endsWith(endsWith));
    }

    /**
     * 获取目录下符合前缀式的文件
     *
     * @param file      目录路径
     * @param startWith 前缀式字符
     * @return 符合前缀式的文件集合
     */
    public static List<File> startWithFiles(File file, String startWith) {
        return matchFiles(file, startFile -> startFile.getName().startsWith(startWith));
    }

    /**
     * 根据类名获取类型对象
     *
     * @param className 类限定名
     * @param <T>       类名
     * @return 目标类名称
     */
    public static <T> Class<T> forName(String className) {
        try {
            return (Class<T>) Class.forName(className);
        } catch (ClassNotFoundException e) {
            throw new IllegalArgumentException("找不到指定的class,请确定类限定名正确", e);
        }
    }

    /**
     * 获取对象类型
     *
     * @param object 给定对象
     * @param <T>    对象类型
     * @return 给定对象的所属类型
     */
    public static <T> Class<T> classOf(T object) {
        Objects.requireNonNull(object, "判断类型的对象为空,无法获取对象类型");
        return (Class<T>) object.getClass();
    }

    /**
     * 获取指定类型的对象实例
     *
     * @param clazz 指定类型
     * @param <T>   对象类型
     * @return 给定对象类型实例
     */
    public static <T> T newInstance(Class<T> clazz) {
        if (Objects.isNull(clazz)) {
            return null;
        }
        Constructor<?>[] constructors = clazz.getDeclaredConstructors();
        Throwable throwable = null;
        T result = null;
        for (Constructor<?> constructor : constructors) {
            int paramNum = constructor.getParameterCount();
            constructor.setAccessible(true);
            try {
                if (paramNum == 0) {
                    result = (T) constructor.newInstance();
                } else {
                    result = (T) constructor.newInstance(new Object[paramNum]);
                }
            } catch (Exception e) {
                throwable = e;
            }
            constructor.setAccessible(false);
            if (Objects.nonNull(result)) {
                return result;
            }
        }
        throw new IllegalArgumentException("创建对象类型未找到可用构造函数", throwable);
    }


    public static <T> List<T> emptyList() {
        return Collections.emptyList();
    }

    public static <T> T safeData(Supplier<T> dataProvider, String msg) {
        try {
            return dataProvider.get();
        } catch (Throwable throwable) {
            log.error(msg, throwable);
            throw new RuntimeException(msg);
        }
    }

    public static <T> T safeRData(Supplier<? extends R<T>> supplier, String msg) {
        R<T> result = safeData(supplier, msg);
        if (result.getSuccess()) {
            return result.getData();
        }
        throw new RuntimeException(msg);
    }
}
