package com.quan.leetcode.question.pq;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * S_973
 *
 * @author Force-oneself
 * @date 2022-05-08
 */
public class S_973 {

    public int[][] kClosest(int[][] points, int k) {
        PriorityQueue<Integer> queue = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return (points[o1][0] * points[o1][0] + points[o1][1] * points[o1][1]) - (points[o2][0] * points[o2][0] + points[o2][1] * points[o2][1]);
            }
        });

        int length = points.length;
        for (int i = 0; i < length; i++) {
            queue.add(i);
        }

        int size = queue.size();
        int[][] ant = new int[Math.min(size, k)][2];
        for (int i = 0; i < size && i < k; i++) {
            Integer poll = queue.poll();
            ant[i][0] = points[poll][0];
            ant[i][1] = points[poll][1];
        }
        return ant;
    }

    /**
     * 排序
     *
     * @param points points
     * @param k k
     * @return  return
     */
    public int[][] kClosest1(int[][] points, int k) {
        Arrays.sort(points, new Comparator<int[]>() {
            @Override
            public int compare(int[] point1, int[] point2) {
                return (point1[0] * point1[0] + point1[1] * point1[1]) - (point2[0] * point2[0] + point2[1] * point2[1]);
            }
        });
        return Arrays.copyOfRange(points, 0, k);
    }

    public static void main(String[] args) {
        System.out.println(Arrays.deepToString(new S_973().kClosest(new int[][]{{1, 3}, {-2, 2}}, 1)));
        System.out.println(Arrays.deepToString(new S_973().kClosest(new int[][]{{3, 3}, {5, -1}, {-2, 4}}, 2)));
    }

}
