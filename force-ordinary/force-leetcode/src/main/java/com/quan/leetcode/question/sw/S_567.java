package com.quan.leetcode.question.sw;

import java.util.Arrays;

/**
 * S_567
 *
 * @author Force-oneself
 * @date 2022-05-01
 */
public class S_567 {

    public boolean checkInclusion(String s1, String s2) {
        int s1Len = s1.length();
        int s2Len = s2.length();
        if (s1Len > s2Len) {
            return false;
        }
        int[] ps = new int[26];
        for (int i = 0; i < s1Len; i++) {
            ps[s1.charAt(i) - 'a']++;
        }
        int l = 0;
        int r = 0;
        int[] ss = new int[26];
        while (r < s2Len) {
            while (r - l < s1Len) {
                ss[s2.charAt(r) - 'a']++;
                r++;
            }
            if (Arrays.equals(ss, ps)) {
                return true;
            }
            ss[s2.charAt(l) - 'a']--;
            l++;
        }
        return false;
    }

    /**
     * 官方滑动窗口
     *
     * @param s1 s1
     * @param s2 s2
     * @return return
     */
    public boolean checkInclusion1(String s1, String s2) {
        int n = s1.length(), m = s2.length();
        if (n > m) {
            return false;
        }
        int[] cnt1 = new int[26];
        int[] cnt2 = new int[26];
        for (int i = 0; i < n; ++i) {
            ++cnt1[s1.charAt(i) - 'a'];
            ++cnt2[s2.charAt(i) - 'a'];
        }
        if (Arrays.equals(cnt1, cnt2)) {
            return true;
        }
        for (int i = n; i < m; ++i) {
            ++cnt2[s2.charAt(i) - 'a'];
            --cnt2[s2.charAt(i - n) - 'a'];
            if (Arrays.equals(cnt1, cnt2)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 滑动窗口优化
     *
     * @param s1 s1
     * @param s2 s2
     * @return return
     */
    public boolean checkInclusion2(String s1, String s2) {
        int n = s1.length(), m = s2.length();
        if (n > m) {
            return false;
        }
        int[] cnt = new int[26];
        for (int i = 0; i < n; ++i) {
            --cnt[s1.charAt(i) - 'a'];
            ++cnt[s2.charAt(i) - 'a'];
        }
        // 记录两个数组的不同字符数量
        int diff = 0;
        for (int c : cnt) {
            if (c != 0) {
                ++diff;
            }
        }
        if (diff == 0) {
            return true;
        }
        for (int i = n; i < m; ++i) {
            int x = s2.charAt(i) - 'a';
            int y = s2.charAt(i - n) - 'a';
            // 进出字符一样对滑动窗口的内不同字符数量不改变
            if (x == y) {
                continue;
            }
            // 当前字符不在数组内说明是新增的不同字符
            if (cnt[x] == 0) {
                ++diff;
            }
            ++cnt[x];
            // 恰好抵消一个不同字符
            if (cnt[x] == 0) {
                --diff;
            }
            // 与上面同理
            if (cnt[y] == 0) {
                ++diff;
            }
            --cnt[y];
            if (cnt[y] == 0) {
                --diff;
            }
            // 没有不同字符即存在答案
            if (diff == 0) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        System.out.println(new S_567().checkInclusion("ab", "eidbaooo"));
        System.out.println(new S_567().checkInclusion("ab", "eidboaoo"));
    }
}
