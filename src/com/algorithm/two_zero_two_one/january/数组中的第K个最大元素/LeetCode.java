package com.algorithm.two_zero_two_one.january.数组中的第K个最大元素;

/**
 * @author Ming
 * @date 2021/1/16 - 21:32
 * @describe
 */
public class LeetCode {
    /**
     * 在未排序的数组中找到第 k 个最大的元素。请注意，你需要找的是数组排序后的第 k 个最大的元素，而不是第 k 个不同的元素。
     *
     * 输入: [3,2,1,5,6,4] 和 k = 2
     * 输出: 5
     *
     * 输入: [3,2,3,1,2,4,5,5,6] 和 k = 4
     * 输出: 4
     */
    /**
     * 思路：冒泡，比较慢
     * 执行用时：43 ms, 在所有 Java 提交中击败了7.03%的用户
     * 内存消耗：38.7 MB, 在所有 Java 提交中击败了73.91%的用户
     */
    public int findKthLargest(int[] nums, int k) {
        int temp, length = nums.length;
        for (int i = 0; i < k; i++) {
            temp = i;
            for (int j = i; j < length; j++) {
                if (nums[j] > nums[temp]) temp = j;
            }
            swag(nums, temp, i);
        }
        return nums[k-1];
    }

    /**
     * 思路：快速查找，快排思想
     * 执行用时：17 ms, 在所有 Java 提交中击败了15.08%的用户
     * 内存消耗：39.2 MB, 在所有 Java 提交中击败了9.41%的用户
     */
    public int findKthLargest1(int[] nums, int k) {
        fastFind(nums, k-1, 0, nums.length-1);
        return nums[k-1];
    }

    private void fastFind(int[] nums, int k, int startIndex, int endIndex){
        int temp = nums[startIndex], end = endIndex, start = startIndex;
        for (int i = startIndex+1; i <= end; i++) {
            if (nums[i] > temp) nums[start++] = nums[i];
            else {
                swag(nums, i, end);
                end--;
                i--;
            }
        }
        nums[start] = temp;
        if (start == k) return;
        else if (start < k) fastFind(nums, k, start+1, endIndex);
        else fastFind(nums, k, startIndex, start-1);
    }

    private void swag(int[] nums, int aIndex, int bIndex){
        int temp = nums[aIndex];
        nums[aIndex] = nums[bIndex];
        nums[bIndex] = temp;
    }

    /**
     * 堆排序
     * 执行用时：2 ms, 在所有 Java 提交中击败了90.28%的用户
     * 内存消耗：38.7 MB, 在所有 Java 提交中击败了66.92%的用户
     */
    class Solution {
        public int findKthLargest(int[] nums, int k) {
            int heapSize = nums.length;
            buildMaxHeap(nums, heapSize);
            for (int i = nums.length - 1; i >= nums.length - k + 1; --i) {
                swap(nums, 0, i);
                --heapSize;
                maxHeapify(nums, 0, heapSize);
            }
            return nums[0];
        }

        public void buildMaxHeap(int[] a, int heapSize) {
            for (int i = heapSize / 2; i >= 0; --i) {
                maxHeapify(a, i, heapSize);
            }
        }

        public void maxHeapify(int[] a, int i, int heapSize) {
            int l = i * 2 + 1, r = i * 2 + 2, largest = i;
            if (l < heapSize && a[l] > a[largest]) {
                largest = l;
            }
            if (r < heapSize && a[r] > a[largest]) {
                largest = r;
            }
            if (largest != i) {
                swap(a, i, largest);
                maxHeapify(a, largest, heapSize);
            }
        }

        public void swap(int[] a, int i, int j) {
            int temp = a[i];
            a[i] = a[j];
            a[j] = temp;
        }
    }
}
