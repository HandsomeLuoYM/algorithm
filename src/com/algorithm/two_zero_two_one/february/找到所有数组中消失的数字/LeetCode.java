package com.algorithm.two_zero_two_one.february.找到所有数组中消失的数字;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Ming
 * @date 2021/2/13 - 12:03
 * @describe
 */
public class LeetCode {
    /**
     *
     给定一个范围在  1 ≤ a[i] ≤ n ( n = 数组大小 ) 的 整型数组，数组中的元素一些出现了两次，另一些只出现一次。

     找到所有在 [1, n] 范围之间没有出现在数组中的数字。

     您能在不使用额外空间且时间复杂度为O(n)的情况下完成这个任务吗? 你可以假定返回的数组不算在额外空间内。

     示例:

     输入:
     [4,3,2,7,8,2,3,1]

     输出:
     [5,6]
     */
    /**
     * 思路：记录次数
     *执行用时：4 ms, 在所有 Java 提交中击败了98.85%的用户
     * 内存消耗：47.7 MB, 在所有 Java 提交中击败了6.82%的用户
     */
    public List<Integer> findDisappearedNumbers(int[] nums) {
        List<Integer> rs = new ArrayList<>();
        int[] index = new int[nums.length + 1];
        int length = nums.length;
        for (int i = 0; i < length; i++) {
            index[nums[i]]++;
        }
        for (int i = 1; i <= length; i++) {
            if (index[i] == 0) {
                rs.add(i);
            }
        }
        return rs;
    }
    
}
