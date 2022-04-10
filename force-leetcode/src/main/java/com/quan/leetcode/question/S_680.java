package com.quan.leetcode.question;

/**
 * @author Force-oneself
 * @description S_680
 * @date 2022-04-10
 */
public class S_680 {

    public boolean validPalindrome(String s) {
        int low = 0, high = s.length() - 1;
        while (low < high) {
            char c1 = s.charAt(low), c2 = s.charAt(high);
            if (c1 == c2) {
                ++low;
                --high;
            } else
                // 判断删除左边 和删除右边是否可以满足回文串
                return validPalindrome(s, low, high - 1) || validPalindrome(s, low + 1, high);
        }
        return true;
    }

    public boolean validPalindrome(String s, int low, int high) {
        for (int i = low, j = high; i < j; ++i, --j) {
            char c1 = s.charAt(i), c2 = s.charAt(j);
            if (c1 != c2) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(new S_680().validPalindrome("aba"));
        System.out.println(new S_680().validPalindrome("abca"));
        System.out.println(new S_680().validPalindrome("abc"));
        System.out.println(new S_680().validPalindrome("aguokepatgbnvfqmgmlcupuufxoohdfpgjdmysgvhmvffcnqxjjxqncffvmhvgsymdjgpfdhooxfuupuculmgmqfvnbgtapekouga"));
    }
}
