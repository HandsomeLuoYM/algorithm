package com.algorithm.two_zero_two_zero.september.one_four;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Ming
 * @date 2020/9/14 - 13:31
 * @describe
 */
public class LeetCode {
    /**
     * 给定一个二叉树，返回它的中序 遍历。
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
     * 输出: [1,3,2]
     */
    /**
     * 自己的解法，递归
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
     * 内存消耗：38 MB, 在所有 Java 提交中击败了44.30%的用户
     */
    List<Integer> list = new ArrayList<>();
    public List<Integer> inorderTraversal(TreeNode root) {
        if (root==null) return list;
        inorderTraversal(root.left);
        list.add(root.val);
        inorderTraversal(root.right);
        return list;
    }

    /**
     * 非递归方法
     */
    class Solution {
        public List<Integer> inorderTraversal(TreeNode root) {
            List<Integer> res = new ArrayList<Integer>();
            Deque<TreeNode> stk = new LinkedList<TreeNode>();
            while (root != null || !stk.isEmpty()) {
                while (root != null) {
                    stk.push(root);
                    root = root.left;
                }
                root = stk.pop();
                res.add(root.val);
                root = root.right;
            }
            return res;
        }
    }

}
