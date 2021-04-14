package com.algorithm.two_zero_two_one.february.数据流中的第K大元素;

import java.util.PriorityQueue;

/**
 * @author Ming
 * @date 2021/2/11 - 1:49
 * @describe
 */
public class LeetCode {
    /**
     * 设计一个找到数据流中第 k 大元素的类（class）。注意是排序后的第 k 大元素，不是第 k 个不同的元素。
     *
     * 请实现 KthLargest 类：
     *
     * KthLargest(int k, int[] nums) 使用整数 k 和整数流 nums 初始化对象。
     * int add(int val) 将 val 插入数据流 nums 后，返回当前数据流中第 k 大的元素。
     *  
     *
     * 示例：
     *
     * 输入：
     * ["KthLargest", "add", "add", "add", "add", "add"]
     * [[3, [4, 5, 8, 2]], [3], [5], [10], [9], [4]]
     * 输出：
     * [null, 4, 5, 5, 8, 8]
     *
     * 解释：
     * KthLargest kthLargest = new KthLargest(3, [4, 5, 8, 2]);
     * kthLargest.add(3);   // return 4
     * kthLargest.add(5);   // return 5
     * kthLargest.add(10);  // return 5
     * kthLargest.add(9);   // return 8
     * kthLargest.add(4);   // return 8
     *
     */
    /**
     * 思路：堆排序
     * 执行用时：18 ms, 在所有 Java 提交中击败了82.19%的用户
     * 内存消耗：43.8 MB, 在所有 Java 提交中击败了27.91%的用户
     */
    class KthLargest {
        PriorityQueue<Integer> queue;
        int k;
        public KthLargest(int k, int[] nums) {
            queue = new PriorityQueue<Integer>((x1, x2) -> {
                return x1 - x2;
            });
            this.k = k;
            for (int i = 0; i < nums.length && i < k; i++){
                queue.add(nums[i]);
            }
            for (int i = k; i < nums.length; i++) {
                if (queue.peek() < nums[i]) {
                    queue.poll();
                    queue.add(nums[i]);
                }
            }
        }

        public int add(int val) {
            if (queue.size() == k && queue.peek() < val) {
                queue.poll();
                queue.add(val);
            }
            if (queue.size() < k) queue.add(val);
            return queue.peek();
        }
    }

/**
 * Your KthLargest object will be instantiated and called as such:
 * KthLargest obj = new KthLargest(k, nums);
 * int param_1 = obj.add(val);
 */

}
