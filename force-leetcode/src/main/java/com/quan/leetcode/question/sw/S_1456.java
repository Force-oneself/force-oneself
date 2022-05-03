package com.quan.leetcode.question.sw;

import java.util.HashMap;
import java.util.Map;

/**
 * S_1456
 *
 * @author Force-oneself
 * @date 2022-05-03
 */
public class S_1456 {

    public int maxVowels1(String s, int k) {
        int l = 0;
        int r = 0;
        int len = s.length();
        Map<Character, Integer> map = new HashMap<Character, Integer>() {{
            put('a', 0);
            put('e', 1);
            put('i', 2);
            put('o', 3);
            put('u', 4);
        }};
        int vowelCount = 0;
        int size = 0;
        int ant = 0;
        while (r < len) {
            char c = s.charAt(r);
            if (map.containsKey(c)) {
                vowelCount++;
            }
            size++;
            while (size > k) {
                size--;
                if (map.containsKey(s.charAt(l))) {
                    vowelCount--;
                }
                l++;
            }
            r++;
            ant = Math.max(ant, vowelCount);
        }
        return ant;
    }

    /**
     * 官方滑动
     *
     * @param s s
     * @param k k
     * @return  return
     */
    public int maxVowels(String s, int k) {
        int n = s.length();
        int vowelCount = 0;
        for (int i = 0; i < k; ++i) {
            vowelCount += isVowel(s.charAt(i));
        }
        int ans = vowelCount;
        for (int i = k; i < n; ++i) {
            vowelCount += isVowel(s.charAt(i)) - isVowel(s.charAt(i - k));
            ans = Math.max(ans, vowelCount);
        }
        return ans;
    }

    public int isVowel(char ch) {
        return ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u' ? 1 : 0;
    }

    public static void main(String[] args) {
        System.out.println(new S_1456().maxVowels("abciiidef", 3));
        System.out.println(new S_1456().maxVowels("aeiou", 2));
        System.out.println(new S_1456().maxVowels("leetcode", 3));
        System.out.println(new S_1456().maxVowels("rhythms", 4));
        System.out.println(new S_1456().maxVowels("tryhard", 4));
    }

}
