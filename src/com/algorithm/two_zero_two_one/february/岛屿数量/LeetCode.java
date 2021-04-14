package com.algorithm.two_zero_two_one.february.岛屿数量;

import sun.security.util.Length;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author Ming
 * @date 2021/2/4 - 0:41
 * @describe
 */
public class LeetCode {

    /**
     *
     * 给你一个由 '1'（陆地）和 '0'（水）组成的的二维网格，请你计算网格中岛屿的数量。
     *
     * 岛屿总是被水包围，并且每座岛屿只能由水平方向和/或竖直方向上相邻的陆地连接形成。
     *
     * 此外，你可以假设该网格的四条边均被水包围。
     * 输入：grid = [
     *   ["1","1","1","1","0"],
     *   ["1","1","0","1","0"],
     *   ["1","1","0","0","0"],
     *   ["0","0","0","0","0"]
     * ]
     * 输出：1
     *
     * 输入：grid = [
     *   ["1","1","0","0","0"],
     *   ["1","1","0","0","0"],
     *   ["0","0","1","0","0"],
     *   ["0","0","0","1","1"]
     * ]
     * 输出：3
     */
    /**
     * 自己的思路：
     *      深搜递归，同时存放走过的路径，遍历一次就行，遍历时累加数量
     * 执行用时：2 ms, 在所有 Java 提交中击败了90.52%的用户
     * 内存消耗：41 MB, 在所有 Java 提交中击败了29.50%的用户
     */
    private int[] xAdd = new int[]{0, 1, 0, -1};
    private int[] yAdd = new int[]{1, 0, -1, 0};
    int xLength, yLength;
    public int numIslands(char[][] grid) {
        int rs = 0;
        xLength = grid[0].length;
        yLength = grid.length;
        int[][] path = new int[yLength][xLength];
        for (int i = 0; i < yLength; i++) {
            for (int j = 0; j < xLength; j++) {
                if (path[i][j] == 0) {
                    if (grid[i][j] == '1') {
                        rs++;
                        dfs(path, grid, j, i);
                    }
                }
            }
        }
        return rs;
    }

    private void dfs(int[][] path, char[][] grid, int x, int y) {
        if (x >= xLength || y >= yLength || x < 0 || y < 0) return;
        if (path[y][x] == 0) {
            path[y][x] = 1;
            if (grid[y][x] == '1') {
                for (int i = 0; i < 4; i++) {
                    dfs(path, grid, x + xAdd[i], y + yAdd[i]);
                }
            }
        }
    }

    /**
     * 广度优先算法，迭代
     */
    class Solution {
        public int numIslands(char[][] grid) {
            if (grid == null || grid.length == 0) {
                return 0;
            }

            int nr = grid.length;
            int nc = grid[0].length;
            int num_islands = 0;

            for (int r = 0; r < nr; ++r) {
                for (int c = 0; c < nc; ++c) {
                    if (grid[r][c] == '1') {
                        ++num_islands;
                        grid[r][c] = '0';
                        Queue<Integer> neighbors = new LinkedList<>();
                        //记录位置
                        neighbors.add(r * nc + c);
                        while (!neighbors.isEmpty()) {
                            //获取所需要遍历的位置
                            int id = neighbors.remove();
                            //行号
                            int row = id / nc;
                            //列号
                            int col = id % nc;
                            //遍历四个方向
                            if (row - 1 >= 0 && grid[row-1][col] == '1') {
                                neighbors.add((row-1) * nc + col);
                                grid[row-1][col] = '0';
                            }
                            if (row + 1 < nr && grid[row+1][col] == '1') {
                                neighbors.add((row+1) * nc + col);
                                grid[row+1][col] = '0';
                            }
                            if (col - 1 >= 0 && grid[row][col-1] == '1') {
                                neighbors.add(row * nc + col-1);
                                grid[row][col-1] = '0';
                            }
                            if (col + 1 < nc && grid[row][col+1] == '1') {
                                neighbors.add(row * nc + col+1);
                                grid[row][col+1] = '0';
                            }
                        }
                    }
                }
            }

            return num_islands;
        }
    }

}
