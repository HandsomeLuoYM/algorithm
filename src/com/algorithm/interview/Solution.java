package com.algorithm.interview;

/**
 * @author honzooban
 * @version 1.0.0
 * @ClassName Solution.java
 * @Description
 * @createTime 2020年09月01日 19:18:00
 */
public class Solution {

    int[] dx = {0, 0, -1, 1}, dy = {1, -1, 0, 0};

    public int getMostSoldierNumber(int n, int m, int[][] soldiers) {
        int ans = 0;
        if (n == 0 || m == 0) {
            return 0;
        }
        // 遍历每个空位，搜索最大队伍数
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (soldiers[i][j] == 0) {
                    int[][] sign = new int[soldiers.length][soldiers[0].length];
                    int num = 0;
                    soldiers[i][j] = 1;
                    int dfs = dfs(i, j, n, m, soldiers, num, sign);
                    ans = Math.max(dfs, ans);
                    soldiers[i][j] = 0;
                }
            }
        }
        return ans;
    }

    public int dfs(int i, int j, int n, int m, int[][] soldiers, int num,int[][] sign) {
        if (i < 0 || i >= n || j < 0 || j >= m || soldiers[i][j] == 0||sign[i][j]==1) {
            return num;
        }
        int max = num;
        num++;
        sign[i][j]=1;
        for (int k = 0; k < 4; k++) {
            int x = i + dx[k], y = j + dy[k];
            int dfs = dfs(x, y, n, m, soldiers, num, sign);
            if (dfs>max) max = dfs;
        }
        return max;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[][] soldiers = {{1, 0, 1, 1}, {1, 1, 0, 1}, {0, 0, 0, 0}, {1, 1, 1, 1}};
        int ans = solution.getMostSoldierNumber(4, 4, soldiers);
        System.out.println(ans);
    }
}
