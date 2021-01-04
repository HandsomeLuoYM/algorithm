package com.algorithm.two_zero_two_zero.november.one_five;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @author Ming
 * @date 2020/11/15 - 13:56
 * @describe 402. 移掉K位数字
 */
public class LeetCode {

    /**
     * 给定一个以字符串表示的非负整数 num，移除这个数中的 k 位数字，使得剩下的数字最小。
     *
     * 注意:
     *      num 的长度小于 10002 且 ≥ k。
     *      num 不会包含任何前导零。
     *
     * 输入: num = "1432219", k = 3
     * 输出: "1219"
     * 解释: 移除掉三个数字 4, 3, 和 2 形成一个新的最小的数字 1219。
     *
     * 输入: num = "10200", k = 1
     * 输出: "200"
     * 解释: 移掉首位的 1 剩下的数字为 200. 注意输出不能有任何前导零。
     *
     * 输入: num = "10", k = 2
     * 输出: "0"
     * 解释: 从原数字移除所有的数字，剩余为空就是0。
     */
    /**
     * 自己的思路：
     *      一位一位的确定值
     * 执行用时：28 ms, 在所有 Java 提交中击败了16.81%的用户
     * 内存消耗：39 MB, 在所有 Java 提交中击败了44.50%的用户
     */
    public String removeKdigits(String num, int k) {
        return findMin(num,k, 0, k+1);
    }

    /**
     * 在 start 到 end 找到最小值
     */
    public String findMin(String num, int k, int start, int end){
        if (k>=num.length())
            return "0";
        if (k==0) return num;
        if (k + start >= end) return num.substring(0,end-k);
        int min = start;
        for (int i = start; i < end; i++){
            if (num.charAt(i) < num.charAt(min)) min = i;
        }
        k -= min - start;
        String s = num.substring(0,start);
        s += num.substring(min,num.length());
        if (s.charAt(0) == '0') {
            for (int i = 1; i<s.length(); i++){
                if (s.charAt(i) != '0'){
                    s = s.substring(i,s.length());
                    break;
                }
                if (i==s.length()-1) return "0";
            }
            return findMin(s, k, 0, k+1);
        }
        if (k > 0){
            int newEnd = Math.min(start + 2 + k, s.length());
            return findMin(s,k,start+1,newEnd);
        }
        return s;
    }

    /**
     * 官方题解：
     *      贪心 + 单调栈
     */
    class Solution {
        public String removeKdigits(String num, int k) {
            Deque<Character> deque = new LinkedList<Character>();
            int length = num.length();
            for (int i = 0; i < length; ++i) {
                char digit = num.charAt(i);
                while (!deque.isEmpty() && k > 0 && deque.peekLast() > digit) {
                    deque.pollLast();
                    k--;
                }
                deque.offerLast(digit);
            }

            for (int i = 0; i < k; ++i) {
                deque.pollLast();
            }

            StringBuilder ret = new StringBuilder();
            boolean leadingZero = true;
            while (!deque.isEmpty()) {
                char digit = deque.pollFirst();
                if (leadingZero && digit == '0') {
                    continue;
                }
                leadingZero = false;
                ret.append(digit);
            }
            return ret.length() == 0 ? "0" : ret.toString();
        }
    }

}
