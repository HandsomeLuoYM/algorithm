package com.algorithm.two_zero_two_zero.september.two_three;

/**
 * @author Ming
 * @date 2020/9/23 - 12:12
 * @describe
 */
public class SixtySix {

    /**
     * 地上有一个m行和n列的方格。一个机器人从坐标0,0的格子开始移动，
     * 每一次只能向左，右，上，下四个方向移动一格，但是不能进入行坐标和列坐标的数位之和大于k的格子。
     *
     * 例如，当k为18时，机器人能够进入方格（35,37），因为3+5+3+7 = 18。
     * 但是，它不能进入方格（35,38），因为3+5+3+8 = 19。请问该机器人能够达到多少个格子？
     */

    /**
     * 自己的想法：初始化一个东西然后左右上下移动，当满足条件就继续左右上下遍历，当不满足就退出
     *
     * 运行时间：10ms
     *
     * 占用内存：9440k
     */
    int[] x = {0,1,0,-1};
    int[] y = {1,0,-1,0};
    int all = 0;
    public int movingCount(int threshold, int rows, int cols) {
        int[][] num = new int[rows][cols];
        add(num,threshold,rows,cols,0,0);
        return all;
    }
    private void add(int[][] num , int threshold, int rows, int cols, int nowRows, int nowCols){
        if (cal(nowRows)+cal(nowCols)<=threshold&&num[nowRows][nowCols]==0) {
            num[nowRows][nowCols]=1;
            all++;
            for (int i = 0;i<4;i++){
                if (nowRows+y[i]>=0 && nowRows+y[i]<rows && nowCols+x[i]>=0 && nowCols+x[i]<cols )
                    add(num,threshold,rows,cols,nowRows+y[i],nowCols+x[i]);
            }
        }

    }
    private int cal(int num) {
        int sum = 0;
        while (num > 0) {
            sum += num % 10;
            num /= 10;
        }
        return sum;
    }

    public static void main(String[] args) {
        SixtySix sixtySix = new SixtySix();
        int i = sixtySix.movingCount(10, 1, 100);

        System.out.println(i);
    }
}
