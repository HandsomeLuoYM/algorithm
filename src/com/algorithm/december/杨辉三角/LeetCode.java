package com.algorithm.december.杨辉三角;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Ming
 * @date 2020/12/6 - 11:13
 * @describe 118. 杨辉三角
 */
public class LeetCode {

    /**
     * 给定一个非负整数 numRows，生成杨辉三角的前 numRows 行。在杨辉三角中，每个数是它左上方和右上方的数的和。
     * 输入: 5
     * 输出:
     * [
     *      [1],
     *     [1,1],
     *    [1,2,1],
     *   [1,3,3,1],
     *  [1,4,6,4,1]
     * ]
     */
    /**
     * 自己的做法
     *      暴力直接解(官方题解也差不多)
     * 执行用时：1 ms, 在所有 Java 提交中击败了59.18%的用户
     * 内存消耗：36.3 MB, 在所有 Java 提交中击败了62.99%的用户
     */
    public List<List<Integer>> generate(int numRows) {
        ArrayList<List<Integer>> result = new ArrayList<List<Integer>>();
        if (numRows == 0) return result;
        result.add(new ArrayList<Integer>(){{add(1);}});
        if (numRows == 1) return result;
        result.add(new ArrayList<Integer>(){{add(1);add(1);}});
        if (numRows == 2) return result;
        for (int i = 3; i<=numRows; i++){
            List<Integer> param = new ArrayList<Integer>(){{add(1);}};
            for (int j = 1; j<i-1; j++){
                param.add(result.get(i-2).get(j-1)+result.get(i-2).get(j));
            }
            param.add(1);
            result.add(param);
        }
        return result;
    }

    public static void main(String[] args) {
        LeetCode l = new LeetCode();
        l.generate(5);
    }
}
