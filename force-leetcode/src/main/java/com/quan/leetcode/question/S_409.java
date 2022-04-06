package com.quan.leetcode.question;

/**
 * @author Force-oneself
 * @description S_409
 * @date 2022-04-06
 */
public class S_409 {

    public int longestPalindrome(String s) {
        int len = s.length();
        int[] charArray = new int[52];

        char[] array = s.toCharArray();
        int res = 0;
        for (char c : array) {
            int i = Character.isUpperCase(c) ? c - 'A' + 26 : c - 'a';
            if (++charArray[i] % 2 == 0) {
                res += 2;
            }
        }
        return res == len ? res : res + 1;
    }

    public static void main(String[] args) {
        S_409 demo = new S_409();
        System.out.println(demo.longestPalindrome("abccccdd"));
        System.out.println(demo.longestPalindrome("a"));
        System.out.println(demo.longestPalindrome("bb"));
    }

}
