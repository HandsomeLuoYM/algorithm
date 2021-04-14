package com.algorithm.two_zero_two_one.february.合并两个有序数组;

/**
 * @author Ming
 * @date 2021/2/11 - 1:34
 * @describe
 */
public class LeetCode {
    /**
     * 给你两个有序整数数组 nums1 和 nums2，请你将 nums2 合并到 nums1 中，使 nums1 成为一个有序数组。
     *
     * 初始化 nums1 和 nums2 的元素数量分别为 m 和 n 。你可以假设 nums1 的空间大小等于 m + n，这样它就有足够的空间保存来自 nums2 的元素。
     *
     *输入：nums1 = [1,2,3,0,0,0], m = 3, nums2 = [2,5,6], n = 3
     * 输出：[1,2,2,3,5,6]
     *
     * 输入：nums1 = [1], m = 1, nums2 = [], n = 0
     * 输出：[1]
     */
    /**
     * 思路：
     *      从后往前遍历
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
     * 内存消耗：38.4 MB, 在所有 Java 提交中击败了85.15%的用户
     */
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int n1 = m - 1, n2 = n - 1, all = m + n - 1;
        for (int i = 0; n1 >= 0 && n2 >= 0 && i <= all; i++) {
            if(nums1[n1] > nums2[n2]) {
                nums1[all - i] = nums1[n1--];
            }else {
                nums1[all - i] = nums2[n2--];
            }
        }
    }

    /**
     * 从前往后
     */
    class Solution {
        public void merge(int[] nums1, int m, int[] nums2, int n) {
            // Make a copy of nums1.
            int [] nums1_copy = new int[m];
            System.arraycopy(nums1, 0, nums1_copy, 0, m);

            // Two get pointers for nums1_copy and nums2.
            int p1 = 0;
            int p2 = 0;

            // Set pointer for nums1
            int p = 0;

            // Compare elements from nums1_copy and nums2
            // and add the smallest one into nums1.
            while ((p1 < m) && (p2 < n))
                nums1[p++] = (nums1_copy[p1] < nums2[p2]) ? nums1_copy[p1++] : nums2[p2++];

            // if there are still elements to add
            if (p1 < m)
                System.arraycopy(nums1_copy, p1, nums1, p1 + p2, m + n - p1 - p2);
            if (p2 < n)
                System.arraycopy(nums2, p2, nums1, p1 + p2, m + n - p1 - p2);
        }
    }

}
