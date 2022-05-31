package com.quan.leetcode.question.string;

/**
 * S_647
 *
 * @author Force-oneself
 * @date 2022-05-31
 */
public class S_647 {

    public int countSubstrings(String s) {
        int n = s.length(), ans = 0;
        for (int i = 0; i < 2 * n - 1; ++i) {
            // l左边起始位置，r右边起始位置
            int l = i / 2, r = i / 2 + i % 2;
            while (l >= 0 && r < n && s.charAt(l) == s.charAt(r)) {
                --l;
                ++r;
                ++ans;
            }
        }
        return ans;
    }

}
