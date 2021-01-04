package com.algorithm.two_zero_two_zero.september.zero_one;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Ming
 * @date 2020/9/1 - 9:54
 * @describe  486 预测赢家
 */
public class LeetCode {

    /**
     * 给定一个表示分数的非负整数数组。 玩家 1 从数组任意一端拿取一个分数，随后玩家 2 继续从剩余数组任意一端拿取分数，然后玩家 1 拿，…… 。每次一个玩家只能拿取一个分数，分数被拿取之后不再可取。直到没有剩余分数可取时游戏结束。最终获得分数总和最多的玩家获胜。
     *
     * 给定一个表示分数的数组，预测玩家1是否会成为赢家。你可以假设每个玩家的玩法都会使他的分数最大化。
     *
     * 输入：[1, 5, 2]
     * 输出：False
     * 解释：一开始，玩家1可以从1和2中进行选择。
     * 如果他选择 2（或者 1 ），那么玩家 2 可以从 1（或者 2 ）和 5 中进行选择。如果玩家 2 选择了 5 ，那么玩家 1 则只剩下 1（或者 2 ）可选。
     * 所以，玩家 1 的最终分数为 1 + 2 = 3，而玩家 2 为 5 。
     * 因此，玩家 1 永远不会成为赢家，返回 False 。
     *
     * 输入：[1, 5, 233, 7]
     * 输出：True
     * 解释：玩家 1 一开始选择 1 。然后玩家 2 必须从 5 和 7 中进行选择。无论玩家 2 选择了哪个，玩家 1 都可以选择 233 。
     *      最终，玩家 1（234 分）比玩家 2（12 分）获得更多的分数，所以返回 True，表示玩家 1 可以成为赢家。
     *
     */

    /**
     * 递归优化
     *
     * 思路：用一个参数people来标识当前是哪个用户，求每一个人他能拿到的最大值，拿到局部最优，然后递归返回
     *      如果是先手用户，他需要保证他拿到的分数是最大的，而如果是另一个用户，他需要让返回的值为最小
     *
     * 执行用时：46 ms, 在所有 Java 提交中击败了10.39%的用户
     * 内存消耗：37.1 MB, 在所有 Java 提交中击败了44.80%的用户
     *
     */

    public boolean PredictTheWinner(int[] nums) {
        if (nums.length<=2) return true;
        return getMax(1,0,nums.length-1,nums)>=0;
    }

    public int getMax(int people,int start,int end, int[] nums){
        if (end-start<=1) return people*(Math.abs(nums[start]-nums[end]));
        int num1 = getMax(-people,start+1,end ,nums)+people*nums[start];
        int num2 = getMax(-people,start,end-1 ,nums)+people*nums[end];
        return people==1? Math.max(num1,num2) : Math.min(num1,num2);
    }

    /**
     * 记忆递归，在递归中记录每个子问题的最优解
     * 执行用时：20 ms, 在所有 Java 提交中击败了12.78%的用户
     * 内存消耗：40 MB, 在所有 Java 提交中击败了5.43%的用户
     */
    class remember{
        Map<String ,Integer> map = new HashMap<>();

        public boolean PredictTheWinner(int[] nums) {
            if (nums.length<=2) return true;
            return getMax(1,0,nums.length-1,nums)>=0;
        }

        /**
         * @param people 1 代表用户 1，2 代表用户 2
         */
        public int getMax(int people,int start,int end, int[] nums){
            if (end-start<=1) return people*(Math.abs(nums[start]-nums[end]));
            if (map.containsKey(""+people+start+end)) return map.get(""+people+start+end);
            int num1 = getMax(-people,start+1,end ,nums)+people*nums[start];
            int num2 = getMax(-people,start,end-1 ,nums)+people*nums[end];
            int result = people==1? Math.max(num1,num2) : Math.min(num1,num2);
            map.put(""+people+start+end,result);
            return result;
        }
    }

}
