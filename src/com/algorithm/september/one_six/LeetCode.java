package com.algorithm.september.one_six;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author Ming
 * @date 2020/9/16 - 21:47
 * @describe 226 翻转二叉树
 */
public class LeetCode {

    /**
     * 翻转一棵二叉树。
     */

    /**
     * 自己的实现
     * 思路：递归翻转，深度遍历
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
     * 内存消耗：36.5 MB, 在所有 Java 提交中击败了47.04%的用户
     */
    public TreeNode invertTree(TreeNode root) {
        if(root==null) return null;
        TreeNode node = root.left;
        root.left = root.right;
        root.right = node;
        invertTree(root.right);
        invertTree(root.left);
        return root;
    }

    /**
     * 广度遍历
     */
    class Solution {
        public TreeNode invertTree(TreeNode root) {
            if (root == null) return null;
            Queue<TreeNode> queue = new LinkedList<>();
            queue.offer(root);
            while (!queue.isEmpty()) {
                int size = queue.size();
                for (int i = 0; i < size; i++) {
                    TreeNode node = queue.poll();
                    swap(node);
                    if (node.left != null) queue.offer(node.left);
                    if (node.right != null) queue.offer(node.right);
                }
            }
            return root;
        }

        public void swap(TreeNode node) {
            TreeNode temp = node.left;
            node.left = node.right;
            node.right = temp;
        }
    }

}
