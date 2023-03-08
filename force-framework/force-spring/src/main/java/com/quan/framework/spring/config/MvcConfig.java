package com.quan.framework.spring.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author Force-oneself
 * @Description MvcConfig
 * @date 2021-08-24
 */
@Configuration
public class MvcConfig implements WebMvcConfigurer {

    private final List<HandlerInterceptor> interceptors;

    public MvcConfig(List<HandlerInterceptor> interceptors) {
        this.interceptors = interceptors;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        interceptors.forEach(registry::addInterceptor);
    }

    public static void main(String[] args) {

        int[] nums = {0,3,7,2,5,8,4,6,0,1};
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            set.add(num);
        }
        int max = 0;
        for (int num : nums) {
            int count = 1;
            if (!set.contains(num + 1)) {
                while (set.contains(num-count)) {
                    count++;
                }
            }
            max = Math.max(max, count);
        }
        System.out.println(max);
    }

    private static void whileMethod(int[] nums) {
        int i = nums.length - 2;
        while (i >= 0 && nums[i] >= nums[i + 1]) {
            i--;
        }
        if (i >= 0) {
            int j = nums.length - 1;
            while (j > i && nums[j] <= nums[i]) {
                j--;
            }
            swap(nums, i, j);
        }
        reverse(nums, i + 1);
        System.out.println(Arrays.stream(nums)
                .mapToObj(String::valueOf)
                .collect(Collectors.joining(",")));
    }

    private static void forMethod(int[] nums) {
        boolean order = true;
        for (int i = nums.length - 2; i >= 0; i--) {
            // 转折点
            if (nums[i] < nums[i + 1]) {
                for (int j = nums.length - 1; j > i; j--) {
                    if (nums[j] > nums[i]) {
                        swap(nums, i, j);
                        break;
                    }
                }
                reverse(nums, i + 1);
                order = false;
                break;
            }
        }
        if (order) {
            reverse(nums, 0);
        }
        System.out.println(Arrays.stream(nums)
                .mapToObj(String::valueOf)
                .collect(Collectors.joining(",")));
    }

    private static void reverse(int[] nums, int start) {
        int l = start;
        int r = nums.length - 1;
        while (l < r) {
            swap(nums, l, r);
            l++;
            r--;
        }
    }

    private static void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
}
