package com.quan.leetcode.question.string;

/**
 * S_387
 *
 * @author Force-oneself
 * @date 2022-05-24
 */
public class S_387 {

    public int firstUniqChar(String s) {
        int len = s.length();
        int[] last = new int[26];
        for (int i = 0; i < len; i++) {
            last[s.charAt(i) - 'a'] = i;
        }
        for (int i = 0; i < len; i++) {
            int idx = s.charAt(i) - 'a';
            if (last[idx] == i) {
                return i;
            }
            last[idx] = -1;
        }
        return -1;
    }

    public static void main(String[] args) {
        System.out.println(new S_387().firstUniqChar("a"));
        System.out.println(new S_387().firstUniqChar("leetcode"));
        System.out.println(new S_387().firstUniqChar("loveleetcode"));
        System.out.println(new S_387().firstUniqChar("aabb"));
    }
}
