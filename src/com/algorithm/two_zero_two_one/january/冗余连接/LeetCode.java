package com.algorithm.two_zero_two_one.january.冗余连接;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Ming
 * @date 2021/1/13 - 11:46
 * @describe
 */
public class LeetCode {

    /**
     * 在本问题中, 树指的是一个连通且无环的无向图。
     *
     * 输入一个图，该图由一个有着N个节点 (节点值不重复1, 2, ..., N) 的树及一条附加的边构成。
     * 附加的边的两个顶点包含在1到N中间，这条附加的边不属于树中已存在的边。
     *
     * 结果图是一个以边组成的二维数组。每一个边的元素是一对[u, v] ，满足 u < v，表示连接顶点u 和v的无向图的边。
     *
     * 返回一条可以删去的边，使得结果图是一个有着N个节点的树。如果有多个答案，则返回二维数组中最后出现的边。答案边 [u, v] 应满足相同的格式 u < v。
     *
     * 输入: [[1,2], [1,3], [2,3]]
     * 输出: [2,3]
     * 解释: 给定的无向图为:
     *   1
     *  / \
     * 2 - 3
     *
     * 输入: [[1,2], [2,3], [3,4], [1,4], [1,5]]
     * 输出: [1,4]
     * 解释: 给定的无向图为:
     * 5 - 1 - 2
     *     |   |
     *     4 - 3
     *
     */
    /**
     * 自己的思路：设置标志，慢慢合并
     * 执行用时：2 ms, 在所有 Java 提交中击败了19.86%的用户
     * 内存消耗：39.1 MB, 在所有 Java 提交中击败了45.31%的用户
     */
    public int[] findRedundantConnection(int[][] edges) {
        int[] path = new int[edges.length+1 ];
        for (int[] edge : edges) {
            if (path[edge[0]] == 0 && path[edge[1]] == 0){
                path[edge[0]] = edge[0];
                path[edge[1]] = edge[0];
            } else if (path[edge[0]] == 0 && path[edge[1]] != 0){
                path[edge[0]] = path[edge[1]];
            } else if (path[edge[1]] == 0 && path[edge[0]] != 0){
                path[edge[1]] = path[edge[0]];
            }else {
                if (path[edge[0]] == path[edge[1]]) return edge;
                else swag(path, path[edge[1]], path[edge[0]]);
            }
        }
        return null;
    }

    private void swag(int[] path, int before, int after){
        for (int i = 0; i < path.length; i++) {
            if (path[i] == before) path[i] = after;
        }
    }

    public static void main(String[] args) {
        LeetCode l = new LeetCode();
        System.out.println(l.findRedundantConnection(new int[][]{{3, 4}, {1, 2}, {2, 4}, {3, 5}, {2, 5}}));
    }
}
