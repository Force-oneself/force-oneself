package com.quan.tools;


import com.quan.tools.log.Logger;
import com.quan.tools.log.LoggerFactory;

import java.lang.reflect.Array;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 万能工具箱
 *
 * @author Force-oneself
 * @date 2022-10-20
 */
public class Toolkit {

    private final static Logger LOGGER = LoggerFactory.getLogger(Toolkit.class);

    /**
     * Default load factor for {@link HashMap}/{@link LinkedHashMap} variants.
     *
     * @see #newHashMap(int)
     * @see #newLinkedHashMap(int)
     */
    static final float DEFAULT_LOAD_FACTOR = 0.75f;

    private Toolkit() {
    }

    // ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    //                                        通用工具API
    // ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

    /**
     * 判断对象为null
     *
     * @param o 对象
     * @return boolean
     */
    public static boolean isNull(Object o) {
        return o == null;
    }

    /**
     * 判断对象不为null
     *
     * @param o 对象
     * @return boolean
     */
    public static boolean notNull(Object o) {
        return !isNull(o);
    }


    /**
     * 自适应判断对象
     *
     * @param obj 对象
     * @return boolean
     */
    public static boolean isNotEmpty(Object obj) {
        return !isEmpty(obj);
    }

    /**
     * 自适应判断对象, 对象 == null或者存在以下情况：
     * <ul>
     *     <li>Collection不存在元素</li>
     *     <li>Map不存在元素</li>
     *     <li>数组不存在元素</li>
     *     <li>字符序列长度 == 0</li>
     *     <li>Optional == false</li>
     * </ul>
     *
     * @param obj 对象
     * @return /
     */
    public static boolean isEmpty(Object obj) {
        if (obj == null) {
            return true;
        }
        if (obj instanceof Optional) {
            return !((Optional<?>) obj).isPresent();
        }
        if (obj instanceof CharSequence) {
            return ((CharSequence) obj).length() == 0;
        }
        if (obj.getClass().isArray()) {
            return Array.getLength(obj) == 0;
        }
        if (obj instanceof Collection) {
            return ((Collection<?>) obj).isEmpty();
        }
        if (obj instanceof Map) {
            return ((Map<?, ?>) obj).isEmpty();
        }
        // else
        return false;
    }

    // ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    //                                        字符工具API
    // ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

    /**
     * <pre>
     * StringUtils.hasLength(null) = false
     * StringUtils.hasLength("") = false
     * StringUtils.hasLength(" ") = true
     * StringUtils.hasLength("Hello") = true
     * </pre>
     *
     * @param str 字符串
     * @return 是否存在字符
     */
    public static boolean hasLength(CharSequence str) {
        return str != null && str.length() > 0;
    }

    /**
     * <pre>
     * StringUtils.isBlank("") = true
     * StringUtils.isBlank("  ") = true
     * StringUtils.isBlank(null) = true
     * StringUtils.isBlank("null") = false
     * StringUtils.isBlank("  s") = false
     * <pre/>
     *
     * @param str 字符串
     * @return boolean
     */
    public static boolean isBlank(String str) {
        return !hasText(str);
    }

    /**
     * <pre>
     * StringUtils.isBlank("") = false
     * StringUtils.isBlank("  ") = false
     * StringUtils.isBlank(null) = false
     * StringUtils.isBlank("null") = false
     * StringUtils.isBlank("  s") = true
     * <pre/>
     *
     * @param str 字符串
     * @return boolean
     */
    public static boolean isNotBlank(String str) {
        return !isBlank(str);
    }

    /**
     * 校验字符是否非空并非全空白字符
     *
     * @param str 字符串
     * @return boolean
     */
    public static boolean hasText(CharSequence str) {
        return (str != null && str.length() > 0 && containsText(str));
    }

    /**
     * 是否存在空白字符
     *
     * @param str 字符串
     * @return boolean
     */
    public static boolean containsWhitespace(CharSequence str) {
        if (!hasLength(str)) {
            return false;
        }

        int strLen = str.length();
        for (int i = 0; i < strLen; i++) {
            if (Character.isWhitespace(str.charAt(i))) {
                return true;
            }
        }
        return false;
    }

    /**
     * 去除字符串两边空白字符
     *
     * @param str 字符串
     * @return boolean
     */
    public static String trimWhitespace(String str) {
        if (!hasLength(str)) {
            return str;
        }
        int beginIndex = 0;
        int endIndex = str.length() - 1;

        while (beginIndex <= endIndex && Character.isWhitespace(str.charAt(beginIndex))) {
            beginIndex++;
        }
        while (endIndex > beginIndex && Character.isWhitespace(str.charAt(endIndex))) {
            endIndex--;
        }
        return str.substring(beginIndex, endIndex + 1);
    }

