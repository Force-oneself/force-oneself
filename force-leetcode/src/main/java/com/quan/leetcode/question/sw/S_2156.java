package com.quan.leetcode.question.sw;

/**
 * S_2156
 *
 * @author Force-oneself
 * @date 2022-05-05
 */
public class S_2156 {

    public String subStrHash(String s, int power, int modulo, int k, int hashValue) {
        // 记录power的k-1次幂
        long p = 1;
        long hash = 0;
        // 记录滚动哈希
        int ans = s.length();
        // 记录符合要求的字符串的起始坐标
        for (int i = 0; i < k; i++) {
            if (i < k - 1) {
                p = p * power % modulo;
            }
            hash = (hash * power + s.charAt(s.length() - 1 - i) - 'a' + 1) % modulo;
        }
        if (hashValue == hash) {
            ans -= k;
        }
        for (int i = s.length() - k - 1; i >= 0; i--) {
            hash = ((hash + (modulo - p) * (s.charAt(i + k) - 'a' + 1)) % modulo * power + s.charAt(i) - 'a' + 1) % modulo;
            if (hash == hashValue) {
                ans = i;
            }
        }
        return s.substring(ans, ans + k);
    }

}
