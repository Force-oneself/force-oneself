package com.quan.leetcode.question.dp;

import java.util.Arrays;

/**
 * S_467
 *
 * @author Force-oneself
 * @date 2022-05-26
 */
public class S_467 {

    public int findSubstringInWraproundString(String p) {
        int[] dp = new int[26];
        int k = 0;
        for (int i = 0; i < p.length(); ++i) {
            // 字符之差为 1 或 -25
            if (i > 0 && (p.charAt(i) - p.charAt(i - 1) + 26) % 26 == 1) {
                ++k;
            } else {
                k = 1;
            }
            int letter = p.charAt(i) - 'a';
            dp[letter] = Math.max(dp[letter], k);
        }
        return Arrays.stream(dp).sum();
    }
}
