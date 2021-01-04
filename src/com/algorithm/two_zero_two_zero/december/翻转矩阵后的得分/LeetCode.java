package com.algorithm.two_zero_two_zero.december.翻转矩阵后的得分;

/**
 * @author Ming
 * @date 2020/12/7 - 10:00
 * @describe
 */
public class LeetCode {

    /**
     * 有一个二维矩阵 A 其中每个元素的值为 0 或 1 。
     * 移动是指选择任一行或列，并转换该行或列中的每一个值：将所有 0 都更改为 1，将所有 1 都更改为 0。
     * 在做出任意次数的移动后，将该矩阵的每一行都按照二进制数来解释，矩阵的得分就是这些数字的总和。
     *
     * 返回尽可能高的分数。
     *
     *输入：[[0,0,1,1],[1,0,1,0],[1,1,0,0]]
     * 输出：39
     * 解释：
     * 转换为 [[1,1,1,1],[1,0,0,1],[1,1,1,1]]
     * 0b1111 + 0b1001 + 0b1111 = 15 + 9 + 15 = 39
     *
     */
    /**
     * 自己的思路：（贪心）
     *      第一行时可以改变行数据，使每一行第一个数字都是 1 ，然后接下去的每行就只能改变列数据，保证每列的 1 都是最多的。
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
     * 内存消耗：36 MB, 在所有 Java 提交中击败了91.53%的用户
     */
    public int matrixScore(int[][] A) {
        //定义一个行和列，以及总值
        int row = A.length, cell = A[0].length, all = 0;
        //先将每一行第一个变为 1 ，可行也可列
        for (int i = 0; i<row; i++){
            if (A[i][0] == 0){
                change(i, cell, A, true);
            }
            all = add(A,0);
        }
        //改变列
        for (int i = 1; i<cell; i++){
            int number = 0;
            for (int j = 0; j<row; j++){
                if (A[j][i]==1) number++;
            }
            if (number>row/2){
                all += add(A, i);
            }else {
                change(row,i,A,false);
                all += add(A, i);
            }
        }
        return all;
    }
    /**
     * 添加第 cell 列的数据
     **/
    private int add(int[][] A, int cell){
        int all = 0, pow = (int)Math.pow(2, A[0].length-cell-1);
        for (int i = 0; i<A.length; i++){
            all += pow*A[i][cell];
        }
        return all;
    }
    /**
     * 倒置数据
     * */
    private void change(int row, int cell, int[][] A, boolean  isRow){
        if (isRow){
            for (int i = 0; i<cell; i++){
                A[row][i] = A[row][i] == 1 ? 0 : 1;
            }
        }else {
            for (int i = 0; i<row; i++){
                A[i][cell] = A[i][cell] == 1 ? 0 : 1;
            }
        }
    }

    /**
     * 官方题解
     */
    class Solution {
        public int matrixScore(int[][] A) {
            int m = A.length, n = A[0].length;
            //首行都为 1，直接加
            int ret = m * (1 << (n - 1));
            //遍历每一列
            for (int j = 1; j < n; j++) {
                int nOnes = 0;
                for (int i = 0; i < m; i++) {
                    //首列为1，后面数字不用倒置
                    if (A[i][0] == 1) {
                        nOnes += A[i][j];
                    } else {//首列为1，后面数字要倒置（0->1，1->0）
                        nOnes += (1 - A[i][j]); // 如果这一行进行了行反转，则该元素的实际取值为 1 - A[i][j]
                    }
                }
                //计算哪一种好
                int k = Math.max(nOnes, m - nOnes);
                //值相加
                ret += k * (1 << (n - j - 1));
            }
            return ret;
        }
    }


}
