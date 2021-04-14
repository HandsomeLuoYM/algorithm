package com.algorithm.two_zero_two_one.january.旋转数组;

/**
 * @author Ming
 * @date 2021/1/8 - 1:24
 * @describe
 */
public class LeetCode {
    /**
     * 给定一个数组，将数组中的元素向右移动 k 个位置，其中 k 是非负数。
     *
     * 输入: [1,2,3,4,5,6,7] 和 k = 3
     * 输出: [5,6,7,1,2,3,4]
     * 解释:
     * 向右旋转 1 步: [7,1,2,3,4,5,6]
     * 向右旋转 2 步: [6,7,1,2,3,4,5]
     * 向右旋转 3 步: [5,6,7,1,2,3,4]
     *
     * 输入: [-1,-100,3,99] 和 k = 2
     * 输出: [3,99,-1,-100]
     * 解释:
     * 向右旋转 1 步: [99,-1,-100,3]
     * 向右旋转 2 步: [3,99,-1,-100]
     */
    /**
     * 思路：赋值再复制
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
     * 内存消耗：39 MB, 在所有 Java 提交中击败了48.44%的用户
     */
    public void rotate(int[] nums, int k) {
        int[] nums2 = new int[nums.length];
        int length = nums.length;
        for (int i = 0; i < length; i++){
            nums2[(i + k) % length] = nums[i];
        }
        System.arraycopy(nums2, 0, nums, 0, length);
    }
}
