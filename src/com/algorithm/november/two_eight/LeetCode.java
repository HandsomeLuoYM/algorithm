package com.algorithm.november.two_eight;

/**
 * @author Ming
 * @date 2020/11/28 - 14:52
 * @describe 493 翻转对
 */
public class LeetCode {
    /**
     * 给定一个数组 nums ，如果 i < j 且 nums[i] > 2*nums[j] 我们就将 (i, j) 称作一个重要翻转对。
     * 你需要返回给定数组中的重要翻转对的数量。
     *
     * 输入: [1,3,2,3,1]
     * 输出: 2
     *
     * 输入: [2,4,3,5,1]
     * 输出: 3
     */
    /**
     * 暴力解法：超时
     */
    public int reversePairs(int[] nums) {
        int length = nums.length;
        long[] doubleNumber = new long[length];
        for (int i = 0; i < length; i++) {
            doubleNumber[i] = (long) (nums[i]) * 2;
        }
        int result = 0;
        for (int i = 0; i < length; i++) {
            for (int j = i + 1; j < length; j++) {
                if (doubleNumber[j] < nums[i])
                    result++;
            }
        }
        return result;
    }

    /**
     * 归并排序思想
     * 执行用时：60 ms, 在所有 Java 提交中击败了59.85%的用户
     * 内存消耗：47.6 MB, 在所有 Java 提交中击败了77.55%的用户
     */
    int result = 0;
    public int reversePairs1(int[] nums) {
        if (nums.length==0) return 0;
        gb(nums,0,nums.length-1);
        return result;
    }

    private int[] gb(int[] nums, int start, int end) {
        if (Math.abs(start - end) == 0) return new int[]{nums[start]};
        int mid = (end - start) / 2 + start;
        int[] gb2 = gb(nums, mid+1, end);
        int[] gb1 = gb(nums, start, mid);

        //添加数量
        int i = 0;
        int j = 0;
        while (i < gb1.length) {
            while (j < gb2.length && (long) gb1[i] > 2 * (long) gb2[j]) {
                j++;
            }
            result += j;
            i++;
        }
        //合并
        int[] newNums = new int[end-start+1];
        int p1 = 0, p2 = 0, now = 0;
        while (p1 < gb1.length && p2 < gb2.length) {
            if (gb1[p1]<gb2[p2]){
                newNums[now++] = gb1[p1++];
            }else {
                newNums[now++] = gb2[p2++];
            }
        }
        while (p1<gb1.length){
            newNums[now++] = gb1[p1++];
        }
        while (p2<gb2.length){
            newNums[now++] = gb2[p2++];
        }
        //返回
        return newNums;
    }

    public int reversePairsRecursive(int[] nums, int left, int right) {
        if (left == right) {
            return 0;
        } else {
            int mid = (left + right) / 2;
            //求左边的个数
            int n1 = reversePairsRecursive(nums, left, mid);
            //求右边的个数
            int n2 = reversePairsRecursive(nums, mid + 1, right);
            //左右两部分的和
            int ret = n1 + n2;

            // 首先统计下标对的数量
            int i = left;
            int j = mid + 1;
            while (i <= mid) {
                while (j <= right && (long) nums[i] > 2 * (long) nums[j]) {
                    j++;
                }
                ret += j - mid - 1;
                i++;
            }

            // 随后合并两个排序数组
            //设置一个临时的数组
            int[] sorted = new int[right - left + 1];
            int p1 = left, p2 = mid + 1;
            int p = 0;
            while (p1 <= mid || p2 <= right) {
                if (p1 > mid) {
                    sorted[p++] = nums[p2++];
                } else if (p2 > right) {
                    sorted[p++] = nums[p1++];
                } else {
                    if (nums[p1] < nums[p2]) {
                        sorted[p++] = nums[p1++];
                    } else {
                        sorted[p++] = nums[p2++];
                    }
                }
            }
            for (int k = 0; k < sorted.length; k++) {
                nums[left + k] = sorted[k];
            }
            return ret;
        }
    }

    public static void main(String[] args) {
        LeetCode l = new LeetCode();
        System.out.println(l.reversePairs1(new int[]{2,4,3,5,1}));
    }
}
