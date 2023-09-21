package com.quan.tools.util;

import com.quan.tools.log.Logger;
import com.quan.tools.log.LoggerFactory;

/**
 * StringUtils
 *
 * @author Force-oneself
 * @date 2022-10-20
 */
public class StringUtils {

    private final static Logger LOGGER = LoggerFactory.getLogger(StringUtils.class);

    private StringUtils() {
    }

    public static boolean isEmpty(Object str) {
        return (str == null || "".equals(str));
    }

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
        return (str != null && str.length() > 0);
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

    public static boolean startsWithIgnoreCase(String str, String prefix) {
        return str != null && prefix != null && str.length() >= prefix.length()
                && str.regionMatches(true, 0, prefix, 0, prefix.length());
    }

    public static boolean endsWithIgnoreCase(String str, String suffix) {
        return str != null && suffix != null
                && str.length() >= suffix.length()
                && str.regionMatches(true, str.length() - suffix.length(), suffix, 0, suffix.length());
    }

    /**
     * 是否为全空白字符
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
}
