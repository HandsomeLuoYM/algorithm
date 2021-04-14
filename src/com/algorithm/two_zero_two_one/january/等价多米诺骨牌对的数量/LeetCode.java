package com.algorithm.two_zero_two_one.january.等价多米诺骨牌对的数量;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Ming
 * @date 2021/1/26 - 21:19
 * @describe
 */
public class LeetCode {
    /**
     * 给你一个由一些多米诺骨牌组成的列表 dominoes。
     *
     * 如果其中某一张多米诺骨牌可以通过旋转 0 度或 180 度得到另一张多米诺骨牌，我们就认为这两张牌是等价的。
     *
     * 形式上，dominoes[i] = [a, b] 和 dominoes[j] = [c, d] 等价的前提是 a==c 且 b==d，或是 a==d 且 b==c。
     *
     * 在 0 <= i < j < dominoes.length 的前提下，找出满足 dominoes[i] 和 dominoes[j] 等价的骨牌对 (i, j) 的数量。
     *
     * 输入：dominoes = [[1,2],[2,1],[3,4],[5,6]]
     * 输出：1
     *
     * 1 <= dominoes.length <= 40000
     * 1 <= dominoes[i][j] <= 9
     */
    /**
     * 思路：利用 hash + 数学公式
     * 执行用时：41 ms, 在所有 Java 提交中击败了5.31%的用户
     * 内存消耗：51.7 MB, 在所有 Java 提交中击败了5.14%的用户
     */
    public int numEquivDominoPairs(int[][] dominoes) {
        int length = dominoes.length, num;
        AtomicInteger rs = new AtomicInteger(0);
        Map<String, Integer> map = new HashMap<>();
        String key;
        for (int i = 0; i < length; i++) {
            if (dominoes[i][0] > dominoes[i][1]){
                key = String.valueOf(dominoes[i][0]).concat("-") + dominoes[i][1];
            }else {
                key = String.valueOf(dominoes[i][1]).concat("-") + dominoes[i][0];
            }
            if (map.containsKey(key)) {
                map.put(key, map.get(key) + 1);
            }else {
                map.put(key, 1);
            }
        }
        map.entrySet().forEach(entry -> {
            if (entry.getValue() > 1){
                rs.addAndGet((entry.getValue() -1 ) * entry.getValue() / 2);
            }
        });
        return rs.get();
    }
}
