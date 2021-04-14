package com.algorithm.two_zero_two_one.january.缀点成线;

/**
 * @author Ming
 * @date 2021/1/17 - 23:22
 * @describe
 */
public class LeetCode {
    /**
     * 在一个 XY 坐标系中有一些点，我们用数组 coordinates 来分别记录它们的坐标，其中 coordinates[i] = [x, y] 表示横坐标为 x、纵坐标为 y 的点。
     *
     * 请你来判断，这些点是否在该坐标系中属于同一条直线上，是则返回 true，否则请返回 false。
     * 输入：coordinates = [[1,2],[2,3],[3,4],[4,5],[5,6],[6,7]]
     * 输出：true
     *
     * 输入：coordinates = [[1,1],[2,2],[3,4],[4,5],[5,6],[7,7]]
     * 输出：false
     */
    /**
     * 思路：
     *      利用乘法避免除数为 0 的情况
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
     * 内存消耗：38 MB, 在所有 Java 提交中击败了77.80%的用户
     */
    public boolean checkStraightLine(int[][] coordinates) {
        int  a = coordinates[1][1] - coordinates[0][1], b =  coordinates[1][0] - coordinates[0][0];
        int length = coordinates.length;
        for (int i = 2; i < length; i++){
            if ((coordinates[i][1] - coordinates[0][1]) * b != (coordinates[i][0] - coordinates[0][0]) * a) return false;
        }
        return  true;
    }
}
