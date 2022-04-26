package com.quan.leetcode.question.bs;

/**
 * S_1574
 *
 * @author Force-oneself
 * @date 2022-04-26
 */
public class S_1574 {

    /**
     *  非二分查找实现方式，具体看每一步的注解，非常清晰了！
     *
     * @param arr arr
     * @return  return
     */
    public int findLengthOfShortestSubarray(int[] arr) {
        int len = arr.length;
        // leftEnd作为左边区域 结束位置的指针,rightStart作为右边区域 起始位置的指针
        int leftEnd = 0, rightStart = len - 1;
        // 计算左边区域 能到的最右的位置
        for (; leftEnd < len - 1; leftEnd++) {
            if (arr[leftEnd] > arr[leftEnd + 1]) break;
        }
        // 如果最右的位置超过了数组空间，说明整个数组都是递增的，不用删除子数组
        if (leftEnd == len - 1) return 0;

        // 计算右边区域 能到的最左边的位置
        for (; rightStart > 1; rightStart--) {
            if (arr[rightStart] < arr[rightStart - 1]) break;
        }

        // 既然leftEnd和rightStart两处位置符合递增的大小关系，那也就是说 左边全部 + 右边全部 是递增的，
        // 那自然也是最长的，所以要删除的也是最短的。例子：{1，4，6，2，3，7，6，7，8，9}
        if (arr[leftEnd] <= arr[rightStart]) return rightStart - leftEnd - 1;

        // 执行到这一步，说明上面的情况都不符合，需要从三个区域中找删除最短的情况

        // 保留当前删除子数组最短的情况，和后面计算里，综合取最短！当前已知能删除的情况有：
        //    ① 保留左边区域， 删除之后的子数组
        //    ② 保留右边区域， 删除之前的子数组       二者取最短的
        int deleteMinLen = Math.min(len - leftEnd - 1, rightStart);

        // 然后计算 左边部分+右边部分 的情况。
        // 到这里因为已经排除了：左边全部 + 右边全部的情况，例子：{1,3,5,8,2,3,5,3,4,5,6,7,8,9}
        // 所以我们现在取最大化的结果，最少删除 左边和右边的长度
        // 最干脆的方式就是从左边区域的第一个起，找每一个元素（下标i）在 右边区域第一个大于等于他的元素（下标j）
        // 取 j - i - 1最小的情况！
        for (int i = 0; i <= leftEnd; i++) {
            for (int j = rightStart; j < len; j++) {
                // 第一个大于等于
                if (arr[i] <= arr[j]) {
                    deleteMinLen = Math.min(deleteMinLen, j - i - 1);
                    break;
                }
            }
        }

        return deleteMinLen;
    }

    /**
     * 二分查找 实现方式
     * 代码的前部分和前面题解没有差别，主要是最后一步：计算 左边部分+右边部分 的情况
     * 可以看到这步骤 就转换成了求："在一个非递减数组中找到第一个大于等于给定值的元素"  的问题
     * 我们知道这种问题也是二分查找的一个典型问题之一！
     * <p>
     * 这也是本道题有二分查找标签的原因，那为什么要用二分查找呢？用线性的方式进行一个一个比较不见得是一件效率低的情况
     * 解答：两点主要原因：
     * ① 线性比较会存在 "比较" 的次数非常多，比如：要找的元素位置在数组的靠后位置，这样二分查找 "比较" 的次数会少很多
     * ② 如果进行 "比较" 的操作比较耗时，那二分查找是更应该考虑的一种方式！
     * <p>
     * 不过使用二分查找，对应这到题在leetcode上的测试用例来看，耗时没有线性处理那么快
     */
    public int findLengthOfShortestSubarray1(int[] arr) {
        int len = arr.length;

        // leftEnd作为左边区域 结束位置的指针,rightStart作为右边区域 起始位置的指针
        int leftEnd = 0, rightStart = len - 1;

        // 计算左边区域 能到的最右的位置
        for (; leftEnd < len - 1; leftEnd++) {
            if (arr[leftEnd] > arr[leftEnd + 1]) break;
        }

        // 如果最右的位置超过了数组空间，说明整个数组都是递增的，不用删除子数组
        if (leftEnd == len - 1) return 0;

        // 计算右边区域 能到的最左边的位置
        for (; rightStart > 1; rightStart--) {
            if (arr[rightStart] < arr[rightStart - 1]) break;
        }

        // 既然leftEnd和rightStart两处位置符合递增的大小关系，那也就是说 左边全部 + 右边全部 是递增的，
        // 那自然也是最长的，所以要删除的也是最短的。例子：{1，4，6，2，3，7，6，7，8，9}
        if (arr[leftEnd] <= arr[rightStart]) return rightStart - leftEnd - 1;

        // 执行到这一步，说明上面的情况都不符合，需要从三个区域中找删除最短的情况

        // 保留当前删除子数组最短的情况，和后面计算里，综合取最短！当前已知能删除的情况有：
        //    ① 保留左边区域， 删除之后的子数组
        //    ② 保留右边区域， 删除之前的子数组       二者取最短的
        int deleteMinLen = Math.min(len - leftEnd - 1, rightStart);

        // 然后计算 左边部分+右边部分 的情况。
        // 到这里因为已经排除了：左边全部 + 右边全部的情况，例子：{1,3,5,8,2,3,5,3,4,5,6,7,8,9}
        // 所以我们现在取最大化的结果，最少删除 左边和右边的长度
        // 最干脆的方式就是从左边区域的第一个起，找每一个元素（下标i）在 右边区域第一个大于等于他的元素（下标j）
        // 取 j - i - 1最小的情况！
        // 二分查找的实现方式：
        for (int i = 0; i <= leftEnd; i++) {
            deleteMinLen = Math.min(deleteMinLen, findFirstBigIndex(arr, rightStart, arr[i]) - i - 1);
        }

        return deleteMinLen;
    }

    // 在一个非递减数组中找到第一个大于等于给定值的元素  典型二份查找问题
    // 二份查找的非递归实现版本
    public int findFirstBigIndex(int[] arr, int left, int num) {
        int low = left, hight = arr.length - 1;

        while (low <= hight) {
            int mid = low + (hight - low) / 2;

            if (arr[mid] < num) {
                low = mid + 1;
            } else { // arr[mid] >= num
                // 如果已经是最左边的值或者当前位置的前一个小于num，说明当前位置就是第一个大于等于的元素
                if (mid == left || arr[mid - 1] < num) return mid;
                hight = mid - 1;
            }
        }

        return Integer.MAX_VALUE;
    }
}
