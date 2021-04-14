package com.algorithm.two_zero_two_one.january.交换字符串中的元素;

import java.util.*;

/**
 * @author Ming
 * @date 2021/1/11 - 12:19
 * @describe
 */
public class LeetCode {
    /**
     * 给你一个字符串 s，以及该字符串中的一些「索引对」数组 pairs，其中 pairs[i] = [a, b] 表示字符串中的两个索引（编号从 0 开始）。
     *
     * 你可以 任意多次交换 在 pairs 中任意一对索引处的字符。
     *
     * 返回在经过若干次交换后，s 可以变成的按字典序最小的字符串。
     *
     * 输入：s = "dcab", pairs = [[0,3],[1,2]]
     * 输出："bacd"
     * 解释：
     * 交换 s[0] 和 s[3], s = "bcad"
     * 交换 s[1] 和 s[2], s = "bacd"
     *
     */
    /**
     * 思路：图论求再排序
     * A 了 百分之 90+
     */
    public String smallestStringWithSwaps(String s, List<List<Integer>> pairs) {
        char[] chars = s.toCharArray();
        int length = s.length();
        int[][] path = new int[s.length()][s.length()];
        pairs.forEach(list -> {
            path[list.get(0)][list.get(1)] = 1;
            path[list.get(1)][list.get(0)] = 1;
        });
        for (int k = 0; k < length; k++){
            for (int i = 0; i < length; i++){
                for (int j = 0; j < length; j++){
                    if (path[i][k] == 1 && path[k][j] == 1) {
                        path[i][j] = 1;
                    }
                }
            }
        }
        Set<Integer> set = new HashSet<>();
        for (int k = 0; k < length; k++) {
            if (!set.contains(k)) {
                for (int i = 0; i < length; i++) {
                    int min = i;
                    for (int j = i; j < length; j++) {
                        if (path[i][j] == 1 ) {
                            set.add(j);
                            if (chars[min] > chars[j]) {
                                min = j;
                            }
                        }
                    }
                    swag(chars, i, min);
                }
            }
        }
        return new String(chars);
    }
    private void swag(char[] chars, int a, int b){
        char temp = chars[a];
        chars[a] = chars[b];
        chars[b] = temp;
    }


    /**
     * 官方题解
     */
    class Solution {
        public String smallestStringWithSwaps(String s, List<List<Integer>> pairs) {
            DisjointSetUnion dsu = new DisjointSetUnion(s.length());
            for (List<Integer> pair : pairs) {
                dsu.unionSet(pair.get(0), pair.get(1));
            }
            Map<Integer, List<Character>> map = new HashMap<Integer, List<Character>>();
            for (int i = 0; i < s.length(); i++) {
                int parent = dsu.find(i);
                if (!map.containsKey(parent)) {
                    map.put(parent, new ArrayList<Character>());
                }
                map.get(parent).add(s.charAt(i));
            }
            for (Map.Entry<Integer, List<Character>> entry : map.entrySet()) {
                Collections.sort(entry.getValue(), new Comparator<Character>() {
                    public int compare(Character c1, Character c2) {
                        return c2 - c1;
                    }
                });
            }
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < s.length(); i++) {
                int x = dsu.find(i);
                List<Character> list = map.get(x);
                sb.append(list.remove(list.size() - 1));
            }
            return sb.toString();
        }
    }

    class DisjointSetUnion {
        int[] f;
        int[] rank;
        int n;

        public DisjointSetUnion(int n) {
            this.n = n;
            rank = new int[n];
            Arrays.fill(rank, 1);
            f = new int[n];
            for (int i = 0; i < n; i++) {
                f[i] = i;
            }
        }

        public int find(int x) {
            return f[x] == x ? x : (f[x] = find(f[x]));
        }

        public void unionSet(int x, int y) {
            int fx = find(x), fy = find(y);
            if (fx == fy) {
                return;
            }
            if (rank[fx] < rank[fy]) {
                int temp = fx;
                fx = fy;
                fy = temp;
            }
            rank[fx] += rank[fy];
            f[fy] = fx;
        }
    }

}
