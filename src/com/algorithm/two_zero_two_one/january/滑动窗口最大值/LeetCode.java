package com.algorithm.two_zero_two_one.january.滑动窗口最大值;

import java.util.Comparator;
import java.util.Deque;
import java.util.LinkedList;
import java.util.PriorityQueue;

/**
 * @author Ming
 * @date 2021/1/2 - 21:18
 * @describe
 */
public class LeetCode {

    /**
     * 思路：
     *      使用堆来存储当前最大值
     * 执行用时：83 ms, 在所有 Java 提交中击败了11.21%的用户
     * 内存消耗：59 MB, 在所有 Java 提交中击败了8.92%的用户
     */
    public int[] maxSlidingWindow(int[] nums, int k) {
        int n = nums.length;
        //存储当前信息
        PriorityQueue<int[]> pq = new PriorityQueue<int[]>(new Comparator<int[]>() {
            @Override
            public int compare(int[] pair1, int[] pair2) {
                return pair1[0] != pair2[0] ? pair2[0] - pair1[0] : pair2[1] - pair1[1];
            }
        });
        //先放前k个
        for (int i = 0; i < k; ++i) {
            pq.offer(new int[]{nums[i], i});
        }
        int[] ans = new int[n - k + 1];
        ans[0] = pq.peek()[0];
        for (int i = k; i < n; ++i) {
            //添加新节点
            pq.offer(new int[]{nums[i], i});
            while (pq.peek()[1] <= i - k) {
                pq.poll();
            }
            //赋值新节点
            ans[i - k + 1] = pq.peek()[0];
        }
        return ans;
    }

    /**
     * 思路：保证deque队列中是单调递减的，并且是按照num来排序的，当出现较大的时候需要更新队列
     */
    class Solution {
        public int[] maxSlidingWindow(int[] nums, int k) {
            int n = nums.length;
            Deque<Integer> deque = new LinkedList<Integer>();
            for (int i = 0; i < k; ++i) {
                while (!deque.isEmpty() && nums[i] >= nums[deque.peekLast()]) {
                    deque.pollLast();
                }
                deque.offerLast(i);
            }

            int[] ans = new int[n - k + 1];
            ans[0] = nums[deque.peekFirst()];
            for (int i = k; i < n; ++i) {
                //当出现较大的数时需要遍历更新
                while (!deque.isEmpty() && nums[i] >= nums[deque.peekLast()]) {
                    deque.pollLast();
                }
                //添加到队列尾
                deque.offerLast(i);
                //当最大值需要被淘汰时，更新头部信息
                while (deque.peekFirst() <= i - k) {
                    deque.pollFirst();
                }
                ans[i - k + 1] = nums[deque.peekFirst()];
            }
            return ans;
        }
    }

}
