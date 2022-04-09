package com.quan.leetcode.question;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author Force-oneself
 * @description S_649
 * @date 2022-04-09
 */
public class S_649 {

    public String predictPartyVictory(String senate) {
        int n = senate.length();
        Queue<Integer> radiant = new LinkedList<>();
        Queue<Integer> dire = new LinkedList<>();
        // 两个队列记录各自议员的顺序
        for (int i = 0; i < n; ++i) {
            if (senate.charAt(i) == 'R') {
                radiant.offer(i);
            } else {
                dire.offer(i);
            }
        }
        while (!radiant.isEmpty() && !dire.isEmpty()) {
            int radiantIndex = radiant.poll(), direIndex = dire.poll();
            // 较小序号的拥有优先淘汰另一方
            if (radiantIndex < direIndex) {
                // + n的目的是为了让他加入下一次的投票
                radiant.offer(radiantIndex + n);
            } else {
                dire.offer(direIndex + n);
            }
        }
        return !radiant.isEmpty() ? "Radiant" : "Dire";
    }


}
