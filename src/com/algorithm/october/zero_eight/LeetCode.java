package com.algorithm.october.zero_eight;

/**
 * @author Ming
 * @date 2020/10/8 - 10:47
 * @describe 344 反转字符串
 */
public class LeetCode {

    /**
     * 编写一个函数，其作用是将输入的字符串反转过来。输入字符串以字符数组 char[] 的形式给出。
     *
     * 不要给另外的数组分配额外的空间，你必须原地修改输入数组、使用 O(1) 的额外空间解决这一问题。
     *
     * 你可以假设数组中的所有字符都是 ASCII 码表中的可打印字符。
     *
     * 输入：["h","e","l","l","o"]
     * 输出：["o","l","l","e","h"]
     *
     * 输入：["H","a","n","n","a","h"]
     * 输出：["h","a","n","n","a","H"]
     */
    /**
     * 思路：单个指针，交叉交换
     * 执行用时：1 ms, 在所有 Java 提交中击败了99.98%的用户
     * 内存消耗：45.5 MB, 在所有 Java 提交中击败了42.25%的用户
     */
    public void reverseString(char[] s) {
        int halfLength = s.length/2,length = s.length;
        char temp;
        for (int i=0; i<halfLength; i++){
            temp = s[i];
            s[i] = s[length-1-i];
            s[length-1-i] = temp;
        }
    }

    /**
     * 官方题解：双指针
     */
    class Solution {
        public void reverseString(char[] s) {
            int n = s.length;
            for (int left = 0, right = n - 1; left < right; ++left, --right) {
                char tmp = s[left];
                s[left] = s[right];
                s[right] = tmp;
            }
        }
    }

}
