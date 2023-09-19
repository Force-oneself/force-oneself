package com.quan.leetcode.question.greedy;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Force-oneself
 * @description S_179
 * @date 2022-04-06
 */
public class S_179 {

    public String largestNumber(int[] nums) {
        List<String> list = new ArrayList<>();
        for (int num : nums)
            list.add(num + "");

        // 取两个数结合后排序
        list.sort((o1, o2) -> (o2 + o1).compareTo(o1 + o2));

        StringBuilder res = new StringBuilder();
        for (String str : list)
            res.append(str);

        if (res.charAt(0) == '0')
            return "0";
        return res.toString();
    }

}
