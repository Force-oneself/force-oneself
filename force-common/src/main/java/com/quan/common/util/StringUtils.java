package com.quan.common.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * 字符串处理的工具类
 * 所有字符串的相关验证，操作，加工的方法都统一集中在这里定义。
 * <p>
 * 可以基于开源的工具库，字符串处理类：
 * 1. org.springframework.util.StringUtils
 * 2. org.apache.commons.lang3.StringUtils
 * <p>
 *
 * @author heyq
 * <p>
 * created by heyq
 * Date: 2019/12/15
 */
public final class StringUtils {

    private static final Logger logger = LoggerFactory.getLogger(StringUtils.class);
    private static final StringMapperFunction STRING_MAPPER_FUNCTION = new StringMapperFunction();
    private static Pattern linePattern = Pattern.compile("_(\\w)");
    private static Pattern humpPattern = Pattern.compile("[A-Z]");

    /**
     * 字符串是否为null
     *
     * @param string 给定字符串
     * @return 给定字符串为null
     */
    public static boolean isNull(CharSequence string) {
        return Objects.isNull(string);
    }

    /**
     * 字符串不为null
     *
     * @param string 给定字符串
     * @return 给定字符串不为null
     */
    public static boolean notNull(CharSequence string) {
        return Objects.nonNull(string);
    }

    /**
     * 字符串是否为null或空或有空格等空白字符组成
     *
     * @param string 给定字符串
     * @return 给定字符串为null或为空或由空白字符组成
     */
    public static boolean isBlank(CharSequence string) {
        return Objects.isNull(string) || string.toString().trim().isEmpty();
    }

    /**
     * 字符串是否为null或空或有空格等空白字符组成
     *
     * @param string 给定字符串
     * @return 给定字符串为null或为空或由空白字符组成
     */
    public static boolean isBlank(String string) {
        return Objects.isNull(string) || string.trim().isEmpty();
    }

    /**
     * 字符串是否不是null且不空且不仅由空格等空白字符组成
     *
     * @param string 给定字符串
     * @return 给定字符串不是null且不空且不仅由空格等空白字符组成
     */
    public static boolean notBlank(CharSequence string) {
        return !isBlank(string);
    }

    /**
     * 字符串是否不是null且不空且不仅由空格等空白字符组成
     *
     * @param string 给定字符串
     * @return 给定字符串不是null且不空且不仅由空格等空白字符组成
     */
    public static boolean notBlank(String string) {
        return !isBlank(string);
    }

    /**
     * 字符串是否为null或空
     *
     * @param string 给定字符串
     * @return 给定字符串为null或为空
     */
    public static boolean isEmpty(CharSequence string) {
        return Objects.isNull(string) || string.length() == 0;
    }

    /**
     * 字符串是否为null或空
     *
     * @param string 给定字符串
     * @return 给定字符串为null或为空
     */
    public static boolean isEmpty(String string) {
        return Objects.isNull(string) || string.isEmpty();
    }

    /**
     * 字符串是否不是null且不空
     *
     * @param string 给定字符串
     * @return 给定字符串不是null且不空
     */
    public static boolean notEmpty(CharSequence string) {
        return !isEmpty(string);
    }

    /**
     * 字符串是否不是null且不空
     *
     * @param string 给定字符串
     * @return 给定字符串不是null且不空
     */
    public static boolean notEmpty(String string) {
        return !isEmpty(string);
    }

    /**
     * 如果字符串str为null 返回默认字符串
     *
     * @param str        给定字符串
     * @param defaultStr 默认字符串
     * @return 给定字符串为null 返回默认字符串 否则返回给定字符串
     */
    public static String defaultIfNull(String str, String defaultStr) {
        return ObjectUtils.defaultIfNull(str, defaultStr);
    }

    /**
     * 如果字符串str为空或空白 返回默认字符串
     *
     * @param str        给定字符串
     * @param defaultStr 默认字符串
     * @return 给定字符串为空或空白 返回默认字符串 否则返回给定字符串
     */
    public static String defaultIfBlank(String str, String defaultStr) {
        return isBlank(str) ? defaultStr : str;
    }

    /**
     * 如果字符串str为空 返回默认字符串
     *
     * @param str        给定字符串
     * @param defaultStr 默认字符串
     * @return 给定字符串为空 返回默认字符串 否则返回给定字符串
     */
    public static String defaultIfEmpty(String str, String defaultStr) {
        return isEmpty(str) ? defaultStr : str;
    }

