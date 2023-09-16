package com.quan.demo.algorithm.demo;

import java.util.Arrays;
import java.util.Comparator;

/**
 * KthMinPair
 * 长度为N的数组arr，一定可以组成N^2个数值对。
 * 例如arr = [3,1,2]，
 * 数值对有(3,3) (3,1) (3,2) (1,3) (1,1) (1,2) (2,3) (2,1) (2,2)，
 * 也就是任意两个数都有数值对，而且自己和自己也算数值对。
 * 数值对怎么排序？规定，第一维数据从小到大，第一维数据一样的，第二维数组也从小到大。所以上面的数值对排序的结果为：
 * (1,1)(1,2)(1,3)(2,1)(2,2)(2,3)(3,1)(3,2)(3,3)
 * <p>
 * 给定一个数组arr，和整数k，返回第k小的数值对
 *
 * @author Force-oneself
 * @date 2022-07-13
 */
public class KthMinPair {

    public static class Pair {
        public int x;
        public int y;

        Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static class PairComparator implements Comparator<Pair> {

        @Override
        public int compare(Pair arg0, Pair arg1) {
            return arg0.x != arg1.x ? arg0.x - arg1.x : arg0.y - arg1.y;
        }

    }

    /**
     * O(N^2 * log (N^2))的复杂度，你肯定过不了
     * 返回的int[] 长度是2，{3,1} int[2] = [3,1]
     *
     * @param arr arr
     * @param k   k
     * @return /
     */
    public static int[] kthMinPair1(int[] arr, int k) {
        int n = arr.length;
        if (k > n * n) {
            return null;
        }
        Pair[] pairs = new Pair[n * n];
        int index = 0;
        for (int j : arr) {
            for (int value : arr) {
                pairs[index++] = new Pair(j, value);
            }
        }
        Arrays.sort(pairs, new PairComparator());
        return new int[]{pairs[k - 1].x, pairs[k - 1].y};
    }

    /**
     * O(N*logN)的复杂度，你肯定过了
     *
     * @param arr arr
     * @param k   k
     * @return /
     */
    public static int[] kthMinPair2(int[] arr, int k) {
        int n = arr.length;
        if (k > n * n) {
            return null;
        }
        // O(n*logN)
        Arrays.sort(arr);
        // 第K小的数值对，第一维数字，是什么 是arr中
        int firstNum = arr[(k - 1) / n];
        // 数出比firstNum小的数有几个
        int lessFirstNumSize = 0;
        // 数出==firstNum的数有几个
        int firstNumSize = 0;
        // <= firstNum
        for (int i = 0; i < n && arr[i] <= firstNum; i++) {
            if (arr[i] < firstNum) {
                lessFirstNumSize++;
            } else {
                firstNumSize++;
            }
        }
        // arr = {1, 1, 2, 2, 2, 3, 3}这个数组，如果去第21小的数字对，我们可以轻松的获取到第一位数字： arr[（21 -1）/ 7]  = arr[2] = 2 ，第一位是2
        // 所有的数值对是从小大排列，arr有3个数字2，那么就会有 {2,2,2} 分别于{1, 1, 2, 2, 2, 3, 3}组成的组合，
        // 那么第二位的数字我们就呼之欲出了，arr[  (21 - 小于2数字的个数 * 7  - 1)  / 3] = arr [( 21 - 2 * 7 - 1) /3]=arr[2] = 2;
        // 最后的结果就是 {arr[2],arr[2]} ={2,2}
        int rest = k - (lessFirstNumSize * n);
        return new int[]{firstNum, arr[(rest - 1) / firstNumSize]};
    }

    /**
     * O(N)的复杂度，你肯定蒙了
     *
     * @param arr arr
     * @param k   k
     * @return /
     */
    public static int[] kthMinPair3(int[] arr, int k) {
        int n = arr.length;
        if (k > n * n) {
            return null;
        }
        // 在无序数组中，找到第K小的数，返回值
        // 第K小，以1作为开始
        int firstNum = getMinKth(arr, (k - 1) / n);
        int lessFirstNumSize = 0;
        int firstNumSize = 0;
        for (int j : arr) {
            if (j < firstNum) {
                lessFirstNumSize++;
            }
            if (j == firstNum) {
                firstNumSize++;
            }
        }
        int rest = k - (lessFirstNumSize * n);
        return new int[]{firstNum, getMinKth(arr, (rest - 1) / firstNumSize)};
    }

    /**
     * 改写快排，时间复杂度O(N)
     * 在无序数组arr中，找到，如果排序的话，arr[index]的数是什么？
     *
     * @param arr   arr
     * @param index index
     * @return /
     */
    public static int getMinKth(int[] arr, int index) {
        int l = 0;
        int r = arr.length - 1;
        int pivot = 0;
        int[] range = null;
        while (l < r) {
            pivot = arr[l + (int) (Math.random() * (r - l + 1))];
            range = partition(arr, l, r, pivot);
            if (index < range[0]) {
                r = range[0] - 1;
            } else if (index > range[1]) {
                l = range[1] + 1;
            } else {
                return pivot;
            }
        }
        return arr[l];
    }

    public static int[] partition(int[] arr, int l, int r, int pivot) {
        int less = l - 1;
        int more = r + 1;
        int cur = l;
        while (cur < more) {
            if (arr[cur] < pivot) {
                swap(arr, ++less, cur++);
            } else if (arr[cur] > pivot) {
                swap(arr, cur, --more);
            } else {
                cur++;
            }
        }
        return new int[]{less + 1, more - 1};
    }

    public static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    /**
     * 为了测试，生成值也随机，长度也随机的随机数组
     */
    public static int[] getRandomArray(int max, int len) {
        int[] arr = new int[(int) (Math.random() * len) + 1];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * max) - (int) (Math.random() * max);
        }
        return arr;
    }

    public static int[] copyArray(int[] arr) {
        if (arr == null) {
            return null;
        }
        int[] res = new int[arr.length];
        System.arraycopy(arr, 0, res, 0, arr.length);
        return res;
    }

    public static void main(String[] args) {
        // 随机测试了百万组，保证三种方法都是对的
        int max = 100;
        int len = 30;
        int testTimes = 100000;
        System.out.println("test bagin, test times : " + testTimes);
        for (int i = 0; i < testTimes; i++) {
            int[] arr = getRandomArray(max, len);
            int[] arr1 = copyArray(arr);
            int[] arr2 = copyArray(arr);
            int[] arr3 = copyArray(arr);
            int n = arr.length * arr.length;
            int k = (int) (Math.random() * n) + 1;
            int[] ans1 = kthMinPair1(arr1, k);
            int[] ans2 = kthMinPair2(arr2, k);
            int[] ans3 = kthMinPair3(arr3, k);
            if (ans1[0] != ans2[0] || ans2[0] != ans3[0] || ans1[1] != ans2[1] || ans2[1] != ans3[1]) {
                System.out.println("Oops!");
            }
        }
        System.out.println("test finish");
    }
}
