package com.algorithm.october.zero_serven;

/**
 * @author Ming
 * @date 2020/10/7 - 2:20
 * @describe 75. 颜色分类
 */
public class LeetCode {

    /**
     * 给定一个包含红色、白色和蓝色，一共 n 个元素的数组，原地对它们进行排序，使得相同颜色的元素相邻，并按照红色、白色、蓝色顺序排列。
     *
     * 此题中，我们使用整数 0、 1 和 2 分别表示红色、白色和蓝色。
     *
     * 输入: [2,0,2,1,1,0]
     * 输出: [0,0,1,1,2,2]
     */

    /**
     * 自己的思路：设置三个指针，指向颜色之间的边界，通过颜色判断来移动边界和改变颜色
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
     * 内存消耗：37.4 MB, 在所有 Java 提交中击败了47.93%的用户
     */
    public void sortColors(int[] nums) {
        int red=0, white=0, length = nums.length;
        for (int i=0; i<length; i++){
            if (nums[i]==0) {
                nums[i] = 2;
                nums[white] = 1;
                nums[red] = 0;
                white++;
                red++;
            }else if (nums[i]==1){
                nums[i] = 2;
                nums[white] = 1;
                white++;
            }
        }
    }

    /**
     * 官方题解一：单指针，遍历两次，交换元素
     */
    class Solution1 {
        public void sortColors(int[] nums) {
            int n = nums.length;
            int ptr = 0;
            for (int i = 0; i < n; ++i) {
                if (nums[i] == 0) {
                    int temp = nums[i];
                    nums[i] = nums[ptr];
                    nums[ptr] = temp;
                    ++ptr;
                }
            }
            for (int i = ptr; i < n; ++i) {
                if (nums[i] == 1) {
                    int temp = nums[i];
                    nums[i] = nums[ptr];
                    nums[ptr] = temp;
                    ++ptr;
                }
            }
        }
    }

    /**
     * 官方题解二、双指针，和我的思路一样
     */
    class Solution2 {
        public void sortColors(int[] nums) {
            int n = nums.length;
            int p0 = 0, p1 = 0;
            for (int i = 0; i < n; ++i) {
                if (nums[i] == 1) {
                    int temp = nums[i];
                    nums[i] = nums[p1];
                    nums[p1] = temp;
                    ++p1;
                } else if (nums[i] == 0) {
                    int temp = nums[i];
                    nums[i] = nums[p0];
                    nums[p0] = temp;
                    if (p0 < p1) {
                        temp = nums[i];
                        nums[i] = nums[p1];
                        nums[p1] = temp;
                    }
                    ++p0;
                    ++p1;
                }
            }
        }
    }

    /**
     * 官方题解三、双指针
     * 思路：设置两个指针，一个专门用来交换0的指针p0，一个专门用来交换2的指针p2，还有一个指针 i ，当i遇到0时与 p0 交换，遇到2时与p2交换，遇到1时不交换
     */
    class Solution3 {
        public void sortColors(int[] nums) {
            int n = nums.length;
            int p0 = 0, p2 = n - 1;
            for (int i = 0; i <= p2; ++i) {
                while (i <= p2 && nums[i] == 2) {
                    int temp = nums[i];
                    nums[i] = nums[p2];
                    nums[p2] = temp;
                    --p2;
                }
                if (nums[i] == 0) {
                    int temp = nums[i];
                    nums[i] = nums[p0];
                    nums[p0] = temp;
                    ++p0;
                }
            }
        }
    }
}
