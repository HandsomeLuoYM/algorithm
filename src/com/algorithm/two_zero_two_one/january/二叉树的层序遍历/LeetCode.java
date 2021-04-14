package com.algorithm.two_zero_two_one.january.二叉树的层序遍历;

import javax.swing.tree.TreeNode;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @author Ming
 * @date 2021/1/31 - 11:50
 * @describe
 */
public class LeetCode {

    /**
     * 给你一个二叉树，请你返回其按 层序遍历 得到的节点值。 （即逐层地，从左到右访问所有节点）。
     * <p>
     * 二叉树：[3,9,20,null,null,15,7],
     * <p>
     * 3
     * / \
     * 9  20
     * /  \
     * 15   7
     * 返回其层序遍历结果：
     * <p>
     * [
     * [3],
     * [9,20],
     * [15,7]
     * ]
     */
    /**
     * 思路：一次遍历，广度优先
     * 执行用时：1 ms, 在所有 Java 提交中击败了94.16%的用户
     * 内存消耗：38.8 MB, 在所有 Java 提交中击败了33.04%的用户
     */
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> rs = new ArrayList<>();
        if (root == null) return rs;
        int num;
        TreeNode now;
        List<Integer> newList;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            num = queue.size();
            newList = new ArrayList<>();
            while (num > 0){
                now = queue.poll();
                newList.add(now.val);
                if (now.left != null) queue.add(now.left);
                if (now.right != null) queue.add(now.right);
                num--;
            }
            rs.add(newList);
        }
        return rs;
    }

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }
}
