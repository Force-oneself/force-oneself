package com.quan.leetcode.question.bs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * S_1170
 *
 * @author Force-oneself
 * @date 2022-04-22
 */
public class S_1170 {

    public int[] numSmallerByFrequency1(String[] queries, String[] words) {
        int[] nums = new int[11], result = new int[queries.length];
        for (String word : words) {
            nums[findMinNum(word)]++;
        }
        int i = 0;
        for (String query : queries) {
            int num = findMinNum(query);
            for (int j = num + 1; j < 11; j++) {
                result[i] += nums[j];
            }
            i++;
        }
        return result;
    }

    private int findMinNum(String s) {
        int num = 1;
        char c = '{';
        for (char c1 : s.toCharArray()) {
            if (c1 < c) {
                num = 1;
                c = c1;
            } else if (c1 == c) {
                num++;
            }
        }
        return num;
    }

    public int[] numSmallerByFrequency(String[] queries, String[] words) {
        List<Integer> queryCount = count(queries);
        List<Integer> wordCount = count(words);

        int[] res = new int[queries.length];
        for (int i = 0; i < queryCount.size(); i++) {
            int row = 0;
            for (Integer word : wordCount) {
                if (word > queryCount.get(i)) {
                    row++;
                }
            }
            res[i] = row;
        }

        return res;
    }

    private List<Integer> count(String[] queries) {
        List<Integer> queryCount = new ArrayList<>();
        for (String query : queries) {
            char[] chars = query.toCharArray();
            Arrays.sort(chars);
            char same = chars[0];
            int count = 1;
            for (int i = 1; i < chars.length; i++) {
                if (chars[i] == same) {
                    count++;
                } else {
                    break;
                }
            }
            queryCount.add(count);
        }
        return queryCount;
    }
}
