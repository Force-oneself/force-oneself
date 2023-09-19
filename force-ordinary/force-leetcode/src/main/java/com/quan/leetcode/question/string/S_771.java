package com.quan.leetcode.question.string;

import java.util.HashMap;
import java.util.Map;

/**
 * S_771
 *
 * @author Force-oneself
 * @date 2022-06-02
 */
public class S_771 {

    public int numJewelsInStones(String jewels, String stones) {
        char[] chars = jewels.toCharArray();
        Map<Character, Integer> map = new HashMap<>();

        for (int i = 0; i < chars.length; i++) {
            map.put(chars[i], i);
        }
        int len = stones.length();
        int count = 0;
        for (int i = 0; i < len; i++) {
            if (map.containsKey(stones.charAt(i))) {
                count++;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        System.out.println(new S_771().numJewelsInStones("aA", "aAAbbbb"));
    }
}
