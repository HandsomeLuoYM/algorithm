package com.algorithm.two_zero_two_one.january.除法求值;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @author Ming
 * @date 2021/1/6 - 17:35
 * @describe
 */
public class LeetCode {
    /**
     * 给你一个变量对数组 equations 和一个实数值数组 values 作为已知条件，
     * 其中 equations[i] = [Ai, Bi] 和 values[i] 共同表示等式 Ai / Bi = values[i] 。
     * 每个 Ai 或 Bi 是一个表示单个变量的字符串。
     *
     * 另有一些以数组 queries 表示的问题，其中 queries[j] = [Cj, Dj] 表示第 j 个问题，
     * 请你根据已知条件找出 Cj / Dj = ? 的结果作为答案。
     *
     * 返回 所有问题的答案 。如果存在某个无法确定的答案，则用 -1.0 替代这个答案。
     *
     * 输入：equations = [["a","b"],["b","c"]], values = [2.0,3.0], queries = [["a","c"],["b","a"],["a","e"],["a","a"],["x","x"]]
     * 输出：[6.00000,0.50000,-1.00000,1.00000,-1.00000]
     * 解释：
     * 条件：a / b = 2.0, b / c = 3.0
     * 问题：a / c = ?, b / a = ?, a / e = ?, a / a = ?, x / x = ?
     * 结果：[6.0, 0.5, -1.0, 1.0, -1.0 ]
     *
     */
    /**
     * 思路：
     *      利用图论，Floyd 算法最最短路径的算法
     * 执行用时：2 ms, 在所有 Java 提交中击败了22.76%的用户
     * 内存消耗：37.1 MB, 在所有 Java 提交中击败了86.24%的用户
     */
    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        Map<String, Integer> map = new HashMap<String, Integer>();
        AtomicInteger atomicInteger = new AtomicInteger(0);
        //初始化该图信息
        equations.forEach(list -> {
            if (!map.containsKey(list.get(0))) {
                map.put(list.get(0), atomicInteger.intValue());
                atomicInteger.incrementAndGet();
            }
            if (!map.containsKey(list.get(1))) {
                map.put(list.get(1), atomicInteger.intValue());
                atomicInteger.incrementAndGet();
            }
        });
        //初始化图结构
        double[][] graph = new double[atomicInteger.get()][atomicInteger.get()];
//        Arrays
        int length = equations.size();
        //初始化图
        for (int i = 0; i < length; i++) {
            graph[map.get(equations.get(i).get(0))][map.get(equations.get(i).get(1))] = values[i];
            graph[map.get(equations.get(i).get(1))][map.get(equations.get(i).get(0))] = 1.0 / values[i];
        }
        //修改图信息
        length = atomicInteger.get();
        for (int k = 0; k < length; k++){
            for (int i = 0; i < length; i++){
                for (int j = 0; j < length; j++){
                    //不需要判断其他，因为只会右一个值
                    if (graph[i][k] > 0 && graph[k][j] > 0) {
                        graph[i][j] = graph[i][k] * graph[k][j];
                    }
                }
            }
        }
        double[] rs = new double[queries.size()];
        length = queries.size();
        for (int i = 0; i < length; i++){
            if (map.get(queries.get(i).get(0)) == null || map.get(queries.get(i).get(1)) == null) {
                rs[i] = -1;
                continue;
            }
            if (graph[map.get(queries.get(i).get(0))][map.get(queries.get(i).get(1))] > 0) {
                rs[i] = graph[map.get(queries.get(i).get(0))][map.get(queries.get(i).get(1))];
            } else {
                rs[i] = -1;
            }
        }
        return rs;
    }

    public static void main(String[] args) {
        /**
         * [["a","b"],["b","c"]]
         * [2.0,3.0]
         * [["a","c"],["b","a"],["a","e"],["a","a"],["x","x"]]
         */
        LeetCode l = new LeetCode();
        List<List<String>> equations = new ArrayList<>(), queries = new ArrayList<>();
        equations.add(new ArrayList<String>(){{
            add("a");
            add("b");
        }});
        equations.add(new ArrayList<String>(){{
            add("b");
            add("c");
        }});

        queries.add(new ArrayList<String>(){{
            add("a");
            add("c");
        }});
        double[] values = new double[2];
        values[0] = 2.0;
        values[1] = 3.0;
        System.out.println(l.calcEquation(equations, values, queries));
    }
}
