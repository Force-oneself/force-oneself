package com.quan.leetcode.question.string;

import java.util.Deque;
import java.util.LinkedList;

/**
 * S_71
 *
 * @author Force-oneself
 * @date 2022-05-21
 */
public class S_71 {

    public String simplifyPath(String path) {
        String[] ps = path.split("/");

        Deque<String> stack = new LinkedList<>();
        for (String p : ps) {
            if ("".equals(p)) {
                continue;
            }
            if (".".equals(p)){
                continue;
            }
            if ("..".equals(p)) {
                if (!stack.isEmpty()) {
                    stack.pop();
                }
                continue;
            }

            stack.push(p);
        }
        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            sb.append("/").append(stack.pollLast());
        }
        int length = sb.length();
        if (length <= 0) {
            sb.append("/");
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(new S_71().simplifyPath("/home/"));
        System.out.println(new S_71().simplifyPath("/../"));
        System.out.println(new S_71().simplifyPath("/home//foo/"));
        System.out.println(new S_71().simplifyPath("/a/./b/../../c/"));
    }

}
