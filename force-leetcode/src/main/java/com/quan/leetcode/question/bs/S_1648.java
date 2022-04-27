package com.quan.leetcode.question.bs;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * S_1648
 *
 * @author Force-oneself
 * @date 2022-04-27
 */
public class S_1648 {

    /**
     * 暴力
     *
     * @param inventory inventory
     * @param orders    orders
     * @return return
     */
    public int maxProfit(int[] inventory, int orders) {
        PriorityQueue<Integer> queue = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        });
        for (int inve : inventory) {
            queue.offer(inve);
        }
        long sum = 0;
        for (int i = orders; i > 0; i--) {
            Integer poll = queue.poll();
            if (poll != null && poll > 0) {
                sum += poll;
                if (poll > 1) {
                    queue.offer(--poll);
                }
            }
        }
        return (int) (sum % (10_0000_0007));
    }

    /**
     * 二分
     *
     * @param inventory inventory
     * @param orders    orders
     * @return return
     */
    public int maxProfit1(int[] inventory, int orders) {
        int mod = (int) (1e9 + 7);
        int l = 0, r = maxNum(inventory);
        while (l < r) {
            int mid = l + (r - l) / 2;
            if (provideOrders(inventory, mid) <= orders) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        long res = 0;
        for (int num : inventory) {
            if (num >= l) {
                orders -= (num - l + 1);
                long first = l, last = num, n = num - l + 1;
                res = (res + ((first + last) * n / 2) % mod) % mod;
            }
        }
        res = (res + (long) orders * (l - 1) % mod) % mod;
        return (int) res;
    }

    private long provideOrders(int[] inventory, int m) {
        long orders = 0;
        for (int num : inventory) {
            orders += Math.max(num - m + 1, 0);
        }
        return orders;
    }

    private int maxNum(int[] inventory) {
        int max = 0;
        for (int num : inventory) {
            max = Math.max(max, num);
        }
        return max;
    }

    public static void main(String[] args) {
        System.out.println(new S_1648().maxProfit(new int[]{2, 5}, 4));
        System.out.println(new S_1648().maxProfit(new int[]{3, 5}, 6));
        System.out.println(new S_1648().maxProfit(new int[]{2, 8, 4, 10, 6}, 20));
        System.out.println(new S_1648().maxProfit(new int[]{1000000000}, 1000000000));
    }

}
