package com.quan.leetcode.question.bs;

/**
 * S_852
 *
 * @author Force-oneself
 * @date 2022-04-20
 */
public class S_852 {

    public int peakIndexInMountainArray(int[] arr) {
        int len = arr.length;
        int l = 1;
        int r = len - 2;
        while (l <= r) {
            int mid = (r - l) / 2 + l;
            if (arr[mid] > arr[mid - 1] && arr[mid] > arr[mid + 1]) {
                return mid;
            } else if (arr[mid] > arr[mid - 1]) {
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }
        return -1;
    }


    public static void main(String[] args) {
        System.out.println(new S_852().peakIndexInMountainArray(new int[]{0,1,0}));
        System.out.println(new S_852().peakIndexInMountainArray(new int[]{0,2,1,0}));
        System.out.println(new S_852().peakIndexInMountainArray(new int[]{0,10,5,2}));
        System.out.println(new S_852().peakIndexInMountainArray(new int[]{3,4,5,1}));
        System.out.println(new S_852().peakIndexInMountainArray(new int[]{24,69,100,99,79,78,67,36,26,19}));
    }
}
