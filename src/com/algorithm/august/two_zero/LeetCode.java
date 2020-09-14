package com.algorithm.august.two_zero;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author Ming
 * @date 2020/8/20 - 12:11
 * @describe
 */
public class LeetCode {
    /**
     * 给定一个代表游戏板的二维字符矩阵。 'M' 代表一个未挖出的地雷，
     * 'E' 代表一个未挖出的空方块，'B' 代表没有相邻（上，下，左，右，和所有4个对角线）地雷的已挖出的空白方块，
     * 数字（'1' 到 '8'）表示有多少地雷与这块已挖出的方块相邻，'X' 则表示一个已挖出的地雷。
     *
     * 现在给出在所有未挖出的方块中（'M'或者'E'）的下一个点击位置（行和列索引），根据以下规则，返回相应位置被点击后对应的面板：
     *      如果一个地雷（'M'）被挖出，游戏就结束了- 把它改为 'X'。
     *      如果一个没有相邻地雷的空方块（'E'）被挖出，修改它为（'B'），并且所有和其相邻的未挖出方块都应该被递归地揭露。
     *      如果一个至少与一个地雷相邻的空方块（'E'）被挖出，修改它为数字（'1'到'8'），表示相邻地雷的数量。
     *      如果在此次点击中，若无更多方块可被揭露，则返回面板。
     *
     */

    /**
     * 自己的解法
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
     * 内存消耗：40.2 MB, 在所有 Java 提交中击败了39.51%的用户
     */
    int[] dirX = {0, 1, 0, -1, 1, 1, -1, -1};
    int[] dirY = {1, 0, -1, 0, 1, -1, 1, -1};

    public char[][] updateBoard(char[][] board, int[] click) {
        //规则一
        if (board[click[0]][click[1]]=='M'){
            board[click[0]][click[1]]='X';
            return board;
        }else {
            change(board,click[0],click[1]);
            return board;
        }
    }

    private void change(char[][] board,int x, int y){
        if (board[x][y]!='E') return;
        int length = dirX.length;
        char now = '0';
        for (int i=0;i<length;i++){
            int x1 = x + dirX[i];
            int y1 = y + dirY[i];
            if (x1>=0 && x1<board.length && y1>=0 && y1<board[0].length){
                if (board[x1][y1]=='M') now++;
            }
        }
        if (now=='0'){
            board[x][y]='B';
            for (int i=0;i<length;i++){
                int x1 = x + dirX[i];
                int y1 = y + dirY[i];
                if (x1>=0 && x1<board.length && y1>=0 && y1<board[0].length && board[x1][y1] == 'E') {
                    change(board,x1,y1);
                }
            }
        }else {
            board[x][y] = now;
        }
    }

    ////////////////////////////////////////////  官方题解 1  ///////////////////////////

    /**
     * 由于题目要求你根据规则来展示执行一次点击操作后游戏面板的变化，所以我们只要明确该扫雷游戏的规则，并用代码模拟出来即可。
     *
     * 那我们着眼于题目的规则，会发现总共分两种情况：
     *
     *      当前点击的是「未挖出的地雷」，我们将其值改为 X 即可。
     *
     *      当前点击的是「未挖出的空方块」，我们需要统计它周围相邻的方块里地雷的数量 cnt
     *          （即 M 的数量）。如果 cnt 为零，即执行规则 22，此时需要将其改为 B，
     *          且递归地处理周围的八个未挖出的方块，递归终止条件即为规则 4，没有更多方块可被揭露的时候。否则执行规则 3，将其修改为数字即可。
     *
     */
    class Solution {
        int[] dirX = {0, 1, 0, -1, 1, 1, -1, -1};
        int[] dirY = {1, 0, -1, 0, 1, -1, 1, -1};

        public char[][] updateBoard(char[][] board, int[] click) {
            int x = click[0], y = click[1];
            if (board[x][y] == 'M') {
                // 规则 1
                board[x][y] = 'X';
            } else{
                dfs(board, x, y);
            }
            return board;
        }

        public void dfs(char[][] board, int x, int y) {
            int cnt = 0;
            for (int i = 0; i < 8; ++i) {
                int tx = x + dirX[i];
                int ty = y + dirY[i];
                if (tx < 0 || tx >= board.length || ty < 0 || ty >= board[0].length) {
                    continue;
                }
                // 不用判断 M，因为如果有 M 的话游戏已经结束了
                if (board[tx][ty] == 'M') {
                    ++cnt;
                }
            }
            if (cnt > 0) {
                // 规则 3
                board[x][y] = (char) (cnt + '0');
            } else {
                // 规则 2
                board[x][y] = 'B';
                for (int i = 0; i < 8; ++i) {
                    int tx = x + dirX[i];
                    int ty = y + dirY[i];
                    // 这里不需要在存在 B 的时候继续扩展，因为 B 之前被点击的时候已经被扩展过了
                    if (tx < 0 || tx >= board.length || ty < 0 || ty >= board[0].length || board[tx][ty] != 'E') {
                        continue;
                    }
                    dfs(board, tx, ty);
                }
            }
        }
    }

    //////////////////////////////////////  官方题解 2  //////////////////////////////

    /**
     * 同样地，我们也可以将深度优先搜索改为广度优先搜索来模拟，
     * 我们只要在\textit{cnt}cnt 为零的时候，将当前点相邻的未挖出的方块加入广度优先搜索的队列里即可，
     * 其他情况不加入队列，这里不再赘述。
     *
     */
    class Solution1 {
        int[] dirX = {0, 1, 0, -1, 1, 1, -1, -1};
        int[] dirY = {1, 0, -1, 0, 1, -1, 1, -1};

        public char[][] updateBoard(char[][] board, int[] click) {
            int x = click[0], y = click[1];
            if (board[x][y] == 'M') {
                // 规则 1
                board[x][y] = 'X';
            } else{
                bfs(board, x, y);
            }
            return board;
        }

        public void bfs(char[][] board, int sx, int sy) {
            Queue<int[]> queue = new LinkedList<int[]>();
            boolean[][] vis = new boolean[board.length][board[0].length];
            queue.offer(new int[]{sx, sy});
            vis[sx][sy] = true;
            while (!queue.isEmpty()) {
                int[] pos = queue.poll();
                int cnt = 0, x = pos[0], y = pos[1];
                for (int i = 0; i < 8; ++i) {
                    int tx = x + dirX[i];
                    int ty = y + dirY[i];
                    if (tx < 0 || tx >= board.length || ty < 0 || ty >= board[0].length) {
                        continue;
                    }
                    // 不用判断 M，因为如果有 M 的话游戏已经结束了
                    if (board[tx][ty] == 'M') {
                        ++cnt;
                    }
                }
                if (cnt > 0) {
                    // 规则 3
                    board[x][y] = (char) (cnt + '0');
                } else {
                    // 规则 2
                    board[x][y] = 'B';
                    for (int i = 0; i < 8; ++i) {
                        int tx = x + dirX[i];
                        int ty = y + dirY[i];
                        // 这里不需要在存在 B 的时候继续扩展，因为 B 之前被点击的时候已经被扩展过了
                        if (tx < 0 || tx >= board.length || ty < 0 || ty >= board[0].length || board[tx][ty] != 'E' || vis[tx][ty]) {
                            continue;
                        }
                        queue.offer(new int[]{tx, ty});
                        vis[tx][ty] = true;
                    }
                }
            }
        }
    }



}
