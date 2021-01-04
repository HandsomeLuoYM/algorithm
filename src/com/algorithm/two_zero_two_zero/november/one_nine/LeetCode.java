package com.algorithm.two_zero_two_zero.november.one_nine;

/**
 * @author Ming
 * @date 2020/11/19 - 0:20
 * @describe 283. 移动零
 */
public class LeetCode {
    /**
     * 给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。
     *
     * 输入: [0,1,0,3,12]
     * 输出: [1,3,12,0,0]
     */
    /**
     * 自己的思路：
     *      设置双指针来边遍历边替换，然后再最后再统一赋 0
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
     * 内存消耗：38.7 MB, 在所有 Java 提交中击败了82.83%的用户
     */
    public void moveZeroes(int[] nums) {
        int left = 0, right = 0, length = nums.length-1;
        while (length!=right){
            if (nums[right] != 0){
                if (left != right) nums[left] = nums[right];
                left++;
            }
            right++;
        }
        for (int i = left; i<length; i++) nums[i] = 0;
    }

    /**
     * 官方题解，边遍历边交换
     */
    class Solution {
        public void moveZeroes(int[] nums) {
            int n = nums.length, left = 0, right = 0;
            while (right < n) {
                if (nums[right] != 0) {
                    swap(nums, left, right);
                    left++;
                }
                right++;
            }
        }

        public void swap(int[] nums, int left, int right) {
            int temp = nums[left];
            nums[left] = nums[right];
            nums[right] = temp;
        }
    }


}
