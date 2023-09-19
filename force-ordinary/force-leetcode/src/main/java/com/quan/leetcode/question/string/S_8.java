package com.quan.leetcode.question.string;

import java.util.ArrayList;
import java.util.List;

/**
 * S_8
 *
 * @author Force-oneself
 * @date 2022-05-18
 */
public class S_8 {

    public int myAtoi1(String s) {
        String trim = s.trim();
        int len = trim.length();
        int mod = 0;
        List<Integer> ant = new ArrayList<>();
        for (int i = 0; i < len; i++) {
            char word = trim.charAt(i);
            if (ant.isEmpty() && word == '0') {
                continue;
            }
            if (mod == 0) {
                if (word == '+') {
                    mod = 1;
                    continue;
                }
                if (word == '-') {
                    mod = -1;
                    continue;
                }
            } else {
                if (word == '+' || word == '-') {
                    return 0;
                }
            }
            boolean digit = Character.isDigit(word);
            if (!digit) {
                break;
            }
            if (mod == 0) {
                mod = 1;
            }
            ant.add(word - '0');
        }
        int size = ant.size();
        long res = 0L;
        long i = 1;
        for (int j = size - 1; j >= 0; j--, i *= 10) {
            Integer num = ant.get(j);
            res += num * i;

        }
        res = res * mod;
        if (res > Integer.MAX_VALUE) {
            res = Integer.MAX_VALUE;
        }
        if (res < Integer.MIN_VALUE) {
            res = Integer.MIN_VALUE;
        }
        return (int) res;
    }


    public int myAtoi(String str) {
        int len = str.length();
        // str.charAt(i) 方法回去检查下标的合法性，一般先转换成字符数组
        char[] charArray = str.toCharArray();

        // 1、去除前导空格
        int index = 0;
        while (index < len && charArray[index] == ' ') {
            index++;
        }

        // 2、如果已经遍历完成（针对极端用例 "      "）
        if (index == len) {
            return 0;
        }

        // 3、如果出现符号字符，仅第 1 个有效，并记录正负
        int sign = 1;
        char firstChar = charArray[index];
        if (firstChar == '+') {
            index++;
        } else if (firstChar == '-') {
            index++;
            sign = -1;
        }

        // 4、将后续出现的数字字符进行转换
        // 不能使用 long 类型，这是题目说的
        int res = 0;
        int max = Integer.MAX_VALUE / 10;
        int min = Integer.MIN_VALUE / 10;
        int maxMod = Integer.MAX_VALUE % 10;
        int minMod = Integer.MIN_VALUE % 10;
        while (index < len) {
            char currChar = charArray[index];
            // 4.1 先判断不合法的情况
            if (currChar > '9' || currChar < '0') {
                break;
            }

            // 题目中说：环境只能存储 32 位大小的有符号整数，因此，需要提前判：断乘以 10 以后是否越界
            if (res > max || (res == max && (currChar - '0') > maxMod)) {
                return Integer.MAX_VALUE;
            }
            if (res < min || (res == min && (currChar - '0') > -minMod)) {
                return Integer.MIN_VALUE;
            }

            // 4.2 合法的情况下，才考虑转换，每一步都把符号位乘进去
            res = res * 10 + sign * (currChar - '0');
            index++;
        }
        return res;
    }


    public static void main(String[] args) {
//        System.out.println(new S_8().myAtoi("   -42"));
//        System.out.println(new S_8().myAtoi("42"));
//        System.out.println(new S_8().myAtoi("4193 with words"));
//        System.out.println(new S_8().myAtoi("-91283472332"));
        System.out.println(new S_8().myAtoi("00000-42a1234"));
    }
}
