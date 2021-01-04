package com.algorithm.two_zero_two_zero.august.one_one;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author Ming
 * @date 2020/10/13 - 19:12
 * @describe
 */
public class LeetCode {

    /**
     * 给定一个二维的矩阵，包含 'X' 和 'O'（字母 O）。
     *
     * 找到所有被 'X' 围绕的区域，并将这些区域里所有的 'O' 用 'X' 填充。
     *
     * X X X X
     * X O O X
     * X X O X
     * X O X X
     *
     * X X X X
     * X X X X
     * X X X X
     * X O X X
     */
    /**
     * 自己的解法：太慢了
     */
    int[] x = {0,-1,0,1};
    int[] y = {-1,0,1,0};
    char[][] path;
    public void solve(char[][] board) {
        if (board.length==0) return;
        int xLength = board[0].length,yLength = board.length;
        path = new char[yLength][xLength];
        for (int i = 0; i < yLength; i++){
            for (int j = 0; j < xLength; j++){
                if (path[i][j]=='1') continue;
                if (i==0||j==0||j==xLength-1||i==yLength-1) {
                    path[i][j] = '1';
                    continue;
                }
                //初步判断该点可能需要改
                if (board[i][j] == 'O'){
                    //进一步判断
                    if (judge(board,i,j)){
                        change(board, i, j, 'O', 'X');
                    }
                    change(path, i, j, '2', '1');
                }
            }
        }
    }

    private void change(char[][] board, int yIndex, int xIndex, char oldCh, char newCh) {
        if (yIndex==0||yIndex==board.length-1||xIndex==0||xIndex==board[0].length-1) return;
        if (board[yIndex][xIndex]==oldCh) {
            board[yIndex][xIndex] = newCh;
            change(board, yIndex+y[0], xIndex+x[0], oldCh, newCh);
            change(board, yIndex+y[1], xIndex+x[1], oldCh, newCh);
            change(board, yIndex+y[2], xIndex+x[2], oldCh, newCh);
            change(board, yIndex+y[3], xIndex+x[3], oldCh, newCh);
        }
    }

    boolean judge(char[][] board, int yIndex, int xIndex){
        //到达边界情况
        if ((yIndex==0||yIndex==board.length-1 || xIndex==0||xIndex==board[0].length-1)&&board[yIndex][xIndex]=='O') {
            return false;
        }
        //访问过了
        if (path[yIndex][xIndex]=='1'&&board[yIndex][xIndex]=='O') {
            return false;
        } else if (path[yIndex][xIndex]=='1') return true;
        //暂时访问过的‘O’点
        if (path[yIndex][xIndex] == '2') return true;
        //为X
        if (board[yIndex][xIndex]=='X') {
            path[yIndex][xIndex] = '1';
            return true;
        }else {
            path[yIndex][xIndex] = '2';
            if(judge(board, yIndex + y[0], xIndex + x[0])
                    && judge(board, yIndex + y[1], xIndex + x[1])
                    && judge(board, yIndex + y[2], xIndex + x[2])
                    && judge(board, yIndex + y[3], xIndex + x[3])){
                return true;
            }else {
                return false;
            }
        }
    }

    class Solution {
        int n, m;

        public void solve(char[][] board) {
            n = board.length;
            if (n == 0) {
                return;
            }
            m = board[0].length;
            //与边界相连的地方的‘O’都变成A
            for (int i = 0; i < n; i++) {
                dfs(board, i, 0);
                dfs(board, i, m - 1);
            }
            for (int i = 1; i < m - 1; i++) {
                dfs(board, 0, i);
                dfs(board, n - 1, i);
            }
            //其他地方的‘O’都变成‘X’
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (board[i][j] == 'A') {
                        board[i][j] = 'O';
                    } else if (board[i][j] == 'O') {
                        board[i][j] = 'X';
                    }
                }
            }
        }

        public void dfs(char[][] board, int x, int y) {
            if (x < 0 || x >= n || y < 0 || y >= m || board[x][y] != 'O') {
                return;
            }
            board[x][y] = 'A';
            dfs(board, x + 1, y);
            dfs(board, x - 1, y);
            dfs(board, x, y + 1);
            dfs(board, x, y - 1);
        }
    }

    class Solution1 {
        int[] dx = {1, -1, 0, 0};
        int[] dy = {0, 0, 1, -1};

        public void solve(char[][] board) {
            int n = board.length;
            if (n == 0) {
                return;
            }
            int m = board[0].length;
            Queue<int[]> queue = new LinkedList<int[]>();
            for (int i = 0; i < n; i++) {
                if (board[i][0] == 'O') {
                    queue.offer(new int[]{i, 0});
                }
                if (board[i][m - 1] == 'O') {
                    queue.offer(new int[]{i, m - 1});
                }
            }
            for (int i = 1; i < m - 1; i++) {
                if (board[0][i] == 'O') {
                    queue.offer(new int[]{0, i});
                }
                if (board[n - 1][i] == 'O') {
                    queue.offer(new int[]{n - 1, i});
                }
            }
            while (!queue.isEmpty()) {
                int[] cell = queue.poll();
                int x = cell[0], y = cell[1];
                board[x][y] = 'A';
                for (int i = 0; i < 4; i++) {
                    int mx = x + dx[i], my = y + dy[i];
                    if (mx < 0 || my < 0 || mx >= n || my >= m || board[mx][my] != 'O') {
                        continue;
                    }
                    queue.offer(new int[]{mx, my});
                }
            }
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (board[i][j] == 'A') {
                        board[i][j] = 'O';
                    } else if (board[i][j] == 'O') {
                        board[i][j] = 'X';
                    }
                }
            }
        }
    }


}
