package com.algorithm.two_zero_two_zero.november.two_serven;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Ming
 * @date 2020/11/27 - 10:01
 * @describe 454. 四数相加 II
 */
public class LeetCode {

    /**
     * 给定四个包含整数的数组列表 A , B , C , D ,计算有多少个元组 (i, j, k, l) ，使得 A[i] + B[j] + C[k] + D[l] = 0。
     *
     * 为了使问题简单化，所有的 A, B, C, D 具有相同的长度 N，且 0 ≤ N ≤ 500 。所有整数的范围在 -228 到 228 - 1 之间，最终结果不会超过 231 - 1 。
     *
     * 输入:
     * A = [ 1, 2]
     * B = [-2,-1]
     * C = [-1, 2]
     * D = [ 0, 2]
     *
     * 输出:
     * 2
     *
     * 解释:
     * 两个元组如下:
     * 1. (0, 0, 0, 1) -> A[0] + B[0] + C[0] + D[1] = 1 + (-2) + (-1) + 2 = 0
     * 2. (1, 1, 0, 0) -> A[1] + B[1] + C[0] + D[0] = 2 + (-1) + (-1) + 0 = 0
     *
     */
    /**
     *自己最开始的思路：
     *      暴力求解，超时，A了 95%
     */
    public int fourSumCount(int[] A, int[] B, int[] C, int[] D) {
        int length = A.length;
        int aAll, bAll, cAll, dAll, result = 0;
        for (int a = 0; a<length; a++){
            aAll = A[a];
            for (int b = 0; b<length; b++){
                bAll = aAll + B[b];
                for (int c = 0; c<length; c++){
                    cAll = bAll + C[c];
                    for (int d = 0; d<length; d++){
                        if ((cAll + D[d]) == 0)  result++;
                    }
                }
            }
        }
        return result;
    }

    /**
     * 自己的做法二：
     *      分组进行 hash，以两个数和为基础进行优化
     * 执行用时：154 ms, 在所有 Java 提交中击败了5.40%的用户
     * 内存消耗：82.3 MB, 在所有 Java 提交中击败了5.04%的用户
     */
    public int fourSumCount1(int[] A, int[] B, int[] C, int[] D) {
        int length = A.length;
        AtomicInteger all = new AtomicInteger(0);
        Map<Integer,Integer> mapA = new HashMap<>(length*length), mapB = new HashMap<>(length*length);
        for (int i = 0; i < length; i++){
            for (int j = 0; j < length; j++){
                if (mapA.containsKey(A[i]+B[j])){
                    mapA.put(A[i]+B[j],mapA.get(A[i]+B[j])+1);
                }else {
                    mapA.put(A[i]+B[j],1);
                }
                if (mapB.containsKey(C[i]+D[j])){
                    mapB.put(C[i]+D[j],mapB.get(C[i]+D[j])+1);
                }else {
                    mapB.put(C[i]+D[j],1);
                }

            }
        }
        mapA.forEach((key,values) -> {
            all.addAndGet(values * mapB.getOrDefault(-key,0));
        });
        return all.get();
    }

    /**
     * 官方题解：
     *      和我第二种思路一样
     * 执行用时：80 ms, 在所有 Java 提交中击败了55.60%的用户
     * 内存消耗：56.9 MB, 在所有 Java 提交中击败了84.09%的用户
     */
    class Solution {
        public int fourSumCount(int[] A, int[] B, int[] C, int[] D) {
            Map<Integer, Integer> countAB = new HashMap<Integer, Integer>();
            for (int u : A) {
                for (int v : B) {
                    countAB.put(u + v, countAB.getOrDefault(u + v, 0) + 1);
                }
            }
            int ans = 0;
            for (int u : C) {
                for (int v : D) {
                    if (countAB.containsKey(-u - v)) {
                        ans += countAB.get(-u - v);
                    }
                }
            }
            return ans;
        }
    }

}
