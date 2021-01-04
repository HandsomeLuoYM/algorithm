package com.algorithm.two_zero_two_zero.october.two_six;

import java.util.*;

/**
 * @author Ming
 * @date 2020/10/26 - 0:27
 * @describe 1365. 有多少小于当前数字的数字
 */
public class LeetCode {

    /**
     * 给你一个数组 nums，对于其中每个元素 nums[i]，请你统计数组中比它小的所有数字的数目。
     *
     * 换而言之，对于每个 nums[i] 你必须计算出有效的 j 的数量，其中 j 满足 j != i 且 nums[j] < nums[i] 。
     *
     * 以数组形式返回答案。
     *
     * 输入：nums = [8,1,2,2,3]
     * 输出：[4,0,1,1,3]
     * 解释：
     * 对于 nums[0]=8 存在四个比它小的数字：（1，2，2 和 3）。
     * 对于 nums[1]=1 不存在比它小的数字。
     * 对于 nums[2]=2 存在一个比它小的数字：（1）。
     * 对于 nums[3]=2 存在一个比它小的数字：（1）。
     * 对于 nums[4]=3 存在三个比它小的数字：（1，2 和 2）。
     *
     * 输入：nums = [6,5,4,8]
     * 输出：[2,1,0,3]
     *
     * 输入：nums = [7,7,7,7]
     * 输出：[0,0,0,0]
     *
     * 2 <= nums.length <= 500
     * 0 <= nums[i] <= 100
     */

    /**
     * 思路：
     *      利用PriorityQueue来从小到大存储出现的数字，利用map来记录他们的个数，最后遍历PriorityQueue来查找从小到大的数字，并且调整map中的数字
     *      最后遍历nums来构造新的数组返回
     * 执行用时：7 ms, 在所有 Java 提交中击败了64.12%的用户
     * 内存消耗：38.4 MB, 在所有 Java 提交中击败了97.00%的用户
     */
    public int[] smallerNumbersThanCurrent(int[] nums) {
        PriorityQueue<Integer> queue = new PriorityQueue(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1 - o2;
            }
        });
        Map<Integer, Integer> map = new HashMap<>();
        int length = nums.length;
        for (int i = 0; i<length; i++){
            if (map.containsKey(nums[i])){
                map.put(nums[i],map.get(nums[i])+1);
            }else {
                map.put(nums[i],1);
                queue.add(nums[i]);
            }
        }
        int lastNumber = 0, last, temp;
        while (!queue.isEmpty()){
            last = queue.poll();
            temp = map.get(last);
            map.put(last,lastNumber);
            lastNumber += temp;
        }
        int[] result = new int[length];
        for (int i = 0; i<length; i++){
            result[i] = map.get(nums[i]);
        }
        return result;
    }

    /**
     * 暴力
     */
    class Solution {
        public int[] smallerNumbersThanCurrent(int[] nums) {
            int n = nums.length;
            int[] ret = new int[n];
            for (int i = 0; i < n; i++) {
                int cnt = 0;
                for (int j = 0; j < n; j++) {
                    if (nums[j] < nums[i]) {
                        cnt++;
                    }
                }
                ret[i] = cnt;
            }
            return ret;
        }
    }

    /**
     * 快速排序
     * 我们也可以将数组排序，并记录每一个数在原数组中的位置。对于排序后的数组中的每一个数，我们找出其左侧第一个小于它的数，这样就能够知道数组中小于该数的数量。
     */
    class Solution1 {
        public int[] smallerNumbersThanCurrent(int[] nums) {
            int n = nums.length;
            int[][] data = new int[n][2];
            for (int i = 0; i < n; i++) {
                data[i][0] = nums[i];
                data[i][1] = i;
            }
            Arrays.sort(data, new Comparator<int[]>() {
                @Override
                public int compare(int[] data1, int[] data2) {
                    return data1[0] - data2[0];
                }
            });

            int[] ret = new int[n];
            int prev = -1;
            for (int i = 0; i < n; i++) {
                if (prev == -1 || data[i][0] != data[i - 1][0]) {
                    prev = i;
                }
                ret[data[i][1]] = prev;
            }
            return ret;
        }
    }

    /**
     * 方法三：计数排序
     * 注意到数组元素的值域为 [0,100][0,100]，所以可以考虑建立一个频次数组 cnt ，
     * cnt[i] 表示数字 i 出现的次数。那么对于数字 i 而言，小于它的数目就为 cnt[0...i−1] 的总和。
     *
     */
    class Solution2 {
        public int[] smallerNumbersThanCurrent(int[] nums) {
            int[] cnt = new int[101];
            int n = nums.length;
            for (int i = 0; i < n; i++) {
                cnt[nums[i]]++;
            }
            for (int i = 1; i <= 100; i++) {
                cnt[i] += cnt[i - 1];
            }
            int[] ret = new int[n];
            for (int i = 0; i < n; i++) {
                ret[i] = nums[i] == 0 ? 0 : cnt[nums[i] - 1];
            }
            return ret;
        }
    }


}
