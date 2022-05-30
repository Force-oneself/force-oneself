package com.quan.leetcode.question.string;

/**
 * S_557
 *
 * @author Force-oneself
 * @date 2022-05-30
 */
public class S_557 {

    public String reverseWords1(String s) {
        String[] sp = s.split(" ");
        StringBuilder sb = new StringBuilder();
        for (String value : sp) {
            sb.append(new StringBuilder(value).reverse()).append(" ");
        }
        return sb.toString().trim();
    }

    public String reverseWords(String s) {
        StringBuilder ret = new StringBuilder();
        int length = s.length();
        int i = 0;
        while (i < length) {
            int start = i;
            while (i < length && s.charAt(i) != ' ') {
                i++;
            }
            for (int p = start; p < i; p++) {
                ret.append(s.charAt(start + i - 1 - p));
            }
            while (i < length && s.charAt(i) == ' ') {
                i++;
                ret.append(' ');
            }
        }
        return ret.toString();
    }

    public static void main(String[] args) {
        System.out.println(new S_557().reverseWords("Let's take LeetCode contest"));
        System.out.println(new S_557().reverseWords("God Ding"));
    }

}
