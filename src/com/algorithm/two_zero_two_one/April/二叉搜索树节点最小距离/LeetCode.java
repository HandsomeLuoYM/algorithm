package com.algorithm.two_zero_two_one.April.二叉搜索树节点最小距离;

/**
 * @author Ming
 * @date 2021/4/13 - 1:24
 * @describe
 */
public class LeetCode {
    /**
     * 给你一个二叉搜索树的根节点 root ，返回 树中任意两不同节点值之间的最小差值 。
     * 输入：root = [4,2,6,1,3]
     * 输出：1
     * <p>
     * 输入：root = [1,0,48,null,null,12,49]
     * 输出：1
     */
    /**
     * 思路：找到最小，然后中序遍历
     * 执行用时： 0 ms , 在所有 Java 提交中击败了 100.00% 的用户
     * 内存消耗： 36.2 MB , 在所有 Java 提交中击败了 19.45% 的用户
     */
    int now;
    int rs = Integer.MAX_VALUE;
    public int minDiffInBST(TreeNode root) {
        TreeNode left = root;
        while (left.left != null) {
            left = left.left;
        }
        now = left.val;
        dfs(root);
        return rs;
    }
    private void dfs(TreeNode node) {
        if (node == null) return;
        dfs(node.left);
        if (node.val != now) {
            rs = Math.min(rs, node.val - now);
            now = node.val;
        }
        dfs(node.right);
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


