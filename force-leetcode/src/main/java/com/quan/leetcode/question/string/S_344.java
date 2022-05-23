package com.quan.leetcode.question.string;

/**
 * S_344
 *
 * @author Force-oneself
 * @date 2022-05-23
 */
public class S_344 {

    public void reverseString(char[] s) {

        int l = 0;
        int r = s.length - 1;
        while (l <= r) {
            swap(s, l++, r--);
        }
    }

    public void swap(char[] s, int f, int t) {
        char temp = s[f];
        s[f] = s[t];
        s[t] = temp;
    }
}
