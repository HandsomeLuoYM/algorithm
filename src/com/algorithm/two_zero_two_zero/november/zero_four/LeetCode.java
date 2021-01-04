package com.algorithm.two_zero_two_zero.november.zero_four;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Ming
 * @date 2020/11/4 - 0:47
 * @describe 57. 插入区间
 */
public class LeetCode {
    /**
     * 给出一个无重叠的 ，按照区间起始端点排序的区间列表。
     * 在列表中插入一个新的区间，你需要确保列表中的区间仍然有序且不重叠（如果有必要的话，可以合并区间）。
     *
     * 输入：intervals = [[1,3],[6,9]], newInterval = [2,5]
     * 输出：[[1,5],[6,9]]
     *
     * 输入：intervals = [[1,2],[3,5],[6,7],[8,10],[12,16]], newInterval = [4,8]
     * 输出：[[1,2],[3,10],[12,16]]
     * 解释：这是因为新的区间 [4,8] 与 [3,5],[6,7],[8,10] 重叠。
     *
     */
    /**
     * 自己的做法：
     *      分情况判断修改，先排除掉最前面和最后面，然后再遍历数组，判断
     *      插入：在两个数组中间
     *      覆盖：新数组的末尾小于当前位置的尾巴（不管前后的情况，我们是向后判断的，无需考虑前面位置），只需要修改intervals[index][0]位置即可
     *      修改：找到开始和结束位置，然后修改
     *
     * 执行用时：1 ms, 在所有 Java 提交中击败了99.65%的用户
     * 内存消耗：40.6 MB, 在所有 Java 提交中击败了87.77%的用户
     */
    public int[][] insert(int[][] intervals, int[] newInterval) {

        int length = intervals.length, start = newInterval[0], end = newInterval[1], startIndex, endIndex = intervals.length-1;
        //排除特殊情况
        if (length == 0) return new int[][]{{newInterval[0],newInterval[1]}};
        if (start<intervals[0][0] && end < intervals[0][0]){//插入第一个并返回
            int[][] result = new int[length + 1][2];
            result[0][0] = start;
            result[0][1] = end;
            for (int i = 1; i<length+1; i++){
                result[i][0] = intervals[i-1][0];
                result[i][1] = intervals[i-1][1];
            }
            return result;
        }
        if (start>intervals[length-1][0] && start > intervals[length-1][1]){//插入最后一个并返回
            int[][] result = new int[length + 1][2];
            for (int i = 0; i<length; i++){
                result[i][0] = intervals[i][0];
                result[i][1] = intervals[i][1];
            }
            result[length][0] = start;
            result[length][1] = end;
            return result;
        }
        //正常情况处理
        for (int i = 0; i<length; i++){
            if (intervals[i][0] < start && intervals[i][1] < start) continue;//；两个都比start小，继续遍历
            if (intervals[i][0] > start && intervals[i][0] > end) { //在 i 位置插入这个，并返回
                int[][] result = new int[length + 1][2];
                for (int j = 0; j<i; j++){
                    result[j][0] = intervals[j][0];
                    result[j][1] = intervals[j][1];
                }
                result[i][0] = start;
                result[i][1] = end;
                for (int j = i+1; j<length+1; j++){
                    result[j][0] = intervals[j-1][0];
                    result[j][1] = intervals[j-1][1];
                }
                return result;
            }
            if ( intervals[i][1] >= end) {//修改并返回
                intervals[i][0] = Math.min(start,intervals[i][0]);
                return intervals;
            }
            if (intervals[i][0] <= start && intervals[i][1] <= end || intervals[i][0] >= start && intervals[i][1] <= end){//找到结束地方并且修改返回
                startIndex = i;
                start = Math.min(intervals[i][0],start);
                for (int j = i; j<length; j++){
                    if (end < intervals[j][0]){
                        endIndex = j-1;
                        break;
                    }
                    if (end >= intervals[j][0] && end <= intervals[j][1]) {
                        endIndex = j;
                        end = intervals[j][1];
                        break;
                    }
                }
                int index = 0;
                int[][] result = new int[length - (endIndex - startIndex)][2];
                for (; index < startIndex; index++){
                    result[index][0] = intervals[index][0];
                    result[index][1] = intervals[index][1];
                }
                result[index][0] = start;
                result[index][1] = end;
                index++;
                for (; index < result.length; index++){
                    result[index][0] = intervals[index + (endIndex - startIndex)][0];
                    result[index][1] = intervals[index + (endIndex - startIndex)][1];
                }
                return result;
            }
        }
        return null;
    }

    /**
     * 官方题解
     */
    class Solution {
        public int[][] insert(int[][] intervals, int[] newInterval) {
            int left = newInterval[0];
            int right = newInterval[1];
            boolean placed = false;
            List<int[]> ansList = new ArrayList<int[]>();
            for (int[] interval : intervals) {
                if (interval[0] > right) {
                    // 在插入区间的右侧且无交集
                    if (!placed) {
                        ansList.add(new int[]{left, right});
                        placed = true;
                    }
                    ansList.add(interval);
                } else if (interval[1] < left) {
                    // 在插入区间的左侧且无交集
                    ansList.add(interval);
                } else {
                    // 与插入区间有交集，计算它们的并集
                    left = Math.min(left, interval[0]);
                    right = Math.max(right, interval[1]);
                }
            }
            if (!placed) {
                ansList.add(new int[]{left, right});
            }
            int[][] ans = new int[ansList.size()][2];
            for (int i = 0; i < ansList.size(); ++i) {
                ans[i] = ansList.get(i);
            }
            return ans;
        }
    }

}
