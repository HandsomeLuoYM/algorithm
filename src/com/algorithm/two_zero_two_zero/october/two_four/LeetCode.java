package com.algorithm.two_zero_two_zero.october.two_four;

import java.util.Arrays;

/**
 * @author Ming
 * @date 2020/10/24 - 1:34
 * @describe
 */
public class LeetCode {

    /**
     * 你将会获得一系列视频片段，这些片段来自于一项持续时长为 T 秒的体育赛事。这些片段可能有所重叠，也可能长度不一。
     *
     * 视频片段 clips[i] 都用区间进行表示：开始于 clips[i][0] 并于 clips[i][1] 结束。
     * 我们甚至可以对这些片段自由地再剪辑，例如片段 [0, 7] 可以剪切成 [0, 1] + [1, 3] + [3, 7] 三部分。
     *
     * 我们需要将这些片段进行再剪辑，并将剪辑后的内容拼接成覆盖整个运动过程的片段（[0, T]）。
     * 返回所需片段的最小数目，如果无法完成该任务，则返回 -1 。
     *
     * 输入：clips = [[0,2],[4,6],[8,10],[1,9],[1,5],[5,9]], T = 10
     * 输出：3
     * 解释：
     * 我们选中 [0,2], [8,10], [1,9] 这三个片段。
     * 然后，按下面的方案重制比赛片段：
     * 将 [1,9] 再剪辑为 [1,2] + [2,8] + [8,9] 。
     * 现在我们手上有 [0,2] + [2,8] + [8,10]，而这些涵盖了整场比赛 [0, 10]。
     *
     * 输入：clips = [[0,1],[6,8],[0,2],[5,6],[0,4],[0,3],[6,7],[1,3],[4,7],[1,4],[2,5],[2,6],[3,4],[4,5],[5,7],[6,9]], T = 9
     * 输出：3
     * 解释：
     * 我们选取片段 [0,4], [4,7] 和 [6,9] 。
     *
     * 输入：clips = [[0,4],[2,8]], T = 5
     * 输出：2
     * 解释：
     * 注意，你可能录制超过比赛结束时间的视频。
     */
    /**
     * 自己的做法：
     *      先排序，然后利用贪心思想，顺序求到达每个点最近的次数
     * 执行用时：1 ms, 在所有 Java 提交中击败了46.14%的用户
     * 内存消耗：36 MB, 在所有 Java 提交中击败了93.44%的用户
     */
    public int videoStitching(int[][] clips, int T) {
        int[] minNumber = new int[T];
        Arrays.fill(minNumber,100);
        int length = clips.length;
        //排序
        for (int i = 0; i<length; i++){
            int min = i;
            for (int j = i; j < length; j++){
                if (clips[min][0]>clips[j][0]) min = j;
            }
            int[] temp = new int[2];
            temp[0] = clips[min][0];
            temp[1] = clips[min][1];
            clips[min][0] = clips[i][0];
            clips[min][1] = clips[i][1];
            clips[i][0] = temp[0];
            clips[i][1] = temp[1];
        }
        if (clips[0][0]!=0) return -1;
        int min ;
        for (int i =0; i<length; i++){
            if (clips[i][0]>T) break;
            if (clips[i][0] != 0)
                min = minNumber[clips[i][0]-1] + 1;
            else
                min = 1;
            for (int j = clips[i][0]; j<clips[i][1] && j<T; j++){
                if (minNumber[j] > min) minNumber[j] = min;
            }
        }
        return minNumber[T-1]==100?-1:minNumber[T-1];
    }

    /**
     * 官方题解一(动态规划)：
     * 比较容易想到的方法是动态规划，我们令dp[i] 表示将区间 [0,i) 覆盖所需的最少子区间的数量。
     * 由于我们希望子区间的数目尽可能少，因此可以将所有 dp[i] 的初始值设为一个大整数，并将dp[0]（即空区间）的初始值设为 0。
     *
     */
    class Solution {
        public int videoStitching(int[][] clips, int T) {
            int[] dp = new int[T + 1];
            Arrays.fill(dp, Integer.MAX_VALUE - 1);
            dp[0] = 0;
            for (int i = 1; i <= T; i++) {
                for (int[] clip : clips) {
                    if (clip[0] < i && i <= clip[1]) {
                        dp[i] = Math.min(dp[i], dp[clip[0]] + 1);
                    }
                }
            }
            return dp[T] == Integer.MAX_VALUE - 1 ? -1 : dp[T];
        }
    }

    /**
     * 贪心
     */
    class Solution1 {
        public int videoStitching(int[][] clips, int T) {
            int[] maxn = new int[T];
            for (int[] clip : clips) {
                if (clip[0] < T) {
                    maxn[clip[0]] = Math.max(maxn[clip[0]], clip[1]);
                }
            }
            int last = 0, ret = 0, pre = 0;
            for (int i = 0; i < T; i++) {
                last = Math.max(last, maxn[i]);
                if (i == last) {
                    return -1;
                }
                if (i == pre) {
                    ret++;
                    pre = last;
                }
            }
            return ret;
        }
    }

}
