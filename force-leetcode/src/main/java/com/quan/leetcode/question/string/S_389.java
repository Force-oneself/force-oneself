package com.quan.leetcode.question.string;

/**
 * S_389
 *
 * @author Force-oneself
 * @date 2022-05-24
 */
public class S_389 {

    public char findTheDifference(String s, String t) {
        int[] letters = new int[26];

        int len = s.length();
        for (int i = 0; i < len; i++) {
            letters[s.charAt(i) - 'a']++;
        }
        for (int i = 0; i < len + 1; i++) {
            letters[t.charAt(i) - 'a']--;
        }
        for (int i = 0; i < 26; i++) {
            if (letters[i] < 0) {
                return (char) (i + 'a');
            }
        }
        return ' ';
    }

    public static void main(String[] args) {
        System.out.println(new S_389().findTheDifference("abcd", "abcde"));
    }
}
