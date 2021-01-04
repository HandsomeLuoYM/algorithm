package com.algorithm.two_zero_two_zero.september.one_three;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Ming
 * @date 2020/9/13 - 20:40
 * @describe 79 单词搜索
 */
public class LeetCode {
    /**
     * 给定一个二维网格和一个单词，找出该单词是否存在于网格中。
     *
     * 单词必须按照字母顺序，通过相邻的单元格内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。同一个单元格内的字母不允许被重复使用。
     *
     * board =
     * [
     *   ['A','B','C','E'],
     *   ['S','F','C','S'],
     *   ['A','D','E','E']
     * ]
     *
     * 给定 word = "ABCCED", 返回 true
     * 给定 word = "SEE", 返回 true
     * 给定 word = "ABCB", 返回 false
     *
     * board 和 word 中只包含大写和小写英文字母。
     * 1 <= board.length <= 200
     * 1 <= board[i].length <= 200
     * 1 <= word.length <= 10^3
     *
     */

    /**
     * 自己的暴力破解
     *
     * 思路：设置一个数组记录走过的位置，并且遍历每个点，当第一个点，等于第一个时进入方法里，
     *       继续回溯，当该点四个发现时返回，而当长度刚好时即找到。
     *
     * 执行用时：14 ms, 在所有 Java 提交中击败了16.93%的用户
     * 内存消耗：41.2 MB, 在所有 Java 提交中击败了90.66%的用户
     */
    int[] xMov = new int[]{1,0,-1,0};
    int[] yMov = new int[]{0,1,0,-1};
    public boolean exist(char[][] board, String word) {
        int y = board.length,x=board[0].length;
        int[][] load = new int[board.length][board[0].length];
        for (int i=0;i<y;i++){
            for (int j=0;j<x;j++){
                if (dfs(board,word,load,new ArrayList<Character>(word.length()),j,i)) return true;
            }
        }
        return false;
    }

    public boolean dfs(char[][] board, String word, int[][] load, List<Character>list,int x,int y){
        if (word.charAt(list.size())==board[y][x]) {
            load[y][x]=1;
            list.add(board[y][x]);
            if (list.size()==word.length()) return true;
            for (int i = 0;i<4;i++){
                if (x+xMov[i]<0||x+xMov[i]>=load[0].length||y+yMov[i]<0||y+yMov[i]>=load.length||load[y+yMov[i]][x+xMov[i]]==1) continue;
                if (word.charAt(list.size())==board[y+yMov[i]][x+xMov[i]]) {
                    if (dfs(board,word,load,list,x+xMov[i],y+yMov[i])) return true;
                }
            }
            list.remove(list.size()-1);
            load[y][x]=0;
        }
        return false;
    }

    public static void main(String[] args) {
        LeetCode leetCode = new LeetCode();
        //[["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]]
        //"SEE"
        char[][] ch = {{'A','B','C','E'},{'S','F','C','S'},{'A','D','E','E'}};
        boolean abcced = leetCode.exist(ch, "SEE");
        System.out.println(abcced);
    }
}
