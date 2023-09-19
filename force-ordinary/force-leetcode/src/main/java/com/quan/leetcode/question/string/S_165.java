package com.quan.leetcode.question.string;

import java.util.Arrays;
import java.util.List;

/**
 * S_165
 *
 * @author Force-oneself
 * @date 2022-05-21
 */
public class S_165 {

    public int compareVersion1(String version1, String version2) {
        List<String> wordList1 = Arrays.asList(version1.split("\\."));
        List<String> wordList2 = Arrays.asList(version2.split("\\."));

        int len1 = wordList1.size();
        int len2 = wordList2.size();
        int len = Math.max(len2, len1);
        int num1 = 0, num2 = 0;
        for (int i = 0; i < len; i++) {
            num1 = i < len1 ? deal(wordList1, i) : 0;
            num2 = i < len2 ? deal(wordList2, i) : 0;
            if (num1 > num2) {
                return 1;
            }
            if (num1 < num2) {
                return -1;
            }
        }
        return 0;
    }

    private int deal(List<String> wordList1, int i) {
        String s = wordList1.get(i);
        boolean first = true;
        StringBuilder sb = new StringBuilder();
        for (int j = 0; j < s.length(); j++) {
            if (first && '0' == s.charAt(j)) {
                continue;
            }
            first = false;
            sb.append(s.charAt(j));
        }
        if (sb.length() == 0) {
            return 0;
        }
        return Integer.parseInt(sb.toString());
    }

    public int compareVersion2(String version1, String version2) {
        String[] v1 = version1.split("\\.");
        String[] v2 = version2.split("\\.");
        for (int i = 0; i < v1.length || i < v2.length; ++i) {
            int x = 0, y = 0;
            if (i < v1.length) {
                x = Integer.parseInt(v1[i]);
            }
            if (i < v2.length) {
                y = Integer.parseInt(v2[i]);
            }
            if (x > y) {
                return 1;
            }
            if (x < y) {
                return -1;
            }
        }
        return 0;
    }

    public int compareVersion(String version1, String version2) {
        int n = version1.length(), m = version2.length();
        int i = 0, j = 0;
        while (i < n || j < m) {
            int x = 0;
            for (; i < n && version1.charAt(i) != '.'; ++i) {
                x = x * 10 + version1.charAt(i) - '0';
            }
            ++i; // 跳过点号
            int y = 0;
            for (; j < m && version2.charAt(j) != '.'; ++j) {
                y = y * 10 + version2.charAt(j) - '0';
            }
            ++j; // 跳过点号
            if (x != y) {
                return x > y ? 1 : -1;
            }
        }
        return 0;
    }

    public static void main(String[] args) {
        System.out.println(new S_165().compareVersion("1.01", "1.001"));
        System.out.println(new S_165().compareVersion("1.0", "1.0.0"));
        System.out.println(new S_165().compareVersion("1.1", "0.1"));
    }
}
