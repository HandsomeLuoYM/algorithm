package com.algorithm.two_zero_two_one.february.杨辉三角II;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Ming
 * @date 2021/2/12 - 1:14
 * @describe
 */
public class LeetCode {
    /**
     * 给定一个非负索引 k，其中 k ≤ 33，返回杨辉三角的第 k 行。
     *
     * 在杨辉三角中，每个数是它左上方和右上方的数的和。
     *
     * 示例:
     *
     * 输入: 3
     * 输出: [1,3,3,1]
     * 进阶：
     *
     * 你可以优化你的算法到 O(k) 空间复杂度吗？
     */
    /**
     * 思路：数组，记录前面的数字，使空间复杂度降低
     * 执行用时：1 ms, 在所有 Java 提交中击败了83.95%的用户
     * 内存消耗：36.1 MB, 在所有 Java 提交中击败了75.22%的用户
     */
    public List<Integer> getRow(int rowIndex) {
        int[] num = new int[rowIndex + 1];
        int temp = 1, before;
        for (int i = 0; i <= rowIndex; i++) {
            num[i] = 1;
            for (int j = 1; j < i; j++) {
                before = temp;
                temp = num[j];
                num[j] += before;
            }
        }
        return Arrays.stream(num).boxed().collect(Collectors.toList());
    }
}
