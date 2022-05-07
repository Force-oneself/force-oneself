package com.quan.leetcode.question.pq;

import java.util.*;

/**
 * S_451
 *
 * @author Force-oneself
 * @date 2022-05-07
 */
public class S_451 {

    public String frequencySort(String s) {
        Map<Character, Integer> map = new HashMap<>();

        char[] chars = s.toCharArray();
        for (char aChar : chars) {
            map.put(aChar, map.getOrDefault(aChar, 0) + 1);
        }
        PriorityQueue<Map.Entry<Character, Integer>> queue = new PriorityQueue<>(new Comparator<Map.Entry<Character, Integer>>() {
            @Override
            public int compare(Map.Entry<Character, Integer> o1, Map.Entry<Character, Integer> o2) {
                return o2.getValue() - o1.getValue();
            }
        });
        queue.addAll(map.entrySet());

        StringBuilder sb = new StringBuilder();
        int size = queue.size();
        for (int i = 0; i < size; i++) {
            Map.Entry<Character, Integer> entry = queue.poll();
            for (int j = 0; j < entry.getValue(); j++) {
                sb.append(entry.getKey());
            }
        }
        return sb.toString();
    }

    public String frequencySort1(String s) {
        Map<Character, Integer> map = new HashMap<>();
        int length = s.length();
        for (int i = 0; i < length; i++) {
            char c = s.charAt(i);
            int frequency = map.getOrDefault(c, 0) + 1;
            map.put(c, frequency);
        }
        List<Character> list = new ArrayList<>(map.keySet());
        list.sort((a, b) -> map.get(b) - map.get(a));
        StringBuilder sb = new StringBuilder();
        for (char c : list) {
            int frequency = map.get(c);
            for (int j = 0; j < frequency; j++) {
                sb.append(c);
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(new S_451().frequencySort("tree"));
        System.out.println(new S_451().frequencySort("cccaaa"));
        System.out.println(new S_451().frequencySort("Aabb"));
    }
}
