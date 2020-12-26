package com.algorithm.december.单调递增的数字;

/**
 * @author Ming
 * @date 2020/12/15 - 0:17
 * @describe
 */
public class LeetCode {
    /**
     * 给定一个非负整数 N，找出小于或等于 N 的最大的整数，同时这个整数需要满足其各个位数上的数字是单调递增。
     * （当且仅当每个相邻位数上的数字 x 和 y 满足 x <= y 时，我们称这个整数是单调递增的。）
     *
     * 输入: N = 10
     * 输出: 9
     *
     * 输入: N = 1234
     * 输出: 1234
     *
     * 输入: N = 332
     * 输出: 299
     */
    /**
     * 自己的做法：（和官方题解一致）
     *      维护一个数组，利用贪心思想求解，数组中记录了每一位的情况：
     *          当上一位小于当前位时，直接进入该维护的数组
     *          当上一位大于当前位时，需要改变之前的位数，使之变成最大，并且当前位需要减一，以保证到当前位为止，后面的数的最大的
     * 执行用时：1 ms, 在所有 Java 提交中击败了97.96%的用户
     * 内存消耗：35.3 MB, 在所有 Java 提交中击败了78.66%的用户
     */
    public int monotoneIncreasingDigits(int N) {
        int length = String.valueOf(N).length();
        int num[] = new int[length];
        int index = 0;
        num[0] = N%10;
        N = N/10;
        while (N!=0){
            int now = N%10;
            //需要降，也就是变小
            if (num[index] < now){
                for (int i = 0; i<=index; i++){
                    num[i] = 9;
                }
                num[++index] = now-1;
            }else {
                num[++index] = now;
            }
            N = N/10;
        }
        //构造返回值
        int result = 0, a = 1;
        for (int i = 0; i<length; i++){
            result += num[i] * a;
            a = a*10;
        }
        return result;
    }

    class Solution {
        public int monotoneIncreasingDigits(int N) {
            char[] strN = Integer.toString(N).toCharArray();
            int i = 1;
            while (i < strN.length && strN[i - 1] <= strN[i]) {
                i += 1;
            }
            if (i < strN.length) {
                //构造前面的数组，当前数减一，然后与前一个数比较，当满足时结束循环，构造后面的数
                while (i > 0 && strN[i - 1] > strN[i]) {
                    strN[i - 1] -= 1;
                    i -= 1;
                }
                //构造后续的数组
                for (i += 1; i < strN.length; ++i) {
                    strN[i] = '9';
                }
            }
            return Integer.parseInt(new String(strN));
        }
    }
}
