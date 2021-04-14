package com.algorithm.two_zero_two_one.january.寻找数组的中心索引;

/**
 * @author Ming
 * @date 2021/1/28 - 11:12
 * @describe
 */
public class LeetCode {

    /**
     * 给定一个整数类型的数组 nums，请编写一个能够返回数组 “中心索引” 的方法。
     *
     * 我们是这样定义数组 中心索引 的：数组中心索引的左侧所有元素相加的和等于右侧所有元素相加的和。
     *
     * 如果数组不存在中心索引，那么我们应该返回 -1。如果数组有多个中心索引，那么我们应该返回最靠近左边的那一个。
     *
     */
    /**
     * 适合全都是正数的，这样只需要一次遍历
     */
    public int pivotIndex(int[] nums) {
        if (nums.length == 0) return -1;
        int left = 1, right = nums.length - 2, lAll = nums[0], rAll = nums[right];
        while (left <= right) {
            if (left == right) return lAll == rAll ? left : -1;
            if (lAll > rAll) {
                rAll += nums[right--];
            }else {
                lAll += nums[left++];
            }
        }
        return lAll == rAll ? left : -1;
    }

    /**
     * 思路：两次遍历，一次求前缀和，一次求结果
     * 执行用时：2 ms, 在所有 Java 提交中击败了55.66%的用户
     * 内存消耗：38.9 MB, 在所有 Java 提交中击败了86.80%的用户
     */
    public int pivotIndex1(int[] nums) {
        if (nums.length == 0) return -1;
        int length = nums.length;
        int[] all = new int[nums.length + 1];
        for (int i = 1; i <= length; i++){
            all[i] = all[i - 1] + nums[i - 1];
        }
        for (int i = 0; i < length; i++) {
            if (all[i] * 2 == all[length] - nums[i]) return i;
        }
        return -1;
    }

    public static void main(String[] args) {
        LeetCode l = new LeetCode();
        System.out.println(l.pivotIndex(new int[]{-1, -1, -1, -1, -1, 0}));
    }
}
