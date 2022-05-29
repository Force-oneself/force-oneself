package com.quan.leetcode.question.string;

/**
 * S_551
 *
 * @author Force-oneself
 * @date 2022-05-29
 */
public class S_551 {

    public boolean checkRecord(String s) {

        int len = s.length();
        int a = 0;
        int l = 0;
        for (int i = 0; i < len; i++) {
            char word = s.charAt(i);
            if ('A' == word) {
                a++;
            }
            if ('L' == word) {
                l++;
            } else {
                l = 0;
            }
            if (a >= 2 || l >= 3) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(new S_551().checkRecord("PPALLP"));
        System.out.println(new S_551().checkRecord("PPALLL"));
    }
}
