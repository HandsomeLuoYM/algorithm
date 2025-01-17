package com.algorithm.two_zero_two_zero.december.使用最小花费爬楼梯;

/**
 * @author Ming
 * @date 2020/12/21 - 10:09
 * @describe
 */
public class LeetCode {
    /**
     * 数组的每个索引作为一个阶梯，第 i个阶梯对应着一个非负数的体力花费值 cost[i](索引从0开始)。
     *
     * 每当你爬上一个阶梯你都要花费对应的体力花费值，然后你可以选择继续爬一个阶梯或者爬两个阶梯。
     *
     * 您需要找到达到楼层顶部的最低花费。在开始时，你可以选择从索引为 0 或 1 的元素作为初始阶梯。
     *
     * 输入: cost = [10, 15, 20]
     * 输出: 15
     * 解释: 最低花费是从cost[1]开始，然后走两步即可到阶梯顶，一共花费15
     *
     * 输入: cost = [1, 100, 1, 1, 1, 100, 1, 1, 100, 1]
     * 输出: 6
     * 解释: 最低花费方式是从cost[0]开始，逐个经过那些1，跳过cost[3]，一共花费6。
     *
     */
    /**
     * 思路：
     *      动态规划
     * 执行用时：1 ms, 在所有 Java 提交中击败了99.68%的用户
     * 内存消耗：38.1 MB, 在所有 Java 提交中击败了80.77%的用户
     */
    public int minCostClimbingStairs(int[] cost) {
        int[] number = new int[cost.length+1];
        int length = cost.length;
        number[0] = number[1] = 0;
        for (int i = 2; i <= length; i++){
            number[i] = Math.min(number[i-1] + cost[i-1], number[i-2] + cost[i-2]);
        }
        return number[length];
    }
}
