package com.quan.leetcode.question.string;

import java.util.HashMap;
import java.util.Map;

/**
 * S_290
 *
 * @author Force-oneself
 * @date 2022-05-23
 */
public class S_290 {

    public boolean wordPattern1(String pattern, String s) {
        String[] split = s.split(" ");
        int sLen = split.length;
        int pLen = pattern.length();
        if (sLen != pLen) {
            return false;
        }
        Map<String, Integer> sMap = new HashMap<>();
        Map<Character, Integer> pMap = new HashMap<>();
        for (int i = 0; i < sLen; i++) {
            if ((sMap.containsKey(split[i]) && pMap.containsKey(pattern.charAt(i)) && sMap.get(split[i]).equals(pMap.get(pattern.charAt(i))))
                    || (!sMap.containsKey(split[i]) && !pMap.containsKey(pattern.charAt(i)))) {
                sMap.put(split[i], i);
                pMap.put(pattern.charAt(i), i);
            } else {
                return false;
            }
        }
        return true;
    }

    public boolean wordPattern(String pattern, String str) {
        String[] words = str.split(" ");
        if (words.length != pattern.length()) {
            return false;
        }
        Map<Object, Integer> map = new HashMap<>();
        for (int i = 0; i < words.length; i++) {
            if (!map.put(pattern.charAt(i), i).equals(map.put(words[i], i))) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(new S_290().wordPattern("abba", "dog cat cat dog"));
        System.out.println(new S_290().wordPattern("abba", "dog cat cat fish"));
        System.out.println(new S_290().wordPattern("aaaa", "dog cat cat dog"));
    }
}
