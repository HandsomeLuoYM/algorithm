package com.algorithm.two_zero_two_zero.december.将数组拆分成斐波那契序列;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Ming
 * @date 2020/12/8 - 11:24
 * @describe
 */
public class LeetCode {

    /**
     * 给定一个数字字符串 S，比如 S = "123456579"，我们可以将它分成斐波那契式的序列 [123, 456, 579]。
     * <p>
     * 形式上，斐波那契式序列是一个非负整数列表 F，且满足：
     * <p>
     * 0 <= F[i] <= 2^31 - 1，（也就是说，每个整数都符合 32 位有符号整数类型）；
     * F.length >= 3；
     * 对于所有的0 <= i < F.length - 2，都有 F[i] + F[i+1] = F[i+2] 成立。
     * <p>
     * 另外，请注意，将字符串拆分成小块时，每个块的数字一定不要以零开头，除非这个块是数字 0 本身。
     * <p>
     * 返回从 S 拆分出来的任意一组斐波那契式的序列块，如果不能拆分则返回 []。
     * <p>
     * 输入："123456579"
     * 输出：[123,456,579]
     * <p>
     * 输入: "11235813"
     * 输出: [1,1,2,3,5,8,13]
     * <p>
     * 输入: "112358130"
     * 输出: []
     * 解释: 这项任务无法完成。
     */
    /**
     *
     * 执行用时：1 ms, 在所有 Java 提交中击败了100.00%的用户
     * 内存消耗：36.9 MB, 在所有 Java 提交中击败了92.89%的用户
     */
    public List<Integer> splitIntoFibonacci(String S) {
        List<Integer> list = new ArrayList<Integer>();
        backtrack(list, S, S.length(), 0, 0, 0);
        return list;
    }

    public boolean backtrack(List<Integer> list, String S, int length, int index, int sum, int prev) {
        if (index == length) {
            return list.size() >= 3;
        }
        long currLong = 0;
        for (int i = index; i < length; i++) {
            if (i > index && S.charAt(index) == '0') {
                break;
            }
            currLong = currLong * 10 + S.charAt(i) - '0';
            if (currLong > Integer.MAX_VALUE) {
                break;
            }
            int curr = (int) currLong;
            if (list.size() >= 2) {
                if (curr < sum) {
                    continue;
                } else if (curr > sum) {
                    break;
                }
            }
            list.add(curr);
            if (backtrack(list, S, length, i + 1, prev + curr, curr)) {
                return true;
            } else {
                list.remove(list.size() - 1);
            }
        }
        return false;
    }


}
