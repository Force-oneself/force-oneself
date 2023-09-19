package com.quan.leetcode.question.bs;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/**
 * S_1346
 *
 * @author Force-oneself
 * @date 2022-04-24
 */
public class S_1346 {


    public boolean checkIfExist1(int[] arr) {
        HashSet<Integer> set = new HashSet<>();
        for (int i : arr) {
            if (set.contains(2 * i) || (i % 2 == 0 && set.contains(i / 2)))
                return true;
            set.add(i);
        }
        return false;
    }

    public boolean checkIfExist(int[] arr) {
        Map<Integer, Integer> map = new HashMap<>();

        int len = arr.length;
        for (int i = 0; i < len; i++) {
            map.put(arr[i], i);
        }

        for (int i = 0; i < len; i++) {
            int key = 2 * arr[i];
            if (map.containsKey(key) && map.get(key) != i) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        System.out.println(new S_1346().checkIfExist(new int[]{10,2,5,3}));
    }
}
