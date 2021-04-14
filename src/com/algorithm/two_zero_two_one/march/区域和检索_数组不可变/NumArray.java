package com.algorithm.two_zero_two_one.march.区域和检索_数组不可变;

/**
 * @author Ming
 * @date 2021/3/1 - 0:23
 * @describe
 */
public class NumArray {
    /**
     * 给定一个整数数组  nums，求出数组从索引 i 到 j（i ≤ j）范围内元素的总和，包含 i、j 两点。
     *
     * 实现 NumArray 类：
     *
     * NumArray(int[] nums) 使用数组 nums 初始化对象
     * int sumRange(int i, int j) 返回数组 nums 从索引 i 到 j（i ≤ j）范围内元素的总和，包含 i、j 两点（也就是 sum(nums[i], nums[i + 1], ... , nums[j])）
     *  
     *
     * 示例：
     *
     * 输入：
     * ["NumArray", "sumRange", "sumRange", "sumRange"]
     * [[[-2, 0, 3, -5, 2, -1]], [0, 2], [2, 5], [0, 5]]
     * 输出：
     * [null, 1, -1, -3]
     *
     * 解释：
     * NumArray numArray = new NumArray([-2, 0, 3, -5, 2, -1]);
     * numArray.sumRange(0, 2); // return 1 ((-2) + 0 + 3)
     * numArray.sumRange(2, 5); // return -1 (3 + (-5) + 2 + (-1))
     * numArray.sumRange(0, 5); // return -3 ((-2) + 0 + 3 + (-5) + 2 + (-1))
     */
    /**
     * 思路：前缀和
     * 执行用时：9 ms, 在所有 Java 提交中击败了99.83%的用户
     * 内存消耗：41.3 MB, 在所有 Java 提交中击败了62.37%的用户
     */
    int[] index;
    public NumArray(int[] nums) {
        index = new int[nums.length + 2];
        for (int i = 0; i < nums.length; i++) {
            index[i + 1] = index[i] + nums[i];
        }
    }

    public int sumRange(int i, int j) {
        return index[j + 1] - index[i];
    }

}
