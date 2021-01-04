package com.algorithm.two_zero_two_zero.september.two_two;


/**
 * @author Ming
 * @date 2020/9/22 - 16:50
 * @describe
 */
public class SixtyFive {

    /**
     * 请设计一个函数，用来判断在一个矩阵中是否存在一条包含某字符串所有字符的路径。
     * 路径可以从矩阵中的任意一个格子开始，每一步可以在矩阵中向左，向右，向上，向下移动一个格子。
     * 如果一条路径经过了矩阵中的某一个格子，则该路径不能再进入该格子。
     * 例如  asabfdcceese矩阵中包含一条字符串"bcced"的路径，但是矩阵中不包含"abcb"路径，
     * 因为字符串的第一个字符b占据了矩阵中的第一行第二个格子之后，路径不能再次进入该格子。
     */

    /**
     * 思路：设置一个数组，记录走过的路径，然后上下左右遍历，同时要判断是否数组越界
     *
     * 运行时间：12ms
     *
     * 占用内存：9596k
     */
    int[] y = {1,0,-1,0};
    int[] x = {0,1,0,-1};
    public boolean hasPath(char[] matrix, int rows, int cols, char[] str) {
        if (matrix.length==1&&str.length==1&&matrix[0]==str[0]) return true;
        boolean[] load = new boolean[matrix.length];
        int length = matrix.length;
        for (int i =0;i< length;i++){
            if (judge(matrix,str,0,i , rows,cols,load)) return true;
        }
        return false;
    }

    boolean judge(char[] matrix,char[] str,int now,int index , int rows, int cols,boolean[] load){
        if (now==str.length) return true;
        int nowRows = index/cols , nowCols = index%cols;
        if (matrix[index]==str[now]&&!load[index]) {
            load[index]=true;
            for (int i = 0;i<4;i++){
                if (nowRows+y[i]>=0 && nowRows+y[i]<rows && nowCols+x[i]>=0 && nowCols+x[i]<cols){
                    if(judge( matrix, str, now+1, index + x[i] +cols*y[i],rows, cols, load)) return true;
                }
            }
            load[index]=false;

        }
        return false;
    }

}
