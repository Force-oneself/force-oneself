package com.quan.leetcode.question.sw;

import java.util.HashMap;
import java.util.Map;

/**
 * S_1297
 *
 * @author Force-oneself
 * @date 2022-05-02
 */
public class S_1297 {

    public int maxFreq(String s, int maxLetters, int minSize, int maxSize) {
        Map<Character, Integer> map = new HashMap<>();
        int len = s.length();
        int left = 0;
        int right = 0;
        int size = 0;
        Map<String, Integer> word = new HashMap<>();
        while (right < len) {
            char cur = s.charAt(right);
            size++;
            map.put(cur, map.getOrDefault(cur, 0) + 1);
            while (map.size() > maxLetters || size > minSize) {
                char le = s.charAt(left);
                Integer count = map.get(le);
                if (count == 1) {
                    map.remove(le);
                } else {
                    map.put(le, map.get(le) - 1);
                }
                size--;
                left++;
            }
            if (size == minSize) {
                String w = s.substring(left, right + 1);
                word.put(w, word.getOrDefault(w, 0) + 1);
            }
            right++;
        }
        return word.values().stream().max(Integer::compareTo).orElse(0);
    }

    public int maxFreq1(String s, int maxLetters, int minSize, int maxSize) {
        int n = s.length();
        // 统计子串出现的个数
        Map<String, Integer> map = new HashMap<>();
        char[] c = s.toCharArray();
        int left = 0, right = 0;
        // 统计窗口中不同字母的数目
        int tmp = 0;
        // 记录窗口中字母的个数
        int[] count = new int[128];
        while (right < n) {
            count[c[right]]++;
            // 当下面条件成立时，则说明窗口中多了一个不同的字母
            if (count[c[right]] == 1) tmp++;
            right++;
            int len = right - left;
            while (tmp > maxLetters || len > minSize) {
                count[c[left]]--;
                // 当窗口左移的过程中，一个字母减为0，则说明窗口中少了一个不同的字母
                if (count[c[left]] == 0) tmp--;
                left++;
                // 如果没有这句，会陷入死循环，len会一直大于minSize
                len--;
            }
            //当不同字母的数目小于等于maxLetters
            if (tmp <= maxLetters) {
                if (len == minSize) {
                    String str = s.substring(left, right);
                    map.put(str, map.getOrDefault(str, 0) + 1);
                }

            }
        }
        //统计字串最大出现的次数
        int ans = 0;
        for (String key : map.keySet()) {
            ans = Math.max(ans, map.get(key));
        }
        return ans;
    }


    public static void main(String[] args) {
        System.out.println(new S_1297().maxFreq("aababcaab", 2, 3, 4));
        System.out.println(new S_1297().maxFreq("aaaa", 1, 3, 3));
        System.out.println(new S_1297().maxFreq("aabcabcab", 2, 2, 3));
        System.out.println(new S_1297().maxFreq("abcde", 2, 3, 3));
        System.out.println(new S_1297().maxFreq("abcabababacabcabc", 3, 3, 10));
    }

}
