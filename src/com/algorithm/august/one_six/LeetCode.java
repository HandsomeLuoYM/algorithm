package com.algorithm.august.one_six;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @author Ming
 * @date 2020/8/16 - 15:24
 * @describe
 */
public class LeetCode {

    /**
     * 有一幅以二维整数数组表示的图画，每一个整数表示该图画的像素值大小，数值在 0 到 65535 之间。
     *
     * 给你一个坐标 (sr, sc) 表示图像渲染开始的像素值（行 ，列）和一个新的颜色值 newColor，让你重新上色这幅图像。
     *
     * 为了完成上色工作，从初始坐标开始，记录初始坐标的上下左右四个方向上像素值与初始坐标相同的相连像素点，
     * 接着再记录这四个方向上符合条件的像素点与他们对应四个方向上像素值与初始坐标相同的相连像素点，……，重复该过程。
     * 将所有有记录的像素点的颜色值改为新的颜色值。
     *
     * 最后返回经过上色渲染后的图像。
     *
     * 输入:
     *      image = [[1,1,1],[1,1,0],[1,0,1]]
     *      sr = 1, sc = 1, newColor = 2
     * 输出:
     *      [[2,2,2],[2,2,0],[2,0,1]]
     * 解析:
     *      在图像的正中间，(坐标(sr,sc)=(1,1)),
     *      在路径上所有符合条件的像素点的颜色都被更改成2。
     *      注意，右下角的像素没有更改为2，
     *      因为它不是在上下左右四个方向上与初始点相连的像素点。
     *
     */

    /**
     * 深度优先算法
     * 执行用时：1 ms, 在所有 Java 提交中击败了97.15%的用户
     * 内存消耗：41 MB, 在所有 Java 提交中击败了6.67%的用户
     */
    public int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
        if (image[sr][sc]==newColor) return image;
        change (image,sr,sc,newColor,image[sr][sc]);
        return image;
    }

    private void change(int[][] image, int sr, int sc, int newColor,int oldColor){

        if (sr < 0 || sc < 0 || sr >= image.length ||  sc >= image[0].length ) return;

        if (image[sr][sc]==oldColor){
            image[sr][sc]=newColor;
            change(image,sr+1,sc,newColor,oldColor);
            change(image,sr-1,sc,newColor,oldColor);
            change(image,sr,sc+1,newColor,oldColor);
            change(image,sr,sc-1,newColor,oldColor);
        }
    }


    /**
     * 广度优先算法
     */
    int[] dx = {1, 0, 0, -1};
    int[] dy = {0, 1, -1, 0};
    public int[][] floodFill2(int[][] image, int sr, int sc, int newColor) {
        int currColor = image[sr][sc];
        if (currColor == newColor) {
            return image;
        }
        int n = image.length, m = image[0].length;
        Queue<int[]> queue = new LinkedList<int[]>();
        queue.offer(new int[]{sr, sc});
        image[sr][sc] = newColor;
        while (!queue.isEmpty()) {
            int[] cell = queue.poll();
            int x = cell[0], y = cell[1];
            for (int i = 0; i < 4; i++) {
                int mx = x + dx[i], my = y + dy[i];
                if (mx >= 0 && mx < n && my >= 0 && my < m && image[mx][my] == currColor) {
                    queue.offer(new int[]{mx, my});
                    image[mx][my] = newColor;
                }
            }
        }
        return image;
    }


}
