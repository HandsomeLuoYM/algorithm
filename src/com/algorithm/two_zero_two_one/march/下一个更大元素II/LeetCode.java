package com.algorithm.two_zero_two_one.march.下一个更大元素II;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;

/**
 * @author Ming
 * @date 2021/3/6 - 3:50
 * @describe
 */
public class LeetCode {
    /**
     * 给定一个循环数组（最后一个元素的下一个元素是数组的第一个元素），输出每个元素的下一个更大元素。数字 x 的下一个更大的元素是按数组遍历顺序，这个数字之后的第一个比它更大的数，这意味着你应该循环地搜索它的下一个更大的数。如果不存在，则输出 -1。
     *
     * 示例 1:
     *
     * 输入: [1,2,1]
     * 输出: [2,-1,2]
     * 解释: 第一个 1 的下一个更大的数是 2；
     * 数字 2 找不到下一个更大的数；
     * 第二个 1 的下一个最大的数需要循环搜索，结果也是 2。
     *
     */
    /**
     * 思路：因为stack里面存放的必然是从大到小的，然后用栈就行
     * 执行用时：13 ms, 在所有 Java 提交中击败了50.97%的用户
     * 内存消耗：40.3 MB, 在所有 Java 提交中击败了40.90%的用户
     */
    public int[] nextGreaterElements(int[] nums) {
        int[] rs = new int[nums.length];
        Arrays.fill(rs, -1);
        Stack<Integer> stack = new Stack<Integer>();
        int length = nums.length * 2 - 1, len = nums.length;
        for (int i = 0; i < length; i++) {
            while (!stack.isEmpty() && nums[stack.peek() % len] < nums[i % len]) {
                rs[stack.pop() % len] = nums[i % len];
            }
            stack.push(i);
        }
        return rs;
    }
}
