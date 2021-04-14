package com.algorithm.two_zero_two_one.march.比特位计数;

/**
 * @author Ming
 * @date 2021/3/3 - 0:42
 * @describe
 */
public class LeetCode {
    /**
     * 338. 比特位计数
     * 给定一个非负整数 num。对于 0 ≤ i ≤ num 范围中的每个数字 i ，计算其二进制数中的 1 的数目并将它们作为数组返回。
     *
     * 示例 1:
     *
     * 输入: 2
     * 输出: [0,1,1]
     * 示例 2:
     *
     * 输入: 5
     * 输出: [0,1,1,2,1,2]
     * 进阶:
     *
     * 给出时间复杂度为O(n*sizeof(integer))的解答非常容易。但你可以在线性时间O(n)内用一趟扫描做到吗？
     * 要求算法的空间复杂度为O(n)。
     * 你能进一步完善解法吗？要求在C++或任何其他语言中不使用任何内置函数（如 C++ 中的 __builtin_popcount）来执行此操作。
     */
    /**
     * 思路：动态规划
     * 执行用时：1 ms, 在所有 Java 提交中击败了99.95%的用户
     * 内存消耗：42.4 MB, 在所有 Java 提交中击败了89.30%的用户
     */
    public int[] countBits(int num) {
        int[] rs = new int[num + 1];
        for (int i = 1; i <= num; i++) {
            rs[i] = rs[i & (i - 1)] + 1;
        }
        return rs;
    }
}
