package com.algorithm.two_zero_two_zero.november.one_six;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * @author Ming
 * @date 2020/11/16 - 1:30
 * @describe
 */
public class LeetCode {

    /**
     * 假设有打乱顺序的一群人站成一个队列。 每个人由一个整数对(h, k)表示，其中h是这个人的身高，k是排在这个人前面且身高大于或等于h的人数。 编写一个算法来重建这个队列。
     *
     * 注意：
     * 总人数少于1100人。
     *
     * 输入:
     * [[7,0], [4,4], [7,1], [5,0], [6,1], [5,2]]
     *
     * 输出:
     * [[5,0], [7,0], [5,2], [6,1], [4,4], [7,1]]
     *
     */
    /**
     * 自己的做法：
     *      先排序，然后从小到大进行位置的查找
     * 执行用时：20 ms, 在所有 Java 提交中击败了10.74%的用户
     * 内存消耗：39 MB, 在所有 Java 提交中击败了98.63%的用户
     */
    public int[][] reconstructQueue(int[][] people) {
        Arrays.sort(people, (person1, person2) -> {
                if (person1[0] != person2[0]) {
                    return person1[0] - person2[0];
                } else {
                    return person1[1] - person2[1];
                }
            });
        int length = people.length;
        int[][] result = new int[length][2];
        for (int i = 0; i < result.length; i++) {
            result[i][0] = -1;
        }
        for (int i = 0; i<length; i++){
            int index = people[i][1];
            for (int j = 0; j < length; j++){
                if (index == 0 && result[j][0] == -1 ) {
                    result[j][0] = people[i][0];
                    result[j][1] = people[i][1];
                    break;
                }
                if (result[j][0] == -1 || result[j][0] == people[i][0]){
                    index--;
                }
            }
        }
        return result;
    }

    /**
     * 官方题解：和我思路一样
     * 从低到高考虑
     */
    class Solution {
        public int[][] reconstructQueue(int[][] people) {
            Arrays.sort(people, new Comparator<int[]>() {
                @Override
                public int compare(int[] person1, int[] person2) {
                    if (person1[0] != person2[0]) {
                        return person1[0] - person2[0];
                    } else {
                        return person2[1] - person1[1];
                    }
                }
            });
            int n = people.length;
            int[][] ans = new int[n][];
            for (int[] person : people) {
                int spaces = person[1] + 1;
                for (int i = 0; i < n; ++i) {
                    if (ans[i] == null) {
                        --spaces;
                        if (spaces == 0) {
                            ans[i] = person;
                            break;
                        }
                    }
                }
            }
            return ans;
        }
    }
    /**
     * 官方题解：
     * 从高到低考虑
     */
    class Solution1 {
        public int[][] reconstructQueue(int[][] people) {
            Arrays.sort(people, new Comparator<int[]>() {
                @Override
                public int compare(int[] person1, int[] person2) {
                    if (person1[0] != person2[0]) {
                        return person2[0] - person1[0];
                    } else {
                        return person1[1] - person2[1];
                    }
                }
            });
            List<int[]> ans = new ArrayList<int[]>();
            for (int[] person : people) {
                ans.add(person[1], person);
            }
            return ans.toArray(new int[ans.size()][]);
        }
    }


    public static void main(String[] args) {
        int[][] ints = {{7, 0}, {4, 4}, {7, 1}, {5, 0}, {6, 1}, {5, 2}};
        LeetCode leetCode = new LeetCode();
        leetCode.reconstructQueue(ints);
    }
}
