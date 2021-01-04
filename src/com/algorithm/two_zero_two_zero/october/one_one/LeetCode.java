package com.algorithm.two_zero_two_zero.october.one_one;

import java.util.Arrays;

/**
 * @author Ming
 * @date 2020/10/11 - 11:04
 * @describe
 */
public class LeetCode {

    /**
     * 给定一个只包含正整数的非空数组。是否可以将这个数组分割成两个子集，使得两个子集的元素和相等。
     * 注意:
     *      每个数组中的元素不会超过 100
     *      数组的大小不会超过 200
     *
     * 输入: [1, 5, 11, 5]
     * 输出: true
     * 解释: 数组可以分割成 [1, 5, 5] 和 [11].
     *
     * 输入: [1, 2, 3, 5]
     * 输出: false
     * 解释: 数组不能分割成两个元素和相等的子集.
     */
    /**
     * 自己的解法：超时
     */
    public boolean canPartition(int[] nums) {
        if (nums==null || nums.length <= 1) return false;
        Arrays.sort(nums);
        int all = 0;
        for (int i = 0; i<nums.length; i++)
            all+=nums[i];
        if (all % 2 ==1) return false;
        return judge(nums, 0, 0, all/2);
    }

    private boolean judge(int[] nums, int all, int index, int target) {
        if (index>=nums.length) return false;
        if (all + nums[index] > target) return false;
        else if (all + nums[index] == target) return true;
        else return judge(nums, all + nums[index], index+1, target) || judge(nums, all , index+1, target);
    }

    class Solution {
        public boolean find(int[] nums, int target, int index){
            if( target == 0) return true;
            for(int i=index; i<nums.length; i++){
                if(i>index && nums[i]==nums[i-1]) continue;
                if(target-nums[i]<0) return false;
                if(find(nums, target-nums[i], i+1)) return true;
            }
            return false;
        }
        public boolean canPartition(int[] nums) {
            int total=0;
            for(int num: nums) total+=num;
            Arrays.sort(nums);
            if(total %2 !=0) return false;
            if(total==0) return true;
            return find(nums, total/2, 0);
        }
    }

    public boolean canPartition1(int[] nums) {
        int n = nums.length;
        if (n < 2) {
            return false;
        }
        int sum = 0, maxNum = 0;
        for (int num : nums) {
            sum += num;
            maxNum = Math.max(maxNum, num);
        }
        if (sum % 2 != 0) {
            return false;
        }
        int target = sum / 2;
        //避免最大值大于一半
        if (maxNum > target) {
            return false;
        }
        //核心代码。dp[i]代表 i 这个数可不可达
        boolean[] dp = new boolean[target + 1];
        dp[0] = true;
        for (int i = 0; i < n; i++) {
            int num = nums[i];
            for (int j = target; j >= num; --j) {
                //j这个数可以由 num 和 j - num 两个数构成，而num已经有了，所以只需要判断 j-num 是否存在而已，当然如果j已经有了，那就不需要判断了
                dp[j] |= dp[j - num];
            }
        }
        return dp[target];
    }


    class Solution2 {
        public boolean canPartition(int[] nums) {
            int n = nums.length;
            if (n < 2) {
                return false;
            }
            int sum = 0, maxNum = 0;
            //避免最大值大于一半
            for (int num : nums) {
                sum += num;
                maxNum = Math.max(maxNum, num);
            }
            if (sum % 2 != 0) {
                return false;
            }
            int target = sum / 2;
            if (maxNum > target) {
                return false;
            }
            //核心代码。dp[i][j]代表从0到i中，j这个数可不可达
            boolean[][] dp = new boolean[n][target + 1];
            //0均可达，即啥都不添加
            for (int i = 0; i < n; i++) {
                dp[i][0] = true;
            }
            //第一个数中只有nums[0]可达
            dp[0][nums[0]] = true;
            for (int i = 1; i < n; i++) {
                //取出第i个数
                int num = nums[i];
                //从大到小依次判断是否可达
                for (int j = 1; j <= target; j++) {
                    if (j >= num) {
                        //j这个数可以由 num 和 j - num 两个数构成，而num已经有了，所以只需要判断 j-num 是否存在而已，当然如果j已经有了，那就不需要判断了
                        dp[i][j] = dp[i - 1][j] | dp[i - 1][j - num];
                    } else {
                        dp[i][j] = dp[i - 1][j];
                    }
                }
            }
            return dp[n - 1][target];
        }
    }

}
