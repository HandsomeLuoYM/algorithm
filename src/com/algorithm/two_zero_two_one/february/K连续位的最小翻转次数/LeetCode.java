package com.algorithm.two_zero_two_one.february.K连续位的最小翻转次数;

/**
 * @author Ming
 * @date 2021/2/18 - 1:24
 * @describe
 */
public class LeetCode {
    /**
     * 在仅包含 0 和 1 的数组 A 中，一次 K 位翻转包括选择一个长度为 K 的（连续）子数组，同时将子数组中的每个 0 更改为 1，而每个 1 更改为 0。
     *
     * 返回所需的 K 位翻转的最小次数，以便数组没有值为 0 的元素。如果不可能，返回 -1。
     *
     * 示例 1：
     *
     * 输入：A = [0,1,0], K = 1
     * 输出：2
     * 解释：先翻转 A[0]，然后翻转 A[2]。
     * 示例 2：
     *
     * 输入：A = [1,1,0], K = 2
     * 输出：-1
     * 解释：无论我们怎样翻转大小为 2 的子数组，我们都不能使数组变为 [1,1,1]。
     * 示例 3：
     *
     * 输入：A = [0,0,0,1,0,1,1,0], K = 3
     * 输出：3
     * 解释：
     * 翻转 A[0],A[1],A[2]: A变成 [1,1,1,1,0,1,1,0]
     * 翻转 A[4],A[5],A[6]: A变成 [1,1,1,1,1,0,0,0]
     * 翻转 A[5],A[6],A[7]: A变成 [1,1,1,1,1,1,1,1]
     *
     */
    /**
     * 超出时间限制，因为有重叠的修改
     */
    public int minKBitFlips(int[] A, int K) {
        int length = A.length, rs = 0, all = -1;
        boolean flag = true;
        for (int i = 0; i <= length - K; i++) {
            if (A[i] == 0) {
                for (int j = 0; j < K; j++) {
                    if (A[j + i] ==  0) {
                        A[j + i] = 1;
                        if (flag) all++;
                    }else {
                        A[j + i] = 0;
                        flag = false;
                    }
                }
                i += all;
                all = -1;
                flag = true;
                rs++;
            }
        }
        for (int i = length - K; i <length; i++) {
            if (A[i] == 0) return -1;
        }
        return rs;
    }

    /**
     * 思路：记录前面的次数，然后就可以不用更换A数组的值
     * 执行用时：8 ms, 在所有 Java 提交中击败了78.26%的用户
     * 内存消耗：46.7 MB, 在所有 Java 提交中击败了23.13%的用户
     */
    public int minKBitFlips1(int[] A, int K) {
        int now, length = A.length;
        int[] nums = new int[length + 1];
        for (int i = 1; i <= length; i++) {
            if (i <= K && i <= length - K + 1) {
                now = nums[i - 1];
                if (A[i - 1] == 0 && now % 2 == 0 || A[i - 1] == 1 && now % 2 == 1) {
                    nums[i] = now + 1;
                }else {
                    nums[i] = now;
                }
            }else if (i <= length - K + 1){
                now =  nums[i - 1] - nums[i - K];
                if (A[i - 1] == 0 && now % 2 == 0 || A[i - 1] == 1 && now % 2 == 1) {
                    nums[i] = nums[i - 1] + 1;
                }else {
                    nums[i] = nums[i - 1];
                }
            }else {
                now = i - K >= 0 ? nums[i - 1] - nums[i - K] : nums[i - 1];
                if (A[i - 1] == 0) {
                    if (now % 2 == 0) return -1;
                }else {
                    if (now % 2 == 1) return -1;
                }
                nums[i] = nums[i - 1];
            }
        }
        return nums[length];
    }

    class Solution {
        public int minKBitFlips(int[] A, int K) {
            int n = A.length;
            int ans = 0, revCnt = 0;
            for (int i = 0; i < n; ++i) {
                if (i >= K && A[i - K] > 1) {
                    revCnt ^= 1;
                    A[i - K] -= 2; // 复原数组元素，若允许修改数组 A，则可以省略
                }
                if (A[i] == revCnt) {
                    if (i + K > n) {
                        return -1;
                    }
                    ++ans;
                    revCnt ^= 1;
                    A[i] += 2;
                }
            }
            return ans;
        }
    }

}
