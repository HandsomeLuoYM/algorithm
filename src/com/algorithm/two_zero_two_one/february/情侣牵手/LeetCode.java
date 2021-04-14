package com.algorithm.two_zero_two_one.february.情侣牵手;

import java.lang.reflect.Array;
import java.util.*;

/**
 * @author Ming
 * @date 2021/2/15 - 13:13
 * @describe
 */
public class LeetCode {

    /**
     * N 对情侣坐在连续排列的 2N 个座位上，想要牵到对方的手。 计算最少交换座位的次数，以便每对情侣可以并肩坐在一起。 一次交换可选择任意两人，让他们站起来交换座位。
     *
     * 人和座位用 0 到 2N-1 的整数表示，情侣们按顺序编号，第一对是 (0, 1)，第二对是 (2, 3)，以此类推，最后一对是 (2N-2, 2N-1)。
     *
     * 这些情侣的初始座位  row[i] 是由最初始坐在第 i 个座位上的人决定的。
     *
     * 示例 1:
     *
     * 输入: row = [0, 2, 1, 3]
     * 输出: 1
     * 解释: 我们只需要交换row[1]和row[2]的位置即可。
     * 示例 2:
     *
     * 输入: row = [3, 2, 0, 1]
     * 输出: 0
     * 解释: 无需交换座位，所有的情侣都已经可以手牵手了。
     * 说明:
     *
     * len(row) 是偶数且数值在 [4, 60]范围内。
     * 可以保证row 是序列 0...len(row)-1 的一个全排列。
     *
     */
    /**
     * 思路：并查集，记录每个数组中索引以及所属的集合
     * 执行用时：1 ms, 在所有 Java 提交中击败了26.79%的用户
     * 内存消耗：35.8 MB, 在所有 Java 提交中击败了72.95%的用户
     */
    public int minSwapsCouples(int[] row) {
        int length = row.length, num1, num2;
        int[] index = new int[length / 2];
        Arrays.fill(index, -1);
        for (int i = 0; i < length / 2; i++) {
            num1 = row[i * 2] / 2;
            num2 = row[i * 2 + 1] / 2;
            if (num1 != num2) {
                if (index[num1] == -1 && index[num2] == -1) {
                    index[num1] = num1;
                    index[num2] = num1;
                }else if (index[num1] != -1 && index[num2] != -1) {
                    change(index, index[num2], index[num1]);
                }else {
                    if (index[num1] != -1) {
                        index[num2] = index[num1];
                    }else {
                        index[num1] = index[num2];
                    }
                }
            }
        }
        int all = 0;
        Set<Integer> set = new HashSet<Integer>();
        for (int i = 0; i < index.length; i++) {
            if (index[i] != -1 && !set.contains(index[i])) {
                set.add(index[i]);
                all++;
            }else if (index[i] != -1 && set.contains(index[i])) {
                all++;
            }
        }
        return all - set.size();
    }

    private void change(int[] index, int before, int after) {
        for (int i = 0; i < index.length; i++) {
            if (index[i] == before) {
                index[i] = after;
            }
        }
    }


    class Solution {
        public int minSwapsCouples(int[] row) {
            int n = row.length;
            int tot = n / 2;

            List<Integer>[] graph = new List[tot];
            for (int i = 0; i < tot; i++) {
                graph[i] = new ArrayList<Integer>();
            }
            for (int i = 0; i < n; i += 2) {
                int l = row[i] / 2;
                int r = row[i + 1] / 2;
                if (l != r) {
                    graph[l].add(r);
                    graph[r].add(l);
                }
            }
            boolean[] visited = new boolean[tot];
            int ret = 0;
            for (int i = 0; i < tot; i++) {
                if (!visited[i]) {
                    Queue<Integer> queue = new LinkedList<Integer>();
                    visited[i] = true;
                    queue.offer(i);
                    int cnt = 0;

                    while (!queue.isEmpty()) {
                        int x = queue.poll();
                        cnt += 1;

                        for (int y : graph[x]) {
                            if (!visited[y]) {
                                visited[y] = true;
                                queue.offer(y);
                            }
                        }
                    }
                    ret += cnt - 1;
                }
            }
            return ret;
        }
    }

}
