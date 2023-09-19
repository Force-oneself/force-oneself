package com.quan.leetcode.question.string;

/**
 * S_520
 *
 * @author Force-oneself
 * @date 2022-05-28
 */
public class S_520 {

    public boolean detectCapitalUse(String word) {
        int len = word.length();
        if (len < 1) {
            return false;
        }
        int upper = 0;
        int lower = 0;
        for (int i = 0; i < len; i++) {
            if (Character.isUpperCase(word.charAt(i))) {
                upper++;
            } else {
                lower++;
            }
            if (upper > 1 && lower > 1) {
                return false;
            }
        }
        return (Character.isUpperCase(word.charAt(0)) && upper == 1) || upper == 0 || lower == 0;
    }

    public static void main(String[] args) {
//        System.out.println(new S_520().detectCapitalUse("USA"));
//        System.out.println(new S_520().detectCapitalUse("FlaG"));
        System.out.println(new S_520().detectCapitalUse("Leetcode"));
    }
}
