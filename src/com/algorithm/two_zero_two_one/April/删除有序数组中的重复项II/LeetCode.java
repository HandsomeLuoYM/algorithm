package com.algorithm.two_zero_two_one.April.删除有序数组中的重复项II;

/**
 * @author Ming
 * @date 2021/4/6 - 18:07
 * @describe
 */
public class LeetCode {
    /**
     * 给你一个有序数组 nums ，请你 原地 删除重复出现的元素，使每个元素 最多出现两次 ，返回删除后数组的新长度。
     *
     * 不要使用额外的数组空间，你必须在 原地 修改输入数组 并在使用 O(1) 额外空间的条件下完成。
     *
     *  
     *
     * 说明：
     *
     * 为什么返回数值是整数，但输出的答案是数组呢？
     *
     * 请注意，输入数组是以「引用」方式传递的，这意味着在函数里修改输入数组对于调用者是可见的。
     *
     * 你可以想象内部操作如下:
     *
     * // nums 是以“引用”方式传递的。也就是说，不对实参做任何拷贝
     * int len = removeDuplicates(nums);
     *
     * // 在函数里修改输入数组对于调用者是可见的。
     * // 根据你的函数返回的长度, 它会打印出数组中 该长度范围内 的所有元素。
     * for (int i = 0; i < len; i++) {
     *     print(nums[i]);
     * }
     *  
     *
     * 示例 1：
     *
     * 输入：nums = [1,1,1,2,2,3]
     * 输出：5, nums = [1,1,2,2,3]
     * 解释：函数应返回新长度 length = 5, 并且原数组的前五个元素被修改为 1, 1, 2, 2, 3 。 不需要考虑数组中超出新长度后面的元素。
     * 示例 2：
     *
     * 输入：nums = [0,0,1,1,1,1,2,3,3]
     * 输出：7, nums = [0,0,1,1,2,3,3]
     * 解释：函数应返回新长度 length = 7, 并且原数组的前五个元素被修改为 0, 0, 1, 1, 2, 3, 3 。 不需要考虑数组中超出新长度后面的元素。
     *
     */
    /**
     * 思路：左右指针
     * 执行用时：1 ms, 在所有 Java 提交中击败了80.95%的用户
     * 内存消耗：38.4 MB, 在所有 Java 提交中击败了83.04%的用户
     */
    public int removeDuplicates(int[] nums) {
        int left = 0, now = 0, length = nums.length;
        for (int i = 0; i < length; i++) {
            int start = i;
            while (i + 1 < length && nums[i] == nums[i + 1]) {
                i++;
            }
            if (i - start + 1 >= 2) {
                nums[left++] = nums[i];
                nums[left++] = nums[i];
            }else {
                nums[left++] = nums[i];
            }
        }

        return left;
    }
}
