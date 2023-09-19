package com.quan.leetcode.question.string;

/**
 * S_383
 *
 * @author Force-oneself
 * @date 2022-05-24
 */
public class S_383 {

    public boolean canConstruct(String ransomNote, String magazine) {
        int rLen = ransomNote.length();
        int mLen = magazine.length();
        int[] letters = new int[26];
        for (int i = 0; i < mLen; i++) {
            letters[magazine.charAt(i) - 'a']++;
        }
        for (int i = 0; i < rLen; i++) {
            letters[ransomNote.charAt(i) - 'a']--;
        }
        for (int i = 0; i < 26; i++) {
            if (letters[i] < 0) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(new S_383().canConstruct("a", "b"));
        System.out.println(new S_383().canConstruct("aa", "ab"));
        System.out.println(new S_383().canConstruct("aa", "aab"));
    }
}
