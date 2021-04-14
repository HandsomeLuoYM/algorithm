package com.algorithm.two_zero_two_one.february.非递减数列;

import jdk.nashorn.internal.ir.annotations.Ignore;

/**
 * @author Ming
 * @date 2021/2/7 - 0:34
 * @describe
 */
public class LeetCode {
    /**
     * 给你一个长度为 n 的整数数组，请你判断在 最多 改变 1 个元素的情况下，该数组能否变成一个非递减数列。
     *
     * 我们是这样定义一个非递减数列的： 对于数组中所有的 i (0 <= i <= n-2)，总满足 nums[i] <= nums[i + 1]。
     *
     * 输入: nums = [4,2,3]
     * 输出: true
     * 解释: 你可以通过把第一个4变成1来使得它成为一个非递减数列。
     *
     * 输入: nums = [4,2,1]
     * 输出: false
     * 解释: 你不能在只改变一个元素的情况下将其变为非递减数列。
     */
    /**
     * 思路：
     *      边遍历边判断
     * 执行用时：1 ms, 在所有 Java 提交中击败了99.54%的用户
     * 内存消耗：39.7 MB, 在所有 Java 提交中击败了73.83%的用户
     */
    public boolean checkPossibility(int[] nums) {
        boolean flag = true;
        int length = nums.length;
        for (int i = 1; i < length; i++) {
            if (nums[i] < nums[i-1]) {
                if (flag) {
                    if (i > 1) {
                        if (nums[i] < nums[i - 2]) nums[i] = nums[i - 1];
                    }else {
                        nums[i] = Math.min(nums[i], nums[i - 1]);
                    }
                    flag = false;
                }else return false;
            }
        }
        return true;
    }
}
