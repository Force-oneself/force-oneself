package com.quan.leetcode.question.greedy;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @author Force-oneself
 * @description S_402
 * @date 2022-04-08
 */
public class S_402 {

    public String removeKdigits(String num, int k) {
        Deque<Character> deque = new LinkedList<>();
        int length = num.length();
        for (int i = 0; i < length; ++i) {
            char digit = num.charAt(i);
            // 栈不为空，把当前值前面更小的值都删除掉，k==0说明已经删除k个元素了
            while (!deque.isEmpty() && k > 0 && deque.peekLast() > digit) {
                deque.pollLast();
                k--;
            }
            deque.offerLast(digit);
        }

        for (int i = 0; i < k; ++i) {
            deque.pollLast();
        }

        StringBuilder ret = new StringBuilder();
        // 是否为前缀0
        boolean leadingZero = true;
        while (!deque.isEmpty()) {
            char digit = deque.pollFirst();
            if (leadingZero && digit == '0') {
                continue;
            }
            leadingZero = false;
            ret.append(digit);
        }
        return ret.length() == 0 ? "0" : ret.toString();
    }

    public static void main(String[] args) {
        System.out.println(new S_402().removeKdigits("1432219", 4));
    }

}
