package com.quan.leetcode.question.sw;

import java.util.HashMap;
import java.util.Map;

/**
 * S_1876
 *
 * @author Force-oneself
 * @date 2022-05-04
 */
public class S_1876 {

    public int countGoodSubstrings(String s) {
        int len = s.length();
        if (len < 3) {
            return 0;
        }
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < 3; i++) {
            char cur = s.charAt(i);
            map.put(cur, map.getOrDefault(cur, 0) + 1);
        }
        int ant = map.size() == 3 ? 1 : 0;
        for (int i = 3; i < len; i++) {
            char cur = s.charAt(i);
            map.put(cur, map.getOrDefault(cur, 0) + 1);
            char left = s.charAt(i - 3);
            Integer count = map.get(left);
            if (count == 1) {
                map.remove(left);
            } else {
                map.put(left, count - 1);
            }
            if (map.size() == 3) {
                ant++;
            }
        }
        return ant;
    }

    public static void main(String[] args) {
        System.out.println(new S_1876().countGoodSubstrings("xyzzaz"));
        System.out.println(new S_1876().countGoodSubstrings("aababcabc"));
    }
}
