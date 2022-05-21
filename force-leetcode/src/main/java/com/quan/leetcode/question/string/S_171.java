package com.quan.leetcode.question.string;

/**
 * S_171
 *
 * @author Force-oneself
 * @date 2022-05-21
 */
public class S_171 {

    public int titleToNumber(String columnTitle) {
        char[] words = columnTitle.toCharArray();
        int ant = 0;
        for (char word : words) {
            ant = ant * 26 + word - 'A' + 1;
        }
        return ant;
    }
}
