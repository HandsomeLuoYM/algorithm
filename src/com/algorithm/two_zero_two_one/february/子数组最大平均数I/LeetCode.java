package com.algorithm.two_zero_two_one.february.子数组最大平均数I;

/**
 * @author Ming
 * @date 2021/2/4 - 1:01
 * @describe
 */
public class LeetCode {
    /**
     * 给定 n 个整数，找出平均数最大且长度为 k 的连续子数组，并输出该最大平均数。
     *
     * 示例：
     *
     * 输入：[1,12,-5,-6,50,3], k = 4
     * 输出：12.75
     * 解释：最大平均数 (12-5-6+50)/4 = 51/4 = 12.75
     *
     */
    /**
     * 思路：滑动窗口，边遍历时边修改最大值，最后再取平均值
     * 执行用时：2 ms, 在所有 Java 提交中击败了100.00%的用户
     * 内存消耗：42.9 MB, 在所有 Java 提交中击败了23.44%的用户
     */
    public double findMaxAverage(int[] nums, int k) {
        int max = 0, length = nums.length, now;
        for (int i = 0; i < k; i++) max += nums[i];
        now = max;
        for (int i = k; i < length; i++) {
            now = now - nums[i - k] + nums[i];
            max = Math.max(max, now);
        }
        return Double.valueOf(max)/k;
    }
}
