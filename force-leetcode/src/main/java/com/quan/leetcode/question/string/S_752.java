package com.quan.leetcode.question.string;

import java.util.*;

/**
 * S_752
 *
 * @author Force-oneself
 * @date 2022-06-02
 */
public class S_752 {


    public int openLock(String[] deadends, String target) {
        return bfs(deadends, target);
    }

    // 将 s[j] 向上拨动⼀次
    String plusOne(String s, int j) {
        char[] ch = s.toCharArray();
        if (ch[j] == '9') ch[j] = '0';
        else ch[j] += 1;
        return new String(ch);
    }

    // 将 s[i] 向下拨动⼀次
    String minusOne(String s, int j) {
        char[] ch = s.toCharArray();
        if (ch[j] == '0') ch[j] = '9';
        else ch[j] -= 1;
        return new String(ch);
    }


    // BFS 框架，打印出所有可能的密码
    int bfs(String[] deadends, String target) {
        Set<String> deads = new HashSet<>(Arrays.asList(deadends));
        // 记录已经穷举过的密码，防⽌⾛回头路
        Set<String> visited = new HashSet<>();
        Queue<String> q = new LinkedList<>();
        int step = 0;
        q.offer("0000");
        visited.add("0000");
        while (!q.isEmpty()) {
            int sz = q.size();
            // 将当前队列中的所有节点向周围扩散
            for (int i = 0; i < sz; i++) {
                String cur = q.poll();
                if (deads.contains(cur)) continue;
                // 判断是否到达终点
                if (cur.equals(target)) return step;
                // 将⼀个节点的相邻节点加⼊队列
                for (int j = 0; j < 4; j++) {
                    String up = plusOne(cur, j);
                    if (!visited.contains(up)) {
                        q.offer(up);
                        visited.add(up);
                    }
                    String down = minusOne(cur, j);
                    if (!visited.contains(down)) {
                        q.offer(down);
                        visited.add(down);
                    }
                }
            }
            // 在这⾥增加步数
            step++;
        }
        return -1;
    }
}
