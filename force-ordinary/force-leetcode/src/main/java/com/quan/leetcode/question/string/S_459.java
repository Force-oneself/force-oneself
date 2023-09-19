package com.quan.leetcode.question.string;

/**
 * S_459
 *
 * @author Force-oneself
 * @date 2022-05-26
 */
public class S_459 {

    public boolean repeatedSubstringPattern(String s) {
        return (s + s).indexOf(s, 1) != s.length();
    }

    public boolean repeatedSubstringPattern2(String s) {
        int n = s.length();
        for (int i = 1; i * 2 <= n; ++i) {
            if (n % i == 0) {
                boolean match = true;
                for (int j = i; j < n; ++j) {
                    if (s.charAt(j) != s.charAt(j - i)) {
                        match = false;
                        break;
                    }
                }
                if (match) {
                    return true;
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        System.out.println(new S_459().repeatedSubstringPattern("abab"));
        System.out.println(new S_459().repeatedSubstringPattern("aba"));
        System.out.println(new S_459().repeatedSubstringPattern("abcabcabcabc"));
    }
}
