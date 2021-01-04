package com.algorithm.two_zero_two_zero.september.one_serven;

import java.util.*;

/**
 * @author Ming
 * @date 2020/9/17 - 14:03
 * @describe  685 冗余连接 II
 */
public class LeetCode {

    /**
     * 在本问题中，有根树指满足以下条件的有向图。该树只有一个根节点，所有其他节点都是该根节点的后继。
     * 每一个节点只有一个父节点，除了根节点没有父节点。
     *
     * 输入一个有向图，该图由一个有着N个节点 (节点值不重复1, 2, ..., N) 的树及一条附加的边构成。
     * 附加的边的两个顶点包含在1到N中间，这条附加的边不属于树中已存在的边。
     *
     * 结果图是一个以边组成的二维数组。 每一个边 的元素是一对 [u, v]，
     * 用以表示有向图中连接顶点 u 和顶点 v 的边，其中 u 是 v 的一个父节点。
     *
     * 返回一条能删除的边，使得剩下的图是有N个节点的有根树。若有多个答案，返回最后出现在给定二维数组的答案。
     *
     */
    /**
     * 失败，可以去掉，无法判断可以去掉哪个
     */
    int[] result;
    boolean flag = true;
    public int[] findRedundantDirectedConnection(int[][] edges) {
        Map<Integer, List<Integer>> map = new HashMap<>();
        Set<Integer> all = new HashSet<>();
        int length = edges.length;
        List<Integer> list;
        for (int i = 0;i<length;i++){
            if (!map.containsKey(edges[i][0])){
                list = new ArrayList<>();
                list.add(edges[i][1]);
                map.put(edges[i][0],list);
            }else {
                list = map.get(edges[i][0]);
                list.add(edges[i][1]);
                map.put(edges[i][0],list);
            }
            all.add(edges[i][0]);
            all.add(edges[i][1]);
        }
        all.remove(edges[0][0]);
        find(map,all,edges[0][0]);
        for (int i =0;i<edges.length;i++){
            if (edges[i][1]==result[1])
                result[0]=edges[i][0];
        }
        return result;
    }

    private void find(Map<Integer, List<Integer>> map, Set<Integer> all, int i) {
        List<Integer> list = map.get(i);
        if (list==null) return;;
        map.remove(i);
        for (int j = 0;j<list.size()&&flag;j++) {
            int integer = list.get(j);
            if (!all.contains(integer)) {
                result = new int[]{i,integer};
                flag= false;
                return;
            }
            all.remove(integer);
            find(map, all, integer);
        }
    }

    /**
     *
     */
    class Solution {
        public int[] findRedundantDirectedConnection(int[][] edges) {
            // 边的条数（在这个问题里等于结点个数）
            int len = edges.length;
            // 步骤 1：预处理入度数组（记录指向某个结点的边的条数）
            int[] inDegree = new int[len + 1];
            for (int[] edge : edges) {
                inDegree[edge[1]]++;
            }

            // 步骤 2：先尝试删除构成入度为 2 的边，看看是否形成环
            for (int i = len - 1; i >= 0; i--) {
                if (inDegree[edges[i][1]] == 2) {
                    // 如果不构成环，这条边就是要去掉的那条边
                    if (!judgeCircle(edges, len, i)) {
                        return edges[i];
                    }
                }
            }

            // 步骤 3：再尝试删除构成入度为 1 的边，看看是否形成环
            for (int i = len - 1; i >= 0; i--) {
                if (inDegree[edges[i][1]] == 1) {
                    // 如果不构成环，这条边就是要去掉的那条边
                    if (!judgeCircle(edges, len, i)) {
                        return edges[i];
                    }
                }
            }
            throw new IllegalArgumentException("输入不符合要求。");
        }

        /**
         * 将 removeEdgeIndex 去掉以后，剩下的有向边是否构成环
         *
         * @param edges
         * @param len             结点总数（从 1 开始，因此初始化的时候 + 1）
         * @param removeEdgeIndex 删除的边的下标
         * @return 构成环，返回 true
         */
        private boolean judgeCircle(int[][] edges, int len, int removeEdgeIndex) {
            UnionFind unionFind = new UnionFind(len + 1);
            for (int i = 0; i < len; i++) {
                if (i == removeEdgeIndex) {
                    continue;
                }
                if (!unionFind.union(edges[i][0], edges[i][1])) {
                    // 合并失败，表示 edges[i][0] 和 edges[i][1] 在一个连通分量里，即构成了环
                    return true;
                }
            }
            return false;
        }

        private class UnionFind {
            // 代表元法
            private int[] parent;

            public UnionFind(int n) {
                parent = new int[n];
                for (int i = 0; i < n; i++) {
                    parent[i] = i;
                }
            }

            public int find(int x) {
                while (x != parent[x]) {
                    // 路径压缩（隔代压缩）
                    parent[x] = parent[parent[x]];
                    x = parent[x];
                }
                return x;
            }

            /**
             * @param x
             * @param y
             * @return 如果合并成功返回 true
             */
            public boolean union(int x, int y) {
                int rootX = find(x);
                int rootY = find(y);

                if (rootX == rootY) {
                    return false;
                }
                parent[rootX] = rootY;
                return true;
            }
        }
    }


}
