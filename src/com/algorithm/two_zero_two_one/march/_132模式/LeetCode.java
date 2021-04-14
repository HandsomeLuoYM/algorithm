package com.algorithm.two_zero_two_one.march._132模式;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @author Ming
 * @date 2021/3/24 - 0:35
 * @describe
 */
public class LeetCode {
    /**
     * 给定一个整数序列：a1, a2, ..., an，一个132模式的子序列 ai, aj, ak 被定义为：当 i < j < k 时，ai < ak < aj。设计一个算法，当给定有 n 个数字的序列时，验证这个序列中是否含有132模式的子序列。
     * <p>
     * 注意：n 的值小于15000。
     * <p>
     * 示例1:
     * <p>
     * 输入: [1, 2, 3, 4]
     * <p>
     * 输出: False
     * <p>
     * 解释: 序列中不存在132模式的子序列。
     * 示例 2:
     * <p>
     * 输入: [3, 1, 4, 2]
     * <p>
     * 输出: True
     * <p>
     * 解释: 序列中有 1 个132模式的子序列： [1, 4, 2].
     * 示例 3:
     * <p>
     * 输入: [-1, 3, 2, 0]
     * <p>
     * 输出: True
     */
    public boolean find132pattern(int[] nums) {
        int n = nums.length;
        Deque<Integer> candidateK = new LinkedList<Integer>();
        candidateK.push(nums[n - 1]);
        int maxK = Integer.MIN_VALUE;
        for (int i = n - 2; i >= 0; --i) {
            if (nums[i] < maxK) {
                return true;
            }
            //更新第二大的值
            while (!candidateK.isEmpty() && nums[i] > candidateK.peek()) {
                maxK = candidateK.pop();
            }
            if (nums[i] > maxK) {
                candidateK.push(nums[i]);
            }
        }
        return false;
    }


}
