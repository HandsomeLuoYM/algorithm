package com.algorithm.november.one_serven;

import java.util.*;

/**
 * @author Ming
 * @date 2020/11/17 - 0:33
 * @describe 1030. 距离顺序排列矩阵单元格
 */
public class LeetCode {
    /**
     * 给出 R 行 C 列的矩阵，其中的单元格的整数坐标为 (r, c)，满足 0 <= r < R 且 0 <= c < C。
     * <p>
     * 另外，我们在该矩阵中给出了一个坐标为 (r0, c0) 的单元格。
     * <p>
     * 返回矩阵中的所有单元格的坐标，并按到 (r0, c0) 的距离从最小到最大的顺序排，其中，两单元格(r1, c1) 和 (r2, c2) 之间的距离是曼哈顿距离，
     * |r1 - r2| + |c1 - c2|。（你可以按任何满足此条件的顺序返回答案。）
     * <p>
     * 输入：R = 1, C = 2, r0 = 0, c0 = 0
     * 输出：[[0,0],[0,1]]
     * 解释：从 (r0, c0) 到其他单元格的距离为：[0,1]
     * <p>
     * 输入：R = 2, C = 2, r0 = 0, c0 = 1
     * 输出：[[0,1],[0,0],[1,1],[1,0]]
     * 解释：从 (r0, c0) 到其他单元格的距离为：[0,1,1,2]
     * [[0,1],[1,1],[0,0],[1,0]] 也会被视作正确答案。
     * <p>
     * 输入：R = 2, C = 3, r0 = 1, c0 = 2
     * 输出：[[1,2],[0,2],[1,1],[0,1],[1,0],[0,0]]
     * 解释：从 (r0, c0) 到其他单元格的距离为：[0,1,1,2,2,3]
     * 其他满足题目要求的答案也会被视为正确，例如 [[1,2],[1,1],[0,2],[1,0],[0,1],[0,0]]。
     */
    /**
     * 我的思路：
     *      先存储处理，再排序
     * 执行用时：14 ms, 在所有 Java 提交中击败了73.79%的用户
     * 内存消耗：40.9 MB, 在所有 Java 提交中击败了49.71%的用户
     */
    public int[][] allCellsDistOrder(int R, int C, int r0, int c0) {
        int[][] ret = new int[R * C][];
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                ret[i * C + j] = new int[]{i, j};
            }
        }
        Arrays.sort(ret, new Comparator<int[]>() {
            @Override
            public int compare(int[] a, int[] b) {
                return (Math.abs(a[0] - r0) + Math.abs(a[1] - c0)) - (Math.abs(b[0] - r0) + Math.abs(b[1] - c0));
            }
        });
        return ret;
    }

    /**
     * 思路：统计各个距离中的值，最后再统一处理
     */
    class Solution {
        public int[][] allCellsDistOrder(int R, int C, int r0, int c0) {
            int maxDist = Math.max(r0, R - 1 - r0) + Math.max(c0, C - 1 - c0);
            List<List<int[]>> bucket = new ArrayList<List<int[]>>();
            for (int i = 0; i <= maxDist; i++) {
                bucket.add(new ArrayList<int[]>());
            }

            for (int i = 0; i < R; i++) {
                for (int j = 0; j < C; j++) {
                    int d = dist(i, j, r0, c0);
                    bucket.get(d).add(new int[]{i, j});
                }
            }
            int[][] ret = new int[R * C][];
            int index = 0;
            for (int i = 0; i <= maxDist; i++) {
                for (int[] it : bucket.get(i)) {
                    ret[index++] = it;
                }
            }
            return ret;
        }

        public int dist(int r1, int c1, int r2, int c2) {
            return Math.abs(r1 - r2) + Math.abs(c1 - c2);
        }
    }

    /**
     * 我的思路二：
     * 几何排序，先到的距离小
     */
    class Solution1 {
        int[] dr = {1, 1, -1, -1};
        int[] dc = {1, -1, -1, 1};

        public int[][] allCellsDistOrder(int R, int C, int r0, int c0) {
            //计算最大距离
            int maxDist = Math.max(r0, R - 1 - r0) + Math.max(c0, C - 1 - c0);
            int[][] ret = new int[R * C][];
            int row = r0, col = c0;
            int index = 0;
            ret[index++] = new int[]{row, col};
            for (int dist = 1; dist <= maxDist; dist++) {
                row--;
                for (int i = 0; i < 4; i++) {
                    while ((i % 2 == 0 && row != r0) || (i % 2 != 0 && col != c0)) {
                        if (row >= 0 && row < R && col >= 0 && col < C) {
                            ret[index++] = new int[]{row, col};
                        }
                        row += dr[i];
                        col += dc[i];
                    }
                }
            }
            return ret;
        }
    }


}
