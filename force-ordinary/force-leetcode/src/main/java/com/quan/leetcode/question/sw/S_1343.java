package com.quan.leetcode.question.sw;

/**
 * S_1343
 *
 * @author Force-oneself
 * @date 2022-05-02
 */
public class S_1343 {

    public int numOfSubarrays(int[] arr, int k, int threshold) {
        int len = arr.length;
        int target = k * threshold;
        int sum = 0;
        for (int i = 0; i < k; i++) {
            sum += arr[i];
        }
        int ant = sum >= target ? 1 : 0;
        for (int i = k; i < len; i++) {
            sum += arr[i] - arr[i - k];
            if (sum >= target) {
                ant++;
            }
        }
        return ant;
    }

    public static void main(String[] args) {
        System.out.println(new S_1343().numOfSubarrays(new int[]{2, 2, 2, 2, 5, 5, 5, 8}, 3, 4));
        System.out.println(new S_1343().numOfSubarrays(new int[]{11, 13, 17, 23, 29, 31, 7, 5, 2, 3}, 3, 5));
    }
}
