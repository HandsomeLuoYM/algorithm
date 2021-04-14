package com.algorithm.two_zero_two_one.january.数组形式的整数加法;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Ming
 * @date 2021/1/22 - 11:55
 * @describe
 */
public class LeetCode {

    /**
     * 对于非负整数 X 而言，X 的数组形式是每位数字按从左到右的顺序形成的数组。例如，如果 X = 1231，那么其数组形式为 [1,2,3,1]。
     *
     * 给定非负整数 X 的数组形式 A，返回整数 X+K 的数组形式。
     *
     * 输入：A = [1,2,0,0], K = 34
     * 输出：[1,2,3,4]
     * 解释：1200 + 34 = 1234
     */
    /**
     * 自己的思路一： 不转换做法，也可以先转换
     * 执行用时：46 ms, 在所有 Java 提交中击败了10.74%的用户
     * 内存消耗：39.9 MB, 在所有 Java 提交中击败了49.46%的用户
     */
    public List<Integer> addToArrayForm(int[] A, int K) {
        List<Integer> list = new ArrayList<>();
        int length = A.length - 1, a, b, c = 0, d;

        while (length >= 0 || K >= 0 || c != 0) {
            a = length >= 0 ? A[length] : 0;
            b = K % 10;
            length--;
            K /= 10;
            d = (c + a + b) % 10;
            c = (c + a + b) / 10;
            list.add(0, d);
        }
        return list;
    }
}
