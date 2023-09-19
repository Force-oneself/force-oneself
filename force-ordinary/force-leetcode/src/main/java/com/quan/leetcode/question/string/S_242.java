package com.quan.leetcode.question.string;

import java.util.Arrays;

/**
 * S_242
 *
 * @author Force-oneself
 * @date 2022-05-22
 */
public class S_242 {

    public boolean isAnagram(String s, String t) {
        int[] sArr = new int[26];
        int[] tArr = new int[26];
        int sLen = s.length();
        int tLen = t.length();
        if (sLen != tLen) {
            return false;
        }
        for (int i = 0; i < sLen; i++) {
            sArr[s.charAt(i) - 'a']++;
            tArr[t.charAt(i) - 'a']++;
        }
        return Arrays.equals(sArr, tArr);
    }
}
