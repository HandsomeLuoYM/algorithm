package com.algorithm.two_zero_two_one.february.最大连续1的个数;

/**
 * @author Ming
 * @date 2021/2/16 - 1:27
 * @describe
 */
public class LeetCode {
    /**
     * 给定一个二进制数组， 计算其中最大连续 1 的个数。
     *
     * 示例：
     *
     * 输入：[1,1,0,1,1,1]
     * 输出：3
     * 解释：开头的两位和最后的三位都是连续 1 ，所以最大连续 1 的个数是 3.
     *  
     *
     * 提示：
     *
     * 输入的数组只包含 0 和 1 。
     * 输入数组的长度是正整数，且不超过 10,000。
     *
     */
    /** 思路：贪心，一次遍历
     * 执行用时：2 ms, 在所有 Java 提交中击败了89.69%的用户
     * 内存消耗：39.9 MB, 在所有 Java 提交中击败了80.95%的用户
     */
    public int findMaxConsecutiveOnes(int[] nums) {
        int rs = 0, now = 0;
        for (int num : nums) {
            if (num == 1) {
                now++;
            }else {
                rs = Math.max(rs, now);
                now = 0;
            }
        }
        return Math.max(rs, now);
    }
}
