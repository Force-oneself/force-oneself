package com.quan.common.util;

import java.util.Objects;

/**
 * 字节工具类
 *
 * @author heyq
 * <p>
 * created by heyq
 * Date: 2019/12/15
 */
public final class ByteUtils {

    /**
     * 字节数组转换成16进制字符串
     *
     * @param bytes 字节数组
     * @return 转换后hex字符串
     */
    public static String toHexString(byte... bytes) {
        if (Objects.isNull(bytes)) {
            return "";
        }
        StringBuilder hexStringBuilder = new StringBuilder();
        String s;
        for (byte b : bytes) {
            s = Integer.toHexString(b & 0xFF).toUpperCase();
            if (s.length() < 2) {
                hexStringBuilder.append(0);
            }
            hexStringBuilder.append(s);
        }
        return hexStringBuilder.toString();
    }

    /**
     * 16进制字符串转字节数组
     *
     * @param hexString hex字符串
     * @return 转换后字节数组
     */
    public static byte[] fromHexString(String hexString) {
        if (StringUtils.isBlank(hexString)) {
            return new byte[0];
        }
        char[] chars = hexString.toLowerCase().toCharArray();
        int size = hexString.length() >> 1;
        byte[] bytes = new byte[size];
        int index = 0;
        for (int i = 0; i < size; i++) {
            byte highDit = (byte) (Character.digit(chars[index], 16) & 0xFF);
            byte lowDit = (byte) (Character.digit(chars[index + 1], 16) & 0xFF);
            bytes[i] = (byte) (highDit << 4 | (lowDit & 0xff));
            index += 2;
        }
        return bytes;
    }

    /**
     * 字节数组转换为二进制字符串
     *
     * @param bytes 字节数组
     * @return 转换后二进制字符串
     */
    public static String toBinaryString(byte... bytes) {
        if (Objects.isNull(bytes)) {
            return "";
        }
        StringBuilder binaryStringBuilder = new StringBuilder();
        String s;
        for (byte b : bytes) {
            s = Integer.toBinaryString(b | 256);
            int length = s.length();
            binaryStringBuilder.append(s, length - 8, length);
        }
        return binaryStringBuilder.toString();
    }

    /**
     * 二进制字符串转换为字节数组
     *
     * @param binaryString 二进制字符串
     * @return 转换后字节数组
     */
    public static byte[] fromBinaryString(String binaryString) {
        if (StringUtils.isBlank(binaryString)) {
            return new byte[0];
        }
        String[] temp = StringUtils.split(binaryString, 8);
        byte[] b = new byte[temp.length];
        for (int i = 0; i < b.length; i++) {
            b[i] = Long.valueOf(temp[i], 2).byteValue();
        }
        return b;
    }

    /**
     * 将一个short转换成字节数组
     *
     * @param shortNum short
     * @return 字节数组
     */
    public static byte[] valueOf(short shortNum) {
        byte[] shortBuf = new byte[2];
        for (int i = 0; i < 2; i++) {
            int offset = (shortBuf.length - 1 - i) * 8;
            shortBuf[i] = (byte) ((shortNum >>> offset) & 0xff);
        }
        return shortBuf;
    }

    /**
     * 将一个int转换成字节数组
     *
     * @param intNum int
     * @return 字节数组
     */
    public static byte[] valueOf(int intNum) {
        byte[] b = new byte[4];
        for (int i = 0; i < 4; i++) {
            int offset = (b.length - 1 - i) * 8;
            b[i] = (byte) ((intNum >>> offset) & 0xFF);
        }
        return b;
    }

    private ByteUtils() {
    }

}
