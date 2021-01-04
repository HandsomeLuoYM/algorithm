package com.algorithm.two_zero_two_zero.december.最后一块石头的重量;

import java.util.PriorityQueue;

/**
 * @author Ming
 * @date 2020/12/30 - 0:06
 * @describe 106
 */
public class LeetCode {
    /**
     * 有一堆石头，每块石头的重量都是正整数。
     *
     * 每一回合，从中选出两块 最重的 石头，然后将它们一起粉碎。假设石头的重量分别为 x 和 y，且 x <= y。那么粉碎的可能结果如下：
     *
     * 如果 x == y，那么两块石头都会被完全粉碎；
     * 如果 x != y，那么重量为 x 的石头将会完全粉碎，而重量为 y 的石头新重量为 y-x。
     * 最后，最多只会剩下一块石头。返回此石头的重量。如果没有石头剩下，就返回 0。
     *
     * 输入：[2,7,4,1,8,1]
     * 输出：1
     * 解释：
     * 先选出 7 和 8，得到 1，所以数组转换为 [2,4,1,1,1]，
     * 再选出 2 和 4，得到 2，所以数组转换为 [2,1,1,1]，
     * 接着是 2 和 1，得到 1，所以数组转换为 [1,1,1]，
     * 最后选出 1 和 1，得到 0，最终数组转换为 [1]，这就是最后剩下那块石头的重量。
     *
     */
    /**
     * 自己的思路：
     *      排序的队列来存储（最大堆）
     * 执行用时：2 ms, 在所有 Java 提交中击败了40.89%的用户
     * 内存消耗：35.6 MB, 在所有 Java 提交中击败了87.23%的用户
     */
    public int lastStoneWeight(int[] stones) {
        PriorityQueue<Integer> queue = new PriorityQueue<Integer>( (x1, x2) -> {
            return x2 - x1;
        });
        int length = stones.length;
        for (int i = 0; i < length; i++){
            queue.add(stones[i]);
        }
        while (queue.size() > 1){
            queue.add(Math.abs(queue.poll() - queue.poll()));
        }
        return queue.size() == 0 ? 0 : queue.poll();
    }

}
