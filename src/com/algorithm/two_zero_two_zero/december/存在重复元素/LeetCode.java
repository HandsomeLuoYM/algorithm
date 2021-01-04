package com.algorithm.two_zero_two_zero.december.存在重复元素;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Ming
 * @date 2020/12/13 - 10:09
 * @describe
 */
public class LeetCode {
    /**
     * 给定一个整数数组，判断是否存在重复元素。
     *
     * 如果任意一值在数组中出现至少两次，函数返回 true 。如果数组中每个元素都不相同，则返回 false 。
     */
    /**
     * 自己的思路：
     *      使用 hash（和官方题解二一致）
     * 执行用时：8 ms, 在所有 Java 提交中击败了55.15%的用户
     * 内存消耗：44.3 MB, 在所有 Java 提交中击败了50.21%的用户
     */
    public boolean containsDuplicate(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            if (set.contains(num)){
                return true;
            }else {
                set.add(num);
            }
        }
        return false;
    }

    /**
     * 官方题解一：排序
     */
    class Solution {
        public boolean containsDuplicate(int[] nums) {
            Arrays.sort(nums);
            int n = nums.length;
            for (int i = 0; i < n - 1; i++) {
                if (nums[i] == nums[i + 1]) {
                    return true;
                }
            }
            return false;
        }
    }
}
