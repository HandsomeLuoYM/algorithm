package com.algorithm.september.one_nine;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author Ming
 * @date 2020/9/19 - 11:27
 * @describe 404. 左叶子之和
 */
public class LeetCode {

    /**
     * 计算给定二叉树的所有左叶子之和
     *
     *     3
     *    / \
     *   9  20
     *     /  \
     *    15   7
     *
     * 在这个二叉树中，有两个左叶子，分别是 9 和 15，所以返回 24
     */

    /**
     * 自己的做法，递归遍历，当发现左子树时，总数就加上他
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
     * 内存消耗：36.8 MB, 在所有 Java 提交中击败了57.10%的用户
     */
    int all = 0;
    public int sumOfLeftLeaves(TreeNode root) {
        if (root == null) return all;
        if (root.right!=null) sumOfLeftLeaves(root.right);
        if (root.left!=null) {
            if(root.left.right==null&&root.left.left==null) all += root.left.val;
            sumOfLeftLeaves(root.left);
        }
        return all;
    }

    /**
     * 广度优先算法
     */
    class Solution {
        public int sumOfLeftLeaves(TreeNode root) {
            if (root == null) {
                return 0;
            }
            Queue<TreeNode> queue = new LinkedList<TreeNode>();
            queue.offer(root);
            int ans = 0;
            while (!queue.isEmpty()) {
                TreeNode node = queue.poll();
                if (node.left != null) {
                    if (isLeafNode(node.left)) {
                        ans += node.left.val;
                    } else {
                        queue.offer(node.left);
                    }
                }
                if (node.right != null) {
                    if (!isLeafNode(node.right)) {
                        queue.offer(node.right);
                    }
                }
            }
            return ans;
        }

        public boolean isLeafNode(TreeNode node) {
            return node.left == null && node.right == null;
        }
    }
}
