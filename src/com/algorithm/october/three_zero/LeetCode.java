package com.algorithm.october.three_zero;

/**
 * @author Ming
 * @date 2020/10/30 - 9:42
 * @describe 463. 岛屿的周长
 */
public class LeetCode {

    /**
     * 给定一个包含 0 和 1 的二维网格地图，其中 1 表示陆地 0 表示水域。
     *
     * 网格中的格子水平和垂直方向相连（对角线方向不相连）。
     * 整个网格被水完全包围，但其中恰好有一个岛屿（或者说，一个或多个表示陆地的格子相连组成的岛屿）。
     *
     * 岛屿中没有“湖”（“湖” 指水域在岛屿内部且不和岛屿周围的水相连）。
     * 格子是边长为 1 的正方形。网格为长方形，且宽度和高度均不超过 100 。计算这个岛屿的周长。
     *
     * 输入:
     * [[0,1,0,0],
     *  [1,1,1,0],
     *  [0,1,0,0],
     *  [1,1,0,0]]
     *
     * 输出: 16
     *
     * 解释: 它的周长是下面图片中的 16 个黄色的边：
     */

    /**
     * 自己的思路：
     *      设置两个记录数组，一个记录 x 轴的记录，一个记录 y 轴的记录，遍历所有点，当有 1 时，判断数组
     *          当为 1 ，表明改线已有，所以需要 number--
     *          当为 0 ，表明该线还未占有，需要改成 1 ，并且 number++
     * 执行用时：11 ms, 在所有 Java 提交中击败了28.15%的用户
     * 内存消耗：39.5 MB, 在所有 Java 提交中击败了70.17%的用户
     */
    public int islandPerimeter(int[][] grid) {
        int number = 0, yLength = grid.length, xLength = grid[0].length;
        int[][] ySign = new int[yLength+1][xLength];
        int[][] xSign = new int[yLength][1+xLength];
        for (int i = 0; i<yLength; i++){
            for (int j = 0; j<xLength; j++){
                if (grid[i][j] == 1){
                    if (ySign[i][j]==1) {
                        number--;
                    }else {
                        ySign[i][j]=1;
                        number++;
                    }
                    if (ySign[i+1][j]==1) {
                        number--;
                    }else {
                        ySign[i+1][j]=1;
                        number++;
                    }
                    if (xSign[i][j]==1) {
                        number--;
                    }else {
                        xSign[i][j]=1;
                        number++;
                    }
                    if (xSign[i][j+1]==1) {
                        number--;
                    }else {
                        xSign[i][j+1]=1;
                        number++;
                    }
                }
            }
        }
        return number;
    }

    static class Solution {
        static int[] dx = {0, 1, 0, -1};
        static int[] dy = {1, 0, -1, 0};

        /**
         * 官方题解一：迭代
         * 对于一个陆地格子的每条边，它被算作岛屿的周长当且仅当这条边为网格的边界或者相邻的另一个格子为水域。
         * 因此，我们可以遍历每个陆地格子，看其四个方向是否为边界或者水域，如果是，将这条边的贡献（即 11）加入答案 ans 中即可。
         */
        public int islandPerimeter(int[][] grid) {
            int n = grid.length, m = grid[0].length;
            int ans = 0;
            for (int i = 0; i < n; ++i) {
                for (int j = 0; j < m; ++j) {
                    if (grid[i][j] == 1) {
                        int cnt = 0;
                        for (int k = 0; k < 4; ++k) {
                            int tx = i + dx[k];
                            int ty = j + dy[k];
                            //关键这个判断
                            if (tx < 0 || tx >= n || ty < 0 || ty >= m || grid[tx][ty] == 0) {
                                cnt += 1;
                            }
                        }
                        ans += cnt;
                    }
                }
            }
            return ans;
        }
    }

    static class Solution1 {
        static int[] dx = {0, 1, 0, -1};
        static int[] dy = {1, 0, -1, 0};

        public int islandPerimeter(int[][] grid) {
            int n = grid.length, m = grid[0].length;
            int ans = 0;
            for (int i = 0; i < n; ++i) {
                for (int j = 0; j < m; ++j) {
                    if (grid[i][j] == 1) {
                        ans += dfs(i, j, grid, n, m);
                    }
                }
            }
            return ans;
        }

        public int dfs(int x, int y, int[][] grid, int n, int m) {
            if (x < 0 || x >= n || y < 0 || y >= m || grid[x][y] == 0) {
                return 1;
            }
            if (grid[x][y] == 2) {
                return 0;
            }
            grid[x][y] = 2;
            int res = 0;
            for (int i = 0; i < 4; ++i) {
                int tx = x + dx[i];
                int ty = y + dy[i];
                res += dfs(tx, ty, grid, n, m);
            }
            return res;
        }
    }

}
