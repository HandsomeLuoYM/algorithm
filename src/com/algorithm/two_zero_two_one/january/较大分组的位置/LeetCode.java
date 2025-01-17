package com.algorithm.two_zero_two_one.january.较大分组的位置;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Ming
 * @date 2021/1/5 - 1:22
 * @describe
 */
public class LeetCode {

    /**
     * 在一个由小写字母构成的字符串 s 中，包含由一些连续的相同字符所构成的分组。
     *
     * 例如，在字符串 s = "abbxxxxzyy" 中，就含有 "a", "bb", "xxxx", "z" 和 "yy" 这样的一些分组。
     *
     * 分组可以用区间 [start, end] 表示，其中 start 和 end 分别表示该分组的起始和终止位置的下标。上例中的 "xxxx" 分组用区间表示为 [3,6] 。
     *
     * 我们称所有包含大于或等于三个连续字符的分组为 较大分组 。
     *
     * 找到每一个 较大分组 的区间，按起始位置下标递增顺序排序后，返回结果。
     *
     * 输入：s = "abbxxxxzzy"
     * 输出：[[3,6]]
     * 解释："xxxx" 是一个起始于 3 且终止于 6 的较大分组。
     *
     * 输入：s = "abc"
     * 输出：[]
     * 解释："a","b" 和 "c" 均不是符合要求的较大分组。
     *
     * 输入：s = "abcdddeeeeaabbbcd"
     * 输出：[[3,5],[6,9],[12,14]]
     * 解释：较大分组为 "ddd", "eeee" 和 "bbb"
     *
     */
    /**
     * 自己的思路：
     *      边遍历边求解
     * 执行用时：1 ms, 在所有 Java 提交中击败了100.00%的用户
     * 内存消耗：38.6 MB, 在所有 Java 提交中击败了73.31%的用户
     */
    public List<List<Integer>> largeGroupPositions(String s) {
        List<List<Integer>> lists = new ArrayList<>();
        List list;
        if (s == null) return lists;
        int length = s.length(), start = 0, now = 1;
        for (int i = 1; i < length; i++){
            if (s.charAt(i) == s.charAt(start)) {
                now++;
            }else {
                if (now >= 3) {
                    list = new ArrayList();
                    list.add(start);
                    list.add(i -1);
                    lists.add(list);
                }
                now = 1;
                start = i;
            }
        }
        if (s.charAt(length - 1) == s.charAt(start) && now > 2) {
            list = new ArrayList();
            list.add(start);
            list.add(length - 1);
            lists.add(list);
        }
        return lists;
    }


}
