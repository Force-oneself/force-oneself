package com.quan.leetcode.question.sw;

import java.util.Arrays;
import java.util.HashMap;

/**
 * S_1234
 *
 * @author Force-oneself
 * @date 2022-05-02
 */
public class S_1234 {

    public int balancedString1(String s) {
        int len = s.length();
        if (len % 4 != 0) {
            return 0;
        }
        HashMap<Character, Integer> map = new HashMap<Character, Integer>() {{
            put('Q', 0);
            put('W', 1);
            put('E', 2);
            put('R', 3);

        }};
        int[] counter = new int[4];
        for (int i = 0; i < len; i++) {
            counter[map.get(s.charAt(i))]++;
        }
        int sum = 0;
        int avg = len / 4;
        for (int count : counter) {
            if (count > avg) {
                sum += count - avg;
            }
        }
        return sum;
    }

    public int balancedString(String s) {
        // 统计每个单词的数目
        int[] count = new int[26];
        int n = s.length();
        for (int i = 0; i < n; i++) {
            count[s.charAt(i) - 'A']++;
        }
        int left = 0, right;
        int ret = n;
        int avg = n / 4;
        for (right = 0; right < n; right++) {
            count[s.charAt(right) - 'A']--;
            // 注意此处 left是小于 n（而不是小于right）;因为窗口最小可以为0，这个时候left是大于right的
            while (left < n
                    && count['Q' - 'A'] <= avg
                    && count['W' - 'A'] <= avg
                    && count['E' - 'A'] <= avg
                    && count['R' - 'A'] <= avg) {
                ret = Math.min(ret, right - left + 1);
                count[s.charAt(left) - 'A']++;
                left++;
            }
        }
        return ret;
    }

    public int balancedString2(String s) {
        if (s == null || s.length() <= 0) return -1;
        int len = s.length();
        // 这里用26有的浪费,为了方便写代码,就这样吧
        int[] need = new int[26];
        // 初始化为-len/4这样最后得到的大于0的值就是多出来的
        Arrays.fill(need, -len / 4);
        int[] cur = new int[26];
        for (int i = 0; i < len; i++) {
            need[s.charAt(i) - 'A']++;
        }
        // 有几个字符多出来了
        int needCount = 0;
        for (int j : need) {
            if (j > 0) needCount++;
        }
        if (needCount == 0) return 0;
        int res = len;
        int left = 0, right = 0;
        int matchCount = 0;
        // 无脑套路滑窗
        while (right < len) {
            char c = s.charAt(right);
            if (need[c - 'A'] > 0) {
                cur[c - 'A']++;
                if (cur[c - 'A'] == need[c - 'A']) {
                    matchCount++;
                }
            }
            while (left <= right && matchCount == needCount) {
                res = Math.min(right - left + 1, res);
                char cl = s.charAt(left);
                if (need[cl - 'A'] > 0) {
                    cur[cl - 'A']--;
                    if (cur[cl - 'A'] < need[cl - 'A']) {
                        matchCount--;
                    }
                }
                left++;
            }
            right++;
        }
        return res;
    }


    public static void main(String[] args) {
        System.out.println(new S_1234().balancedString("QWER"));
        System.out.println(new S_1234().balancedString("QQWE"));
        System.out.println(new S_1234().balancedString("QQQW"));
        System.out.println(new S_1234().balancedString("QQQQ"));
    }

}
