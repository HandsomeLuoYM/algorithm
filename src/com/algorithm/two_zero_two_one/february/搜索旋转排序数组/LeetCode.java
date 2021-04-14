package com.algorithm.two_zero_two_one.february.搜索旋转排序数组;

/**
 * @author Ming
 * @date 2021/2/14 - 0:59
 * @describe
 */
public class LeetCode {
    /**
     * 升序排列的整数数组 nums 在预先未知的某个点上进行了旋转（例如， [0,1,2,4,5,6,7] 经旋转后可能变为 [4,5,6,7,0,1,2] ）。
     *
     * 请你在数组中搜索 target ，如果数组中存在这个目标值，则返回它的索引，否则返回 -1 。
     *
     * 示例 1：
     *
     * 输入：nums = [4,5,6,7,0,1,2], target = 0
     * 输出：4
     * 示例 2：
     *
     * 输入：nums = [4,5,6,7,0,1,2], target = 3
     * 输出：-1
     * 示例 3：
     *
     * 输入：nums = [1], target = 0
     * 输出：-1
     *
     */
    /**
     * 思路：二分查找修改版
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
     * 内存消耗：37.9 MB, 在所有 Java 提交中击败了52.75%的用户
     */
    public int search(int[] nums, int target) {
        int right = nums.length - 1, left = 0, half;
        while (right >= left) {
            half = (right + left) / 2;
            if (nums[half] == target) return half;
            if (nums[left] == target) return left;
            if (nums[right] == target) return right;
            if (nums[left] < nums[half]) {
                if (nums[left] < target && target < nums[half]) {
                    right = half - 1;
                }else {
                    left = half + 1;
                }
            }else {
                if (nums[half] < target && target < nums[right]) {
                    left = half + 1;
                }else {
                    right = half - 1;
                }
            }
        }
        return -1;
    }
}
