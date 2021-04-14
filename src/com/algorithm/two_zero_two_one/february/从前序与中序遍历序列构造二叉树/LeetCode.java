package com.algorithm.two_zero_two_one.february.从前序与中序遍历序列构造二叉树;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @author Ming
 * @date 2021/2/9 - 0:21
 * @describe
 */
public class LeetCode {
    /**
     * 思路：递归
     * 执行用时：5 ms, 在所有 Java 提交中击败了26.45%的用户
     * 内存消耗：38.3 MB, 在所有 Java 提交中击败了88.49%的用户
     */
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        return dfs(preorder, inorder, 0, preorder.length - 1, 0, inorder.length - 1);
    }

    public TreeNode dfs(int[] preorder, int[] inorder, int pStart, int pEnd, int iStart, int iEnd) {
        if (pStart > pEnd) return null;
        if (pStart == pEnd) return new TreeNode(preorder[pStart]);
        int min = 0;
        while (inorder[iStart + min] != preorder[pStart]) min++;
        TreeNode node = new TreeNode(preorder[pStart]);
        node.left = dfs(preorder, inorder, pStart + 1, pStart + min, iStart, iStart + min -1);
        node.right = dfs(preorder, inorder, pStart + min + 1, pEnd, iStart + min + 1, iEnd);
        return node;
    }

    /**
     * 迭代
     */
    class Solution {
        public TreeNode buildTree(int[] preorder, int[] inorder) {
            if (preorder == null || preorder.length == 0) {
                return null;
            }
            TreeNode root = new TreeNode(preorder[0]);
            Deque<TreeNode> stack = new LinkedList<TreeNode>();
            stack.push(root);
            int inorderIndex = 0;
            for (int i = 1; i < preorder.length; i++) {
                int preorderVal = preorder[i];
                TreeNode node = stack.peek();
                if (node.val != inorder[inorderIndex]) {
                    node.left = new TreeNode(preorderVal);
                    stack.push(node.left);
                } else {
                    while (!stack.isEmpty() && stack.peek().val == inorder[inorderIndex]) {
                        node = stack.pop();
                        inorderIndex++;
                    }
                    node.right = new TreeNode(preorderVal);
                    stack.push(node.right);
                }
            }
            return root;
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
