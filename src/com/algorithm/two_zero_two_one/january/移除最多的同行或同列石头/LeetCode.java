package com.algorithm.two_zero_two_one.january.移除最多的同行或同列石头;

import java.util.*;

/**
 * @author Ming
 * @date 2021/1/15 - 15:24
 * @describe
 */
public class LeetCode {

    /**
     * n 块石头放置在二维平面中的一些整数坐标点上。每个坐标点上最多只能有一块石头。
     *
     * 如果一块石头的 同行或者同列 上有其他石头存在，那么就可以移除这块石头。
     *
     * 给你一个长度为 n 的数组 stones ，其中 stones[i] = [xi, yi] 表示第 i 块石头的位置，返回 可以移除的石子 的最大数量。
     *
     * 输入：stones = [[0,0],[0,1],[1,0],[1,2],[2,1],[2,2]]
     * 输出：5
     * 解释：一种移除 5 块石头的方法如下所示：
     * 1. 移除石头 [2,2] ，因为它和 [2,1] 同行。
     * 2. 移除石头 [2,1] ，因为它和 [0,1] 同列。
     * 3. 移除石头 [1,2] ，因为它和 [1,0] 同行。
     * 4. 移除石头 [1,0] ，因为它和 [0,0] 同列。
     * 5. 移除石头 [0,1] ，因为它和 [0,0] 同行。
     * 石头 [0,0] 不能移除，因为它没有与另一块石头同行/列。
     *
     * 输入：stones = [[0,0],[0,2],[1,1],[2,0],[2,2]]
     * 输出：3
     * 解释：一种移除 3 块石头的方法如下所示：
     * 1. 移除石头 [2,2] ，因为它和 [2,0] 同行。
     * 2. 移除石头 [2,0] ，因为它和 [0,0] 同列。
     * 3. 移除石头 [0,2] ，因为它和 [0,0] 同行。
     * 石头 [0,0] 和 [1,1] 不能移除，因为它们没有与另一块石头同行/列。
     *
     */
    /**
     * 并查集
     * 思路：
     *      设置两个map来存储所以，在边判断是否能合并，合并时需要修改map的映射关系
     */
    public int removeStones(int[][] stones) {
        Map<Integer, Integer> row = new HashMap<Integer, Integer>(), cell = new HashMap<Integer, Integer>();
        int[] path = new int[stones.length];
        int now = 0, length = stones.length;
        for (int i = 0; i < length; i++) path[i] = i;
        for (int i = 0; i < length; i++){
            if (row.containsKey(stones[i][0])){
                path[i] = path[row.get(stones[i][0])];
            }else {
                row.put(stones[i][0], i);
            }
            if (cell.containsKey(stones[i][1]) && path[i] != cell.get(stones[i][1])){
                change(cell, row, path, path[i], cell.get(stones[i][1]), i, stones);
            }else {
                cell.put(stones[i][1], path[i]);
            }
        }
        int rs = 0;
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < length; i++){
            if (!set.contains(path[i])){
                rs++;
                set.add(path[i]);
            }
        }
        return length - rs;
    }

    private void change(Map<Integer, Integer> cell, Map<Integer, Integer> row, int[] path, int oldInt, int newInt, int endIndex, int[][] stones){
        for (int i = 0; i <= endIndex; i++){
            if (path[i] == oldInt){
                path[i] = newInt;
                cell.put(stones[i][1], newInt);
                row.put(stones[i][0], newInt);
            }
        }
    }
}
