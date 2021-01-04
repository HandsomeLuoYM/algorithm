package com.algorithm.two_zero_two_zero.may.one_four;

/**
 * @author Ming
 * @date 2020/5/15 - 1:01
 * @describe
 *
 * 一只青蛙一次可以跳上1级台阶，也可以跳上2级。求该青蛙跳上一个n级的台阶总共有多少种跳法（先后次序不同算不同的结果）。
 */
public class Eight {

    /**
     * 运行时间：1014ms
     *
     * 占用内存：9428k
     * @param target
     * @return
     * 自己的解法
     */
    public int JumpFloor(int target) {
        if (target<0) return 0;
        if (target==0) return 1;
        return JumpFloor(target-1) + JumpFloor(target-2);
    }

    /**
     * 运行时间：13ms
     *
     * 占用内存：9272k

		本质上还是斐波那契数列，所以迭代也可以求

		当成 dp 问题来想的话：首先分析问题，它最终解是由前面的解累积起来的解，如何缩小问题的规模？

		首先可知，第一阶有只能一步，一种；，第二阶可以两次一步、一次两步两种

		若楼梯阶级 n = 3
		跳 2 步到 3：剩下的是第一步没跳，起始跳到第一步只有一种
		跳 1 步到 3：剩下的是第二步没跳，起始跳到第二步有两种
		通过分类讨论，问题规模就减少了:

		若楼梯阶级 n = n
		跳 2 步到 n：剩下的是第 n - 2 步没跳，起始跳到第 n - 2 步设它为 pre2 种
		跳 1 步到 n：剩下的是第 n - 1 步没跳，起始跳到第 n - 1 步设它为 pre1 种
		同时可以发现第 n 阶的解法，只要用到 n - 1 和 n - 2 阶是多少，其他的不用考虑，因此用两个变量临时存下来即可

		dp(i) = dp(i-2) + dp(i-1)
     */
    public int JumpFloor1(int target) {
        if(target <= 2){
            return target;
        }
        int pre2 = 1, pre1 = 2;
        for (int i = 3; i <= target; i++){
            int cur = pre2 + pre1;
            pre2 = pre1;
            pre1 = cur;
        }
        return pre1;
    }
}
