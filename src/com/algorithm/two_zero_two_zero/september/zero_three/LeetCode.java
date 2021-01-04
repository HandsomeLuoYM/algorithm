package com.algorithm.two_zero_two_zero.september.zero_three;

import java.util.*;

/**
 * @author Ming
 * @date 2020/9/3 - 10:15
 * @describe  51 n皇后
 */
public class LeetCode {

    /**
     * n 皇后问题研究的是如何将 n 个皇后放置在 n×n 的棋盘上，并且使皇后彼此之间不能相互攻击。
     * 给定一个整数 n，返回所有不同的 n 皇后问题的解决方案。
     *
     * 每一种解法包含一个明确的 n 皇后问题的棋子放置方案，该方案中 'Q' 和 '.' 分别代表了皇后和空位。
     *
     * 输入：4
     * 输出：[
     *  [".Q..",  // 解法 1
     *   "...Q",
     *   "Q...",
     *   "..Q."],
     *
     *  ["..Q.",  // 解法 2
     *   "Q...",
     *   "...Q",
     *   ".Q.."]
     * ]
     * 解释: 4 皇后问题存在两个不同的解法。
     *
     */

    /**
     * 自己的解法。
     *
     * 规律：每一行都会有最多一个棋子
     * 定义一个棋盘，等于0表示可以走，在判断该位置为0时可以选择放置棋子，放置后修改棋盘，将其八个方向都修改（-1（也可能-2）代表不能走）
     * 在这个位置确定后，递归调用进入下一行进行判断，以此类推，当剩余皇后为0时即已经找到该排序
     *
     * 执行用时：4 ms, 在所有 Java 提交中击败了63.52%的用户
     * 内存消耗：40.2 MB, 在所有 Java 提交中击败了34.07%的用户
     */
    List<List<String>> all = new ArrayList<>();

    public List<List<String>> solveNQueens(int n) {
        dfs(n,new int[n][n],n,0);
        return all;
    }

    public void dfs(int r ,int[][] ints,int n,int number){
        if (r==0){
            List<String> ele = new ArrayList<>();
            for (int i=0;i<n;i++){
                String str = "";
                for (int j=0;j<n;j++){
                    if (ints[i][j]==1){
                        str+="Q";
                    }else {
                        str+=".";
                    }
                }
                ele.add(str);
            }
            all.add(ele);
            return;
        }
        for (int j=0;j<n;j++){
            if (ints[number][j]==0){
                change(j,number,n,ints,-1);
                ints[number][j]+=1;

                dfs(r-1,ints,n,number+1);

                ints[number][j]-=1;
                change(j,number,n,ints,1);

            }
        }
    }

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
//////////////////////////////////////////  官方题解一  ////////////////////////////////////////////

    /**
     * 为了判断一个位置所在的列和两条斜线上是否已经有皇后，使用三个集合 \textit{columns}columns、\textit{diagonals}_1diagonals
     * 1
     * ​
     *   和 \textit{diagonals}_2diagonals
     * 2
     * ​
     *   分别记录每一列以及两个方向的每条斜线上是否有皇后。
     *
     * 列的表示法很直观，一共有 NN 列，每一列的下标范围从 00 到 N-1N−1，使用列的下标即可明确表示每一列。
     *
     * 如何表示两个方向的斜线呢？对于每个方向的斜线，需要找到斜线上的每个位置的行下标与列下标之间的关系。
     *
     * 方向一的斜线为从左上到右下方向，同一条斜线上的每个位置满足行下标与列下标之差相等，
     * 例如 (0,0)(0,0) 和 (3,3)(3,3) 在同一条方向一的斜线上。
     * 因此使用行下标与列下标之差即可明确表示每一条方向一的斜线。
     *
     */
    class Solution {
    public List<List<String>> solveNQueens(int n) {
        List<List<String>> solutions = new ArrayList<List<String>>();
        int[] queens = new int[n];
        Arrays.fill(queens, -1);
        Set<Integer> columns = new HashSet<Integer>();
        Set<Integer> diagonals1 = new HashSet<Integer>();
        Set<Integer> diagonals2 = new HashSet<Integer>();
        backtrack(solutions, queens, n, 0, columns, diagonals1, diagonals2);
        return solutions;
    }

    public void backtrack(List<List<String>> solutions, int[] queens, int n, int row, Set<Integer> columns, Set<Integer> diagonals1, Set<Integer> diagonals2) {
        if (row == n) {
            List<String> board = generateBoard(queens, n);
            solutions.add(board);
        } else {
            for (int i = 0; i < n; i++) {
                if (columns.contains(i)) {
                    continue;
                }
                int diagonal1 = row - i;
                if (diagonals1.contains(diagonal1)) {
                    continue;
                }
                int diagonal2 = row + i;
                if (diagonals2.contains(diagonal2)) {
                    continue;
                }
                queens[row] = i;
                columns.add(i);
                diagonals1.add(diagonal1);
                diagonals2.add(diagonal2);
                backtrack(solutions, queens, n, row + 1, columns, diagonals1, diagonals2);
                queens[row] = -1;
                columns.remove(i);
                diagonals1.remove(diagonal1);
                diagonals2.remove(diagonal2);
            }
        }
    }

    public List<String> generateBoard(int[] queens, int n) {
        List<String> board = new ArrayList<String>();
        for (int i = 0; i < n; i++) {
            char[] row = new char[n];
            Arrays.fill(row, '.');
            row[queens[i]] = 'Q';
            board.add(new String(row));
        }
        return board;
    }
}


}
