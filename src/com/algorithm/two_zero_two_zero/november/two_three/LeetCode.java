package com.algorithm.two_zero_two_zero.november.two_three;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @author Ming
 * @date 2020/11/23 - 22:39
 * @describe
 */
public class LeetCode {

    /**
     * 在二维空间中有许多球形的气球。对于每个气球，提供的输入是水平方向上，气球直径的开始和结束坐标。
     * 由于它是水平的，所以纵坐标并不重要，因此只要知道开始和结束的横坐标就足够了。开始坐标总是小于结束坐标。
     *
     * 一支弓箭可以沿着 x 轴从不同点完全垂直地射出。在坐标 x 处射出一支箭，
     * 若有一个气球的直径的开始和结束坐标为 xstart，xend，
     * 且满足  xstart ≤ x ≤ xend，则该气球会被引爆。可以射出的弓箭的数量没有限制。
     * 弓箭一旦被射出之后，可以无限地前进。我们想找到使得所有气球全部被引爆，所需的弓箭的最小数量。
     *
     * 给你一个数组 points ，其中 points [i] = [xstart,xend] ，返回引爆所有气球所必须射出的最小弓箭数。
     *
     * 输入：points = [[10,16],[2,8],[1,6],[7,12]]
     * 输出：2
     * 解释：对于该样例，x = 6 可以射爆 [2,8],[1,6] 两个气球，以及 x = 11 射爆另外两个气球
     *
     * 输入：points = [[1,2],[3,4],[5,6],[7,8]]
     * 输出：4
     *
     * 输入：points = [[1,2],[2,3],[3,4],[4,5]]
     * 输出：2
     *
     * 输入：points = [[1,2]]
     * 输出：1
     */
    public int findMinArrowShots(int[][] points) {
        if (points.length == 0) {
            return 0;
        }
        //预排序
        Arrays.sort(points, new Comparator<int[]>() {
            @Override
            public int compare(int[] point1, int[] point2) {
                if (point1[1] > point2[1]) {
                    return 1;
                } else if (point1[1] < point2[1]) {
                    return -1;
                } else {
                    return 0;
                }
            }
        });
        int pos = points[0][1];
        int ans = 1;
        for (int[] balloon: points) {
            if (balloon[0] > pos) {
                pos = balloon[1];
                ++ans;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int[][] points = new int[][]{
                {10,16},{2,8},{1,6},{7,12},{5,7}
        };
        LeetCode l = new LeetCode();
        l.findMinArrowShots(points);
    }

}
