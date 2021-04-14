package com.algorithm.two_zero_two_one.february.可获得的最大点数;

/**
 * @author Ming
 * @date 2021/2/6 - 11:35
 * @describe
 */
public class LeetCode {
    /**
     * 几张卡牌 排成一行，每张卡牌都有一个对应的点数。点数由整数数组 cardPoints 给出。
     *
     * 每次行动，你可以从行的开头或者末尾拿一张卡牌，最终你必须正好拿 k 张卡牌。
     *
     * 你的点数就是你拿到手中的所有卡牌的点数之和。
     *
     * 给你一个整数数组 cardPoints 和整数 k，请你返回可以获得的最大点数。
     *
     * 输入：cardPoints = [1,2,3,4,5,6,1], k = 3
     * 输出：12
     * 解释：第一次行动，不管拿哪张牌，你的点数总是 1 。但是，先拿最右边的卡牌将会最大化你的可获得点数。最优策略是拿右边的三张牌，最终点数为 1 + 6 + 5 = 12 。
     *
     * 输入：cardPoints = [2,2,2], k = 2
     * 输出：4
     * 解释：无论你拿起哪两张卡牌，可获得的点数总是 4 。
     */
    /**
     * 思路：
     *     滑动窗口，来判断最大值
     * 执行用时：2 ms, 在所有 Java 提交中击败了95.77%的用户
     * 内存消耗：47.7 MB, 在所有 Java 提交中击败了38.20%的用户
     */
    public int maxScore(int[] cardPoints, int k) {
        int length = cardPoints.length, start = length - k, rs = 0, now;
        for (int i = start; i < length; i++) {
            rs += cardPoints[i];
        }
        now = rs;
        for (int i = 0; i < k; i++) {
            now = now - cardPoints[start + i] + cardPoints[i];
            rs = Math.max(rs, now);
        }
        return rs;
    }
}
