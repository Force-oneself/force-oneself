package com.quan.leetcode.question.bs;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Force-oneself
 * @description S_658
 * @date 2022-04-19
 */
public class S_658 {

    public List<Integer> findClosestElements1(int[] arr, int k, int x) {
        List<Integer> ret = Arrays.stream(arr)
                .boxed()
                // 按差值排序
                .sorted((a, b) -> a.equals(b) ? 0 : Math.abs(a - x) - Math.abs(b - x))
                .collect(Collectors.toList());
        ret = ret.subList(0, k);
        Collections.sort(ret);
        return ret;
    }

    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        List<Integer> ret = Arrays.stream(arr).boxed().collect(Collectors.toList());
        int n = ret.size();
        if (x <= ret.get(0)) {
            return ret.subList(0, k);
        } else if (ret.get(n - 1) <= x) {
            return ret.subList(n - k, n);
        } else {

            int index = Collections.binarySearch(ret, x);
            if (index < 0)
                index = -index - 1;
            int low = Math.max(0, index - k - 1), high = Math.min(ret.size() - 1, index + k - 1);

            while (high - low > k - 1) {
                if ((x - ret.get(low)) <= (ret.get(high) - x))
                    high--;
                else if ((x - ret.get(low)) > (ret.get(high) - x))
                    low++;
            }
            return ret.subList(low, high + 1);
        }
    }
}
