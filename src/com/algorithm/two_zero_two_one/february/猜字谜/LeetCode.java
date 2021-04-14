package com.algorithm.two_zero_two_one.february.猜字谜;

import java.util.*;

/**
 * @author Ming
 * @date 2021/2/26 - 0:12
 * @describe
 */
public class LeetCode {
    /**
     * 外国友人仿照中国字谜设计了一个英文版猜字谜小游戏，请你来猜猜看吧。
     *
     * 字谜的迷面 puzzle 按字符串形式给出，如果一个单词 word 符合下面两个条件，那么它就可以算作谜底：
     *
     * 单词 word 中包含谜面 puzzle 的第一个字母。
     * 单词 word 中的每一个字母都可以在谜面 puzzle 中找到。
     * 例如，如果字谜的谜面是 "abcdefg"，那么可以作为谜底的单词有 "faced", "cabbage", 和 "baggage"；而 "beefed"（不含字母 "a"）以及 "based"（其中的 "s" 没有出现在谜面中）。
     * 返回一个答案数组 answer，数组中的每个元素 answer[i] 是在给出的单词列表 words 中可以作为字谜迷面 puzzles[i] 所对应的谜底的单词数目。
     *
     *  
     *
     * 示例：
     *
     * 输入：
     * words = ["aaaa","asas","able","ability","actt","actor","access"],
     * puzzles = ["aboveyz","abrodyz","abslute","absoryz","actresz","gaswxyz"]
     * 输出：[1,1,3,2,4,0]
     * 解释：
     * 1 个单词可以作为 "aboveyz" 的谜底 : "aaaa"
     * 1 个单词可以作为 "abrodyz" 的谜底 : "aaaa"
     * 3 个单词可以作为 "abslute" 的谜底 : "aaaa", "asas", "able"
     * 2 个单词可以作为 "absoryz" 的谜底 : "aaaa", "asas"
     * 4 个单词可以作为 "actresz" 的谜底 : "aaaa", "asas", "actt", "access"
     * 没有单词可以作为 "gaswxyz" 的谜底，因为列表中的单词都不含字母 'g'。
     *
     */
    /**
     * 超时：暴力
     */
    public List<Integer> findNumOfValidWords(String[] words, String[] puzzles) {
        List<int[]> list = new LinkedList<>();
        int[] index, next, now;
        for (String word : words) {
            index = new int[26];
            for (int i = 0; i < word.length(); i++) {
                index[word.charAt(i) - 'a']++;
            }
            list.add(index);
        }
        Iterator<int[]> iterator;
        List<Integer> rs = new ArrayList<>();
        int number;
        for (String puzzle : puzzles) {
            number = 0;
            iterator = list.iterator();
            now = new int[26];
            for (int i = 0; i < puzzle.length(); i++) {
                now[puzzle.charAt(i) - 'a']++;
            }
            while (iterator.hasNext()) {
                next = iterator.next();
                if (next[puzzle.charAt(0) - 'a'] == 0) {
                    continue;
                }
                for (int i = 0; i < 26; i++) {
                    if (next[i] != 0 && now[i] == 0) {
                        number--;
                        break;
                    }
                }
                number++;
            }
            rs.add(number);
        }
        return rs;
    }
    class Solution {
        public List<Integer> findNumOfValidWords(String[] words, String[] puzzles) {
            Map<Integer, Integer> frequency = new HashMap<Integer, Integer>();

            for (String word : words) {
                int mask = 0;
                for (int i = 0; i < word.length(); ++i) {
                    char ch = word.charAt(i);
                    mask |= (1 << (ch - 'a'));
                }
                if (Integer.bitCount(mask) <= 7) {
                    frequency.put(mask, frequency.getOrDefault(mask, 0) + 1);
                }
            }

            List<Integer> ans = new ArrayList<Integer>();
            for (String puzzle : puzzles) {
                int total = 0;

                // 枚举子集方法一
                // for (int choose = 0; choose < (1 << 6); ++choose) {
                //     int mask = 0;
                //     for (int i = 0; i < 6; ++i) {
                //         if ((choose & (1 << i)) != 0) {
                //             mask |= (1 << (puzzle.charAt(i + 1) - 'a'));
                //         }
                //     }
                //     mask |= (1 << (puzzle.charAt(0) - 'a'));
                //     if (frequency.containsKey(mask)) {
                //         total += frequency.get(mask);
                //     }
                // }

                // 枚举子集方法二
                int mask = 0;
                for (int i = 1; i < 7; ++i) {
                    mask |= (1 << (puzzle.charAt(i) - 'a'));
                }
                int subset = mask;
                do {
                    int s = subset | (1 << (puzzle.charAt(0) - 'a'));
                    if (frequency.containsKey(s)) {
                        total += frequency.get(s);
                    }
                    subset = (subset - 1) & mask;
                } while (subset != mask);

                ans.add(total);
            }
            return ans;
        }

        /**
         * 执行用时：85 ms, 在所有 Java 提交中击败了70.65%的用户
         * 内存消耗：53.8 MB, 在所有 Java 提交中击败了20.65%的用户
         */
        public List<Integer> findNumOfValidWords1(String[] words, String[] puzzles) {
            List<Integer> res = new ArrayList<>();

            // 2^26的数组会爆内存。因此用HashMap，存放每种状态的个数
            Map<Integer, Integer> state = new HashMap<>();
            for (int i = 0; i < words.length; i++) {
                String s = words[i];
                int temp = 0;
                for (int j = 0; j < s.length(); j++) {
                    temp = temp | (1 << s.charAt(j) - 'a');
                }
                state.put(temp, state.getOrDefault(temp, 0) + 1);
            }

            for (int i = 0; i < puzzles.length; i++) {
                String s = puzzles[i];
                int temp = 0;
                for (int j = 0; j < s.length(); j++) {
                    temp = temp | (1 << s.charAt(j) - 'a');
                }
                int cnt = 0;
                // 枚举子集
                for (int k = temp; k != 0; k = (k - 1) & temp) {
                    // 还得满足条件一
                    if ((1 << (s.charAt(0) - 'a') & k) != 0)
                        cnt += state.getOrDefault(k, 0);
                }
                res.add(cnt);
            }
            return res;
        }
    }

    public static void main(String[] args) {
        System.out.println(1<<3);
    }
}
