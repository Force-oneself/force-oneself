package com.quan.leetcode.question.sw;

/**
 * S_1248
 *
 * @author Force-oneself
 * @date 2022-05-02
 */
public class S_1248 {

    public int numberOfSubarrays1(int[] nums, int k) {
        int len = nums.length;
        int odd = 0;
        int ant = 0;
        int l = 0;
        int r = 0;
        while (r < len) {
            odd += nums[r] & 1;
            while (odd > k) {
                odd -= nums[l] & 1;
                l++;
            }
            if (odd == k) {
                ant++;
            }
            r++;
        }
        return ant;
    }

    public int numberOfSubarrays(int[] nums, int k) {
        int n = nums.length;
        int[] odd = new int[n + 2];
        int ans = 0, cnt = 0;
        for (int i = 0; i < n; ++i) {
            if ((nums[i] & 1) != 0) {
                odd[++cnt] = i;
            }
        }
        odd[0] = -1;
        odd[++cnt] = n;
        for (int i = 1; i + k <= cnt; ++i) {
            ans += (odd[i] - odd[i - 1]) * (odd[i + k] - odd[i + k - 1]);
        }
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(new S_1248().numberOfSubarrays(new int[]{1, 1, 2, 1, 1}, 3));
        System.out.println(new S_1248().numberOfSubarrays(new int[]{2, 4, 6}, 1));
        System.out.println(new S_1248().numberOfSubarrays(new int[]{2, 2, 2, 1, 2, 2, 1, 2, 2, 2}, 2));
    }
}
