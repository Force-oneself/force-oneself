package com.quan.demo.algorithm.demo;

import java.util.HashMap;
import java.util.HashSet;

/**
 * Split4Parts
 * <p>
 * 给定一个正数数组arr，返回该数组能不能分成4个部分，并且每个部分的累加和相等，切分位置的数不要。
 * 例如:
 * arr=[3, 2, 4, 1, 4, 9, 5, 10, 1, 2, 2] 返回true
 * 三个切割点下标为2, 5, 7. 切出的四个子数组为[3,2], [1,4], [5], [1,2,2]，
 * 累加和都是5
 *
 * @author Force-oneself
 * @date 2022-07-21
 */
public class Split4Parts {

    public static boolean canSplits1(int[] arr) {
        if (arr == null || arr.length < 7) {
            return false;
        }
        HashSet<String> set = new HashSet<>();
        int sum = 0;
        for (int j : arr) {
            sum += j;
        }
        int leftSum = arr[0];
        for (int i = 1; i < arr.length - 1; i++) {
            set.add(leftSum + "_" + (sum - leftSum - arr[i]));
            leftSum += arr[i];
        }
        int l = 1;
        int lSum = arr[0];
        int r = arr.length - 2;
        int rSum = arr[arr.length - 1];
        while (l < r - 3) {
            if (lSum == rSum) {
                String lKey = String.valueOf(lSum * 2 + arr[l]);
                String rKey = String.valueOf(rSum * 2 + arr[r]);
                if (set.contains(lKey + "_" + rKey)) {
                    return true;
                }
                lSum += arr[l++];
            } else if (lSum < rSum) {
                lSum += arr[l++];
            } else {
                rSum += arr[r--];
            }
        }
        return false;
    }

    public static boolean canSplits2(int[] arr) {
        if (arr == null || arr.length < 7) {
            return false;
        }
        // key 某一个累加和， value出现的位置
        HashMap<Integer, Integer> map = new HashMap<>();
        int sum = arr[0];
        for (int i = 1; i < arr.length; i++) {
            map.put(sum, i);
            sum += arr[i];
        }
        // 第一刀左侧的累加和
        int lSum = arr[0];
        // s1是第一刀的位置
        for (int s1 = 1; s1 < arr.length - 5; s1++) {
            // 100 x 100   100*2 + x
            int checkSum = lSum * 2 + arr[s1];
            if (map.containsKey(checkSum)) {
                // j -> y
                int s2 = map.get(checkSum);
                checkSum += lSum + arr[s2];
                // 100 * 3 + x + y
                if (map.containsKey(checkSum)) {
                    // k -> z
                    int s3 = map.get(checkSum);
                    if (checkSum + arr[s3] + lSum == sum) {
                        return true;
                    }
                }
            }
            lSum += arr[s1];
        }
        return false;
    }

    public static int[] generateRandomArray() {
        int[] res = new int[(int) (Math.random() * 10) + 7];
        for (int i = 0; i < res.length; i++) {
            res[i] = (int) (Math.random() * 10) + 1;
        }
        return res;
    }

    public static void main(String[] args) {
        int testTime = 3000000;
        for (int i = 0; i < testTime; i++) {
            int[] arr = generateRandomArray();
            if (canSplits1(arr) ^ canSplits2(arr)) {
                System.out.println("Error");
            }
        }
    }
}
