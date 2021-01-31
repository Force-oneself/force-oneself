package com.quan.common.util;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 数组操作工具类
 *
 * @author heyq
 * <p>
 * created by heyq
 * Date: 2019/12/15
 */
public final class ArrayUtils {
    /**
     * 数据元素是否全为空
     *
     * @param array 判断数据
     * @param <T>   数据元素类型
     * @return 数组对象为空或数据元素全为空
     */
    @SafeVarargs
    public static <T> boolean isAllNull(T... array) {
        if (Objects.isNull(array)) {
            return true;
        }
        for (T element : array) {
            if (Objects.nonNull(element)) {
                return false;
            }
        }
        return true;
    }

    /**
     * 数据元素是否不全为空
     *
     * @param array 判断数据
     * @param <T>   数据元素类型
     * @return 数组对象不为空且数据元素不全为空
     */
    @SafeVarargs
    public static <T> boolean notAllNull(T... array) {
        return !isAllNull(array);
    }

    /**
     * 数据元素是否全不为空
     *
     * @param array 判断数据
     * @param <T>   数据元素类型
     * @return 数组对象不为空且数据元素全不为空
     */
    @SafeVarargs
    public static <T> boolean isAllNotNull(T... array) {
        if (Objects.isNull(array) || isEmpty(array)) {
            return false;
        }
        for (T element : array) {
            if (Objects.isNull(element)) {
                return false;
            }
        }
        return true;
    }

    /**
     * 数据元素是否不全不为空
     *
     * @param array 判断数据
     * @param <T>   数据元素类型
     * @return 数组对象为空或数据元素不全不为空
     */
    @SafeVarargs
    public static <T> boolean notAllNotNull(T... array) {
        return !isAllNotNull(array);
    }

    /**
     * 数组是否为空
     *
     * @param array 判断数组
     * @return true if is null or size is 0
     */
    @SafeVarargs
    public static <T> boolean isEmpty(T... array) {
        return Objects.isNull(array) || array.length == 0;
    }

    /**
     * 数组是否不为空
     *
     * @param array 判断数组
     * @return true if not null and size is not 0
     */
    @SafeVarargs
    public static <T> boolean notEmpty(T... array) {
        return !isEmpty(array);
    }

    /**
     * 创建指定长度对象数组
     *
     * @param capacity 数组容量
     * @return 创建的指定长度数组
     * @throws IllegalArgumentException capacity为负数
     */
    public static Object[] create(int capacity) {
        return create(capacity, Object.class);
    }

    /**
     * 创建指定类型对象数组
     *
     * @param capacity 数组容量
     * @param clazz    数组元素类型 仅支持包装类型和对象
     * @return 指定长度及元素类型的数组
     * @throws IllegalArgumentException 1.capacity为负数 2.元素类型为空 3.元素类型为基本类型
     */
    @SuppressWarnings("unchecked")
    public static <T> T[] create(int capacity, Class<T> clazz) {
        if (capacity < 0) {
            throw new IllegalArgumentException("数组长度需要为自然数");
        }
        if (Objects.isNull(clazz)) {
            throw new IllegalArgumentException("数组元素类型不能为空");
        }
        if (!Object.class.isAssignableFrom(clazz)) {
            throw new IllegalArgumentException("数组元素类型不能为基本类型");
        }
        return (T[]) Array.newInstance(clazz, capacity);
    }

    /**
     * 创建指定类型对象数组并使用指定默认值填充
     *
     * @param capacity      数组容量
     * @param clazz         数组元素类型 仅支持包装类型和对象
     * @param defaultObject 填充默认值
     * @return 使用指定默认值填充的指定长度及元素类型的数组
     * @throws IllegalArgumentException 1.capacity为负数 2.元素类型为空 3.元素类型为基本类型
     */
    public static <T> T[] createWithDefault(int capacity, Class<T> clazz, T defaultObject) {
        if (capacity < 0) {
            throw new IllegalArgumentException("数组长度需要为自然数");
        }
        if (Objects.isNull(clazz)) {
            throw new IllegalArgumentException("数组元素类型不能为空");
        }
        if (!Object.class.isAssignableFrom(clazz)) {
            throw new IllegalArgumentException("数组元素类型不能为基本类型");
        }
        return StreamUtils.create(defaultObject, capacity).toArray(value -> create(value, clazz));
    }

