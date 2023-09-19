package com.quan.leetcode.question.string;

/**
 * S_168
 *
 * @author Force-oneself
 * @date 2022-05-21
 */
public class S_168 {

    public String convertToTitle(int columnNumber) {
        StringBuffer sb = new StringBuffer();
        while (columnNumber != 0) {
            columnNumber--;
            sb.append((char) (columnNumber % 26 + 'A'));
            columnNumber /= 26;
        }
        return sb.reverse().toString();
    }
}
