package com.algorithm.two_zero_two_zero.october.zero_six;

import java.util.*;

/**
 * @author Ming
 * @date 2020/10/6 - 13:04
 * @describe 834. 树中距离之和
 */
public class LeetCode {

    /**
     * 给定一个无向、连通的树。树中有 N 个标记为 0...N-1 的节点以及 N-1 条边 。
     * 第 i 条边连接节点 edges[i][0] 和 edges[i][1] 。
     * 返回一个表示节点 i 与其他所有节点距离之和的列表 ans。
     *
     * 输入: N = 6, edges = [[0,1],[0,2],[2,3],[2,4],[2,5]]
     * 输出: [8,12,6,10,10,10]
     * 解释:
     * 如下为给定的树的示意图：
     *   0
     *  / \
     * 1   2
     *    /|\
     *   3 4 5
     *
     * 我们可以计算出 dist(0,1) + dist(0,2) + dist(0,3) + dist(0,4) + dist(0,5)
     * 也就是 1 + 1 + 2 + 2 + 2 = 8。 因此，answer[0] = 8，以此类推。
     *
     */

    /**
     * 自己的思路：树形结构，题干中不是树状结构
     *            节点的距离 = 父节点距离 - 该节点的子节点 + （总数 - 该节点的子节点） -2
     */
    public int[] sumOfDistancesInTree(int N, int[][] edges) {
        if (edges.length==1) return new int[]{1,1};
        //存储子节点个数
        Map<Integer,Integer> sonNumber = new HashMap<>(N);
        //存储直接子节点
        Map<Integer, List<Integer>> son = new HashMap<>(N);
        //存储父节点
        int[] father = new int[N], result = new int[N];
        int length = edges.length;
        Arrays.fill(father, -1);
        List<Integer> list;
        for (int i = 0; i<length; i++){
            father[edges[i][1]] = edges[i][0];
            sonNumber.put(edges[i][0],0);
            if (son.containsKey(edges[i][0])){
                list = son.get(edges[i][0]);
                list.add(edges[i][1]);
            }else{
                List<Integer> node = new ArrayList<>();
                node.add(edges[i][1]);
                son.put(edges[i][0],node);
            }
        }
        add(father,edges,sonNumber);
        result[0] = fatherNumber(son);
        calc(son,father,result,sonNumber,N);
        return result;
    }

    void calc(Map<Integer,List<Integer>> son, int[] father, int[] result, Map<Integer,Integer> sonNumber, int N){
        Stack<Integer> stack = new Stack<>();
        stack.push(0);
        List<Integer> list;
        while (!stack.isEmpty()){
            Integer pop = stack.pop();
            list = son.get(pop);
            if (list!=null){
                for (int i =0; i<list.size(); i++){
                    stack.push(list.get(i));
                }
            }
            int fatherNode = father[pop];
            if (pop==0) continue;
            int sonNum = sonNumber.get(pop) == null ? 0 : sonNumber.get(pop);
            result[pop] = result[fatherNode] - sonNum + (N - sonNum) -2;
        }
    }

    /**
     * 获取父节点的距离
     */
    int fatherNumber(Map<Integer,List<Integer>> son){
        int all = 0,now=0;
        Stack<Integer> stack = new Stack<>();
        stack.push(0);
        int number;
        while (!stack.isEmpty()) {
            number = stack.size();
            all += now * number;
            now++;
            for (int i = 0; i<number; i++){
                List<Integer> list = son.get(stack.pop());
                if (list!=null){
                    list.forEach(stack::push);
                }
            }

        }
        return all;
    }

    /**
     * 修改子节点
     */
    void add(int[] father, int[][] edges, Map<Integer,Integer> map){
        int length = edges.length;
        for (int i = 0; i<length; i++){
            int temp = father[edges[i][1]];
            while (temp!=-1){
                map.put(temp,map.get(temp)+1);
                temp = father[temp];
            }
        }
    }

    class Solution {
        int[] ans;
        int[] sz;
        int[] dp;
        List<List<Integer>> graph;

        public int[] sumOfDistancesInTree(int N, int[][] edges) {
            ans = new int[N];
            sz = new int[N];
            dp = new int[N];
            graph = new ArrayList<List<Integer>>();
            for (int i = 0; i < N; ++i) {
                graph.add(new ArrayList<Integer>());
            }
            for (int[] edge: edges) {
                int u = edge[0], v = edge[1];
                graph.get(u).add(v);
                graph.get(v).add(u);
            }
            dfs(0, -1);
            dfs2(0, -1);
            return ans;
        }

        public void dfs(int u, int f) {
            sz[u] = 1;
            dp[u] = 0;
            for (int v: graph.get(u)) {
                if (v == f) {
                    continue;
                }
                dfs(v, u);
                dp[u] += dp[v] + sz[v];
                sz[u] += sz[v];
            }
        }

        public void dfs2(int u, int f) {
            ans[u] = dp[u];
            for (int v: graph.get(u)) {
                if (v == f) {
                    continue;
                }
                int pu = dp[u], pv = dp[v];
                int su = sz[u], sv = sz[v];

                dp[u] -= dp[v] + sz[v];
                sz[u] -= sz[v];
                dp[v] += dp[u] + sz[u];
                sz[v] += sz[u];

                dfs2(v, u);

                dp[u] = pu;
                dp[v] = pv;
                sz[u] = su;
                sz[v] = sv;
            }
        }
    }

}
