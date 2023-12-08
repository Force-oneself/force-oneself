package com.quan.tools;


import com.quan.tools.log.Logger;
import com.quan.tools.log.LoggerFactory;

import java.io.*;
import java.lang.reflect.Array;
import java.nio.charset.Charset;
import java.util.*;
import java.util.function.Supplier;
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

    /**
     * The default buffer size used when copying bytes.
     */
    public static final int BUFFER_SIZE = 4096;

    private static final byte[] EMPTY_CONTENT = new byte[0];


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
     * hasLength(null) = false
     * hasLength("") = false
     * hasLength(" ") = true
     * hasLength("Hello") = true
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
     * isBlank("") = true
     * isBlank("  ") = true
     * isBlank(null) = true
     * isBlank("null") = false
     * isBlank("  s") = false
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
     * isBlank("") = false
     * isBlank("  ") = false
     * isBlank(null) = false
     * isBlank("null") = false
     * isBlank("  s") = true
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
            Object key = en.nextElement();
            Object value = props.get(key);
            if (value == null) {
                // Allow for defaults fallback or potentially overridden accessor...
                value = props.getProperty(key.toString());
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


    // ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    //                                        断言工具API
    // ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

    /**
     * Assert a boolean expression, throwing an {@code IllegalStateException}
     * if the expression evaluates to {@code false}.
     * <p>Call {@link #assertIsTrue} if you wish to throw an {@code IllegalArgumentException}
     * on an assertion failure.
     * <pre class="code">Assert.state(id == null, "The id property must not already be initialized");</pre>
     *
     * @param expression a boolean expression
     * @param message    the exception message to use if the assertion fails
     * @throws IllegalStateException if {@code expression} is {@code false}
     */
    public static void assertState(boolean expression, String message) {
        if (!expression) {
            throw new IllegalStateException(message);
        }
    }

    /**
     * Assert a boolean expression, throwing an {@code IllegalStateException}
     * if the expression evaluates to {@code false}.
     * <p>Call {@link #assertIsTrue} if you wish to throw an {@code IllegalArgumentException}
     * on an assertion failure.
     * <pre class="code">
     * Assert.state(entity.getId() == null,
     *     () -&gt; "ID for entity " + entity.getName() + " must not already be initialized");
     * </pre>
     *
     * @param expression      a boolean expression
     * @param messageSupplier a supplier for the exception message to use if the
     *                        assertion fails
     * @throws IllegalStateException if {@code expression} is {@code false}
     * @since 5.0
     */
    public static void assertState(boolean expression, Supplier<String> messageSupplier) {
        if (!expression) {
            throw new IllegalStateException(nullSafeGet(messageSupplier));
        }
    }

    /**
     * Assert a boolean expression, throwing an {@code IllegalArgumentException}
     * if the expression evaluates to {@code false}.
     * <pre class="code">Assert.isTrue(i &gt; 0, "The value must be greater than zero");</pre>
     *
     * @param expression a boolean expression
     * @param message    the exception message to use if the assertion fails
     * @throws IllegalArgumentException if {@code expression} is {@code false}
     */
    public static void assertIsTrue(boolean expression, String message) {
        if (!expression) {
            throw new IllegalArgumentException(message);
        }
    }

    /**
     * Assert a boolean expression, throwing an {@code IllegalArgumentException}
     * if the expression evaluates to {@code false}.
     * <pre class="code">
     * Assert.isTrue(i &gt; 0, () -&gt; "The value '" + i + "' must be greater than zero");
     * </pre>
     *
     * @param expression      a boolean expression
     * @param messageSupplier a supplier for the exception message to use if the
     *                        assertion fails
     * @throws IllegalArgumentException if {@code expression} is {@code false}
     * @since 5.0
     */
    public static void assertIsTrue(boolean expression, Supplier<String> messageSupplier) {
        if (!expression) {
            throw new IllegalArgumentException(nullSafeGet(messageSupplier));
        }
    }

    /**
     * Assert that an object is {@code null}.
     * <pre class="code">Assert.isNull(value, "The value must be null");</pre>
     *
     * @param object  the object to check
     * @param message the exception message to use if the assertion fails
     * @throws IllegalArgumentException if the object is not {@code null}
     */
    public static void assertIsNull(Object object, String message) {
        if (object != null) {
            throw new IllegalArgumentException(message);
        }
    }

    /**
     * Assert that an object is {@code null}.
     * <pre class="code">
     * Assert.isNull(value, () -&gt; "The value '" + value + "' must be null");
     * </pre>
     *
     * @param object          the object to check
     * @param messageSupplier a supplier for the exception message to use if the
     *                        assertion fails
     * @throws IllegalArgumentException if the object is not {@code null}
     * @since 5.0
     */
    public static void assertIsNull(Object object, Supplier<String> messageSupplier) {
        if (object != null) {
            throw new IllegalArgumentException(nullSafeGet(messageSupplier));
        }
    }

    /**
     * Assert that an object is not {@code null}.
     * <pre class="code">assertNotNull(clazz, "The class must not be null");</pre>
     *
     * @param object  the object to check
     * @param message the exception message to use if the assertion fails
     * @throws IllegalArgumentException if the object is {@code null}
     */
    public static void assertNotNull(Object object, String message) {
        if (object == null) {
            throw new IllegalArgumentException(message);
        }
    }

    /**
     * Assert that an object is not {@code null}.
     * <pre class="code">
     * assertNotNull(entity.getId(),
     *     () -&gt; "ID for entity " + entity.getName() + " must not be null");
     * </pre>
     *
     * @param object          the object to check
     * @param messageSupplier a supplier for the exception message to use if the
     *                        assertion fails
     * @throws IllegalArgumentException if the object is {@code null}
     * @since 5.0
     */
    public static void assertNotNull(Object object, Supplier<String> messageSupplier) {
        if (object == null) {
            throw new IllegalArgumentException(nullSafeGet(messageSupplier));
        }
    }

    /**
     * Assert that the given String is not empty; that is,
     * it must not be {@code null} and not the empty String.
     * <pre class="code">Assert.hasLength(name, "Name must not be empty");</pre>
     *
     * @param text    the String to check
     * @param message the exception message to use if the assertion fails
     * @throws IllegalArgumentException if the text is empty
     */
    public static void assertHasLength(String text, String message) {
        if (!hasLength(text)) {
            throw new IllegalArgumentException(message);
        }
    }

    /**
     * Assert that the given String is not empty; that is,
     * it must not be {@code null} and not the empty String.
     * <pre class="code">
     * Assert.hasLength(account.getName(),
     *     () -&gt; "Name for account '" + account.getId() + "' must not be empty");
     * </pre>
     *
     * @param text            the String to check
     * @param messageSupplier a supplier for the exception message to use if the
     *                        assertion fails
     * @throws IllegalArgumentException if the text is empty
     * @since 5.0
     */
    public static void assertHasLength(String text, Supplier<String> messageSupplier) {
        if (!hasLength(text)) {
            throw new IllegalArgumentException(nullSafeGet(messageSupplier));
        }
    }

    /**
     * Assert that the given String contains valid text content; that is, it must not
     * be {@code null} and must contain at least one non-whitespace character.
     * <pre class="code">Assert.hasText(name, "'name' must not be empty");</pre>
     *
     * @param text    the String to check
     * @param message the exception message to use if the assertion fails
     * @throws IllegalArgumentException if the text does not contain valid text content
     * @see #hasText
     */
    public static void assertHasText(String text, String message) {
        if (!hasText(text)) {
            throw new IllegalArgumentException(message);
        }
    }

    /**
     * Assert that the given String contains valid text content; that is, it must not
     * be {@code null} and must contain at least one non-whitespace character.
     * <pre class="code">
     * Assert.hasText(account.getName(),
     *     () -&gt; "Name for account '" + account.getId() + "' must not be empty");
     * </pre>
     *
     * @param text            the String to check
     * @param messageSupplier a supplier for the exception message to use if the
     *                        assertion fails
     * @throws IllegalArgumentException if the text does not contain valid text content
     * @see #hasText
     * @since 5.0
     */
    public static void assertHasText(String text, Supplier<String> messageSupplier) {
        if (!hasText(text)) {
            throw new IllegalArgumentException(nullSafeGet(messageSupplier));
        }
    }

    /**
     * Assert that the given text does not contain the given substring.
     * <pre class="code">Assert.doesNotContain(name, "rod", "Name must not contain 'rod'");</pre>
     *
     * @param textToSearch the text to search
     * @param substring    the substring to find within the text
     * @param message      the exception message to use if the assertion fails
     * @throws IllegalArgumentException if the text contains the substring
     */
    public static void assertDoesNotContain(String textToSearch, String substring, String message) {
        if (hasLength(textToSearch) && hasLength(substring) &&
                textToSearch.contains(substring)) {
            throw new IllegalArgumentException(message);
        }
    }

    /**
     * Assert that the given text does not contain the given substring.
     * <pre class="code">
     * Assert.doesNotContain(name, forbidden, () -&gt; "Name must not contain '" + forbidden + "'");
     * </pre>
     *
     * @param textToSearch    the text to search
     * @param substring       the substring to find within the text
     * @param messageSupplier a supplier for the exception message to use if the
     *                        assertion fails
     * @throws IllegalArgumentException if the text contains the substring
     * @since 5.0
     */
    public static void assertDoesNotContain(String textToSearch, String substring, Supplier<String> messageSupplier) {
        if (hasLength(textToSearch) && hasLength(substring) &&
                textToSearch.contains(substring)) {
            throw new IllegalArgumentException(nullSafeGet(messageSupplier));
        }
    }

    /**
     * Assert that an array contains elements; that is, it must not be
     * {@code null} and must contain at least one element.
     * <pre class="code">Assert.notEmpty(array, "The array must contain elements");</pre>
     *
     * @param array   the array to check
     * @param message the exception message to use if the assertion fails
     * @throws IllegalArgumentException if the object array is {@code null} or contains no elements
     */
    public static void assertNotEmpty(Object[] array, String message) {
        if (isEmpty(array)) {
            throw new IllegalArgumentException(message);
        }
    }

    /**
     * Assert that an array contains elements; that is, it must not be
     * {@code null} and must contain at least one element.
     * <pre class="code">
     * Assert.notEmpty(array, () -&gt; "The " + arrayType + " array must contain elements");
     * </pre>
     *
     * @param array           the array to check
     * @param messageSupplier a supplier for the exception message to use if the
     *                        assertion fails
     * @throws IllegalArgumentException if the object array is {@code null} or contains no elements
     * @since 5.0
     */
    public static void assertNotEmpty(Object[] array, Supplier<String> messageSupplier) {
        if (isEmpty(array)) {
            throw new IllegalArgumentException(nullSafeGet(messageSupplier));
        }
    }

    /**
     * Assert that an array contains no {@code null} elements.
     * <p>Note: Does not complain if the array is empty!
     * <pre class="code">Assert.noNullElements(array, "The array must contain non-null elements");</pre>
     *
     * @param array   the array to check
     * @param message the exception message to use if the assertion fails
     * @throws IllegalArgumentException if the object array contains a {@code null} element
     */
    public static void assertNoNullElements(Object[] array, String message) {
        if (array != null) {
            for (Object element : array) {
                if (element == null) {
                    throw new IllegalArgumentException(message);
                }
            }
        }
    }

    /**
     * Assert that an array contains no {@code null} elements.
     * <p>Note: Does not complain if the array is empty!
     * <pre class="code">
     * Assert.noNullElements(array, () -&gt; "The " + arrayType + " array must contain non-null elements");
     * </pre>
     *
     * @param array           the array to check
     * @param messageSupplier a supplier for the exception message to use if the
     *                        assertion fails
     * @throws IllegalArgumentException if the object array contains a {@code null} element
     * @since 5.0
     */
    public static void assertNoNullElements(Object[] array, Supplier<String> messageSupplier) {
        if (array != null) {
            for (Object element : array) {
                if (element == null) {
                    throw new IllegalArgumentException(nullSafeGet(messageSupplier));
                }
            }
        }
    }

    /**
     * Assert that a collection contains elements; that is, it must not be
     * {@code null} and must contain at least one element.
     * <pre class="code">Assert.notEmpty(collection, "Collection must contain elements");</pre>
     *
     * @param collection the collection to check
     * @param message    the exception message to use if the assertion fails
     * @throws IllegalArgumentException if the collection is {@code null} or
     *                                  contains no elements
     */
    public static void assertNotEmpty(Collection<?> collection, String message) {
        if (isEmpty(collection)) {
            throw new IllegalArgumentException(message);
        }
    }

    /**
     * Assert that a collection contains elements; that is, it must not be
     * {@code null} and must contain at least one element.
     * <pre class="code">
     * Assert.notEmpty(collection, () -&gt; "The " + collectionType + " collection must contain elements");
     * </pre>
     *
     * @param collection      the collection to check
     * @param messageSupplier a supplier for the exception message to use if the
     *                        assertion fails
     * @throws IllegalArgumentException if the collection is {@code null} or
     *                                  contains no elements
     * @since 5.0
     */
    public static void assertNotEmpty(Collection<?> collection, Supplier<String> messageSupplier) {
        if (isEmpty(collection)) {
            throw new IllegalArgumentException(nullSafeGet(messageSupplier));
        }
    }

    /**
     * Assert that a collection contains no {@code null} elements.
     * <p>Note: Does not complain if the collection is empty!
     * <pre class="code">Assert.noNullElements(collection, "Collection must contain non-null elements");</pre>
     *
     * @param collection the collection to check
     * @param message    the exception message to use if the assertion fails
     * @throws IllegalArgumentException if the collection contains a {@code null} element
     * @since 5.2
     */
    public static void assertNoNullElements(Collection<?> collection, String message) {
        if (notNull(collection)) {
            for (Object element : collection) {
                if (element == null) {
                    throw new IllegalArgumentException(message);
                }
            }
        }
    }

    /**
     * Assert that a collection contains no {@code null} elements.
     * <p>Note: Does not complain if the collection is empty!
     * <pre class="code">
     * Assert.noNullElements(collection, () -&gt; "Collection " + collectionName + " must contain non-null elements");
     * </pre>
     *
     * @param collection      the collection to check
     * @param messageSupplier a supplier for the exception message to use if the
     *                        assertion fails
     * @throws IllegalArgumentException if the collection contains a {@code null} element
     * @since 5.2
     */
    public static void assertNoNullElements(Collection<?> collection, Supplier<String> messageSupplier) {
        if (collection != null) {
            for (Object element : collection) {
                if (element == null) {
                    throw new IllegalArgumentException(nullSafeGet(messageSupplier));
                }
            }
        }
    }

    /**
     * Assert that a Map contains entries; that is, it must not be {@code null}
     * and must contain at least one entry.
     * <pre class="code">Assert.notEmpty(map, "Map must contain entries");</pre>
     *
     * @param map     the map to check
     * @param message the exception message to use if the assertion fails
     * @throws IllegalArgumentException if the map is {@code null} or contains no entries
     */
    public static void assertNotEmpty(Map<?, ?> map, String message) {
        if (isEmpty(map)) {
            throw new IllegalArgumentException(message);
        }
    }

    /**
     * Assert that a Map contains entries; that is, it must not be {@code null}
     * and must contain at least one entry.
     * <pre class="code">
     * Assert.notEmpty(map, () -&gt; "The " + mapType + " map must contain entries");
     * </pre>
     *
     * @param map             the map to check
     * @param messageSupplier a supplier for the exception message to use if the
     *                        assertion fails
     * @throws IllegalArgumentException if the map is {@code null} or contains no entries
     * @since 5.0
     */
    public static void assertNotEmpty(Map<?, ?> map, Supplier<String> messageSupplier) {
        if (isEmpty(map)) {
            throw new IllegalArgumentException(nullSafeGet(messageSupplier));
        }
    }

    /**
     * Assert that the provided object is an instance of the provided class.
     * <pre class="code">Assert.instanceOf(Foo.class, foo, "Foo expected");</pre>
     *
     * @param type    the type to check against
     * @param obj     the object to check
     * @param message a message which will be prepended to provide further context.
     *                If it is empty or ends in ":" or ";" or "," or ".", a full exception message
     *                will be appended. If it ends in a space, the name of the offending object's
     *                type will be appended. In any other case, a ":" with a space and the name
     *                of the offending object's type will be appended.
     * @throws IllegalArgumentException if the object is not an instance of type
     */
    public static void assertIsInstanceOf(Class<?> type, Object obj, String message) {
        assertNotNull(type, "Type to check against must not be null");
        if (!type.isInstance(obj)) {
            instanceCheckFailed(type, obj, message);
        }
    }

    /**
     * Assert that the provided object is an instance of the provided class.
     * <pre class="code">
     * Assert.instanceOf(Foo.class, foo, () -&gt; "Processing " + Foo.class.getSimpleName() + ":");
     * </pre>
     *
     * @param type            the type to check against
     * @param obj             the object to check
     * @param messageSupplier a supplier for the exception message to use if the
     *                        assertion fails. See {@link #assertIsInstanceOf(Class, Object, String)} for details.
     * @throws IllegalArgumentException if the object is not an instance of type
     * @since 5.0
     */
    public static void assertIsInstanceOf(Class<?> type, Object obj, Supplier<String> messageSupplier) {
        assertNotNull(type, "Type to check against must not be null");
        if (!type.isInstance(obj)) {
            instanceCheckFailed(type, obj, nullSafeGet(messageSupplier));
        }
    }

    /**
     * Assert that the provided object is an instance of the provided class.
     * <pre class="code">Assert.instanceOf(Foo.class, foo);</pre>
     *
     * @param type the type to check against
     * @param obj  the object to check
     * @throws IllegalArgumentException if the object is not an instance of type
     */
    public static void assertIsInstanceOf(Class<?> type, Object obj) {
        assertIsInstanceOf(type, obj, "");
    }

    /**
     * Assert that {@code superType.isAssignableFrom(subType)} is {@code true}.
     * <pre class="code">Assert.isAssignable(Number.class, myClass, "Number expected");</pre>
     *
     * @param superType the super type to check against
     * @param subType   the sub type to check
     * @param message   a message which will be prepended to provide further context.
     *                  If it is empty or ends in ":" or ";" or "," or ".", a full exception message
     *                  will be appended. If it ends in a space, the name of the offending sub type
     *                  will be appended. In any other case, a ":" with a space and the name of the
     *                  offending sub type will be appended.
     * @throws IllegalArgumentException if the classes are not assignable
     */
    public static void assertIsAssignable(Class<?> superType, Class<?> subType, String message) {
        assertNotNull(superType, "Super type to check against must not be null");
        if (subType == null || !superType.isAssignableFrom(subType)) {
            assignableCheckFailed(superType, subType, message);
        }
    }

    /**
     * Assert that {@code superType.isAssignableFrom(subType)} is {@code true}.
     * <pre class="code">
     * Assert.isAssignable(Number.class, myClass, () -&gt; "Processing " + myAttributeName + ":");
     * </pre>
     *
     * @param superType       the super type to check against
     * @param subType         the sub type to check
     * @param messageSupplier a supplier for the exception message to use if the
     *                        assertion fails. See {@link #assertIsAssignable(Class, Class, String)} for details.
     * @throws IllegalArgumentException if the classes are not assignable
     * @since 5.0
     */
    public static void assertIsAssignable(Class<?> superType, Class<?> subType, Supplier<String> messageSupplier) {
        assertNotNull(superType, "Super type to check against must not be null");
        if (subType == null || !superType.isAssignableFrom(subType)) {
            assignableCheckFailed(superType, subType, nullSafeGet(messageSupplier));
        }
    }

    /**
     * Assert that {@code superType.isAssignableFrom(subType)} is {@code true}.
     * <pre class="code">Assert.isAssignable(Number.class, myClass);</pre>
     *
     * @param superType the super type to check
     * @param subType   the sub type to check
     * @throws IllegalArgumentException if the classes are not assignable
     */
    public static void assertIsAssignable(Class<?> superType, Class<?> subType) {
        assertIsAssignable(superType, subType, "");
    }


    private static void instanceCheckFailed(Class<?> type, Object obj, String msg) {
        String className = (obj != null ? obj.getClass().getName() : "null");
        String result = "";
        boolean defaultMessage = true;
        if (hasLength(msg)) {
            if (endsWithSeparator(msg)) {
                result = msg + " ";
            } else {
                result = messageWithTypeName(msg, className);
                defaultMessage = false;
            }
        }
        if (defaultMessage) {
            result = result + ("Object of class [" + className + "] must be an instance of " + type);
        }
        throw new IllegalArgumentException(result);
    }

    private static void assignableCheckFailed(Class<?> superType, Class<?> subType, String msg) {
        String result = "";
        boolean defaultMessage = true;
        if (hasLength(msg)) {
            if (endsWithSeparator(msg)) {
                result = msg + " ";
            } else {
                result = messageWithTypeName(msg, subType);
                defaultMessage = false;
            }
        }
        if (defaultMessage) {
            result = result + (subType + " is not assignable to " + superType);
        }
        throw new IllegalArgumentException(result);
    }

    private static boolean endsWithSeparator(String msg) {
        return (msg.endsWith(":") || msg.endsWith(";") || msg.endsWith(",") || msg.endsWith("."));
    }

    private static String messageWithTypeName(String msg, Object typeName) {
        return msg + (msg.endsWith(" ") ? "" : ": ") + typeName;
    }


    private static String nullSafeGet(Supplier<String> messageSupplier) {
        return (messageSupplier != null ? messageSupplier.get() : null);
    }


    // ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    //                                        IO工具API
    // ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++


    /**
     * Copy the contents of the given InputStream into a new byte array.
     * <p>Leaves the stream open when done.
     *
     * @param in the stream to copy from (may be {@code null} or empty)
     * @return the new byte array that has been copied to (possibly empty)
     * @throws IOException in case of I/O errors
     */
    public static byte[] copyToByteArray(InputStream in) throws IOException {
        if (in == null) {
            return new byte[0];
        }

        ByteArrayOutputStream out = new ByteArrayOutputStream(BUFFER_SIZE);
        copy(in, out);
        return out.toByteArray();
    }

    /**
     * Copy the contents of the given InputStream into a String.
     * <p>Leaves the stream open when done.
     *
     * @param in      the InputStream to copy from (may be {@code null} or empty)
     * @param charset the {@link Charset} to use to decode the bytes
     * @return the String that has been copied to (possibly empty)
     * @throws IOException in case of I/O errors
     */
    public static String copyToString(InputStream in, Charset charset) throws IOException {
        if (in == null) {
            return "";
        }

        StringBuilder out = new StringBuilder(BUFFER_SIZE);
        InputStreamReader reader = new InputStreamReader(in, charset);
        char[] buffer = new char[BUFFER_SIZE];
        int charsRead;
        while ((charsRead = reader.read(buffer)) != -1) {
            out.append(buffer, 0, charsRead);
        }
        return out.toString();
    }

    /**
     * Copy the contents of the given {@link ByteArrayOutputStream} into a {@link String}.
     * <p>This is a more effective equivalent of {@code new String(baos.toByteArray(), charset)}.
     *
     * @param baos    the {@code ByteArrayOutputStream} to be copied into a String
     * @param charset the {@link Charset} to use to decode the bytes
     * @return the String that has been copied to (possibly empty)
     * @since 5.2.6
     */
    public static String copyToString(ByteArrayOutputStream baos, Charset charset) {
        assertNotNull(baos, "No ByteArrayOutputStream specified");
        assertNotNull(charset, "No Charset specified");
        try {
            // Can be replaced with toString(Charset) call in Java 10+
            return baos.toString(charset.name());
        } catch (UnsupportedEncodingException ex) {
            // Should never happen
            throw new IllegalArgumentException("Invalid charset name: " + charset, ex);
        }
    }

    /**
     * Copy the contents of the given byte array to the given OutputStream.
     * <p>Leaves the stream open when done.
     *
     * @param in  the byte array to copy from
     * @param out the OutputStream to copy to
     * @throws IOException in case of I/O errors
     */
    public static void copy(byte[] in, OutputStream out) throws IOException {
        assertNotNull(in, "No input byte array specified");
        assertNotNull(out, "No OutputStream specified");

        out.write(in);
        out.flush();
    }

    /**
     * Copy the contents of the given String to the given OutputStream.
     * <p>Leaves the stream open when done.
     *
     * @param in      the String to copy from
     * @param charset the Charset
     * @param out     the OutputStream to copy to
     * @throws IOException in case of I/O errors
     */
    public static void copy(String in, Charset charset, OutputStream out) throws IOException {
        assertNotNull(in, "No input String specified");
        assertNotNull(charset, "No Charset specified");
        assertNotNull(out, "No OutputStream specified");

        Writer writer = new OutputStreamWriter(out, charset);
        writer.write(in);
        writer.flush();
    }

    /**
     * Copy the contents of the given InputStream to the given OutputStream.
     * <p>Leaves both streams open when done.
     *
     * @param in  the InputStream to copy from
     * @param out the OutputStream to copy to
     * @return the number of bytes copied
     * @throws IOException in case of I/O errors
     */
    public static int copy(InputStream in, OutputStream out) throws IOException {
        assertNotNull(in, "No InputStream specified");
        assertNotNull(out, "No OutputStream specified");

        int byteCount = 0;
        byte[] buffer = new byte[BUFFER_SIZE];
        int bytesRead;
        while ((bytesRead = in.read(buffer)) != -1) {
            out.write(buffer, 0, bytesRead);
            byteCount += bytesRead;
        }
        out.flush();
        return byteCount;
    }

    /**
     * Copy a range of content of the given InputStream to the given OutputStream.
     * <p>If the specified range exceeds the length of the InputStream, this copies
     * up to the end of the stream and returns the actual number of copied bytes.
     * <p>Leaves both streams open when done.
     *
     * @param in    the InputStream to copy from
     * @param out   the OutputStream to copy to
     * @param start the position to start copying from
     * @param end   the position to end copying
     * @return the number of bytes copied
     * @throws IOException in case of I/O errors
     * @since 4.3
     */
    public static long copyRange(InputStream in, OutputStream out, long start, long end) throws IOException {
        assertNotNull(in, "No InputStream specified");
        assertNotNull(out, "No OutputStream specified");

        long skipped = in.skip(start);
        if (skipped < start) {
            throw new IOException("Skipped only " + skipped + " bytes out of " + start + " required");
        }

        long bytesToCopy = end - start + 1;
        byte[] buffer = new byte[(int) Math.min(BUFFER_SIZE, bytesToCopy)];
        while (bytesToCopy > 0) {
            int bytesRead = in.read(buffer);
            if (bytesRead == -1) {
                break;
            } else if (bytesRead <= bytesToCopy) {
                out.write(buffer, 0, bytesRead);
                bytesToCopy -= bytesRead;
            } else {
                out.write(buffer, 0, (int) bytesToCopy);
                bytesToCopy = 0;
            }
        }
        return (end - start + 1 - bytesToCopy);
    }

    /**
     * Drain the remaining content of the given InputStream.
     * <p>Leaves the InputStream open when done.
     *
     * @param in the InputStream to drain
     * @return the number of bytes read
     * @throws IOException in case of I/O errors
     * @since 4.3
     */
    public static int drain(InputStream in) throws IOException {
        assertNotNull(in, "No InputStream specified");
        byte[] buffer = new byte[BUFFER_SIZE];
        int bytesRead = -1;
        int byteCount = 0;
        while ((bytesRead = in.read(buffer)) != -1) {
            byteCount += bytesRead;
        }
        return byteCount;
    }

    /**
     * Return an efficient empty {@link InputStream}.
     *
     * @return a {@link ByteArrayInputStream} based on an empty byte array
     * @since 4.2.2
     */
    public static InputStream emptyInput() {
        return new ByteArrayInputStream(EMPTY_CONTENT);
    }
}
