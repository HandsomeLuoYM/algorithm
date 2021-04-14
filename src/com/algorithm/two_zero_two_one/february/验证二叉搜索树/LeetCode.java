package com.algorithm.two_zero_two_one.february.验证二叉搜索树;

import javax.swing.tree.TreeNode;
import java.util.Deque;
import java.util.LinkedList;

/**
 * @author Ming
 * @date 2021/2/12 - 1:43
 * @describe
 */
public class LeetCode {
    /**
     * 给定一个二叉树，判断其是否是一个有效的二叉搜索树。
     * <p>
     * 假设一个二叉搜索树具有如下特征：
     * <p>
     * 节点的左子树只包含小于当前节点的数。
     * 节点的右子树只包含大于当前节点的数。
     * 所有左子树和右子树自身必须也是二叉搜索树。
     * <p>
     * 示例 1:
     * <p>
     * 输入:
     * 2
     * / \
     * 1   3
     * 输出: true
     */
    /**
     * 思路：
     *      递归中序遍历
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
     * 内存消耗：38.1 MB, 在所有 Java 提交中击败了46.07%的用户
     */
    Integer before;
    public boolean isValidBST(TreeNode root) {
        if (root == null) return true;
        if (!isValidBST(root.left)) return false;
        if (before != null && before >= root.val) return false;
        before = root.val;
        return isValidBST(root.right);
    }

    /**
     * 递归：记录边界区
     */
    class Solution {
        public boolean isValidBST(TreeNode root) {
            return isValidBST(root, Long.MIN_VALUE, Long.MAX_VALUE);
        }

        public boolean isValidBST(TreeNode node, long lower, long upper) {
            if (node == null) {
                return true;
            }
            if (node.val <= lower || node.val >= upper) {
                return false;
            }
            return isValidBST(node.left, lower, node.val) && isValidBST(node.right, node.val, upper);
        }
    }


    /**
     * 迭代遍历
     */
    class Solution1 {
        public boolean isValidBST(TreeNode root) {
            Deque<TreeNode> stack = new LinkedList<TreeNode>();
            double inorder = -Double.MAX_VALUE;

            while (!stack.isEmpty() || root != null) {
                while (root != null) {
                    stack.push(root);
                    root = root.left;
                }
                root = stack.pop();
                // 如果中序遍历得到的节点的值小于等于前一个 inorder，说明不是二叉搜索树
                if (root.val <= inorder) {
                    return false;
                }
                inorder = root.val;
                root = root.right;
            }
            return true;
        }
    }

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
}
