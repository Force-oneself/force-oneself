package com.quan.leetcode.question.string;

/**
 * S_14
 *
 * @author Force-oneself
 * @date 2022-05-19
 */
public class S_14 {

    public String longestCommonPrefix(String[] strs) {

        int len = strs.length;
        if ( len < 2) {
            return strs[0];
        }
        int minLen = Integer.MAX_VALUE;
        for (String str : strs) {
            minLen = Math.min(minLen, str.length());
        }
        int ant = 0;
        for (int i = 0; i < minLen; i++) {
            char word = strs[0].charAt(i);
            for (int j = 1; j < len; j++) {
                if (word != strs[j].charAt(i)) {
                    return strs[0].substring(0, ant);
                }
            }
            ant++;
        }
        return strs[0].substring(0, ant);
    }

    public static void main(String[] args) {
        System.out.println(new S_14().longestCommonPrefix(new String[]{"flower","flow","flight"}));
    }
}
