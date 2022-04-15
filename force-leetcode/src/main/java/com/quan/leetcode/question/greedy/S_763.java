package com.quan.leetcode.question.greedy;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Force-oneself
 * @description S_763
 * @date 2022-04-11
 */
public class S_763 {

    public List<Integer> partitionLabels(String s) {
        char[] chars = s.toCharArray();
        int[] last = new int[26];
        int len = chars.length;
        for (int i = 0; i < len; i++) {
            last[chars[i] - 'a'] = i;
        }

        int end = last[chars[0] - 'a'];
        List<Integer> res = new ArrayList<>();
        int row = 0;
        for (int i = 0; i < len; i++) {
            if (i < end) {
                row++;
                end = Math.max(end, last[chars[i] - 'a']);
            } else if (i == end) {
                res.add(row + 1);
                row = 0;
                if (i + 1 < len)
                    end = last[chars[i + 1] - 'a'];
            }
        }
        return res;
    }

    public List<Integer> partitionLabels1(String s) {
        int[] last = new int[26];
        int length = s.length();
        for (int i = 0; i < length; i++) {
            last[s.charAt(i) - 'a'] = i;
        }
        List<Integer> partition = new ArrayList<>();
        int start = 0, end = 0;
        for (int i = 0; i < length; i++) {
            end = Math.max(end, last[s.charAt(i) - 'a']);
            if (i == end) {
                partition.add(end - start + 1);
                start = end + 1;
            }
        }
        return partition;
    }

    public static void main(String[] args) {
        new S_763().partitionLabels("ababcbacadefegdehijhklij").forEach(System.out::println);
    }

}
