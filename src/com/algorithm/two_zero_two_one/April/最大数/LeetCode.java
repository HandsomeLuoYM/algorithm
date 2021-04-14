package com.algorithm.two_zero_two_one.April.最大数;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

/**
 * @author Ming
 * @date 2021/4/12 - 0:27
 * @describe
 */
public class LeetCode {
    /**
     * 给定一组非负整数 nums，重新排列每个数的顺序（每个数不可拆分）使之组成一个最大的整数。
     *
     * 注意：输出结果可能非常大，所以你需要返回一个字符串而不是整数。
     *
     * 示例 1：
     *
     * 输入：nums = [10,2]
     * 输出："210"
     * 示例 2：
     *
     * 输入：nums = [3,30,34,5,9]
     * 输出："9534330"
     * 示例 3：
     *
     * 输入：nums = [1]
     * 输出："1"
     * 示例 4：
     *
     * 输入：nums = [10]
     * 输出："10"
     */
    /**
     * 思路：
     *      先排序再拼接
     * 执行用时：8 ms, 在所有 Java 提交中击败了33.63%的用户
     * 内存消耗：37.3 MB, 在所有 Java 提交中击败了99.21%的用户
     */
    public String largestNumber(int[] nums) {
        Integer[] integers = Arrays.stream(nums).boxed().toArray(Integer[]::new);
        Arrays.sort(integers,   (x, y) -> {
            long sx = 10, sy = 10;
            while (sx <= x) {
                sx *= 10;
            }
            while (sy <= y) {
                sy *= 10;
            }
            return (int) (-sy * x - y + sx * y + x);
        });
        if (integers[0] == 0) {
            return "0";
        }
        StringBuilder sb = new StringBuilder();
        for (Integer integer : integers) {
            sb.append(integer);
        }
        return sb.toString();
    }

}
