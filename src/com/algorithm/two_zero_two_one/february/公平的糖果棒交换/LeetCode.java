package com.algorithm.two_zero_two_one.february.公平的糖果棒交换;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author Ming
 * @date 2021/2/1 - 17:57
 * @describe
 */
public class LeetCode {

    /**
     * 爱丽丝和鲍勃有不同大小的糖果棒：A[i] 是爱丽丝拥有的第 i 根糖果棒的大小，B[j] 是鲍勃拥有的第 j 根糖果棒的大小。
     *
     * 因为他们是朋友，所以他们想交换一根糖果棒，这样交换后，他们都有相同的糖果总量。（一个人拥有的糖果总量是他们拥有的糖果棒大小的总和。）
     *
     * 返回一个整数数组 ans，其中 ans[0] 是爱丽丝必须交换的糖果棒的大小，ans[1] 是 Bob 必须交换的糖果棒的大小。
     *
     * 如果有多个答案，你可以返回其中任何一个。保证答案存在
     *
     * 输入：A = [1,1], B = [2,2]
     * 输出：[1,2]
     *
     * 输入：A = [1,2], B = [2,3]
     * 输出：[1,2]
     */
    /**
     * 思路：hash 表，利用 hash 来存储节点。
     * 执行用时：12 ms, 在所有 Java 提交中击败了55.86%的用户
     * 内存消耗：39.8 MB, 在所有 Java 提交中击败了85.84%的用户
     */
    public int[] fairCandySwap(int[] A, int[] B) {
        int aAll = 0, bAll = 0, aLength = A.length, bLength = B.length;
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < aLength; i++) {
            aAll += A[i];
            set.add(A[i]);
        }
        for (int i = 0; i < bLength; i++) bAll += B[i];
        int sub = (aAll - bAll) / 2;
        for (int i = 0; i < bLength; i++) {
            if (set.contains(B[i] + sub)) return new int[]{B[i] + sub, B[i]};
        }
        return null;
    }
}
