package com.algorithm.two_zero_two_zero.november.one_two;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Ming
 * @date 2020/11/12 - 9:43
 * @describe 922 按奇偶排序数组 II
 */
public class LeetCode {
    /**
     * 给定一个非负整数数组 A， A 中一半整数是奇数，一半整数是偶数。
     * 对数组进行排序，以便当 A[i] 为奇数时，i 也是奇数；当 A[i] 为偶数时， i 也是偶数。
     * 你可以返回任何满足上述条件的数组作为答案。
     *
     * 输入：[4,2,5,7]
     * 输出：[4,5,2,7]
     * 解释：[4,7,2,5]，[2,5,4,7]，[2,7,4,5] 也会被接受。
     *
     * 2 <= A.length <= 20000
     * A.length % 2 == 0
     * 0 <= A[i] <= 1000
     */
    /**
     * 自己的做法：
     *      设置一个队列，存储多余的，然后遍历时修改
     * 执行用时：8 ms, 在所有 Java 提交中击败了10.75%的用户
     * 内存消耗：40.7 MB, 在所有 Java 提交中击败了44.63%的用户
     */
    public int[] sortArrayByParityII(int[] A) {
        int length = A.length;
        List<Integer> d = new ArrayList<>(), s = new ArrayList<>();
        boolean flag = true;
        for (int i = 0; i<length; i++,flag = !flag){
            if (flag){
                if (A[i]%2!=0){
                    if (s.size()!=0){
                        swag(A, i, s.get(0));
                        s.remove(0);
                    }else {
                        d.add(i);
                    }
                }
            }else {
                if (A[i]%2!=1){
                    if (d.size()!=0){
                        swag(A, i, d.get(0));
                        d.remove(0);
                    }else {
                        s.add(i);
                    }
                }
            }
        }
        return A;
    }

    void swag(int[] A, int i, int j){
        int temp = A[i];
        A[i] = A[j];
        A[j] = temp;
    }

    /**
     * 双指针
     */
    class Solution {
        public int[] sortArrayByParityII(int[] A) {
            int n = A.length;
            int j = 1;
            for (int i = 0; i < n; i += 2) {
                if (A[i] % 2 == 1) {
                    while (A[j] % 2 == 1) {
                        j += 2;
                    }
                    swap(A, i, j);
                }
            }
            return A;
        }

        public void swap(int[] A, int i, int j) {
            int temp = A[i];
            A[i] = A[j];
            A[j] = temp;
        }
    }
}
