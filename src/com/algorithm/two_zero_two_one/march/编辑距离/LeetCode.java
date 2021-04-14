package com.algorithm.two_zero_two_one.march.编辑距离;

/**
 * @author Ming
 * @date 2021/3/8 - 11:04
 * @describe
 */
public class LeetCode {
    /**
     * 给你两个单词 word1 和 word2，请你计算出将 word1 转换成 word2 所使用的最少操作数 。
     *
     * 你可以对一个单词进行如下三种操作：
     *
     * 插入一个字符
     * 删除一个字符
     * 替换一个字符
     *  
     *
     * 示例 1：
     *
     * 输入：word1 = "horse", word2 = "ros"
     * 输出：3
     * 解释：
     * horse -> rorse (将 'h' 替换为 'r')
     * rorse -> rose (删除 'r')
     * rose -> ros (删除 'e')
     * 示例 2：
     *
     * 输入：word1 = "intention", word2 = "execution"
     * 输出：5
     * 解释：
     * intention -> inention (删除 't')
     * inention -> enention (将 'i' 替换为 'e')
     * enention -> exention (将 'n' 替换为 'x')
     * exention -> exection (将 'n' 替换为 'c')
     * exection -> execution (插入 'u')
     *
     */
    /**
     * 思路：动态规划：字节笔试题
     * 执行用时： 6 ms , 在所有 Java 提交中击败了 86.51% 的用户
     * 内存消耗： 38.6 MB , 在所有 Java 提交中击败了 33.52% 的用户
     */
    public int minDistance(String word1, String word2) {
        int[][] index = new int[word1.length() + 1][word2.length() + 1 ];
        int len1 = word1.length(), len2 = word2.length(), left, up, left_up;
        for (int i = 0; i <= len1; i++) index[i][0] = i;
        for (int i = 0; i <= len2; i++) index[0][i] = i;
        for (int i = 0; i < len2; i++) {
           for (int j = 0; j < len1; j++) {
               left = index[j + 1][i] + 1;
               up = index[j][i + 1] + 1;
               left_up = index[j][i];
               if (word1.charAt(j) != word2.charAt(i)) left_up++;
               index[j + 1][i + 1] = Math.min(Math.min(left, up), left_up);
           }
        }
        return index[len1][len2];
    }

}
