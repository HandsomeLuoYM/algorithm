package com.algorithm.december.不同路径;

/**
 * @author Ming
 * @date 2020/12/9 - 0:16
 * @describe
 */
public class LeetCode {
    /**
     * 我的做法（超时）：暴力解法
     */
    int all = 0;

    public int uniquePaths(int m, int n) {
        if (m == 1 && n == 1) {
            all++;
            return all;
        }
        if (m > 0) uniquePaths(m - 1, n);
        if (n > 0) uniquePaths(m, n - 1);
        return all;
    }

    /**
     * 自己的思路：
     *      动态规划（带记忆的暴力），分为下和右，所以每次只需要去分两种情况即可，加上左和下他们的值就是当前位置的路径
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
     * 内存消耗：35.5 MB, 在所有 Java 提交中击败了43.27%的用户
     */
    public int uniquePaths1(int m, int n) {
        int[][] mn = new int[n][m];
        for (int i = 0; i < m; i++) mn[n - 1][i] = 1;
        for (int i = 0; i < n; i++) mn[i][m - 1] = 1;
        for (int i = n - 2; i >= 0; i--) {
            for (int j = m - 2; j >= 0; j--) {
                mn[i][j] = mn[i][j + 1] + mn[i + 1][j];
            }
        }
        return mn[0][0];
    }

    /**
     * 组合数学
     */
    class Solution {
        public int uniquePaths(int m, int n) {
            long ans = 1;
            for (int x = n, y = 1; y < m; ++x, ++y) {
                ans = ans * x / y;
            }
            return (int) ans;
        }
    }

}
