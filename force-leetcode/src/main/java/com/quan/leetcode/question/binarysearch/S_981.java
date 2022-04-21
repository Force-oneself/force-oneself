package com.quan.leetcode.question.binarysearch;

import java.util.*;

/**
 * S_981
 *
 * @author Force-oneself
 * @date 2022-04-21
 */
public class S_981 {

    HashMap<String, TreeMap<Integer, String>> map;

    public S_981() {
        map = new HashMap();
    }

    public void set(String key, String value, int timestamp) {
        map.computeIfAbsent(key, k -> new TreeMap()).put(timestamp, value);
    }

    public String get(String key, int timestamp) {
        if (!map.containsKey(key)) {
            return "";
        }
        Integer time = map.get(key).floorKey(timestamp);
        if (time == null) {
            return "";
        }
        return map.get(key).get(time);
    }


    class TimeMap {
        class Pair implements Comparable<Pair> {
            int timestamp;
            String value;

            public Pair(int timestamp, String value) {
                this.timestamp = timestamp;
                this.value = value;
            }

            @Override
            public int hashCode() {
                return timestamp + value.hashCode();
            }

            @Override
            public boolean equals(Object obj) {
                if (obj instanceof Pair) {
                    Pair pair2 = (Pair) obj;
                    return this.timestamp == pair2.timestamp && this.value.equals(pair2.value);
                }
                return false;
            }

            @Override
            public int compareTo(Pair pair2) {
                if (this.timestamp != pair2.timestamp) {
                    return this.timestamp - pair2.timestamp;
                } else {
                    return this.value.compareTo(pair2.value);
                }
            }
        }

        Map<String, List<Pair>> map;

        public TimeMap() {
            map = new HashMap<String, List<Pair>>();
        }

        public void set(String key, String value, int timestamp) {
            List<Pair> pairs = map.getOrDefault(key, new ArrayList<Pair>());
            pairs.add(new Pair(timestamp, value));
            map.put(key, pairs);
        }

        public String get(String key, int timestamp) {
            List<Pair> pairs = map.getOrDefault(key, new ArrayList<Pair>());
            // 使用一个大于所有 value 的字符串，以确保在 pairs 中含有 timestamp 的情况下也返回大于 timestamp 的位置
            Pair pair = new Pair(timestamp, String.valueOf((char) 127));
            int i = binarySearch(pairs, pair);
            if (i > 0) {
                return pairs.get(i - 1).value;
            }
            return "";
        }

        private int binarySearch(List<Pair> pairs, Pair target) {
            int low = 0, high = pairs.size() - 1;
            if (high < 0 || pairs.get(high).compareTo(target) <= 0) {
                return high + 1;
            }
            while (low < high) {
                int mid = (high - low) / 2 + low;
                Pair pair = pairs.get(mid);
                if (pair.compareTo(target) <= 0) {
                    low = mid + 1;
                } else {
                    high = mid;
                }
            }
            return low;
        }
    }
}
