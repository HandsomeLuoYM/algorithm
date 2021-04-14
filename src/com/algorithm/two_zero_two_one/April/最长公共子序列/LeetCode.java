package com.algorithm.two_zero_two_one.April.最长公共子序列;

/**
 * @author Ming
 * @date 2021/4/3 - 8:19
 * @describe
 */
public class LeetCode {
    /**
     * 给定两个字符串 text1 和 text2，返回这两个字符串的最长 公共子序列 的长度。如果不存在 公共子序列 ，返回 0 。
     *
     * 一个字符串的 子序列 是指这样一个新的字符串：它是由原字符串在不改变字符的相对顺序的情况下删除某些字符（也可以不删除任何字符）后组成的新字符串。
     *
     * 例如，"ace" 是 "abcde" 的子序列，但 "aec" 不是 "abcde" 的子序列。
     * 两个字符串的 公共子序列 是这两个字符串所共同拥有的子序列。
     *
     *  
     *
     * 示例 1：
     *
     * 输入：text1 = "abcde", text2 = "ace"
     * 输出：3
     * 解释：最长公共子序列是 "ace" ，它的长度为 3 。
     * 示例 2：
     *
     * 输入：text1 = "abc", text2 = "abc"
     * 输出：3
     * 解释：最长公共子序列是 "abc" ，它的长度为 3 。
     * 示例 3：
     *
     * 输入：text1 = "abc", text2 = "def"
     * 输出：0
     * 解释：两个字符串没有公共子序列，返回 0 。
     *
     */
    /**
     * 思路：动态规划
     * 执行用时：9 ms, 在所有 Java 提交中击败了86.20%的用户
     * 内存消耗：42.4 MB, 在所有 Java 提交中击败了28.47%的用户
     */
    public int longestCommonSubsequence(String text1, String text2) {
        int n = text1.length(), m = text2.length();
        int[][] numbers = new int[n + 1][m + 1];
        char ch1, ch2;
        for (int i = 1; i <= n; i++) {
            ch1 = text1.charAt(i - 1);
            for (int j = 1; j <= m; j++) {
                ch2 = text2.charAt(j - 1);
                if (ch1 == ch2) {
                    numbers[i][j] = numbers[i- 1][j - 1] + 1;
                }else {
                    numbers[i][j] = Math.max(numbers[i- 1][j], numbers[i][j - 1]);
                }
            }
        }
        return numbers[n][m];
    }
}
