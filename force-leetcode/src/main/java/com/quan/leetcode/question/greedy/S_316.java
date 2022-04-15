package com.quan.leetcode.question.greedy;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @author Force-oneself
 * @description S_316
 * @date 2022-04-06
 */
public class S_316 {


    public String removeDuplicateLetters(String s) {
        int len = s.length();
        char[] charArray = s.toCharArray();
        // 保存该元素最后一个坐标位置，来判断当前元素是否存在相同元素
        int[] lastIndex = new int[26];
        for (int i = 0; i < len; i++) {
            lastIndex[charArray[i] - 'a'] = i;
        }
        Deque<Character> stack = new ArrayDeque<>();
        // 维护元素是否在栈中
        boolean[] visited = new boolean[26];
        for (int i = 0; i < len; i++) {
            // 当元素已经在栈中则直接丢弃
            if (visited[charArray[i] - 'a']) {
                continue;
            }
            while (!stack.isEmpty()
                    // cur = a, stack = b, c; b>a  如果后面元素存在b 即 ba > ab,这样就需要删除前面的，反之删除后面的
                    && stack.peekLast() > charArray[i]
                    // 栈顶值的存在相同元素
                    && lastIndex[stack.peekLast() - 'a'] > i) {
                // 弹出元素
                Character top = stack.removeLast();
                // 访问数组设置该元素未访问，因为可能存在多个相同情况
                visited[top - 'a'] = false;
            }
            // 前面会把栈中比该元素大的字符进行剔除，这样可以保证栈中顺序为最小字典序
            stack.addLast(charArray[i]);
            visited[charArray[i] - 'a'] = true;
        }
        // 栈中元素即为结果
        StringBuilder sb = new StringBuilder();
        for (Character c : stack) {
            sb.append(c);
        }
        return sb.toString();
    }

}
