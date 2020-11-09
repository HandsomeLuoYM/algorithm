package com.algorithm.november.zero_nine;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Random;

/**
 * @author Ming
 * @date 2020/11/9 - 1:50
 * @describe 973. 最接近原点的 K 个点
 */
public class LeetCode {

    /**
     * 我们有一个由平面上的点组成的列表 points。需要从中找出 K 个距离原点 (0, 0) 最近的点。
     *
     * （这里，平面上两点之间的距离是欧几里德距离。）
     *
     * 你可以按任何顺序返回答案。除了点坐标的顺序之外，答案确保是唯一的。
     *
     * 输入：points = [[1,3],[-2,2]], K = 1
     * 输出：[[-2,2]]
     * 解释：
     * (1, 3) 和原点之间的距离为 sqrt(10)，
     * (-2, 2) 和原点之间的距离为 sqrt(8)，
     * 由于 sqrt(8) < sqrt(10)，(-2, 2) 离原点更近。
     * 我们只需要距离原点最近的 K = 1 个点，所以答案就是 [[-2,2]]。
     *
     * 输入：points = [[3,3],[5,-1],[-2,4]], K = 2
     * 输出：[[3,3],[-2,4]]
     * （答案 [[-2,4],[3,3]] 也会被接受。）
     *
     * 1 <= K <= points.length <= 10000
     * -10000 < points[i][0] < 10000
     * -10000 < points[i][1] < 10000
     *
     */
    /**
     * 自己的思路：暴力法排序
     * 执行用时：28 ms, 在所有 Java 提交中击败了65.59%的用户
     * 内存消耗：46.8 MB, 在所有 Java 提交中击败了87.99%的用户
     */
    public int[][] kClosest(int[][] points, int K) {
        Arrays.sort(points, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return (o1[0] * o1[0] + o1[1] * o1[1]) - (o2[0] * o2[0] + o2[1] * o2[1]);
            }
        });
        return Arrays.copyOfRange(points, 0, K);
    }

    /**
     * 官方题解二：
     *      构造一个数组，第一个元素存放距离，第二个节点存放索引，然后排序交给比较器处理
     */
    class Solution {
        public int[][] kClosest(int[][] points, int K) {
            PriorityQueue<int[]> pq = new PriorityQueue<int[]>(new Comparator<int[]>() {
                @Override
                public int compare(int[] array1, int[] array2) {
                    return array2[0] - array1[0];
                }
            });
            for (int i = 0; i < K; ++i) {
                pq.offer(new int[]{points[i][0] * points[i][0] + points[i][1] * points[i][1], i});
            }
            int n = points.length;
            for (int i = K; i < n; ++i) {
                int dist = points[i][0] * points[i][0] + points[i][1] * points[i][1];
                if (dist < pq.peek()[0]) {
                    pq.poll();
                    pq.offer(new int[]{dist, i});
                }
            }
            int[][] ans = new int[K][2];
            for (int i = 0; i < K; ++i) {
                ans[i] = points[pq.poll()[1]];
            }
            return ans;
        }
    }

    /**
     * 快排思想排序，时间复杂度为 n
     *      快排思想。比较加入序列号
     */
    class Solution1 {
        Random rand = new Random();

        public int[][] kClosest(int[][] points, int K) {
            int n = points.length;
            random_select(points, 0, n - 1, K);
            return Arrays.copyOfRange(points, 0, K);
        }

        public void random_select(int[][] points, int left, int right, int K) {
            int pivotId = left + rand.nextInt(right - left + 1);
            int pivot = points[pivotId][0] * points[pivotId][0] + points[pivotId][1] * points[pivotId][1];
            swap(points, right, pivotId);
            int i = left - 1;
            for (int j = left; j < right; ++j) {
                int dist = points[j][0] * points[j][0] + points[j][1] * points[j][1];
                if (dist <= pivot) {
                    ++i;
                    swap(points, i, j);
                }
            }
            ++i;
            swap(points, i, right);
            // [left, i-1] 都小于等于 pivot, [i+1, right] 都大于 pivot
            if (K < i - left + 1) {
                random_select(points, left, i - 1, K);
            } else if (K > i - left + 1) {
                random_select(points, i + 1, right, K - (i - left + 1));
            }
        }

        public void swap(int[][] points, int index1, int index2) {
            int[] temp = points[index1];
            points[index1] = points[index2];
            points[index2] = temp;
        }
    }

}
