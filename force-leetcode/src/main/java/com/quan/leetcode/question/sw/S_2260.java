package com.quan.leetcode.question.sw;

import java.util.HashMap;
import java.util.Map;

/**
 * S_2260
 *
 * @author Force-oneself
 * @date 2022-05-05
 */
public class S_2260 {

    public int minimumCardPickup1(int[] cards) {
        int len = cards.length;
        int r = 0;
        int l = 0;
        int ant = -1;
        int minLen = Integer.MAX_VALUE;
        Map<Integer, Integer> map = new HashMap<>();
        while (r < len) {
            while (map.containsKey(cards[r])) {
                if (r - l + 2 <= minLen) {
                    minLen = Math.min(r - l + 1, minLen);
                    if (cards[r] > ant) {
                        ant = cards[r];
                    }
                }
                map.remove(cards[l]);
                l++;
            }
            map.put(cards[r], r);
            minLen = Math.min(r - l + 1, minLen);
            r++;
        }
        return ant;
    }

    public int minimumCardPickup(int[] cards) {
        int len = cards.length;
        int ant = Integer.MAX_VALUE;
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < len; i++) {
            if (map.containsKey(cards[i])) {
                ant = Math.min(i - map.get(cards[i]) + 1, ant);
            }
            map.put(cards[i], i);
        }
        return ant == Integer.MAX_VALUE ? -1 : ant;
    }

    public static void main(String[] args) {
        System.out.println(new S_2260().minimumCardPickup(new int[]{3, 4, 2, 3, 4, 7}));
        System.out.println(new S_2260().minimumCardPickup(new int[]{1, 0, 5, 3}));
    }
}