    /**
     * 根据样例数组创建对象数组,使用样例数组的元素类型
     *
     * @param capacity    数组容量
     * @param sampleArray 元素类型实例数组
     * @return 与实例数组元素类型一致且指定长度的数组
     * @throws IllegalArgumentException 1.capacity为负数 2.实例数组为空
     */
    @SuppressWarnings("unchecked")
    public static <T> T[] createLike(int capacity, T[] sampleArray) {
        if (Objects.isNull(sampleArray)) {
            throw new IllegalArgumentException("实例数组对象不能为空");
        }
        return (T[]) create(capacity, sampleArray.getClass().getComponentType());
    }

    /**
     * 复制给定数组
     *
     * @param array 给定数组
     * @return 复制得到的数组 给定数组为空时 复制数组为空
     */
    public static <T> T[] copy(T[] array) {
        return Objects.nonNull(array) ? Arrays.copyOf(array, array.length) : null;
    }

    /**
     * 返回截取给定数组
     *
     * @param arrays     给定数组
     * @param startIndex 开始索引(从0计算)
     * @param length     截取元素个数
     * @return 截取的数组
     */
    public static <T> T[] subArray(T[] arrays, int startIndex, int length) {
        if (Objects.isNull(arrays)) {
            return null;
        }
        if (startIndex < 0) {
            throw new IllegalArgumentException("开始索引小于0");
        }
        if (startIndex >= arrays.length) {
            throw new IllegalArgumentException("开始索引大于等于数组长度");
        }
        if (length < 0) {
            throw new IllegalArgumentException("截取元素个数小于0");
        }
        if (startIndex + length > arrays.length) {
            throw new IllegalArgumentException("截取目标大于等于数组长度");
        }
        return Arrays.copyOfRange(arrays, startIndex, startIndex + length);
    }

    /**
     * 将数组内指定索引后元素向右位移
     * 位移后元素覆盖原元素
     *
     * @param arrays     操作数组
     * @param startIndex 最左元素索引(从0计算)
     * @param shiftSeat  移动位数
     * @return 移动后的新数组
     */
    public static <T> T[] shiftRightElement(T[] arrays, int startIndex, int shiftSeat) {
        return shiftRightElement(arrays, startIndex, shiftSeat, true);
    }

    /**
     * 将数组内指定索引后元素向右位移
     * 位移后元素覆盖原元素
     *
     * @param arrays     操作数组
     * @param startIndex 最左元素索引(从0计算)
     * @param shiftSeat  移动位数
     * @param newArray   是否建立新的数组
     * @return 移动后的数组
     */
    public static <T> T[] shiftRightElement(T[] arrays, int startIndex, int shiftSeat, boolean newArray) {
        if (Objects.isNull(arrays)) {
            return null;
        }
        if (startIndex < 0) {
            throw new IllegalArgumentException("最左元素索引小于0");
        }
        if (shiftSeat < 0) {
            throw new IllegalArgumentException("移动位数小于1");
        }
        int length = arrays.length;
        T[] targetArrays = newArray ? copy(arrays) : arrays;
        if (isEmpty(targetArrays) || length == 1 || shiftSeat == 0 || startIndex >= length || startIndex + shiftSeat > length) {
            return targetArrays;
        }
        int shiftLength = length - startIndex - shiftSeat;
        System.arraycopy(targetArrays, startIndex, targetArrays, startIndex + shiftSeat, shiftLength);
        return targetArrays;
    }

    /**
     * 将数组内指定索引前元素向左位移
     * 位移后元素覆盖原元素
     *
     * @param arrays    操作数组
     * @param endIndex  最右元素索引(从1计算)
     * @param shiftSeat 移动位数
     * @return 移动后的新数组
     */
    public static <T> T[] shiftLeftElement(T[] arrays, int endIndex, int shiftSeat) {
        return shiftLeftElement(arrays, endIndex, shiftSeat, true);
    }