    /**
     * 如果字符串str为null 返回空字符串
     *
     * @param str 给定字符串
     * @return 给定字符串为null 返回空字符串
     */
    public static String emptyIfNull(String str) {
        return defaultIfNull(str, "");
    }

    /**
     * 字符串是否全是数字
     * 字符串为空时返回false
     *
     * @param str 给定字符串
     * @return 给定字符串全由数字组成
     */
    public static boolean isAllDigit(String str) {
        if (isEmpty(str)) {
            return false;
        }
        for (char aChar : str.toCharArray()) {
            if (!Character.isDigit(aChar)) {
                return false;
            }
        }
        return true;
    }

    /**
     * 字符串是否不全是数字
     * 字符串为空时返回true
     *
     * @param str 给定字符串
     * @return 给定字符串不全由数字组成
     */
    public static boolean notAllDigit(String str) {
        return !isAllDigit(str);
    }

    /**
     * 下划线转驼峰
     *
     * @param underscore 下划线字符
     * @return
     */
    public static String lineToHump(String underscore) {
        underscore = underscore.toLowerCase();
        Matcher matcher = linePattern.matcher(underscore);
        StringBuffer sb = new StringBuffer();
        while (matcher.find()) {
            matcher.appendReplacement(sb, matcher.group(1).toUpperCase());
        }
        matcher.appendTail(sb);
        return sb.toString();
    }

    /**
     * 驼峰转下划线
     *
     * @param hump 驼峰字符串
     * @return
     */
    public static String humpToLine(String hump) {
        Matcher matcher = humpPattern.matcher(hump);
        StringBuffer sb = new StringBuffer();
        while (matcher.find()) {
            matcher.appendReplacement(sb, "_" + matcher.group(0).toLowerCase());
        }
        matcher.appendTail(sb);
        return sb.toString();
    }

    /**
     * 获取字符串长度
     *
     * @param str 给定字符串
     * @return 给定字符串长度
     */
    public static int length(CharSequence str) {
        return Objects.isNull(str) ? 0 : str.length();
    }

    /**
     * 计算给定的字符串的长度
     * 一个汉字的长度为2
     * 一个字符的长度为1
     *
     * @param str 给定字符串
     * @return 给定字符串字节数
     */
    public static int lengthInByte(String str) {
        if (isEmpty(str)) {
            return 0;
        }
        char[] chars = str.toCharArray();
        int length = 0;
        for (char aChar : chars) {
            length += (aChar >= '\u0391' && aChar <= '\uFFE5' ? 2 : 1);
        }
        return length;
    }

    /**
     * 删除字符串开始指定数量字符
     *
     * @param str       给定字符串
     * @param delLength 删除字符数量
     * @return 删除指定数量字符后字符串
     */
    public static String delStartChars(String str, int delLength) {
        if (isEmpty(str)) {
            return str;
        }
        int strLength = str.length();
        if (delLength == strLength) {
            return "";
        }
        if (delLength <= 0 || delLength > strLength) {
            return str;
        }
        return str.substring(delLength, strLength);
    }

    /**
     * 删除字符串最后指定数量字符
     *
     * @param str       给定字符串
     * @param delLength 删除字符数量
     * @return 删除指定数量字符后字符串
     */
    public static String delEndChars(String str, int delLength) {
        if (isEmpty(str)) {
            return str;
        }
        int strLength = str.length();
        if (delLength == strLength) {
            return "";
        }
        if (delLength <= 0 || delLength > strLength) {
            return str;
        }
        return str.substring(0, strLength - delLength);
    }

