package com.quan.leetcode.question.bt;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @author Force-oneself
 * @description S_1947
 * @date 2022-04-13
 */
public class S_1947 {

    int m, n, max;

    public int maxCompatibilitySum1(int[][] students, int[][] mentors) {
        m = students.length;
        n = students[0].length;
        bt(students, mentors, new boolean[m], new boolean[m], 0, 0);
        return max;
    }


    public void bt(int[][] students, int[][] mentors, boolean[] st, boolean[] te, int len, int sum) {
        if (len == m) {
            max = Math.max(max, sum);
            return;
        }
        for (int i = 0; i < m; i++) {
            if (st[i]) continue;
            st[i] = true;
            for (int j = 0; j < m; j++) {
                if (te[j]) continue;
                te[j] = true;
                int curSum = 0;
                for (int k = 0; k < n; k++) {
                    if (mentors[j][k] == students[i][k]) {
                        curSum++;
                    }
                }
                bt(students, mentors, st, te, len + 1, sum + curSum);
                te[j] = false;
            }
            st[i] = false;
        }
    }

    public int maxCompatibilitySum(int[][] students, int[][] mentors) {
        // n 个问题
        int n = students[0].length;
        // m 名学生和 m 名导师
        int m = students.length;
        // 预处理
        // score[i][j] 代表下标为 i 的学生与下标为 j 的老师的 兼容性评分
        int[][] score = new int[m][m];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < m; j++) {
                for (int k = 0; k < n; k++) {
                    if (students[i][k] == mentors[j][k]) {
                        score[i][j] += 1;
                    }
                }
            }
        }
        KmAlgo kmAlgo = new KmAlgo(m, score);
        return kmAlgo.getMaximumWeight();
    }

    private static class KmAlgo {
        private final int n;
        // 左集合对应的匹配点
        private final int[] matchX;
        // 右集合对应的匹配点
        private final int[] matchY;
        // 连接右集合的左点
        private final int[] pre;
        // 拜访数组 左
        private final boolean[] visX;
        // 拜访数组 右
        private final boolean[] visY;
        // 可行顶标 给每个节点 i 分配一个权值 l(i)，对于所有边 (u,v) 满足 w(u,v) <= l(u) + l(v)。
        private final int[] lx;
        private final int[] ly;
        private final int[][] graph;
        private final int[] slack;
        private static final int INF = Integer.MAX_VALUE;
        private final Queue<Integer> queue;

        public KmAlgo(int n, int[][] graph) {
            this.n = n;
            this.graph = graph;
            this.queue = new LinkedList<>();
            this.matchX = new int[n];
            Arrays.fill(matchX, -1);
            this.matchY = new int[n];
            Arrays.fill(matchY, -1);
            this.pre = new int[n];
            this.visX = new boolean[n];
            this.visY = new boolean[n];
            this.lx = new int[n];
            Arrays.fill(lx, -INF);
            this.ly = new int[n];
            this.slack = new int[n];
        }

        public int getMaximumWeight() {
            // 初始顶标
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    lx[i] = Math.max(lx[i], graph[i][j]);
                }
            }
            for (int i = 0; i < n; i++) {
                Arrays.fill(slack, INF);
                Arrays.fill(visX, false);
                Arrays.fill(visY, false);
                bfs(i);
            }
            // custom
            int res = 0;
            for (int i = 0; i < n; i++) {
                res += graph[i][matchX[i]];
            }
            return res;
        }

        private boolean check(int v) {
            visY[v] = true;
            if (matchY[v] != -1) {
                queue.add(matchY[v]);
                // in S
                visX[matchY[v]] = true;
                return false;
            }
            // 找到新的未匹配点 更新匹配点 pre 数组记录着"非匹配边"上与之相连的点
            while (v != -1) {
                matchY[v] = pre[v];
                // swap(v, matchx[pre[v]]);
                int tmp = matchX[pre[v]];
                matchX[pre[v]] = v;
                v = tmp;
            }
            return true;
        }

        private void bfs(int i) {
            queue.clear();
            queue.add(i);
            visX[i] = true;
            while (true) {
                while (!queue.isEmpty()) {
                    int u = queue.remove();
                    for (int v = 0; v < n; v++) {
                        if (!visY[v]) {
                            int delta = lx[u] + ly[v] - graph[u][v];
                            if (slack[v] >= delta) {
                                pre[v] = u;
                                if (delta > 0) {
                                    slack[v] = delta;
                                } else if (check(v)) {
                                    // delta=0 代表有机会加入相等子图 找增广路
                                    // 找到就return 重建交错树
                                    return;
                                }
                            }
                        }
                    }
                }
                // 没有增广路 修改顶标
                int a = INF;
                for (int j = 0; j < n; j++) {
                    if (!visY[j]) {
                        a = Math.min(a, slack[j]);
                    }
                }
                for (int j = 0; j < n; j++) {
                    // S
                    if (visX[j]) {
                        lx[j] -= a;
                    }
                    // T
                    if (visY[j]) {
                        ly[j] += a;
                    }
                    // T'
                    else {
                        slack[j] -= a;
                    }
                }
                for (int j = 0; j < n; j++) {
                    if (!visY[j] && slack[j] == 0 && check(j)) {
                        return;
                    }
                }
            }
        }
    }
}
