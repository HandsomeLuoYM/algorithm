package com.algorithm.two_zero_two_one.february.最长湍流子数组;

/**
 * @author Ming
 * @date 2021/2/8 - 11:54
 * @describe
 */
public class LeetCode {
    /**
     * 当 A 的子数组 A[i], A[i+1], ..., A[j] 满足下列条件时，我们称其为湍流子数组：
     * <p>
     * 若 i <= k < j，当 k 为奇数时， A[k] > A[k+1]，且当 k 为偶数时，A[k] < A[k+1]；
     * 或 若 i <= k < j，当 k 为偶数时，A[k] > A[k+1] ，且当 k 为奇数时， A[k] < A[k+1]。
     * 也就是说，如果比较符号在子数组中的每个相邻元素对之间翻转，则该子数组是湍流子数组。
     * <p>
     * 返回 A 的最大湍流子数组的长度。
     * <p>
     * 输入：[9,4,2,10,7,8,8,1,9]
     * 输出：5
     * 解释：(A[1] > A[2] < A[3] > A[4] < A[5])
     * <p>
     * 输入：[4,8,12,16]
     * 输出：2
     */
    /**
     * 思路：双指针
     * 执行用时：5 ms, 在所有 Java 提交中击败了97.24%的用户
     * 内存消耗：41.8 MB, 在所有 Java 提交中击败了68.11%的用户
     */
    public int maxTurbulenceSize(int[] arr) {
        int rs = 0, length = arr.length, start = 0, i = 0;
        if (length < 2) return length;
        boolean flag = arr[i] > arr[i + 1];
        while (i < length - 1) {
            if (flag) {
                if (arr[i] <= arr[i + 1]) {
                    rs = Math.max(rs, i - start + 1);
                    while (i < length - 1 && arr[i] == arr[i + 1]) i++;
                    start = i;
                    if (i >= length - 1) break;
                    flag = arr[i] > arr[i + 1];
                }
            } else {
                if (arr[i] >= arr[i + 1]) {
                    rs = Math.max(rs, i - start + 1);
                    while (i < length - 1 && arr[i] == arr[i + 1]) i++;
                    start = i;
                    if (i >= length - 1) break;
                    flag = arr[i] > arr[i + 1];
                }
            }
            i++;
            flag = !flag;
        }

        rs = Math.max(rs, length - start);
        return rs;
    }

    /**
     * 动态规划
     */
    class Solution {
        public int maxTurbulenceSize(int[] arr) {
            int ret = 1;
            int n = arr.length;
            int dp0 = 1, dp1 = 1;
            for (int i = 1; i < n; i++) {
                if (arr[i - 1] > arr[i]) {
                    dp0 = dp1 + 1;
                    dp1 = 1;
                } else if (arr[i - 1] < arr[i]) {
                    dp1 = dp0 + 1;
                    dp0 = 1;
                } else {
                    dp0 = 1;
                    dp1 = 1;
                }
                ret = Math.max(ret, dp0);
                ret = Math.max(ret, dp1);
            }
            return ret;
        }
    }

}