    /**
     * 将数组内指定索引前元素向左位移
     * 位移后元素覆盖原元素
     *
     * @param arrays    操作数组
     * @param endIndex  最右元素索引(从1计算)
     * @param shiftSeat 移动位数
     * @param newArray  是否建立新的数组
     * @return 移动后的数组
     */
    public static <T> T[] shiftLeftElement(T[] arrays, int endIndex, int shiftSeat, boolean newArray) {
        if (Objects.isNull(arrays)) {
            return null;
        }
        int length = arrays.length;
        if (endIndex > length) {
            throw new IllegalArgumentException("最右元素索引大于数组长度");
        }
        T[] targetArrays = newArray ? copy(arrays) : arrays;
        if (isEmpty(targetArrays) || length == 1 | shiftSeat == 0 || endIndex > length || shiftSeat >= endIndex) {
            return targetArrays;
        }
        int shiftLength = endIndex - shiftSeat;
        System.arraycopy(targetArrays, shiftSeat, targetArrays, 0, shiftLength);
        return targetArrays;
    }

    /**
     * 合并两个数组
     *
     * @param baseArray    基础数组
     * @param anotherArray 最佳数据
     * @return 合并后的新数组
     */
    @SuppressWarnings("unchecked")
    public static <T> T[] concat(T[] baseArray, T... anotherArray) {
        if (isEmpty(baseArray)) {
            return copy(anotherArray);
        }
        if (isEmpty(anotherArray)) {
            return copy(baseArray);
        }
        int baseSize = baseArray.length;
        int anotherSize = anotherArray.length;
        T[] concatArray = createLike(baseSize + anotherSize, baseArray);
        System.arraycopy(baseArray, 0, concatArray, 0, baseSize);
        System.arraycopy(anotherArray, 0, concatArray, baseSize, anotherSize);
        return concatArray;
    }

    /**
     * 数组是否包含给定元素
     *
     * @param array  给定数组
     * @param target 查询目标
     * @return 数组是否包含给定查询目标
     */
    public static <T> boolean contain(T[] array, T target) {
        if (Objects.isNull(array)) {
            return false;
        }
        for (T element : array) {
            if (Objects.equals(element, target)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 在数组中搜索元素
     *
     * @param array         待操作的数组
     * @param targetElement 待匹配的元素
     * @return 索引，如不存在，-1
     */
    public static <T> int index(T[] array, T targetElement) {
        if (Objects.isNull(array)) {
            return -1;
        }
        int index = 0;
        for (T element : array) {
            if (Objects.equals(element, targetElement)) {
                return index;
            }
            index++;
        }
        return -1;
    }

    /**
     * 将给定的数组转换为按','分隔的字符串
     *
     * @param objects 给定的数组
     * @return 1, 2, 3, ...
     */
    public static <T> String toString(T[] objects) {
        return toString(objects, ",");
    }

    /**
     * 将给定的数组转换为按给定分隔符分隔的字符串
     *
     * @param objects   给定的数组
     * @param separator 分隔符
     * @return 转换后字符串
     */
    public static <T> String toString(T[] objects, String separator) {
        return toString(objects, separator, "", "");
    }

    /**
     * 将给定的数组转换为按给定分隔符分隔的字符串 并在前后加上指定头尾符号
     *
     * @param array     给定的数组
     * @param separator 分隔符
     * @param prefix    开始符号
     * @param suffix    结束符号
     * @return 转换后字符串
     */
    public static <T> String toString(T[] array, String separator, String prefix, String suffix) {
        prefix = StringUtils.emptyIfNull(prefix);
        separator = StringUtils.emptyIfNull(separator);
        suffix = StringUtils.emptyIfNull(suffix);
        if (isEmpty(array)) {
            return prefix + suffix;
        }
        return Stream.of(array).map(Object::toString).collect(Collectors.joining(separator, prefix, suffix));
    }

    private ArrayUtils() {
    }

}

