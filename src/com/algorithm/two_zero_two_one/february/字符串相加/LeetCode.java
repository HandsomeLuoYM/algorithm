package com.algorithm.two_zero_two_one.february.字符串相加;

import java.math.BigInteger;

/**
 * @author Ming
 * @date 2021/2/2 - 10:50
 * @describe
 */
public class LeetCode {
    /**
     * 给定两个字符串形式的非负整数 num1 和num2 ，计算它们的和。
     *
     * 提示：
     *
     * num1 和num2 的长度都小于 5100
     * num1 和num2 都只包含数字 0-9
     * num1 和num2 都不包含任何前导零
     * 你不能使用任何內建 BigInteger 库， 也不能直接将输入的字符串转换为整数形式
     *
     */
    public String addStrings(String num1, String num2) {
        BigInteger a = new BigInteger(num1), b = new BigInteger(num2);
        return a.add(b).toString();
    }


}
