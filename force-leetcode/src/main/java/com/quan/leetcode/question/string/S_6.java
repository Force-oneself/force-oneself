package com.quan.leetcode.question.string;

import java.util.ArrayList;
import java.util.List;

/**
 * S_6
 *
 * @author Force-oneself
 * @date 2022-05-18
 */
public class S_6 {

    public String convert(String s, int numRows) {
        if (numRows < 2) {
            return s;
        }

        List<StringBuilder> rows = new ArrayList<>();
        for (int i = 0; i < numRows; i++) {
            rows.add(new StringBuilder());
        }

        int i = 0, flag = -1;
        for (char c : s.toCharArray()) {
            rows.get(i).append(c);
            // 上下边界需要转换上下移动的方向
            if (i == 0 || i == numRows - 1) {
                flag = -flag;
            }

            i += flag;
        }
        StringBuilder res = new StringBuilder();
        for (StringBuilder row : rows) {
            res.append(row);
        }
        return res.toString();
    }
}
