package com.algorithm.two_zero_two_zero.october.one_serven;

/**
 * @author Ming
 * @date 2020/10/17 - 1:46
 * @describe
 */
public class LeetCode {

    /**
     * n 皇后问题研究的是如何将 n 个皇后放置在 n×n 的棋盘上，并且使皇后彼此之间不能相互攻击。
     * 给定一个整数 n，返回 n 皇后不同的解决方案的数量。
     * 输入: 4
     * 输出: 2
     * 解释: 4 皇后问题存在如下两个不同的解法。
     * [
     *  [".Q..",  // 解法 1
     *   "...Q",
     *   "Q...",
     *   "..Q."],
     *
     *  ["..Q.",  // 解法 2
     *   "Q...",
     *   "...Q",
     *   ".Q.."]
     * ]
     *
     */

    /**
     * 自己的解法：之前的自己的思路修改的
     * 执行用时：2 ms, 在所有 Java 提交中击败了56.54%的用户
     * 内存消耗：34.7 MB, 在所有 Java 提交中击败了100.00%的用户
     */
    int all = 0;
    public int totalNQueens(int n) {
        dfs(n,new int[n][n],n,0);
        return all;
    }

    /**
     * 深度搜索查找符合的情况
     * @param r 当前剩下的皇后数量
     * @param ints 走过的路径  0表示没用走过，< 0 表示皇后的路径可以到达的地方， =1 表示皇后的位置
     * @param n n的长度
     * @param number 表示到达的y值层数
     */
    public void dfs(int r ,int[][] ints,int n,int number){
        if (r==0){
            all++;
            return;
        }
        for (int j=0;j<n;j++){
            if (ints[number][j]==0){
                //修改值，-1代表走过
                change(j,number,n,ints,-1);
                //调整参数，标记这个点
                ints[number][j]=1;

                //进一步判定访问
                dfs(r-1,ints,n,number+1);

                //回溯表示这个点已经没了
                ints[number][j]=0;
                //回调修改值
                change(j,number,n,ints,1);

            }
        }
    }

    /**
     * x,y 这个点如果有皇后，把它能涉及到的所有点的值改  +result
     * @param x 坐标x
     * @param y 坐标y
     * @param n n个点
     * @param ints 路径数组
     * @param result 修改值
     */
    public void change(int x,int y,int n,int[][] ints,int result){
        for (int i=0;i<n;i++){
            if (i!=x){
                ints[y][i]+=result;
            }
            if (i!=y){
                ints[i][x]+=result;
            }
            if (y-1-i>=0&&x-1-i>=0){
                ints[y-1-i][x-1-i]+=result;
            }
            if (y-1-i>=0&&x+1+i<n){
                ints[y-1-i][x+1+i]+=result;
            }
            if (y+1+i<n&&x+1+i<n){
                ints[y+1+i][x+1+i]+=result;
            }
            if (y+1+i<n&&x-1-i>=0){
                ints[y+1+i][x-1-i]+=result;
            }
        }
    }

    class Solution {
        public int totalNQueens(int n) {
            return solve(n, 0, 0, 0, 0);
        }

        public int solve(int n, int row, int columns, int diagonals1, int diagonals2) {
            if (row == n) {
                return 1;
            } else {
                int count = 0;
                int availablePositions = ((1 << n) - 1) & (~(columns | diagonals1 | diagonals2));
                while (availablePositions != 0) {
                    int position = availablePositions & (-availablePositions);
                    availablePositions = availablePositions & (availablePositions - 1);
                    count += solve(n, row + 1, columns | position, (diagonals1 | position) << 1, (diagonals2 | position) >> 1);
                }
                return count;
            }
        }
    }

}
