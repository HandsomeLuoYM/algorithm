package com.algorithm.two_zero_two_one.january.三个数的最大乘积;

/**
 * @author Ming
 * @date 2021/1/28 - 17:26
 * @describe
 */
public class LeetCode {

    /**
     * 给你一个整型数组 nums ，在数组中找出由三个数组成的最大乘积，并输出这个乘积。
     */
    /**
     * 我们实际上只要求出数组中最大的三个数以及最小的两个数，因此我们可以不用排序，用线性扫描直接得出这五个数。
     * 执行用时：2 ms, 在所有 Java 提交中击败了99.81%的用户
     * 内存消耗：40 MB, 在所有 Java 提交中击败了53.88%的用户
     */
    public int maximumProduct(int[] nums) {
        // 最小的和第二小的
        int min1 = Integer.MAX_VALUE, min2 = Integer.MAX_VALUE;
        // 最大的、第二大的和第三大的
        int max1 = Integer.MIN_VALUE, max2 = Integer.MIN_VALUE, max3 = Integer.MIN_VALUE;

        for (int x : nums) {
            if (x < min1) {
                min2 = min1;
                min1 = x;
            } else if (x < min2) {
                min2 = x;
            }

            if (x > max1) {
                max3 = max2;
                max2 = max1;
                max1 = x;
            } else if (x > max2) {
                max3 = max2;
                max2 = x;
            } else if (x > max3) {
                max3 = x;
            }
        }

        return Math.max(min1 * min2 * max1, max1 * max2 * max3);
    }
}
