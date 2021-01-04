package com.algorithm.two_zero_two_zero.december.按要求补齐数组;

/**
 * @author Ming
 * @date 2020/12/29 - 0:21
 * @describe
 */
public class leetCode {

    /**
     * 给定一个已排序的正整数数组 nums，和一个正整数 n 。从 [1, n] 区间内选取任意个数字补充到 nums 中，
     * 使得 [1, n] 区间内的任何数字都可以用 nums 中某几个数字的和来表示。
     * 请输出满足上述要求的最少需要补充的数字个数。
     *
     * 输入: nums = [1,3], n = 6
     * 输出: 1
     * 解释:
     * 根据 nums 里现有的组合 [1], [3], [1,3]，可以得出 1, 3, 4。
     * 现在如果我们将 2 添加到 nums 中， 组合变为: [1], [2], [3], [1,3], [2,3], [1,2,3]。
     * 其和可以表示数字 1, 2, 3, 4, 5, 6，能够覆盖 [1, 6] 区间里所有的数。
     * 所以我们最少需要添加一个数字。
     *
     * 输入: nums = [1,5,10], n = 20
     * 输出: 2
     * 解释: 我们需要添加 [2, 4]。
     *
     * 输入: nums = [1,2,2], n = 5
     * 输出: 0
     */
    /**
     * 贪心思想：
     *      1、既然 [ 1 ， x] 可以有，那么就可以造出 x + nums[i] 这整个区间的数
     *      2、而如果当前数  nums[i] > x，那么就他们之间的区间就无法造了，这时候只需要添加最大的数 X ，就可以把 [x,2x] 之间的数填满，然后再回到 1 中
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
     * 内存消耗：37.9 MB, 在所有 Java 提交中击败了89.53%的用户
     */
    public int minPatches(int[] nums, int n) {
        int patches = 0;
        long x = 1;
        int length = nums.length, index = 0;
        while (x <= n) {
            if (index < length && nums[index] <= x) {
                x += nums[index];
                index++;
            } else {
                //添加一个值为 X 的数，翻倍
                x *= 2;
                patches++;
            }
        }
        return patches;
    }

}
