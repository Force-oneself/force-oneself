package com.quan.leetcode.question.string;

import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * S_20
 *
 * @author Force-oneself
 * @date 2022-05-19
 */
public class S_20 {

    public boolean isValid(String s) {
        int n = s.length();
        if ((n & 1) == 1) {
            return false;
        }

        Map<Character, Character> pairs = new HashMap<Character, Character>() {{
            put(')', '(');
            put(']', '[');
            put('}', '{');
        }};
        Deque<Character> stack = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            char ch = s.charAt(i);
            if (pairs.containsKey(ch)) {
                // 栈为空或者栈顶不是该右括号对应的左括号
                if (stack.isEmpty() || !stack.peek().equals(pairs.get(ch))) {
                    return false;
                }
                // 匹配成功出栈
                stack.pop();
            } else {
                // 左括号入栈
                stack.push(ch);
            }
        }
        return stack.isEmpty();
    }


}
