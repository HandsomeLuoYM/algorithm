package com.algorithm.two_zero_two_one.february.最大子序和;

/**
 * @author Ming
 * @date 2021/2/5 - 11:30
 * @describe
 */
public class LeetCode {
    /**
     * 给定一个整数数组 nums ，找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
     *
     * 输入：nums = [-2,1,-3,4,-1,2,1,-5,4]
     * 输出：6
     * 解释：连续子数组 [4,-1,2,1] 的和最大，为 6 。
     *
     * 输入：nums = [1]
     * 输出：1
     */
    /**
     * 思路：遍历时使用动态规划思想
     * 执行用时：1 ms, 在所有 Java 提交中击败了93.46%的用户
     * 内存消耗：38.5 MB, 在所有 Java 提交中击败了39.90%的用户
     */
    public int maxSubArray(int[] nums) {
        int rs = Integer.MIN_VALUE, length = nums.length, now = 0;
        for (int i = 0; i < length; i++) {
            now += nums[i];
            if (now < 0) {
                now = 0;
            }
            rs = Math.max(rs, now);
        }
        return rs;
    }
}
