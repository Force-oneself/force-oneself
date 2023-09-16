package com.quan.demo.algorithm.classic;


import java.util.Scanner;

/**
 *  Manacher 查询最长回环字符串
 * @author Force-oneself
 * @date 2022-03-26
 */
public class Manacher {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String str = in.next();
        String res = longestPalindrome(str);
        System.out.println(res + ": " + res.length());
    }

    /**
     * 插入字符
     *
     * @param s s
     * @return  /
     */
    public static String preProcess(String s) {
        int n = s.length();
        if (n == 0) {
            return "^$";
        }
        String ret = "^";
        for (int i = 0; i < n; i++)
            ret = ret + "#" + s.charAt(i);
        ret = ret + "#$";
        return ret;
    }


    public static String longestPalindrome(String str) {
        String S = preProcess(str);
        // 保留回文串的长度
        int n = S.length();
        int[] len = new int[n];
        // 保留边界最右的回文核心以及右边界
        int center = 0, right = 0;
        // 从第 1 个字符开始
        for (int i = 1; i < n - 1; i++) {
            // 找出i对于后面核心的对称
            int mirror = 2 * center - i;
            if (right > i) {
                // i 在右边界的范畴内，看看i的对称点的回文串长度，以及i到右边界的长度，取两个较小的那个
                // 不能溢出之前的边界，否则就得核心拓展
                len[i] = Math.min(right - i, len[mirror]);
            } else {
                // 超过范畴了，核心拓展
                len[i] = 0;
            }

            // 核心拓展
            while (S.charAt(i + 1 + len[i]) == S.charAt(i - 1 - len[i])) {
                len[i]++;
            }

            // 看看新的索引是不是比之前保留的最右边界的回文串还要靠右
            if (i + len[i] > right) {
                // 更新核心
                center = i;
                // 更新右边界
                right = i + len[i];
            }

        }

        // 通过回文长度数组找出最长的回文串
        int maxLen = 0;
        int centerIndex = 0;
        for (int i = 1; i < n - 1; i++) {
            if (len[i] > maxLen) {
                maxLen = len[i];
                centerIndex = i;
            }
        }
        int start = (centerIndex - maxLen) / 2;
        return str.substring(start, start + maxLen);
    }
}

