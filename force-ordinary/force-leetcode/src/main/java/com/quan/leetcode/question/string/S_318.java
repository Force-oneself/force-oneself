package com.quan.leetcode.question.string;

/**
 * S_318
 *
 * @author Force-oneself
 * @date 2022-05-23
 */
public class S_318 {

    public int maxProduct(String[] words) {

        int len = words.length;
        int[] w = new int[len];
        for (int i = 0; i < len; i++) {
            char[] chars = words[i].toCharArray();
            for (char a : chars) {
                w[i] |= 1 << (a - 'a');
            }
        }
        int max = 0;
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len; j++) {
                if ((w[i] & w[j]) == 0) {
                    max = Math.max(words[i].length() * words[j].length(), max);
                }
            }
        }
        return max;
    }

    public static void main(String[] args) {
        System.out.println(new S_318().maxProduct(new String[]{"abcw", "baz", "foo", "bar", "xtfn", "abcdef"}));
        System.out.println(new S_318().maxProduct(new String[]{"a", "ab", "abc", "d", "cd", "bcd", "abcd"}));
        System.out.println(new S_318().maxProduct(new String[]{"a", "aa", "aaa", "aaaa"}));
    }
}
