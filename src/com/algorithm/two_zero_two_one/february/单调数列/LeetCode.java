package com.algorithm.two_zero_two_one.february.单调数列;

/**
 * @author Ming
 * @date 2021/2/28 - 2:21
 * @describe
 */
public class LeetCode {
    /**
     * 如果数组是单调递增或单调递减的，那么它是单调的。
     *
     * 如果对于所有 i <= j，A[i] <= A[j]，那么数组 A 是单调递增的。 如果对于所有 i <= j，A[i]> = A[j]，那么数组 A 是单调递减的。
     *
     * 当给定的数组 A 是单调数组时返回 true，否则返回 false。
     *
     * 示例 1：
     *
     * 输入：[1,2,2,3]
     * 输出：true
     * 示例 2：
     *
     * 输入：[6,5,4,4]
     * 输出：true
     * 示例 3：
     *
     * 输入：[1,3,2]
     * 输出：false
     * 示例 4：
     *
     * 输入：[1,2,4,5]
     * 输出：true
     * 示例 5：
     *
     * 输入：[1,1,1]
     * 输出：true
     *
     */
    /**
     * 思路：直接判断
     * 执行用时：1 ms, 在所有 Java 提交中击败了100.00%的用户
     * 内存消耗：46.4 MB, 在所有 Java 提交中击败了84.92%的用户
     */
    public boolean isMonotonic(int[] A) {
        int i = 0;
        while (i + 1 < A.length && A[i] == A[i + 1]) i++;
        if (i + 1 == A.length) return true;
        else {
            boolean flag = A[i] < A[i + 1];
            for (i++; i + 1 < A.length; i++) {
                if (flag && A[i] > A[i + 1] || !flag && A[i] < A[i + 1]) return false;
            }
            return true;
        }
    }
}
