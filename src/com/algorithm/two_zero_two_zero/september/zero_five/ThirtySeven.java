package com.algorithm.two_zero_two_zero.september.zero_five;

/**
 * @author Ming
 * @date 2020/9/5 - 10:59
 * @describe
 */
public class ThirtySeven {

    /**
     * 统计一个数字在升序数组中出现的次数。
     */

    /**
     * 自己的暴力解法
     * 运行时间：10ms
     *
     * 占用内存：9480k
     */
    public int GetNumberOfK(int [] array , int k) {
        int length = array.length,num=0;
        for (int i=0;i<length;i++){
            if (array[i]==k) num++;
        }
        return num;
    }
    /**
     * 运行时间：10ms
     *
     * 占用内存：9148k
     * 递归二分法
     */
    public int GetNumberOfK1(int [] array , int k) {
        int lbound = 0;
        // 寻找小的边界
        int l = 0, r = array.length;
        while (l < r) {
            int mid = l + (r - l) / 2;
            if (array[mid] < k) {
                l = mid + 1;
            } else {
                r = mid;
            }
        }
        lbound = l;
        // 寻找大的边界
        l = 0;
        r = array.length;
        while (l < r) {
            int mid = l + (r - l) / 2;
            if (array[mid] <= k) {
                l = mid + 1;
            } else {
                r = mid;
            }
        }
        return r - lbound;
    }

    public static void main(String[] args) {
        ThirtySeven thirtySeven = new ThirtySeven();
        int i = thirtySeven.GetNumberOfK1(new int[]{1, 2,2,2,2,2,2,2,3,4,5,6,8,9}, 2);
        System.out.println(i);
    }
}
