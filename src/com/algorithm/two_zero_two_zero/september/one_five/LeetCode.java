package com.algorithm.two_zero_two_zero.september.one_five;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Ming
 * @date 2020/9/15 - 10:38
 * @describe 37 解数独
 */
public class LeetCode {

    /**
     * 编写一个程序，通过已填充的空格来解决数独问题。
     *
     * 一个数独的解法需遵循如下规则：
     *
     * 数字 1-9 在每一行只能出现一次。
     * 数字 1-9 在每一列只能出现一次。
     * 数字 1-9 在每一个以粗实线分隔的 3x3 宫内只能出现一次。
     *
     */

    /**
     * 官方题解一
     *
     * 思路：我们首先对整个数独数组进行遍历，当我们遍历到第 ii 行第 jj 列的位置：
     *
     * 如果该位置是一个空白格，那么我们将其加入一个用来存储空白格位置的列表中，方便后续的递归操作；
     *
     * 如果该位置是一个数字 xx，那么我们需要将 line[i][x−1]，column[j][x−1] 以及 block[⌊i/3⌋][⌊j/3⌋][x−1] 均置为 True。
     *
     * 当我们结束了遍历过程之后，就可以开始递归枚举。当递归到第 i 行第 j 列的位置时，我们枚举填入的数字 x。根据题目的要求，数字 x 不能
     * 和当前行、列、九宫格中已经填入的数字相同，因此 line[i][x−1]，column[j][x−1] 以及 block[⌊i/3⌋][⌊j/3⌋][x−1] 必须均为 False。
     *
     * 当我们填入了数字 x 之后，我们要将上述的三个值都置为 True，并且继续对下一个空白格位置进行递归。在回溯到当前递归层时，我们还要将上述的三个值重新置为 False。
     *
     * 执行用时：4 ms, 在所有 Java 提交中击败了74.85%的用户
     * 内存消耗：37.1 MB, 在所有 Java 提交中击败了66.57%的用户
     */
    //存储第i行是否有j数字
    private boolean[][] line = new boolean[9][9];
    //存储第i列是否有j数字
    private boolean[][] column = new boolean[9][9];
    //第i行j个3×3格子中是否有k这个数字
    private boolean[][][] block = new boolean[3][3][9];
    //递归时判断后续是否已经解出答案
    private boolean valid = false;
    //存储空白格的坐标
    private List<int[]> spaces = new ArrayList<int[]>();

    public void solveSudoku(char[][] board) {
        for (int i = 0; i < 9; ++i) {
            for (int j = 0; j < 9; ++j) {
                if (board[i][j] == '.') {
                    spaces.add(new int[]{i, j});
                } else {
                    int digit = board[i][j] - '1';
                    line[i][digit] = column[j][digit] = block[i / 3][j / 3][digit] = true;
                }
            }
        }
        dfs(board, 0);
    }

    public void dfs(char[][] board, int pos) {
        if (pos == spaces.size()) {
            valid = true;
            return;
        }
        //取第k个空格
        int[] space = spaces.get(pos);
        //得到它的坐标
        int i = space[0], j = space[1];
        for (int digit = 0; digit < 9 && !valid; ++digit) {
            //枚举遍历尝试
            if (!line[i][digit] && !column[j][digit] && !block[i / 3][j / 3][digit]) {
                line[i][digit] = column[j][digit] = block[i / 3][j / 3][digit] = true;
                board[i][j] = (char) (digit + '1');
                dfs(board, pos + 1);
                //回溯
                line[i][digit] = column[j][digit] = block[i / 3][j / 3][digit] = false;
            }
        }
    }

    /**
     * 位运算优化
     */
    class Solution {
        private int[] line = new int[9];
        private int[] column = new int[9];
        private int[][] block = new int[3][3];
        private boolean valid = false;
        private List<int[]> spaces = new ArrayList<int[]>();

        public void solveSudoku(char[][] board) {
            for (int i = 0; i < 9; ++i) {
                for (int j = 0; j < 9; ++j) {
                    if (board[i][j] == '.') {
                        spaces.add(new int[]{i, j});
                    } else {
                        int digit = board[i][j] - '0' - 1;
                        flip(i, j, digit);
                    }
                }
            }

            dfs(board, 0);
        }

        public void dfs(char[][] board, int pos) {
            if (pos == spaces.size()) {
                valid = true;
                return;
            }

            int[] space = spaces.get(pos);
            int i = space[0], j = space[1];
            int mask = ~(line[i] | column[j] | block[i / 3][j / 3]) & 0x1ff;
            for (; mask != 0 && !valid; mask &= (mask - 1)) {
                int digitMask = mask & (-mask);
                int digit = Integer.bitCount(digitMask - 1);
                flip(i, j, digit);
                board[i][j] = (char) (digit + '0' + 1);
                dfs(board, pos + 1);
                flip(i, j, digit);
            }
        }

        public void flip(int i, int j, int digit) {
            line[i] ^= (1 << digit);
            column[j] ^= (1 << digit);
            block[i / 3][j / 3] ^= (1 << digit);
        }
    }


    /**
     * 枚举优化
     */
    class Solution1 {
        private int[] line = new int[9];
        private int[] column = new int[9];
        private int[][] block = new int[3][3];
        private boolean valid = false;
        private List<int[]> spaces = new ArrayList<int[]>();

        public void solveSudoku(char[][] board) {
            for (int i = 0; i < 9; ++i) {
                for (int j = 0; j < 9; ++j) {
                    if (board[i][j] != '.') {
                        int digit = board[i][j] - '0' - 1;
                        flip(i, j, digit);
                    }
                }
            }

            while (true) {
                boolean modified = false;
                for (int i = 0; i < 9; ++i) {
                    for (int j = 0; j < 9; ++j) {
                        if (board[i][j] == '.') {
                            int mask = ~(line[i] | column[j] | block[i / 3][j / 3]) & 0x1ff;
                            if ((mask & (mask - 1)) == 0) {
                                int digit = Integer.bitCount(mask - 1);
                                flip(i, j, digit);
                                board[i][j] = (char) (digit + '0' + 1);
                                modified = true;
                            }
                        }
                    }
                }
                if (!modified) {
                    break;
                }
            }

            for (int i = 0; i < 9; ++i) {
                for (int j = 0; j < 9; ++j) {
                    if (board[i][j] == '.') {
                        spaces.add(new int[]{i, j});
                    }
                }
            }

            dfs(board, 0);
        }

        public void dfs(char[][] board, int pos) {
            if (pos == spaces.size()) {
                valid = true;
                return;
            }

            int[] space = spaces.get(pos);
            int i = space[0], j = space[1];
            int mask = ~(line[i] | column[j] | block[i / 3][j / 3]) & 0x1ff;
            for (; mask != 0 && !valid; mask &= (mask - 1)) {
                int digitMask = mask & (-mask);
                int digit = Integer.bitCount(digitMask - 1);
                flip(i, j, digit);
                board[i][j] = (char) (digit + '0' + 1);
                dfs(board, pos + 1);
                flip(i, j, digit);
            }
        }

        public void flip(int i, int j, int digit) {
            line[i] ^= (1 << digit);
            column[j] ^= (1 << digit);
            block[i / 3][j / 3] ^= (1 << digit);
        }
    }

}
