package com.algorithm.november.one_zero;

/**
 * @author Ming
 * @date 2020/11/10 - 0:57
 * @describe 31. 下一个排列
 */
public class LeetCode {

    /**
     * 实现获取下一个排列的函数，算法需要将给定数字序列重新排列成字典序中下一个更大的排列。
     * 如果不存在下一个更大的排列，则将数字重新排列成最小的排列（即升序排列）。
     * 必须原地修改，只允许使用额外常数空间。
     *
     * 以下是一些例子，输入位于左侧列，其相应输出位于右侧列。
     * 1,2,3 → 1,3,2
     * 3,2,1 → 1,2,3
     * 1,1,5 → 1,5,1
     *
     */
    /**
     * 自己的思路：
     *      从尾找到非递增的第一个数，将其替换成后续一个比他大最小的数，然后将后续数重新排序
     * 执行用时：1 ms, 在所有 Java 提交中击败了98.72%的用户
     * 内存消耗：38.6 MB, 在所有 Java 提交中击败了87.47%的用户
     */
    public void nextPermutation(int[] nums) {
        if (nums.length<2) return;
        int number = nums.length-2;
        while (number>0){
            if (nums[number]<nums[number+1]) break;
            number--;
        }
        int big = nums.length-1,swag;
        for (int i = nums.length-1; i>=number; i--){
            if (nums[number]<nums[i]){
                big = i;
                break;
            }
        }
        swag = nums[big];
        nums[big]=nums[number];
        nums[number]=swag;
        for (int i = number+1;i<nums.length;i++){
            int temp = i;
            for (int j = i; j<nums.length;j++){
                if (nums[j]<nums[temp]) temp = j;
            }
            swag = nums[i];
            nums[i]=nums[temp];
            nums[temp]=swag;
        }
    }

    public static void main(String[] args) {
        LeetCode l = new LeetCode();
        l.nextPermutation(new int[]{3,2,1});
    }

}
