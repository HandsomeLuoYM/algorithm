package com.algorithm.october.one_six;

import java.util.Arrays;

/**
 * @author Ming
 * @date 2020/10/16 - 1:09
 * @describe
 */
public class LeetCode {

    /**
     * 给定一个按非递减顺序排序的整数数组 A，返回每个数字的平方组成的新数组，要求也按非递减顺序排序
     *
     * 输入：[-7,-3,2,3,11]
     * 输出：[4,9,9,49,121]
     *
     * 输入：[-4,-1,0,3,10]
     * 输出：[0,1,9,16,100]
     */

    /**
     * 思路：（与官方题解三一致）
     *      双指针，一个指向头一个指向尾，代表正负最大或最小，然后循环判断哪个加入数组，
     *      当相等时停止循环，顺便把最后答案（一个未添加的数）调整一下。在加入数组时也要从尾部开始加入，
     *      因为两个指针代表了两个极端，里面必存在正数最大或者负数最大
     * 执行用时：1 ms, 在所有 Java 提交中击败了100.00%的用户
     * 内存消耗：40.5 MB, 在所有 Java 提交中击败了58.03%的用户
     */
    public int[] sortedSquares(int[] A) {
        int tail = A.length-1,head = 0,index = A.length-1;
        int[] result = new int[A.length];
        while (head != tail){
            if (Math.abs(A[head])>Math.abs(A[tail])){
                result[index--] = A[head]*A[head];
                head++;
            }else {
                result[index--] = A[tail]*A[tail];
                tail++;
            }
        }
        return result;
    }

    /**
     * 官方题解一，暴力，最后才排序
     */
    class Solution {
        public int[] sortedSquares(int[] A) {
            int[] ans = new int[A.length];
            for (int i = 0; i < A.length; ++i) {
                ans[i] = A[i] * A[i];
            }
            Arrays.sort(ans);
            return ans;
        }
    }

    /**
     * 官方题解二：
     *      思路：和我一开始的思路一致
     *      看注解
     */
    class Solution1 {
        public int[] sortedSquares(int[] A) {
            int n = A.length;
            int negative = -1;
//            先找到大于或等于0的第一个点
            for (int i = 0; i < n; ++i) {
                if (A[i] < 0) {
                    negative = i;
                } else {
                    break;
                }
            }

            int[] ans = new int[n];
            int index = 0, i = negative, j = negative + 1;
            while (i >= 0 || j < n) {
                //当i已经到尽头了，剩下的就都是j的了
                if (i < 0) {
                    ans[index] = A[j] * A[j];
                    ++j;
                } else if (j == n) { //当j到达尽头了，剩下的就是i的了
                    ans[index] = A[i] * A[i];
                    --i;
                } else if (A[i] * A[i] < A[j] * A[j]) {//若都没到达尽头，比较两者的大小，j大添加i因为是从index是从0位置开始的
                    ans[index] = A[i] * A[i];
                    --i;
                } else {//若都没到达尽头，比较两者的大小，i大添加j因为是从index是从0位置开始的
                    ans[index] = A[j] * A[j];
                    ++j;
                }
                ++index;//目前添加到的索引
            }

            return ans;
        }
    }

}
