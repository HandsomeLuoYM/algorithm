package com.algorithm.two_zero_two_one.january.种花问题;

/**
 * @author Ming
 * @date 2021/1/1 - 12:11
 * @describe
 */
public class LeetCode {

    /**
     * 假设你有一个很长的花坛，一部分地块种植了花，另一部分却没有。可是，花卉不能种植在相邻的地块上，它们会争夺水源，两者都会死去。
     *
     * 给定一个花坛（表示为一个数组包含0和1，其中0表示没种植花，1表示种植了花），和一个数 n 。
     * 能否在不打破种植规则的情况下种入 n 朵花？
     * 能则返回True，不能则返回False。
     *
     * 输入: flowerbed = [1,0,0,0,1], n = 1
     * 输出: True
     *
     * 输入: flowerbed = [1,0,0,0,1], n = 2
     * 输出: False
     */
    /**
     * 自己的思路：
     *      一次遍历，变遍历边判断
     * 执行用时：1 ms, 在所有 Java 提交中击败了100.00%的用户
     * 内存消耗：39.9 MB, 在所有 Java 提交中击败了69.06%的用户
     */
    public boolean canPlaceFlowers(int[] flowerbed, int n) {
        int length = flowerbed.length, all = 0, i = 1;
        if (length == 1 && n == 1) return flowerbed[0]==0;
        if (flowerbed[0] == 0 && length > 1 && flowerbed[1] == 0){
            flowerbed[0] = 1;
            all++;
        }
        while (i + 1 < length){
            if (flowerbed[i] == 0 && flowerbed[i-1] == 0 && flowerbed[i+1] == 0){
                all++;
                flowerbed[i] = 1;
                i += 2;
            }else {
                i++;
            }
        }
        if (flowerbed[length-1] == 0 && length > 1 && flowerbed[length-2] == 0) all++;
        return all >= n;
    }

    public static void main(String[] args) {
        LeetCode l = new LeetCode();
        System.out.println(l.canPlaceFlowers(new int[]{1, 0, 0, 0, 0, 1}, 2));
    }
}
