package com.algorithm.november.zero_six;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * @author Ming
 * @date 2020/11/6 - 11:49
 * @describe
 */
public class LeetCode {

    /**
     *给你一个整数数组 arr 。请你将数组中的元素按照其二进制表示中数字 1 的数目升序排序。
     * 如果存在多个数字二进制中 1 的数目相同，则必须将它们按照数值大小升序排列。
     * 请你返回排序后的数组
     *
     * 输入：arr = [0,1,2,3,4,5,6,7,8]
     * 输出：[0,1,2,4,8,3,5,6,7]
     * 解释：[0] 是唯一一个有 0 个 1 的数。
     * [1,2,4,8] 都有 1 个 1 。
     * [3,5,6] 有 2 个 1 。
     * [7] 有 3 个 1 。
     * 按照 1 的个数排序得到的结果数组为 [0,1,2,4,8,3,5,6,7]
     *
     * 输入：arr = [1024,512,256,128,64,32,16,8,4,2,1]
     * 输出：[1,2,4,8,16,32,64,128,256,512,1024]
     * 解释：数组中所有整数二进制下都只有 1 个 1 ，所以你需要按照数值大小将它们排序。
     *
     * 输入：arr = [2,3,5,7,11,13,17,19]
     * 输出：[2,3,5,17,7,11,13,19]
     *
     * 输入：arr = [10,100,1000,10000]
     * 输出：[10,100,10000,1000]
     */
    /**
     * 我的做法
     *      设置一个存储 1 数量的数组，利用排序来进行排列
     * 执行用时：12 ms, 在所有 Java 提交中击败了34.14%的用户
     * 内存消耗：38.7 MB, 在所有 Java 提交中击败了89.51%的用户
     */
    public int[] sortByBits(int[] arr) {
        int length = arr.length;
        int[] number = new int[arr.length];
        for (int i = 0; i<length; i++){
            number[i] = get(arr[i]);
        }
        for (int i = 0; i<length; i++){
            int temp = number[i];
            int min = i;
            for (int j = i; j<length; j++){
                if (temp == number[j]){
                    if (arr[min] > arr[j]){
                        min = j;
                        continue;
                    }
                }
                if (temp > number[j]){
                    min = j;
                    temp = number[j];
                }
            }
            int t = arr[i];
            arr[i] = arr[min];
            arr[min] = t;
            number[min] = number[i];
        }
        return arr;
    }

    public int get(int x) {
        int res = 0;
        while (x != 0) {
            res += x % 2;
            x /= 2;
        }
        return res;
    }

    /**
     * 官方题解一：暴力解法
     */
    class Solution {
        public int[] sortByBits(int[] arr) {
            int[] bit = new int[10001];
            List<Integer> list = new ArrayList<Integer>();
            for (int x : arr) {
                list.add(x);
                bit[x] = get(x);
            }
            Collections.sort(list, new Comparator<Integer>() {
                @Override
                public int compare(Integer x, Integer y) {
                    if (bit[x] != bit[y]) {
                        return bit[x] - bit[y];
                    } else {
                        return x - y;
                    }
                }
            });
            for (int i = 0; i < arr.length; ++i) {
                arr[i] = list.get(i);
            }
            return arr;
        }

        public int get(int x) {
            int res = 0;
            while (x != 0) {
                res += x % 2;
                x /= 2;
            }
            return res;
        }
    }

    /**
     * 我们定义 bit[i] 为数字 i 二进制表示下数字 1 的个数，则可以列出递推式：
     *
     * bit[i]=bit[i>>1]+(i&1)
     *
     * 所以我们线性预处理 bit 数组然后去排序即可。
     *
     */
    class Solution1 {
        public int[] sortByBits(int[] arr) {
            List<Integer> list = new ArrayList<Integer>();
            for (int x : arr) {
                list.add(x);
            }
            int[] bit = new int[10001];
            for (int i = 1; i <= 10000; ++i) {
                bit[i] = bit[i >> 1] + (i & 1);
            }
            Collections.sort(list, new Comparator<Integer>() {
                @Override
                public int compare(Integer x, Integer y) {
                    if (bit[x] != bit[y]) {
                        return bit[x] - bit[y];
                    } else {
                        return x - y;
                    }
                }
            });
            for (int i = 0; i < arr.length; ++i) {
                arr[i] = list.get(i);
            }
            return arr;
        }
    }


}
