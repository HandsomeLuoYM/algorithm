package com.algorithm.two_zero_two_one.january.省份数量;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author Ming
 * @date 2021/1/7 - 11:16
 * @describe
 */
public class LeetCode {

    /**
     * 有 n 个城市，其中一些彼此相连，另一些没有相连。如果城市 a 与城市 b 直接相连，且城市 b 与城市 c 直接相连，那么城市 a 与城市 c 间接相连。
     *
     * 省份 是一组直接或间接相连的城市，组内不含其他没有相连的城市。
     *
     * 给你一个 n x n 的矩阵 isConnected ，其中 isConnected[i][j] = 1 表示第 i 个城市和第 j 个城市直接相连，而 isConnected[i][j] = 0 表示二者不直接相连。
     *
     * 返回矩阵中 省份 的数量。
     */

    /**
     * 思路：
     *      先用flod算法求每个点能到达的地方，然后再判断是否是同一个省份
     * 执行用时：18 ms, 在所有 Java 提交中击败了5.39%的用户
     * 内存消耗：39.4 MB, 在所有 Java 提交中击败了58.48%的用户
     */
    public int findCircleNum(int[][] isConnected) {
        int length = isConnected.length;
        for (int k = 0; k < length; k++) {
            for (int i = 0; i < length; i++){
                for (int j = 0; j < length; j++){
                    if (isConnected[i][k] != 0 && isConnected[k][j] != 0) {
                        isConnected[i][j] = isConnected[i][k] + isConnected[k][j];
                    }
                }
            }
        }
        int number = 0;
        int[] path = new int[length];
        for (int i = 0; i < length; i++){
            if (path[i] == 0){
                for (int j = 0; j < length; j++){
                    if (isConnected[i][j] != 0) {
                        path[j] = 1;
                    }
                }
                path[i] = 1;
                number++;
            }
        }
        return number;
    }

    /**
     * 深度优先算法
     */
    class Solution {
        public int findCircleNum(int[][] isConnected) {
            int provinces = isConnected.length;
            boolean[] visited = new boolean[provinces];
            int circles = 0;
            for (int i = 0; i < provinces; i++) {
                if (!visited[i]) {
                    dfs(isConnected, visited, provinces, i);
                    circles++;
                }
            }
            return circles;
        }

        public void dfs(int[][] isConnected, boolean[] visited, int provinces, int i) {
            for (int j = 0; j < provinces; j++) {
                if (isConnected[i][j] == 1 && !visited[j]) {
                    visited[j] = true;
                    dfs(isConnected, visited, provinces, j);
                }
            }
        }
    }

    /**
     * 广度优先算法
     */
    class Solution1 {
        public int findCircleNum(int[][] isConnected) {
            int provinces = isConnected.length;
            boolean[] visited = new boolean[provinces];
            int circles = 0;
            Queue<Integer> queue = new LinkedList<Integer>();
            for (int i = 0; i < provinces; i++) {
                if (!visited[i]) {
                    queue.offer(i);
                    while (!queue.isEmpty()) {
                        int j = queue.poll();
                        visited[j] = true;
                        for (int k = 0; k < provinces; k++) {
                            if (isConnected[j][k] == 1 && !visited[k]) {
                                queue.offer(k);
                            }
                        }
                    }
                    circles++;
                }
            }
            return circles;
        }
    }

}