    /**
     * 去除字符串所有空白字符
     *
     * @param str str
     * @return /
     */
    public static String trimAllWhitespace(String str) {
        if (!hasLength(str)) {
            return str;
        }

        int len = str.length();
        StringBuilder sb = new StringBuilder(str.length());
        for (int i = 0; i < len; i++) {
            char c = str.charAt(i);
            if (!Character.isWhitespace(c)) {
                sb.append(c);
            }
        }
        return sb.toString();
    }

    /**
     * 不区分大小写匹配前缀
     *
     * @param str    字符串
     * @param prefix 前缀
     * @return boolean
     */
    public static boolean startsWithIgnoreCase(String str, String prefix) {
        return str != null && prefix != null && str.length() >= prefix.length()
                && str.regionMatches(true, 0, prefix, 0, prefix.length());
    }

    /**
     * 不区分大小写匹配后缀
     *
     * @param str    字符串
     * @param suffix 前缀
     * @return boolean
     */
    public static boolean endsWithIgnoreCase(String str, String suffix) {
        return str != null && suffix != null
                && str.length() >= suffix.length()
                && str.regionMatches(true, str.length() - suffix.length(), suffix, 0, suffix.length());
    }

    /**
     * 包含非空白字符
     *
     * @param str 字符串
     * @return boolean
     */
    private static boolean containsText(CharSequence str) {
        int strLen = str.length();
        for (int i = 0; i < strLen; i++) {
            if (!Character.isWhitespace(str.charAt(i))) {
                return true;
            }
        }
        return false;
    }

    // ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    //                                        集合工具API
    // ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

    /**
     * Instantiate a new {@link HashMap} with an initial capacity
     * that can accommodate the specified number of elements without
     * any immediate resize/rehash operations to be expected.
     * <p>This differs from the regular {@link HashMap} constructor
     * which takes an initial capacity relative to a load factor
     * but is effectively aligned with the JDK's
     * {@link java.util.concurrent.ConcurrentHashMap#ConcurrentHashMap(int)}.
     *
     * @param expectedSize the expected number of elements (with a corresponding
     *                     capacity to be derived so that no resize/rehash operations are needed)
     * @see #newLinkedHashMap(int)
     * @since 5.3
     */
    public static <K, V> HashMap<K, V> newHashMap(int expectedSize) {
        return new HashMap<>((int) (expectedSize / DEFAULT_LOAD_FACTOR), DEFAULT_LOAD_FACTOR);
    }

    /**
     * Instantiate a new {@link LinkedHashMap} with an initial capacity
     * that can accommodate the specified number of elements without
     * any immediate resize/rehash operations to be expected.
     * <p>This differs from the regular {@link LinkedHashMap} constructor
     * which takes an initial capacity relative to a load factor but is
     * aligned with Spring's own {@link LinkedCaseInsensitiveMap} and
     * {@link LinkedMultiValueMap} constructor semantics as of 5.3.
     *
     * @param expectedSize the expected number of elements (with a corresponding
     *                     capacity to be derived so that no resize/rehash operations are needed)
     * @see #newHashMap(int)
     * @since 5.3
     */
    public static <K, V> LinkedHashMap<K, V> newLinkedHashMap(int expectedSize) {
        return new LinkedHashMap<>((int) (expectedSize / DEFAULT_LOAD_FACTOR), DEFAULT_LOAD_FACTOR);
    }


    /**
     * Merge the given Properties instance into the given Map,
     * copying all properties (key-value pairs) over.
     * <p>Uses {@code Properties.propertyNames()} to even catch
     * default properties linked into the original Properties instance.
     *
     * @param props the Properties instance to merge (may be {@code null})
     * @param map   the target Map to merge the properties into
     */
    @SuppressWarnings("unchecked")
    public static <K, V> void mergePropertiesIntoMap(Properties props, Map<K, V> map) {
        if (!notNull(props) || !notNull(map)) {
            return;
        }
        for (Enumeration<?> en = props.propertyNames(); en.hasMoreElements(); ) {
            String key = (String) en.nextElement();
            Object value = props.get(key);
            if (value == null) {
                // Allow for defaults fallback or potentially overridden accessor...
                value = props.getProperty(key);
            }
            map.put((K) key, (V) value);
        }
    }

    /**
     * 空Map
     *
     * @return Map
     */
    public static <K, V> Map<K, V> emptyMap() {
        return Collections.emptyMap();
    }

    /**
     * 空集合
     *
     * @return 集合
     */
    public static <T> List<T> emptyList() {
        return Collections.emptyList();
    }

    /**
     * 将数组元素放入集合类中
     *
     * @param array      数组
     * @param collection 集合
     */
    public static <E> void mergeIntoCollection(E[] array, Collection<E> collection) {
        if (notNull(array) && notNull(collection)) {
            collection.addAll(Arrays.asList(array));
        }
    }

    /**
     * 将枚举转换成集合
     *
     * @param elements 枚举数组值
     * @return 集合
     */
    @SafeVarargs
    public static <E> List<E> asList(E... elements) {
        return notNull(elements) ? Arrays.stream(elements).collect(Collectors.toList()) : emptyList();
    }

}
