package com.quan.leetcode.question.sw;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * S_438
 *
 * @author Force-oneself
 * @date 2022-04-30
 */
public class S_438 {

    public List<Integer> findAnagrams(String s, String p) {
        int len = p.length();
        int length = s.length();
        List<Integer> ant = new ArrayList<>();
        if (len > length) {
            return ant;
        }

        int[] ps = new int[26];
        for (int i = 0; i < len; i++) {
            ps[p.charAt(i) - 'a']++;
        }
        int r = 0;
        int l = 0;
        int[] ss = new int[26];
        while (r < length) {
            while (r - l < len) {
                ss[s.charAt(r) - 'a']++;
                r++;
            }
            if (Arrays.equals(ss, ps)) {
                ant.add(l);
            }
            ss[s.charAt(l) - 'a']--;
            l++;
        }
        return ant;
    }

    public static void main(String[] args) {
        System.out.println(new S_438().findAnagrams("cbaebabacd", "abc"));
        System.out.println(new S_438().findAnagrams("abab", "ab"));
        System.out.println(new S_438().findAnagrams("aaaaaaaaaa", "aaaaaaaaaaaaa"));
    }
}
