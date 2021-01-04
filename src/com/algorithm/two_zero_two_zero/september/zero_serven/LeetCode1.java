package com.algorithm.two_zero_two_zero.september.zero_serven;

import java.util.*;

/**
 * @author Ming
 * @date 2020/9/7 - 20:24
 * @describe
 */
public class LeetCode1 {

    /**
     * 自己的解法
     * 执行用时：15 ms, 在所有 Java 提交中击败了87.76%的用户
     * 内存消耗：42.6 MB, 在所有 Java 提交中击败了13.15%的用户
     */
    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer,Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        // int[] 的第一个元素代表数组的值，第二个元素代表了该值出现的次数
        PriorityQueue<int[]> queue = new PriorityQueue<int[]>(new Comparator<int[]>() {
            @Override
            public int compare(int[] m, int[] n) {
                return m[1] - n[1];
            }
        });
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            int num = entry.getKey(), count = entry.getValue();
            if (queue.size() == k) {
                if (queue.peek()[1] < count) {
                    queue.poll();
                    queue.offer(new int[]{num, count});
                }
            } else {
                queue.offer(new int[]{num, count});
            }
        }
        int[] ret = new int[k];
        for (int i = 0; i < k; ++i) {
            ret[i] = queue.poll()[0];
        }
        return ret;

    }

}
