package com.algorithm.november.two_four;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @author Ming
 * @date 2020/11/24 - 10:27
 * @describe
 */
public class LeetCode {

    /**
     * 完全二叉树的定义如下：在完全二叉树中，除了最底层节点可能没填满外，其余每层节点数都达到最大值，
     * 并且最下面一层的节点都集中在该层最左边的若干位置。若最底层为第 h 层，则该层包含 1~ 2h 个节点。
     *
     *
     * 输入:
     *     1
     *    / \
     *   2   3
     *  / \  /
     * 4  5 6
     *
     * 输出: 6
     */
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    /**
     * 自己的做法：
     *      遍历增加
     * 执行用时：4 ms, 在所有 Java 提交中击败了12.33%的用户
     * 内存消耗：41.2 MB, 在所有 Java 提交中击败了55.29%的用户
     */
    public int countNodes(TreeNode root) {
        if(root == null) return 0;
        Queue<TreeNode> queue = new LinkedList();
        queue.add(root);
        int number = 0;
        while (!queue.isEmpty()){
            int now = queue.size(), thisNumber = 0;
            number += now;
            for (int i = 0; i<now; i++){
                TreeNode poll = queue.poll();
                if (poll.left != null){
                    queue.add(poll.left);
                    thisNumber++;
                }else {
                    return number + thisNumber;
                }
                if (poll.right != null){
                    queue.add(poll.right);
                    thisNumber++;
                }else {
                    return number + thisNumber;
                }
            }
        }
        return 0;
    }

    /**
     * 官方题解一：
     *      二分查找 + 位运算
     */
    class Solution {
        public int countNodes(TreeNode root) {
            if (root == null) {
                return 0;
            }
            int level = 0;
            TreeNode node = root;
            //查找最深的层数
            while (node.left != null) {
                level++;
                node = node.left;
            }
            int low = 1 << level, high = (1 << (level + 1)) - 1;
            while (low < high) {
                int mid = (high - low + 1) / 2 + low;
                if (exists(root, level, mid)) {
                    low = mid;
                } else {
                    high = mid - 1;
                }
            }
            return low;
        }

        public boolean exists(TreeNode root, int level, int k) {
            int bits = 1 << (level - 1);
            TreeNode node = root;
            while (node != null && bits > 0) {
                if ((bits & k) == 0) {
                    node = node.left;
                } else {
                    node = node.right;
                }
                bits >>= 1;
            }
            return node != null;
        }
    }

}
