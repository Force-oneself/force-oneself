package com.quan.algorithm;

import java.util.PriorityQueue;

/**
 * @author Force-oneself
 * @description Huffman
 * @date 2022-04-05
 */
public class Huffman {

    public int lessMoney(int[] arr) {
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        for (int j : arr) {
            queue.add(j);
        }
        int sum = 0;
        int cur = 0;
        while (queue.size() > 1) {
            cur = queue.poll() + queue.poll();
            sum += cur;
            queue.add(cur);
        }
        return sum;
    }

}
