package com.algorithm.two_zero_two_zero.september.two_nine;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Ming
 * @date 2020/9/29 - 14:14
 * @describe
 */
public class LeetCode {

    /**
     * 给定一个二叉树，返回它的 后序 遍历。
     *
     * 示例:
     *
     * 输入: [1,null,2,3]
     *    1
     *     \
     *      2
     *     /
     *    3
     *
     * 输出: [3,2,1]
     *
     */
    /**
     * 自己的思路：递归
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
     * 内存消耗：37.5 MB, 在所有 Java 提交中击败了5.09%的用户
     */
    List<Integer> list = new ArrayList<>();
    public List<Integer> postorderTraversal(TreeNode root) {
        if (root==null) return list;
        dfs(root);
        return list;
    }

    void dfs(TreeNode node){
        if (node.left!=null) postorderTraversal(node.left);
        if (node.right!=null) postorderTraversal(node.right);
        list.add(node.val);
    }

    /**
     * 迭代方法
     */
    class Solution {
        public List<Integer> postorderTraversal(TreeNode root) {
            List<Integer> res = new ArrayList<Integer>();
            if (root == null) {
                return res;
            }

            Deque<TreeNode> stack = new LinkedList<TreeNode>();
            TreeNode prev = null;
            while (root != null || !stack.isEmpty()) {
                while (root != null) {
                    stack.push(root);
                    root = root.left;
                }
                root = stack.pop();
                if (root.right == null || root.right == prev) {
                    res.add(root.val);
                    prev = root;
                    root = null;
                } else {
                    stack.push(root);
                    root = root.right;
                }
            }
            return res;
        }
    }

    /**
     * Morris 遍历
     */
    class Solution1 {
        public List<Integer> postorderTraversal(TreeNode root) {
            List<Integer> res = new ArrayList<Integer>();
            if (root == null) {
                return res;
            }

            TreeNode p1 = root, p2 = null;

            while (p1 != null) {
                p2 = p1.left;
                if (p2 != null) {
                    while (p2.right != null && p2.right != p1) {
                        p2 = p2.right;
                    }
                    if (p2.right == null) {
                        p2.right = p1;
                        p1 = p1.left;
                        continue;
                    } else {
                        p2.right = null;
                        addPath(res, p1.left);
                    }
                }
                p1 = p1.right;
            }
            addPath(res, root);
            return res;
        }

        public void addPath(List<Integer> res, TreeNode node) {
            List<Integer> tmp = new ArrayList<Integer>();
            while (node != null) {
                tmp.add(node.val);
                node = node.right;
            }
            for (int i = tmp.size() - 1; i >= 0; --i) {
                res.add(tmp.get(i));
            }
        }
    }

}
