package com.algorithm.two_zero_two_one.february.二叉树的直径;

/**
 * @author Ming
 * @date 2021/2/8 - 13:11
 * @describe
 */
public class LeetCode {
    /**
     * 给定一棵二叉树，你需要计算它的直径长度。一棵二叉树的直径长度是任意两个结点路径长度中的最大值。这条路径可能穿过也可能不穿过根结点。
     * <p>
     * 示例 :
     * 给定二叉树
     * <p>
     * 1
     * / \
     * 2   3
     * / \
     * 4   5
     * 返回 3, 它的长度是路径 [4,2,1,3] 或者 [5,2,1,3]。
     */
    /**
     * 思路：深搜
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
     * 内存消耗：38.2 MB, 在所有 Java 提交中击败了92.88%的用户
     */
    int rs = 0;
    public int diameterOfBinaryTree(TreeNode root) {
        dfs(root);
        return rs;
    }

    private int dfs(TreeNode node){
        if (node == null) return 0;
        int lLength = dfs(node.left);
        int rLength = dfs(node.right);
        rs = Math.max(rs, lLength + rLength + 1);
        return Math.max(lLength, rLength) + 1;
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
