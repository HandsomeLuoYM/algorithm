package com.algorithm.two_zero_two_one.january.最长连续递增序列;

/**
 * @author Ming
 * @date 2021/1/24 - 20:06
 * @describe
 */
public class LeetCode {
    /**
     * 给定一个未经排序的整数数组，找到最长且 连续递增的子序列，并返回该序列的长度。
     *
     * 连续递增的子序列 可以由两个下标 l 和 r（l < r）确定，如果对于每个 l <= i < r，
     * 都有 nums[i] < nums[i + 1] ，那么子序列 [nums[l], nums[l + 1], ..., nums[r - 1], nums[r]] 就是连续递增子序列。
     *
     * 输入：nums = [1,3,5,4,7]
     * 输出：3
     * 解释：最长连续递增序列是 [1,3,5], 长度为3。
     * 尽管 [1,3,5,7] 也是升序的子序列, 但它不是连续的，因为 5 和 7 在原数组里被 4 隔开。
     *
     * 输入：nums = [2,2,2,2,2]
     * 输出：1
     * 解释：最长连续递增序列是 [2], 长度为1。
     */
    /**
     * 思路：边遍历边判断
     * 执行用时：2 ms, 在所有 Java 提交中击败了46.77%的用户
     * 内存消耗：39.3 MB, 在所有 Java 提交中击败了26.26%的用户
     */
    public int findLengthOfLCIS(int[] nums) {
        int max = 0, length = nums.length, nowNumber = 1;
        if (length < 2) return length;
        for (int i = 1; i < length; i++){
            if (nums[i-1] < nums[i]) {
                nowNumber++;
            }else {
                nowNumber = 1;
            }
            max = Math.max(max, nowNumber);
        }
        return max;
    }
}
