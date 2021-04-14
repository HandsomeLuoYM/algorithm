package com.algorithm.two_zero_two_one.february.最大连续1的个数III;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Ming
 * @date 2021/2/19 - 0:02
 * @describe
 */
public class LeetCode {
    /**
     * 给定一个由若干 0 和 1 组成的数组 A，我们最多可以将 K 个值从 0 变成 1 。
     *
     * 返回仅包含 1 的最长（连续）子数组的长度。
     *
     *
     * 示例 1：
     *
     * 输入：A = [1,1,1,0,0,0,1,1,1,1,0], K = 2
     * 输出：6
     * 解释：
     * [1,1,1,0,0,1,1,1,1,1,1]
     * 粗体数字从 0 翻转到 1，最长的子数组长度为 6。
     * 示例 2：
     *
     * 输入：A = [0,0,1,1,0,0,1,1,1,0,1,1,0,0,0,1,1,1,1], K = 3
     * 输出：10
     * 解释：
     * [0,0,1,1,1,1,1,1,1,1,1,1,0,0,0,1,1,1,1]
     * 粗体数字从 0 翻转到 1，最长的子数组长度为 10。
     *
     */
    /**
     * 思路：先并和，在算，A99不知道哪里有问题
     */
    public int longestOnes(int[] A, int K) {
        List<int[]> list = new ArrayList<int[]>();
        int length = A.length, start, end;
        //分段隔开
        for (int i = 0; i < length; i++) {
            if (A[i] == 1) {
                start = i;
                do {
                    i++;
                } while (i < length && A[i] == 1);
                list.add(new int[]{start, i - 1});
            }
        }
        if (list.size() == 0) return Math.min(K, length);
        int now = K, rs = list.get(0)[1] - list.get(0)[0] + 1 + Math.min(list.get(0)[0], K);
        start = 0;
        for (int i = 1; i < list.size(); i++) {
            if (now >= list.get(i)[0] - list.get(i - 1)[1] - 1) {
                now -= (list.get(i)[0] - list.get(i - 1)[1] - 1);
            }else {//start指针后移
                //指针后移
                while (start < i && now < list.get(i)[0] - list.get(i - 1)[1] + 1){
                    now += (list.get(start + 1)[0] - list.get(start)[1] - 1);
                    start++;
                }
                now -= (list.get(i)[0] - list.get(i - 1)[1] - 1);
            }
            //修改最大值
            rs = Math.max(rs, list.get(i)[1] - list.get(start)[0] + now + 1);
        }
        return Math.min(A.length, rs);
    }

    /**
     * 思路：双指针
     * 执行用时：4 ms, 在所有 Java 提交中击败了47.01%的用户
     * 内存消耗：39.4 MB, 在所有 Java 提交中击败了95.61%的用户
     */
    public int longestOnes1(int[] A, int K) {
        int length = A.length, start = 0, rs = 0;
        for (int i = 0; i < A.length; i++) {
            if (K == 0) {
                if (A[i] == 0) {
                    if (A[start] == 1) {
                        K--;
                    }
                    start++;
                }
            }else if (K > 0) {
                if (A[i] == 0) K--;
            }else {
                if (A[i] == 0) {
                    if (A[start] == 1) K--;
                }else {
                    if (A[start] == 0) K++;
                }
                start++;
            }
            rs = Math.max(rs, i - start);
        }
        return rs;
    }

    /**
     * 二分查找：先前缀和，再查找
     */
    class Solution {
        public int longestOnes(int[] A, int K) {
            int n = A.length;
            int[] P = new int[n + 1];
            for (int i = 1; i <= n; ++i) {
                P[i] = P[i - 1] + (1 - A[i - 1]);
            }

            int ans = 0;
            //遍历n
            for (int right = 0; right < n; ++right) {
                int left = binarySearch(P, P[right + 1] - K);
                ans = Math.max(ans, right - left + 1);
            }
            return ans;
        }

        public int binarySearch(int[] P, int target) {
            int low = 0, high = P.length - 1;
            while (low < high) {
                int mid = (high - low) / 2 + low;
                if (P[mid] < target) {
                    low = mid + 1;
                } else {
                    high = mid;
                }
            }
            return low;
        }
    }

}
