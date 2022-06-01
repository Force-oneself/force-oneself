package com.quan.leetcode.question.string;

/**
 * S_686
 *
 * @author Force-oneself
 * @date 2022-06-01
 */
public class S_686 {

    /**
     * KMP
     *
     * @param a a
     * @param b b
     * @return return
     */
    public int repeatedStringMatch(String a, String b) {
        int an = a.length(), bn = b.length();
        int index = strStr(a, b);
        if (index == -1) {
            return -1;
        }
        if (an - index >= bn) {
            return 1;
        }
        return (bn + index - an - 1) / an + 2;
    }

    public int strStr(String haystack, String needle) {
        int n = haystack.length(), m = needle.length();
        if (m == 0) {
            return 0;
        }
        int[] pi = new int[m];
        for (int i = 1, j = 0; i < m; i++) {
            while (j > 0 && needle.charAt(i) != needle.charAt(j)) {
                j = pi[j - 1];
            }
            if (needle.charAt(i) == needle.charAt(j)) {
                j++;
            }
            pi[i] = j;
        }
        // b 开始匹配的位置是否超过第一个叠加的 a
        for (int i = 0, j = 0; i - j < n; i++) {
            // haystack 是循环叠加的字符串，所以取 i % n
            while (j > 0 && haystack.charAt(i % n) != needle.charAt(j)) {
                j = pi[j - 1];
            }
            if (haystack.charAt(i % n) == needle.charAt(j)) {
                j++;
            }
            if (j == m) {
                return i - m + 1;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        System.out.println(new S_686().repeatedStringMatch("abcd", "cdabcdab"));
        System.out.println(new S_686().repeatedStringMatch("abab", "aba"));
    }
}
