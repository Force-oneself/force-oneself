package com.quan.leetcode.question.string;

import java.util.LinkedList;
import java.util.Queue;

/**
 * S_38
 *
 * @author Force-oneself
 * @date 2022-05-20
 */
public class S_38 {

    public String countAndSay(int n) {
        StringBuilder sb = new StringBuilder();
        Queue<Integer> arr = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            if (!arr.isEmpty()) {
                int size = arr.size();
                int pre = arr.poll();
                int count = 1;
                for (int j = 1; j < size; j++) {
                    int row = arr.poll();
                    if (row == pre) {
                        count++;
                    } else {
                        arr.add(count);
                        arr.add(pre);
                        pre = row;
                        count = 1;
                    }
                }
                arr.add(count);
                arr.add(pre);
            } else {
                arr.add(1);
            }
        }
        for (Integer num : arr) {
            sb.append(num);
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(new S_38().countAndSay(5));
        System.out.println(new S_38().countAndSay(4));
    }
}
