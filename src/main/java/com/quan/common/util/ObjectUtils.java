package com.quan.common.util;

import lombok.NonNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Objects;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * @author heyq
 * <p>
 * created by heyq
 * Date: 2019/12/15
 */
public final class ObjectUtils {

    private static final Logger logger = LoggerFactory.getLogger(ObjectUtils.class);

    /**
     * 对象为空则返回默认对象
     *
     * @param object        给定对象
     * @param defaultObject 默认对象
     * @param <T>           对象类型
     * @return 给定对象为空则返回默认对象 给定对象不为空则返回给定对象
     */
    public static <T> T defaultIfNull(T object, T defaultObject) {
        return Objects.nonNull(object) ? object : defaultObject;
    }

    /**
     * 对象为空则根据提供函数返回默认对象
     *
     * @param object         给定对象
     * @param objectSupplier 默认对象生成函数
     * @param <T>            对象类型
     * @return 给定对象为空则根据提供函数返回默认对象 给定对象不为空则返回给定对象
     */
    public static <T> T defaultIfNull(T object, @NonNull Supplier<T> objectSupplier) {
        return Objects.nonNull(object) ? object : objectSupplier.get();
    }

    /**
     * 对象为空则返回默认对象
     *
     * @param object        给定对象
     * @param getter        返回值获取方法
     * @param defaultObject 默认对象
     * @param <T>           返回值类型
     * @param <K>           对象类型
     * @return 给定对象为空则返回默认对象 给定对象不为空则使用返回值获取方法从给定对象获取返回值
     */
    public static <T, K> T defaultIfNull(K object, @NonNull Function<K, T> getter, @NonNull T defaultObject) {
        return Objects.nonNull(object) ? getter.apply(object) : defaultObject;
    }

    /**
     * 对象为空则根据提供函数返回默认对象
     *
     * @param object         给定对象
     * @param getter         返回值获取方法
     * @param objectSupplier 默认对象生成函数
     * @param <T>            返回值类型
     * @param <K>            对象类型
     * @return 给定对象为空则根据提供函数返回默认对象 给定对象不为空则使用返回值获取方法从给定对象获取返回值
     */
    public static <T, K> T defaultIfNull(K object, @NonNull Function<K, T> getter, @NonNull Supplier<T> objectSupplier) {
        return Objects.nonNull(object) ? getter.apply(object) : objectSupplier.get();
    }

    /**
     * 对象不为空则计算
     *
     * @param object   给定对象
     * @param computer 对象计算逻辑
     * @param <T>      返回值类型
     * @param <K>      对象类型
     * @return 对象为空时返回null 对象不为空时返回计算后结果
     */
    public static <T, K> T computeIfNotNull(K object, @NonNull Function<K, T> computer) {
        if (Objects.isNull(object)) {
            return null;
        }
        return computer.apply(object);
    }

    /**
     * 对象不为空则计算
     *
     * @param object   给定对象
     * @param computer 对象计算逻辑
     * @param <K>      对象类型
     */
    public static <K> void computeIfNotNull(K object, @NonNull Consumer<K> computer) {
        if (Objects.isNull(object)) {
            return;
        }
        computer.accept(object);
    }

    /**
     * 对象不为空通过值获取获取内部值后计算
     * 注: 获取的内部值可能为空
     *
     * @param object   给定对象
     * @param getter   值获取方法
     * @param computer 值计算逻辑
     * @param <T>      值类型
     * @param <K>      对象类型
     * @return 对象为空时返回null 对象不为空时 获取内部值后计算 返回对象
     */
    public static <T, K> K computeIfNotNull(K object, @NonNull Function<K, T> getter, @NonNull Consumer<T> computer) {
        if (Objects.isNull(object)) {
            return null;
        }
        T apply = getter.apply(object);
        computer.accept(apply);
        return object;
    }

    /**
     * 对象不为空通过值获取获取内部值后计算
     * 注: 获取的内部值可能为空
     *
     * @param object   给定对象
     * @param getter   值获取方法
     * @param computer 值计算逻辑
     * @param <T>      值类型
     * @param <K>      对象类型
     * @return 对象为空时返回null 对象不为空时 获取内部值后计算 返回对象
     */
    public static <T, K> K computeIfNotNull(K object, @NonNull Function<K, T> getter, @NonNull BiConsumer<T, K> computer) {
        if (Objects.isNull(object)) {
            return null;
        }
        T apply = getter.apply(object);
        computer.accept(apply, object);
        return object;
    }

    /**
     * 序列化对象
     *
     * @param object 目标对象
     * @return 序列化后字节数组
     */
    public static byte[] serialize(Object object) {
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream();
             ObjectOutputStream oos = new ObjectOutputStream(baos)) {
            oos.writeObject(object);
            oos.flush();
            return baos.toByteArray();
        } catch (Exception e) {
            throw new IllegalArgumentException("Java对象序列化失败", e);
        }
    }

    /**
     * 反序列化对象
     *
     * @param objectBytes 序列化后对象数组
     * @return 反序列化后对象
     */
    public static Object deserialize(byte[] objectBytes) {
        try (ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(objectBytes))) {
            return ois.readObject();
        } catch (Exception e) {
            throw new IllegalArgumentException("Java对象反序列化失败", e);
        }
    }

    /**
     * 复制Java对象
     * 目前使用Json方式 复制作用范围不可定义 不推荐使用
     *
     * @param object 目标对象
     * @return 复制后对象
     */
    @SuppressWarnings("unchecked")
    public static <T> T copy(T object) {
        if (Objects.isNull(object)) {
            return null;
        }
        return (T) JsonUtils.toObject(JsonUtils.toString(object), object.getClass());
    }

    private ObjectUtils() {
    }

}

