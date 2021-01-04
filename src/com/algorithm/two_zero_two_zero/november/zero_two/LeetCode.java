package com.algorithm.two_zero_two_zero.november.zero_two;

import java.util.*;

/**
 * @author Ming
 * @date 2020/11/2 - 0:22
 * @describe 349. 两个数组的交集
 */
public class LeetCode {

    /**
     * 给定两个数组，编写一个函数来计算它们的交集。
     *
     * 输入：nums1 = [1,2,2,1], nums2 = [2,2]
     * 输出：[2]
     *
     * 输入：nums1 = [4,9,5], nums2 = [9,4,9,8,4]
     * 输出：[9,4]
     */
    /**
     * 自己的做法：
     *      遍历其中一个，然后放到set中，在遍历另一个将一样的存到set中，最后将相交的转成数组
     * 执行用时：3 ms, 在所有 Java 提交中击败了95.89%的用户
     * 内存消耗：38.5 MB, 在所有 Java 提交中击败了91.39%的用户
     */
    public int[] intersection(int[] nums1, int[] nums2) {
        int length1 = nums1.length,length2 = nums2.length;
        Set<Integer> set1 = new HashSet<Integer>();
        for(int i = 0; i<length1; i++){
            set1.add(nums1[i]);
        }
        Set<Integer> set = new HashSet<>();
        for(int i = 0; i<length2; i++){
            if(set1.contains(nums2[i])){
                set.add(nums2[i]);
            }
        }
        int[] result = new int[set.size()];
        int index = 0;
        Iterator<Integer> iterator = set.iterator();
        while (iterator.hasNext()){
            result[index++] = iterator.next();
        }
        return result;
    }

    /**
     * 官方题解：先排序再用双指针来判断
     * 执行用时：1 ms, 在所有 Java 提交中击败了99.97%的用户
     * 内存消耗：38.6 MB, 在所有 Java 提交中击败了89.30%的用户
     */
    class Solution {
        public int[] intersection(int[] nums1, int[] nums2) {
            Arrays.sort(nums1);
            Arrays.sort(nums2);
            int length1 = nums1.length, length2 = nums2.length;
            int[] intersection = new int[Math.max(length1,length2)];
            int index = 0, index1 = 0, index2 = 0;
            while (index1 < length1 && index2 < length2) {
                int num1 = nums1[index1], num2 = nums2[index2];
                if (num1 == num2) {
                    // 保证加入元素的唯一性
                    if (index == 0 || num1 != intersection[index - 1]) {
                        intersection[index++] = num1;
                    }
                    index1++;
                    index2++;
                } else if (num1 < num2) {
                    index1++;
                } else {
                    index2++;
                }
            }
            return Arrays.copyOfRange(intersection, 0, index);
        }
    }

}
