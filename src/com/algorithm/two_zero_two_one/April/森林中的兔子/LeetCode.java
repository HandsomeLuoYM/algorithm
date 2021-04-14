package com.algorithm.two_zero_two_one.April.森林中的兔子;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Ming
 * @date 2021/4/4 - 10:43
 * @describe
 */
public class LeetCode {
    /**
     * 森林中，每个兔子都有颜色。其中一些兔子（可能是全部）告诉你还有多少其他的兔子和自己有相同的颜色。我们将这些回答放在 answers 数组里。
     *
     * 返回森林中兔子的最少数量。
     *
     * 示例:
     * 输入: answers = [1, 1, 2]
     * 输出: 5
     * 解释:
     * 两只回答了 "1" 的兔子可能有相同的颜色，设为红色。
     * 之后回答了 "2" 的兔子不会是红色，否则他们的回答会相互矛盾。
     * 设回答了 "2" 的兔子为蓝色。
     * 此外，森林中还应有另外 2 只蓝色兔子的回答没有包含在数组中。
     * 因此森林中兔子的最少数量是 5: 3 只回答的和 2 只没有回答的。
     *
     * 输入: answers = [10, 10, 10]
     * 输出: 11
     *
     * 输入: answers = []
     * 输出: 0
     *
     */
    /**
     * 思路：遍历统计次数，贪心思想
     * 执行用时：4 ms, 在所有 Java 提交中击败了50.38%的用户
     * 内存消耗：37.7 MB, 在所有 Java 提交中击败了85.98%的用户
     */
    public int numRabbits(int[] answers) {
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        for (int answer : answers) {
            map.put(answer, map.getOrDefault(answer, 0) + 1);
        }
        int rs = 0;
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (entry.getKey() + 1 >= entry.getValue()) {
                rs += entry.getKey() + 1;
            }else {
                if (entry.getValue() % (entry.getKey() + 1) != 0) {
                    rs += (entry.getValue() / (entry.getKey() + 1) + 1) * (entry.getKey() + 1);
                }else {
                    rs += entry.getValue();
                }
            }
        }
        return rs;
    }
}
