package com.algorithm.two_zero_two_one.April.笨阶乘;

/**
 * @author Ming
 * @date 2021/4/1 - 9:43
 * @describe
 */
public class LeetCode {
    /**
     * 通常，正整数 n 的阶乘是所有小于或等于 n 的正整数的乘积。例如，factorial(10) = 10 * 9 * 8 * 7 * 6 * 5 * 4 * 3 * 2 * 1。
     *
     * 相反，我们设计了一个笨阶乘 clumsy：在整数的递减序列中，我们以一个固定顺序的操作符序列来依次替换原有的乘法操作符：乘法(*)，除法(/)，加法(+)和减法(-)。
     *
     * 例如，clumsy(10) = 10 * 9 / 8 + 7 - 6 * 5 / 4 + 3 - 2 * 1。然而，这些运算仍然使用通常的算术运算顺序：我们在任何加、减步骤之前执行所有的乘法和除法步骤，并且按从左到右处理乘法和除法步骤。
     *
     * 另外，我们使用的除法是地板除法（floor division），所以 10 * 9 / 8 等于 11。这保证结果是一个整数。
     *
     * 实现上面定义的笨函数：给定一个整数 N，它返回 N 的笨阶乘。
     * 示例 1：
     *
     * 输入：4
     * 输出：7
     * 解释：7 = 4 * 3 / 2 + 1
     * 示例 2：
     *
     * 输入：10
     * 输出：12
     * 解释：12 = 10 * 9 / 8 + 7 - 6 * 5 / 4 + 3 - 2 * 1
     *
     */
    /**
     * 思路：暴力
     * 执行用时：1 ms, 在所有 Java 提交中击败了81.94%的用户
     * 内存消耗：35.2 MB, 在所有 Java 提交中击败了72.90%的用户
     */
    public int clumsy(int N) {
        if (N == 3) return 6;
        if (N == 2) return 2;
        if (N == 1) return 1;
        int rs = 2 * (N * (N - 1) / (N - 2));
        while (N > 0) {
            if (N >= 4) {
                rs = rs - N * (N - 1) / (N - 2) + (N - 3);
            }else if (N == 3) {
                rs -= 6;
            }else if (N == 2) {
                rs -= 2;
            }else if (N == 1){
                rs -= 1;
            }else {
                rs += 0;
            }
            N -= 4;
        }
        return rs;
    }

    public static void main(String[] args) {
        LeetCode l = new LeetCode();
        System.out.println(l.clumsy(7));
    }
}
