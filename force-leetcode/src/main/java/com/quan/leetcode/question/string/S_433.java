package com.quan.leetcode.question.string;

import java.util.*;

/**
 * S_433
 *
 * @author Force-oneself
 * @date 2022-05-25
 */
public class S_433 {

    public int minMutation(String start, String end, String[] bank) {
        Set<String> visited = new HashSet<>();
        char[] keys = {'A', 'C', 'G', 'T'};
        Set<String> cnt = new HashSet<>(Arrays.asList(bank));
        if (start.equals(end)) {
            return 0;
        }
        if (!cnt.contains(end)) {
            return -1;
        }
        Queue<String> queue = new ArrayDeque<>();
        queue.offer(start);
        visited.add(start);
        int step = 1;
        while (!queue.isEmpty()) {
            int sz = queue.size();
            for (int i = 0; i < sz; i++) {
                String curr = queue.poll();
                for (int j = 0; j < 8; j++) {
                    for (int k = 0; k < 4; k++) {
                        if (keys[k] != curr.charAt(j)) {
                            StringBuilder sb = new StringBuilder(curr);
                            sb.setCharAt(j, keys[k]);
                            String next = sb.toString();
                            if (!visited.contains(next) && cnt.contains(next)) {
                                if (next.equals(end)) {
                                    return step;
                                }
                                queue.offer(next);
                                visited.add(next);
                            }
                        }
                    }
                }
            }
            step++;
        }
        return -1;
    }
}
