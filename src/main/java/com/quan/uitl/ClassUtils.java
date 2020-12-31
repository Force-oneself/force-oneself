package com.quan.uitl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Constructor;
import java.util.Objects;

/**
 * @author wubingtao
 * <p>
 * created by wubingtao
 * Date: 2019/8/31
 */
public final class ClassUtils {

    private static final Logger logger = LoggerFactory.getLogger(ClassUtils.class);

    /**
     * 根据类名获取类型对象
     *
     * @param className 类限定名
     * @param <T>       类名
     * @return 目标类名称
     */
    @SuppressWarnings("unchecked")
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
    @SuppressWarnings("unchecked")
    public static <T> Class<T> classOf(T object) {
        if (Objects.isNull(object)) {
            throw new IllegalArgumentException("判断类型的对象为空,无法获取对象类型");
        }
        return (Class<T>) object.getClass();
    }

    /**
     * 获取指定类型的对象实例
     *
     * @param clazz 指定类型
     * @param <T>   对象类型
     * @return 给定对象类型实例
     */
    @SuppressWarnings("unchecked")
    public static <T> T newInstant(Class<T> clazz) {
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

    private ClassUtils() {
    }
}

