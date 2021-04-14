package com.algorithm.two_zero_two_one.january.可被5整除的二进制前缀;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Ming
 * @date 2021/1/14 - 12:26
 * @describe
 */
public class LeetCode {
    /**
     * 给定由若干 0 和 1 组成的数组 A。我们定义 N_i：从 A[0] 到 A[i] 的第 i 个子数组被解释为一个二进制数（从最高有效位到最低有效位）。
     *
     * 返回布尔值列表 answer，只有当 N_i 可以被 5 整除时，
     * 答案 answer[i] 为 true，否则为 false。
     *
     * 输入：[0,1,1]
     * 输出：[true,false,false]
     * 解释：
     * 输入数字为 0, 01, 011；也就是十进制中的 0, 1, 3 。只有第一个数可以被 5 整除，因此 answer[0] 为真。
     *
     * 输入：[0,1,1,1,1,1]
     * 输出：[true,false,false,false,true,false]
     */
    /**
     * 自己的做法：模拟
     * 执行用时：361 ms, 在所有 Java 提交中击败了5.28%的用户
     * 内存消耗：40.5 MB, 在所有 Java 提交中击败了5.11%的用户
     */
    public List<Boolean> prefixesDivBy5(int[] A) {
        List<Boolean> rs = new ArrayList<>();
        if (A == null) return rs;
        BigInteger now = new BigInteger("0"), five = new BigInteger("5"), zero = new BigInteger("0");
        int length = A.length;
        for (int i = 0; i < length; i++){
            now = now.add(now).add(new BigInteger(String.valueOf(A[i])));
            if (now.mod(five).compareTo(zero) == 0) {
                rs.add(true);
                now.multiply(zero);
            }else {
                rs.add(false);
            }
        }
        return rs;
    }

    /**
     * 官方题解
     * 执行用时：5 ms, 在所有 Java 提交中击败了36.59%的用户
     * 内存消耗：39.3 MB, 在所有 Java 提交中击败了22.20%的用户
     */
    class Solution {
        public List<Boolean> prefixesDivBy5(int[] A) {
            List<Boolean> list = new ArrayList<Boolean>();
            int prefix = 0;
            int length = A.length;
            for (int i = 0; i < length; i++) {
                prefix = ((prefix << 1) + A[i]) % 5;
                list.add(prefix == 0);
            }
            return list;
        }
    }

}
