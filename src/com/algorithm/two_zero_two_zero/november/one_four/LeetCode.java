package com.algorithm.two_zero_two_zero.november.one_four;

import java.util.*;

/**
 * @author Ming
 * @date 2020/11/14 - 10:22
 * @describe
 */
public class LeetCode {

    /**
     * 给你两个数组，arr1 和 arr2，
     *
     * arr2 中的元素各不相同
     * arr2 中的每个元素都出现在 arr1 中
     * 对 arr1 中的元素进行排序，使 arr1 中项的相对顺序和 arr2 中的相对顺序相同。未在 arr2 中出现过的元素需要按照升序放在 arr1 的末尾。
     *
     * 输入：arr1 = [2,3,1,3,2,4,6,7,9,2,19], arr2 = [2,1,4,3,9,6]
     * 输出：[2,2,2,1,4,3,3,9,6,7,19]
     *
     *      arr1.length, arr2.length <= 1000
     *      0 <= arr1[i], arr2[i] <= 1000
     *      arr2 中的元素 arr2[i] 各不相同
     *      arr2 中的每个元素 arr2[i] 都出现在 arr1 中
     */
    public int[] relativeSortArray(int[] arr1, int[] arr2) {
        int index1 =0, index2 = 0;
        int[] arr = new int[arr1.length];
        Map<Integer,Integer> map = new HashMap<>(arr1.length);
        for (int i : arr1) {
            map.put(i,map.getOrDefault(i , 0)+1);
        }
        Set<Integer> set = new HashSet<>();
        for (int i : arr2) {
            set.add(i);
        }
        int length1 = arr1.length, length2 = arr2.length, index = 0;
        Set<Integer> exist = new HashSet<>(), last = new HashSet<>();
        while (index1 < length1 && index2 < length2){
            if (exist.contains(arr1[index1])){
                index1++;
                continue;
            }
            if (!map.containsKey(arr2[index2])){
                index2++;
                continue;
            }
            if (last.contains(arr2[index2])) {
                int number = map.get(arr2[index2]);
                while (number>0){
                    arr[index++] = arr2[index2];
                    number--;
                }
                exist.add(arr2[index2]);
                last.remove(arr2[index2]);
                index2++;
                continue;
            }
            if (arr2[index2] != arr1[index1] ){
                if (!set.contains(arr1[index1])){
                    int number = map.get(arr1[index1]);
                    exist.add(arr1[index1]);
                    while (number>0){
                        arr[index++] = arr1[index1];
                        number--;
                    }
                }else {
                    last.add(arr1[index1]);
                }
                index1++;
            }else {
                int number = map.get(arr1[index1]);
                exist.add(arr1[index1]);
                while (number>0){
                    arr[index++] = arr1[index1];
                    number--;
                }
                index1++;
                index2++;
            }
        }
        while (index1<length1){
            if (!exist.contains(arr1[index1])){
                int number = map.get(arr1[index1]);
                while (number>0){
                    arr[index++] = arr1[index1];
                    number--;
                }
                exist.add(index1);
            }
            index1++;
        }
        return arr;
    }

    /**
     * 思路:
     *      先遍历一次 arry1 存放元素，再遍历一次arry2 数组将数组变成基本有序的，最后再遍历一次array1看看是否有缺漏
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
     * 内存消耗：38.5 MB, 在所有 Java 提交中击败了49.22%的用户
     */
    class Solution {
        public int[] relativeSortArray(int[] arr1, int[] arr2) {
            int upper = 0;
            for (int x : arr1) {
                upper = Math.max(upper, x);
            }
            int[] frequency = new int[upper + 1];
            for (int x : arr1) {
                ++frequency[x];
            }
            int[] ans = new int[arr1.length];
            int index = 0;
            for (int x : arr2) {
                for (int i = 0; i < frequency[x]; ++i) {
                    ans[index++] = x;
                }
                frequency[x] = 0;
            }
            for (int x = 0; x <= upper; ++x) {
                for (int i = 0; i < frequency[x]; ++i) {
                    ans[index++] = x;
                }
            }
            return ans;
        }
    }


    public static void main(String[] args) {
        LeetCode leetCode = new LeetCode();
        leetCode.relativeSortArray(new int[]{2,3,1,3,2,4,6,7,9,7,2,19},new int[]{2,1,4,5,3,6});
    }
}
