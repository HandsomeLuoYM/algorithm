package com.algorithm.september.zero_five;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Ming
 * @date 2020/9/5 - 9:59
 * @describe 60. 第k个排列
 */
public class LeetCode {

    /**
     * 给出集合 [1,2,3,…,n]，其所有元素共有 n! 种排列。
     *
     * 按大小顺序列出所有排列情况，并一一标记，当 n = 3 时, 所有排列如下：
     *
     * "123"
     * "132"
     * "213"
     * "231"
     * "312"
     * "321"
     * 给定 n 和 k，返回第 k 个排列。
     *
     * 说明：
     *
     * 给定 n 的范围是 [1, 9]。
     * 给定 k 的范围是[1,  n!]。
     *
     * 输入: n = 3, k = 3
     * 输出: "213"
     *
     * 输入: n = 4, k = 9
     * 输出: "2314"
     */

    /**
     * 自己的解法：
     *
     * 思路：初始化一个比较表和一个按小到大的序号表，
     *       然后根据比较表从高位到地位一个一个确定其值，然后从序号表中移除，以此类推直至最后
     *
     * 注意：需要把k先减一，因为 1*2*3 即表示有六种排序，也就是不需要动用到高一位，减一确保计算的正确
     *
     * 执行用时：1 ms, 在所有 Java 提交中击败了100.00%的用户
     * 内存消耗：37.3 MB, 在所有 Java 提交中击败了65.02%的用户
     */
    public String getPermutation(int n, int k) {
        List list = new ArrayList<Integer>(n);
        int[] num = new int[n+1];
        StringBuilder stringBuilder = new StringBuilder();
        num[0]=1;
        k--;
        //初始化比较表，以及数字序号
        for (int i=1;i<=n;i++){
            list.add(i);
            num[i]=num[i-1]*i;
        }
        int result;
        for (int i=n-1;i>=0;i--){
            result=k/num[i];
            k=k%num[i];
            stringBuilder.append(list.get(result));
            list.remove(result);
        }
        return stringBuilder.toString();
    }

    public static void main(String[] args) {
        LeetCode leetCode = new LeetCode();
        String permutation = leetCode.getPermutation(3, 3);
        System.out.println(permutation);
    }
}
