package com.quan.leetcode.question.dp;

/**
 * S_392
 *
 * @author Force-oneself
 * @date 2022-05-17
 */
public class S_392 {

    /**
     * 双指针
     *
     * @param s s
     * @param t t
     * @return  return
     */
    public boolean isSubsequence(String s, String t) {
        int n = s.length(), m = t.length();
        int i = 0, j = 0;
        while (i < n && j < m) {
            if (s.charAt(i) == t.charAt(j)) {
                i++;
            }
            j++;
        }
        return i == n;
    }
}