    /**
     * 全角字符转化为半角字符
     *
     * @param string 给定字符串
     * @return 转换为半角后字符串
     */
    public static String fullToHalfWidth(String string) {
        if (isEmpty(string)) {
            return emptyIfNull(string);
        }
        char[] chars = string.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == 12288) {
                //空格
                chars[i] = ' ';
            } else if (chars[i] >= 65281 && chars[i] <= 65374) {
                //全角字符
                chars[i] = (char) (chars[i] - 65248);
            }
        }
        return new String(chars);
    }

    /**
     * 半角字符转化为全角字符
     *
     * @param string 给定字符串
     * @return 转换为全角后字符串
     */
    public static String halfToFullWidth(String string) {
        if (isEmpty(string)) {
            return emptyIfNull(string);
        }
        char[] source = string.toCharArray();
        for (int i = 0; i < source.length; i++) {
            if (source[i] == ' ') {
                //空格
                source[i] = (char) 12288;
            } else if (source[i] >= 33 && source[i] <= 126) {
                //半角字符
                source[i] = (char) (source[i] + 65248);
            }
        }
        return new String(source);
    }

    /**
     * 删除字符串内所有空白字符 [空格 tab 换行]
     *
     * @param string 给定字符串
     * @return 删除空白字符后字符串
     */
    public static String cleanWhiteSpaceChar(String string) {
        if (isEmpty(string)) {
            return "";
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (char aChar : string.toCharArray()) {
            if (!Character.isWhitespace(aChar)) {
                stringBuilder.append(aChar);
            }
        }
        return stringBuilder.toString();
    }

    /**
     * 把给定的字符串用给定的字符分割
     *
     * @param string 给定的字符串
     * @param ch     分隔的字符
     * @return 分割后的字符串数组
     */
    public static String[] split(String string, char ch) {
        ArrayList<String> stringList = new ArrayList<>();
        if (isEmpty(string)) {
            return new String[0];
        }
        char[] chars = string.toCharArray();
        int nextStart = 0;
        for (int w = 0; w < chars.length; w++) {
            if (ch == chars[w]) {
                stringList.add(new String(chars, nextStart, w - nextStart));
                nextStart = w + 1;
                //当最后一位是分割符的话，就再添加一个空的字符串到分割数组中去
                if (nextStart == chars.length) {
                    stringList.add("");
                }
            }
        }
        if (nextStart < chars.length) {
            //如果最后一位不是分隔符的话，就将最后一个分割符到最后一个字符中间的左右字符串作为一个字符串添加到分割数组中去
            stringList.add(new String(chars, nextStart, chars.length - 1 - nextStart + 1));
        }
        return stringList.toArray(new String[0]);
    }

    /**
     * 把给定的字符串用另一个字符串分割
     *
     * @param string   给定的字符串
     * @param splitStr 分隔字符串
     * @return 分割后的字符串数组, 参数为空时结果数组length为0
     */
    public static String[] split(String string, String splitStr) {
        if (isEmpty(string) || isEmpty(splitStr)) {
            return new String[0];
        }
        return splitToList(string, splitStr).toArray(new String[0]);
    }

    /**
     * 按指定长度每字符串分隔指定字符串为字符串数组
     *
     * @param string     指定字符串
     * @param eachLength 每段字符串指定长度
     * @return 分隔后字符串数组
     */
    public static String[] split(String string, int eachLength) {
        if (eachLength <= 0) {
            throw new IllegalArgumentException("每段字符串长度小于等于0");
        }
        if (isEmpty(string)) {
            return new String[0];
        }
        int strLength = string.length();
        String[] strings = new String[strLength / eachLength + ((strLength % eachLength == 0) ? 0 : 1)];
        int currentIndex = 0;
        int currentLength = 0;
        StringBuilder eachString = new StringBuilder(eachLength);
        for (int i = 0; i < strLength; i++) {
            eachString.append(string.charAt(i));
            if (++currentLength >= eachLength) {
                strings[currentIndex++] = eachString.toString();
                eachString.delete(0, eachString.length());
                currentLength = 0;
            }
        }
        if (eachString.length() > 0) {
            strings[currentIndex] = eachString.toString();
        }
        return strings;
    }

    /**
     * 把给定的字符串用另一个字符串分割
     *
     * @param string   给定的字符串
     * @param splitStr 分隔字符串
     * @return 分割后的字符串列表
     */
    public static List<String> splitToList(String string, String splitStr) {
        return splitToList(string, splitStr, STRING_MAPPER_FUNCTION);
    }

    /**
     * 将给定字符串分隔为目标类型对象列表
     *
     * @param string   给定字符串
     * @param splitStr 分隔字符串
     * @param mapper   分隔后结果映射函数
     * @param <R>      分隔后结果对象类型
     * @return 分隔后结果, 以整型数组方式返回
     */
    public static <R> List<R> splitToList(String string, String splitStr, Function<String, R> mapper) {
        if (isEmpty(string) || Objects.isNull(splitStr)) {
            return Collections.emptyList();
        }
        if (isEmpty(splitStr)) {
            return string.chars().mapToObj(a -> (char) a).map(String::valueOf).map(mapper).collect(Collectors.toList());
        }
        List<R> result = new ArrayList<>();
        int fromIndex = 0;
        int splitStrLength = splitStr.length() == 0 ? 1 : splitStr.length();
        int totalLength = string.length();
        int index;
        while ((index = string.indexOf(splitStr, fromIndex)) != -1 && fromIndex < totalLength) {
            //将字符串添加入列表
            result.add(mapper.apply(string.substring(fromIndex, index)));
            fromIndex = index + splitStrLength;
        }
        result.add(mapper.apply(string.substring(fromIndex)));
        return result;
    }

    /**
     * 将给定字符串分隔为指定长度数组
     * 分组数已满则剩余字符串填充到最后一个分组
     * 分组未满则补充空字符串
     *
     * @param string   给定字符串
     * @param splitStr 分隔字符串
     * @param partNum  数组长度
     * @return 分隔后字符串数组
     */
    public static String[] splitToMultipart(String string, String splitStr, int partNum) {
        if (partNum <= 0) {
            return new String[0];
        }
        if (partNum == 1) {
            return new String[]{emptyIfNull(string)};
        }
        String[] strings = ArrayUtils.createWithDefault(partNum, String.class, "");
        if (isEmpty(string) || Objects.isNull(splitStr)) {
            return strings;
        }
        if (isEmpty(splitStr)) {
            int prePart = partNum - 1;
            for (int i = 0; i < prePart; i++) {
                strings[i] = String.valueOf(string.charAt(i));
            }
            strings[prePart] = string.substring(prePart);
            return strings;
        }
        int fromIndex = 0, partIndex = 0;
        int splitStrLength = splitStr.length();
        int index;
        while ((index = string.indexOf(splitStr, fromIndex)) != -1 && partIndex <= partNum - 2) {
            //将字符串添加入列表
            strings[partIndex++] = string.substring(fromIndex, index);
            fromIndex = index + splitStrLength;
        }
        //将字符串添加入列表
        strings[partIndex] = fromIndex < string.length() ? string.substring(fromIndex) : "";
        return strings;
    }

    /**
     * 在给定的字符串中，用新的字符替换所有旧的字符
     *
     * @param string  给定的字符串
     * @param oldChar 旧的字符
     * @param newChar 新的字符
     * @return 替换后的字符串
     */
    public static String replace(String string, char oldChar, char newChar) {
        if (isEmpty(string)) {
            return "";
        }
        char[] chars = string.toCharArray();
        for (int w = 0; w < chars.length; w++) {
            if (chars[w] != oldChar) {
                continue;
            }
            chars[w] = newChar;
        }
        return new String(chars);
    }

    /**
     * 在给定的字符串中，用新的字符串替换所有旧的字符串
     *
     * @param string 给定的字符串
     * @param oldStr 旧的字符串
     * @param newStr 新的字符串
     * @return 替换后的字符串
     */
    public static String replace(String string, String oldStr, String newStr) {
        if (isEmpty(string)) {
            return "";
        }
        if (Objects.isNull(oldStr)) {
            oldStr = "";
        }
        if (Objects.isNull(newStr)) {
            newStr = "";
        }
        if (Objects.equals(oldStr, newStr)) {
            return string;
        }
        return String.join(newStr, splitToList(string, oldStr));
    }

    /**
     * 删除字符串中所有指定的字符
     *
     * @param string 源字符串
     * @param ch     要删除的字符
     * @return 删除后的字符串
     */
    public static String deleteChar(String string, char ch) {
        if (isEmpty(string)) {
            return "";
        }
        StringBuilder sb = new StringBuilder(string.length());
        for (char cha : string.toCharArray()) {
            if (cha != ch) {
                sb.append(cha);
            }
        }
        return sb.toString();
    }

    /**
     * 删除字符串中指定索引处的字符
     *
     * @param string 给定字符串
     * @param index  给定位置
     * @return 删除自定索引处字符或的数组
     */
    public static String deleteChar(String string, int index) {
        if (isEmpty(string)) {
            return "";
        }
        int length = string.length();
        char[] chars = string.toCharArray();
        if (index < 0) {
            return string;
        }
        if (index >= length) {
            return string;
        }
        if (index == 0) {
            return new String(chars, 1, length - 1);
        }
        if (index == length - 1) {
            return new String(chars, 0, length - 1);
        }
        return new String(chars, 0, index) + new String(chars, index + 1, length - index - 1);
    }

    /**
     * 若字符串指定位置字符与给定字符相同 删除字符串中指定位置处的字符
     *
     * @param string 给定字符串
     * @param index  给定位置
     * @param ch     如果同给定位置处的字符相同，则将给定位置处的字符删除
     * @return 删除自定索引处字符或的数组
     */
    public static String deleteChar(String string, int index, char ch) {
        if (isEmpty(string)) {
            return "";
        }
        if (string.charAt(index) != ch) {
            return string;
        }
        return deleteChar(string, index);
    }

    /**
     * 对字符串指定索引中的字符进行转换操作
     *
     * @param string       给定字符串
     * @param beginIndex   开始索引
     * @param endIndex     结束索引
     * @param castFunction 在给定范围字符转换函数
     * @return 转换指定索引范围内字符后字符串
     */
    public static String castCharBetween(String string, int beginIndex, int endIndex, Function<Character, Character> castFunction) {
        if (isEmpty(string)) {
            return "";
        }
        if (beginIndex >= endIndex) {
            return string;
        }
        char[] chars = string.toCharArray();
        int length = chars.length;
        for (int i = 0; i < length; i++) {
            if (i < beginIndex || i >= endIndex) {
                continue;
            }
            chars[i] = castFunction.apply(chars[i]);
        }
        return new String(chars);
    }

    /**
     * 将给定字符串中给定的区域的字符转换成小写
     *
     * @param str 给定字符串中
     * @return 新的字符串
     */
    public static String toLowerCase(String str) {
        return isEmpty(str) ? "" : str.toLowerCase();
    }

    /**
     * 将给定字符串中给定的区域的字符转换成小写
     *
     * @param str        给定字符串中
     * @param beginIndex 开始索引（包括）
     * @param endIndex   结束索引（不包括）
     * @return 新的字符串
     */
    public static String toLowerCase(String str, int beginIndex, int endIndex) {
        return castCharBetween(str, beginIndex, endIndex, Character::toLowerCase);
    }

    /**
     * 将给定字符串中给定的区域的字符转换成大写
     *
     * @param str 给定字符串中
     * @return 新的字符串
     */
    public static String toUpperCase(String str) {
        return isEmpty(str) ? "" : str.toUpperCase();
    }

    /**
     * 将给定字符串中给定的区域的字符转换成大写
     *
     * @param str        给定字符串
     * @param beginIndex 开始索引（包括）
     * @param endIndex   结束索引（不包括）
     * @return 新的字符串
     */
    public static String toUpperCase(String str, int beginIndex, int endIndex) {
        return castCharBetween(str, beginIndex, endIndex, Character::toUpperCase);
    }

    /**
     * 将字符串首字母小写
     *
     * @param str 给定字符串
     * @return 字符串首字母转化为小写后字符串
     */
    public static String lowerCaseFirstChar(String str) {
        if (isEmpty(str)) {
            return "";
        }
        char firstChar = str.charAt(0);
        return !Character.isLetter(firstChar) || Character.isLowerCase(firstChar) ? str
                : Character.toLowerCase(firstChar) + str.substring(1);
    }

    /**
     * 将字符串首字母大写
     *
     * @param str 给定字符串
     * @return 字符串首字母转化为大写后字符串
     */
    public static String upperCaseFirstChar(String str) {
        if (isEmpty(str)) {
            return "";
        }
        char firstChar = str.charAt(0);
        return !Character.isLetter(firstChar) || Character.isUpperCase(firstChar) ? str
                : Character.toUpperCase(firstChar) + str.substring(1);
    }

    /**
     * 判断给定的字符串是否以匹配的字符串开头，忽略大小写
     *
     * @param sourceString 给定的字符串
     * @param matchString  匹配的字符串
     * @return 给定字符串以匹配的字符串开始
     */
    public static boolean startWith(String sourceString, String matchString) {
        if (isEmpty(sourceString)) {
            return false;
        }
        if (isEmpty(matchString)) {
            return true;
        }
        return sourceString.startsWith(matchString);
    }

    /**
     * 判断给定的字符串是否以匹配的字符串开头，忽略大小写
     *
     * @param sourceString 给定的字符串
     * @param matchString  匹配的字符串
     * @return 忽略大小写后 给定字符串以匹配的字符串开始
     */
    public static boolean startWithIgnoreCase(String sourceString, String matchString) {
        if (isEmpty(sourceString)) {
            return false;
        }
        if (isEmpty(matchString)) {
            return true;
        }
        int matchStrLength = matchString.length();
        int sourceStrLength = sourceString.length();
        if (matchStrLength > sourceStrLength) {
            return false;
        }
        if (matchStrLength < sourceStrLength) {
            sourceString = sourceString.substring(0, matchStrLength);
        }
        return matchString.equalsIgnoreCase(sourceString);
    }

    /**
     * 判断给定的字符串是否以匹配的字符串结尾，不忽略大小写
     *
     * @param sourceString 给定的字符串
     * @param matchString  匹配的字符串
     * @return 给定字符串以匹配的字符串结束
     */
    public static boolean endsWith(String sourceString, String matchString) {
        if (isEmpty(sourceString)) {
            return false;
        }
        if (isEmpty(matchString)) {
            return true;
        }
        return sourceString.endsWith(matchString);
    }

    /**
     * 判断给定的字符串是否以匹配的字符串结尾，不忽略大小写
     *
     * @param sourceString 给定的字符串
     * @param matchString  匹配的字符串
     * @return 忽略大小写后 给定字符串以匹配的字符串结束
     */
    public static boolean endsWithIgnoreCase(String sourceString, String matchString) {
        if (isEmpty(sourceString)) {
            return false;
        }
        if (isEmpty(matchString)) {
            return true;
        }
        int matchStrLength = matchString.length();
        int sourceStrLength = sourceString.length();
        if (matchStrLength > sourceStrLength) {
            return false;
        }
        if (matchStrLength < sourceStrLength) {
            sourceString = sourceString.substring(sourceStrLength - matchStrLength, sourceStrLength);
        }
        return matchString.equalsIgnoreCase(sourceString);
    }

    /**
     * 缩减字符串到指定长度
     *
     * @param str       目标字符串
     * @param maxLength 指定最大长度
     * @return 缩减后字符串
     */
    public static String retrench(String str, int maxLength) {
        if (isEmpty(str)) {
            return "";
        }
        if (maxLength <= 0) {
            return "";
        }
        return str.length() <= maxLength ? str : str.substring(0, maxLength);
    }

    /**
     * 缩减字符串 如缩减 将指定字符串拼接到尾部
     * 检查字符串长度，如果字符串的长度超过maxLength，就截取字符串前maxLength个字符并在末尾拼上appendString
     *
     * @param str          目标字符串
     * @param maxLength    指定最大长度
     * @param appendString 后缀字符串
     * @return 缩减后字符串 若有缩减 则添加后缀字符串
     */
    public static String retrench(String str, int maxLength, String appendString) {
        if (isEmpty(str)) {
            return "";
        }
        if (maxLength <= 0) {
            return emptyIfNull(appendString);
        }
        if (str.length() <= maxLength) {
            return str;
        }
        StringBuilder stringBuilder = new StringBuilder().append(str, 0, maxLength);
        if (notEmpty(appendString)) {
            stringBuilder.append(appendString);
        }
        return stringBuilder.toString();
    }

    /**
     * 获取字符串对应Hex字符串
     *
     * @param string 字符串
     * @return 转换后Hex字符串
     */
    public static String toHexString(String string) {
        if (isEmpty(string)) {
            return "";
        }
        return ByteUtils.toHexString(string.getBytes());
    }

    /**
     * 从Hex字符串获取原字符串
     *
     * @param hexString Hex字符串
     * @return 原字符串
     */
    public static String fromHexString(String hexString) {
        if (isEmpty(hexString)) {
            return "";
        }
        return new String(ByteUtils.fromHexString(hexString));
    }

    /**
     * 将字符串中指定左符号和右符号之间内容裁剪出
     * 不包含左右符号
     *
     * @param str         字符串
     * @param leftSymbol  左符号
     * @param rightSymbol 右符号
     * @return 左右符号中字符串
     */
    public static String getContentBetween(String str, String leftSymbol, String rightSymbol) {
        return getContentBetween(str, leftSymbol, rightSymbol, false);
    }

    /**
     * 将字符串中指定左符号和右符号之间内容裁剪出
     * 包含左右符号
     *
     * @param str         字符串
     * @param leftSymbol  左符号
     * @param rightSymbol 右符号
     * @return 左右符号中字符串 包含左右符号
     */
    public static String getContentBetweenWithSymbol(String str, String leftSymbol, String rightSymbol) {
        return getContentBetween(str, leftSymbol, rightSymbol, true);

    }

    private static String getContentBetween(String str, String leftSymbol, String rightSymbol, boolean containSymbol) {
        if (isEmpty(str)) {
            return "";
        }
        int leftIndex = str.indexOf(leftSymbol);
        int rightIndex = str.lastIndexOf(rightSymbol);
        if (leftIndex == -1 || rightIndex == -1 || leftIndex >= rightIndex) {
            return "";
        }
        return containSymbol
                ? str.substring(leftIndex, rightIndex + rightSymbol.length())
                : str.substring(leftIndex + leftSymbol.length(), rightIndex);
    }

    /**
     * 若字符串包含括号 则获取字符串最外层括号内内容(不包含括号) 否则返回空字符串
     * 将获取最前的左括号与最右右括号的内容
     *
     * @param str 给定字符串
     * @return 括号中内容 无完整括号时返回空字符串
     */
    public static String getContentInBracket(String str) {
        return getContentBetween(str, "(", ")");
    }

    /**
     * 将字符串中不在指定左符号和右符号之间内容及左右符号去除
     *
     * @param str         字符串
     * @param leftSymbol  左符号
     * @param rightSymbol 右符号
     * @return 删除左右符号后字符串 不包含左右符号
     */
    public static String delContentBetween(String str, String leftSymbol, String rightSymbol) {
        return delContentBetween(str, leftSymbol, rightSymbol, false);
    }

    /**
     * 将字符串中不在指定左符号和右符号之间内容及左右符号去除
     *
     * @param str         字符串
     * @param leftSymbol  左符号
     * @param rightSymbol 右符号
     * @return 删除左右符号后字符串 包含左右符号
     */
    public static String delContentBetweenWithSymbol(String str, String leftSymbol, String rightSymbol) {
        return delContentBetween(str, leftSymbol, rightSymbol, true);
    }

    /**
     * 将字符串中不在指定左符号和右符号之间内容去除
     *
     * @param containSymbol 结果是否包含左右符号
     */
    private static String delContentBetween(String str, String leftSymbol, String rightSymbol, boolean containSymbol) {
        if (isEmpty(str)) {
            return "";
        }
        int leftIndex = str.indexOf(leftSymbol);
        int rightIndex = str.lastIndexOf(rightSymbol);
        if (leftIndex == -1 || rightIndex == -1 || leftIndex >= rightIndex) {
            return str;
        }
        String leftString = str.substring(0, leftIndex + (containSymbol ? leftSymbol.length() : 0));
        String rightString = str.substring(rightIndex + (containSymbol ? 0 : rightSymbol.length()));
        return leftString + rightString;
    }

    /**
     * 若字符串包含括号 则去除字符串最外层括号内内容(包含括号) 否则返回原字符串
     * 将获取最前的左括号与最右右括号之外的内容
     *
     * @param str 给定字符串
     * @return 括号外内容 无完整括号时返回整个字符串
     */
    public static String delContentInBracket(String str) {
        return delContentBetween(str, "(", ")");
    }

    /**
     * 反转给定字符串字符顺序
     *
     * @param content 给定字符串
     * @return 反转字符顺序后字符串
     */
    public static String reverse(String content) {
        if (isEmpty(content)) {
            return "";
        }
        return new StringBuilder(content).reverse().toString();
    }

    /**
     * 给定字符串是否包含匹配字符
     *
     * @param string 给定字符串
     * @param str    匹配字符串
     * @return 给定字符串是否包含匹配字符串
     */
    public static boolean contain(String string, String str) {
        return notEmpty(string) && isEmpty(str) || string.contains(str);
    }

    /**
     * 判断两个字符串是否相
     *
     * @param cs1 字符串1
     * @param cs2 字符串2
     * @return 给定字符串是否相同
     */
    public static boolean equals(CharSequence cs1, CharSequence cs2) {
        return cs1 == cs2 || (
                Objects.nonNull(cs1) && Objects.nonNull(cs2) && cs1.length() == cs2.length()
                        && ((cs1 instanceof String && cs2 instanceof String) ? cs1.equals(cs2) : regionMatches(cs1, cs2, cs1.length()))
        );
    }

    private static boolean regionMatches(CharSequence charSequence1, CharSequence charSequence2, int length) {
        int csLength1 = charSequence1.length();
        int csLength2 = charSequence2.length();
        if (length >= 0 && csLength1 == csLength2 && csLength1 >= length) {
            for (int i = csLength1 - 1; i >= 0; i--) {
                if (charSequence1.charAt(i) != charSequence2.charAt(i)) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    /**
     * suffix不为空时将split及suffix拼接到prefix
     *
     * @param prefix 前缀字符串
     * @param suffix 后缀字符串
     * @return 返回拼接后字符串
     */
    public static String concatIfNotEmpty(String prefix, String suffix) {
        return concatIfNotEmpty(prefix, "", suffix);
    }

    /**
     * suffix不为空时将split及suffix拼接到prefix
     *
     * @param prefix    前缀字符串
     * @param delimiter 分隔字符串
     * @param suffix    后缀字符串
     * @return 返回拼接后字符串
     */
    public static String concatIfNotEmpty(String prefix, String delimiter, String suffix) {
        return isEmpty(suffix) ? emptyIfNull(prefix) : emptyIfNull(prefix) + emptyIfNull(delimiter) + suffix;
    }

    /**
     * 确保str字符串以startWith字符串开始,若不是则在前面追加startWith字符串
     *
     * @param str       给定字符串
     * @param startWith 匹配开始字符串
     * @return 拼接后字符串
     */
    public static String concatIfNotStartWith(String str, String startWith) {
        if (isEmpty(startWith)) {
            return emptyIfNull(str);
        }
        if (isEmpty(str)) {
            return startWith;
        }
        return str.startsWith(startWith) ? str : startWith + str;
    }

    /**
     * 确保str字符串以endWith字符串结尾,若不是则在最后追加endWith字符串
     *
     * @param str     给定字符串
     * @param endWith 匹配结尾字符串
     * @return 拼接后字符串
     */
    public static String concatIfNotEndWith(String str, String endWith) {
        if (isEmpty(endWith)) {
            return emptyIfNull(str);
        }
        if (isEmpty(str)) {
            return endWith;
        }
        return str.endsWith(endWith) ? str : str + endWith;
    }

    /**
     * 确保str字符串不以startWith字符串开头,若是则在删除开始的指定字符串
     *
     * @param str       给定字符串
     * @param startWith 匹配开始字符串
     * @return 删除后字符串
     */
    public static String deleteIfStartWith(String str, String startWith) {
        if (isEmpty(startWith)) {
            return emptyIfNull(str);
        }
        if (isEmpty(str)) {
            return "";
        }
        return str.startsWith(startWith) ? str.substring(startWith.length()) : str;
    }

    /**
     * 确保str字符串不以endWith字符串结尾,若是则在删除最后的指定字符串
     *
     * @param str     给定字符串
     * @param endWith 匹配结尾字符串
     * @return 删除后字符串
     */
    public static String deleteIfEndWith(String str, String endWith) {
        if (isEmpty(endWith)) {
            return emptyIfNull(str);
        }
        if (isEmpty(str)) {
            return "";
        }
        return str.endsWith(endWith) ? str.substring(0, str.length() - endWith.length()) : str;
    }

    /**
     * 检测字符串是否有emoji字符
     */
    public static boolean containEmoji(String source) {
        if (isBlank(source)) {
            return false;
        }
        int len = source.length();
        for (int i = 0; i < len; i++) {
            char codePoint = source.charAt(i);
            if (isEmojiCharacter(codePoint)) {
                return true;
            }
        }
        return false;
    }

    private static boolean isEmojiCharacter(char codePoint) {
        return codePoint == 0x0 || codePoint == 0x9
                || codePoint == 0xA || codePoint == 0xD
                || codePoint >= 0x20 && codePoint <= 0xD7FF
                || codePoint >= 0xE000 && codePoint <= 0xFFFD;
    }

    private static final class StringMapperFunction implements Function<String, String> {

        @Override
        public String apply(String string) {
            return string;
        }

    }

    private StringUtils() {
    }

}

