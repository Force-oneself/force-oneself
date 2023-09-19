package com.quan.leetcode.question.string;

import java.util.HashMap;
import java.util.Map;

/**
 * S_205
 *
 * @author Force-oneself
 * @date 2022-05-22
 */
public class S_205 {

    public boolean isIsomorphic1(String s, String t) {
        int sLen = s.length();
        int tLen = t.length();
        if (sLen != tLen) {
            return false;
        }
        return getString(s, sLen).equals(getString(t, tLen));
    }

    private String getString(String s, int sLen) {
        Map<Character, Integer> indexMap = new HashMap<>();
        StringBuilder sb = new StringBuilder();
        int counter = 0;
        for (int i = 0; i < sLen; i++) {
            if (indexMap.containsKey(s.charAt(i))) {
                sb.append(indexMap.get(s.charAt(i)));
            } else {
                counter++;
                indexMap.put(s.charAt(i), counter);
                sb.append(counter);
            }
        }
        return sb.toString();
    }

    public boolean isIsomorphic(String s, String t) {
        Map<Character, Character> s2t = new HashMap<>();
        Map<Character, Character> t2s = new HashMap<>();
        int len = s.length();
        for (int i = 0; i < len; ++i) {
            char x = s.charAt(i), y = t.charAt(i);
            if ((s2t.containsKey(x) && s2t.get(x) != y) || (t2s.containsKey(y) && t2s.get(y) != x)) {
                return false;
            }
            s2t.put(x, y);
            t2s.put(y, x);
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(new S_205().isIsomorphic("egg", "add"));
        System.out.println(new S_205().isIsomorphic("paper", "title"));
    }
}
