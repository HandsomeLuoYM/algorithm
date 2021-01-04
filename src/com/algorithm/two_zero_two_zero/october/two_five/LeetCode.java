package com.algorithm.two_zero_two_zero.october.two_five;

/**
 * @author Ming
 * @date 2020/10/25 - 11:51
 * @describe
 */
public class LeetCode {

    /**
     * 我们把数组 A 中符合下列属性的任意连续子数组 B 称为 “山脉”：
     *      B.length >= 3
     *      存在 0 < i < B.length - 1 使得 B[0] < B[1] < ... B[i-1] < B[i] > B[i+1] > ... > B[B.length - 1]
     * （注意：B 可以是 A 的任意子数组，包括整个数组 A。）
     *
     * 给出一个整数数组 A，返回最长 “山脉” 的长度。
     * 如果不含有 “山脉” 则返回 0。
     *
     * 输入：[2,1,4,7,3,2,5]
     * 输出：5
     * 解释：最长的 “山脉” 是 [1,4,7,3,2]，长度为 5。
     *
     * 输入：[2,2,2]
     * 输出：0
     * 解释：不含 “山脉”。
     */

    /**
     * 自己的思路：
     *      设置一个flag来表示是上坡还是下坡，然后再按照各自的规则来判断长度，当然长度必须大于2
     * 执行用时：2 ms, 在所有 Java 提交中击败了99.84%的用户
     * 内存消耗：39.2 MB, 在所有 Java 提交中击败了94.87%的用户
     */
    public int longestMountain(int[] A) {
        int max = 0, now = 1, length = A.length;
        boolean big = true, small = true;
        for (int i=1; i<length; i++){
            if (big && A[i] < A[i-1]) {
                big = false;
            }
            if (small && A[i] > A[i-1]) {
                small = false;
            }
            if (!small && !big) break;
        }
        if (small || big) return 0;
        //上坡为true，下坡为false
        boolean flag = true,equal = false;
        for (int i = 1; i<length; i++){
            if (flag){
                if (A[i] > A[i-1]) now++;
                else if(A[i] < A[i-1]){
                    flag = false;
                    now++;
                }else{
                    now=1;
                    while (i+1<length && A[i+1] <= A[i]) i++;
                }
            }else {
                if (A[i] < A[i-1]){
                    now++;
                }else if(A[i] > A[i-1]){
                    flag = true;
                    if (now > max) max = now;
                    now = 2;
                }else{
                    flag = true;
                    if (now > max) max = now;
                    now = 1;
                }
            }
        }
        if (now > max) max = now;
        return max > 2 ? max : 0;
    }

    /**
     * 官方题解一、枚举山顶
     * 用数组来记录第 n 个点做为上坡和下坡时的长度，再最后来用上下坡来计算最大的值
     */
    class Solution {
        public int longestMountain(int[] A) {
            int n = A.length;
            if (n == 0) {
                return 0;
            }
            int[] left = new int[n];
            for (int i = 1; i < n; ++i) {
                left[i] = A[i - 1] < A[i] ? left[i - 1] + 1 : 0;
            }
            int[] right = new int[n];
            for (int i = n - 2; i >= 0; --i) {
                right[i] = A[i + 1] < A[i] ? right[i + 1] + 1 : 0;
            }

            int ans = 0;
            for (int i = 0; i < n; ++i) {
                if (left[i] > 0 && right[i] > 0) {
                    ans = Math.max(ans, left[i] + right[i] + 1);
                }
            }
            return ans;
        }
    }

    /**
     * 官方题解二（枚举山脚）
     */
    class Solution1 {
        public int longestMountain(int[] A) {
            int n = A.length;
            int ans = 0;
            int left = 0;
            while (left + 2 < n) {
                int right = left + 1;
                if (A[left] < A[left + 1]) {
                    while (right + 1 < n && A[right] < A[right + 1]) {
                        ++right;
                    }
                    if (right < n - 1 && A[right] > A[right + 1]) {
                        while (right + 1 < n && A[right] > A[right + 1]) {
                            ++right;
                        }
                        ans = Math.max(ans, right - left + 1);
                    } else {
                        ++right;
                    }
                }
                left = right;
            }
            return ans;
        }
    }


}
