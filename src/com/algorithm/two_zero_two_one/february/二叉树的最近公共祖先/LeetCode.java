package com.algorithm.two_zero_two_one.february.二叉树的最近公共祖先;

import javax.swing.tree.TreeNode;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author Ming
 * @date 2021/2/1 - 18:08
 * @describe
 */
public class LeetCode {
    /**
     * 给定一个二叉树, 找到该树中两个指定节点的最近公共祖先。
     * <p>
     * 百度百科中最近公共祖先的定义为：“对于有根树 T 的两个结点 p、q，最近公共祖先表示为一个结点 x，满足 x 是 p、q 的祖先且 x 的深度尽可能大（一个节点也可以是它自己的祖先）。”
     * <p>
     * 例如，给定如下二叉树:  root = [3,5,1,6,2,0,8,null,null,7,4]
     * <p>
     * 输入: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 1
     * 输出: 3
     * 解释: 节点 5 和节点 1 的最近公共祖先是节点 3。
     * <p>
     * 输入: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 4
     * 输出: 5
     * 解释: 节点 5 和节点 4 的最近公共祖先是节点 5。因为根据定义最近公共祖先节点可以为节点本身。
     */
    /**
     * 自己的思路：遍历并且用hash来存储父节点
     * 执行用时：10 ms, 在所有 Java 提交中击败了22.56%的用户
     * 内存消耗：39.9 MB, 在所有 Java 提交中击败了96.55%的用户
     */
    Set<TreeNode> set = new HashSet<>();
    TreeNode rs = null;
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        dfs(root, p);
        System.out.println(set.size());
        dfs2(root, q);
        return rs;
    }

    private boolean dfs2(TreeNode node, TreeNode target){
        if (node == null) return false;
        if (node == target || dfs2(node.left, target) || dfs2(node.right, target)) {
            if (rs == null && set.contains(node)) rs = node;
            return true;
        }
        return false;
    }

    private boolean dfs(TreeNode node, TreeNode target){
        if (node == null) return false;
        if (node == target || dfs(node.left, target) || dfs(node.right, target)) {
            set.add(node);
            return true;
        }
        return false;
    }

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    /**
     * 存储父节点的信息
     */
    class Solution {
        Map<Integer, TreeNode> parent = new HashMap<Integer, TreeNode>();
        Set<Integer> visited = new HashSet<Integer>();

        public void dfs(TreeNode root) {
            //存储父节点
            if (root.left != null) {
                parent.put(root.left.val, root);
                dfs(root.left);
            }
            if (root.right != null) {
                parent.put(root.right.val, root);
                dfs(root.right);
            }
        }

        public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
            dfs(root);
            while (p != null) {
                visited.add(p.val);
                p = parent.get(p.val);
            }
            while (q != null) {
                if (visited.contains(q.val)) {
                    return q;
                }
                q = parent.get(q.val);
            }
            return null;
        }
    }

    /**
     * 递归
     */
    class Solution1 {

        private TreeNode ans;

        public Solution1() {
            this.ans = null;
        }

        private boolean dfs(TreeNode root, TreeNode p, TreeNode q) {
            if (root == null) return false;
            boolean lson = dfs(root.left, p, q);
            boolean rson = dfs(root.right, p, q);
            if ((lson && rson) || ((root.val == p.val || root.val == q.val) && (lson || rson))) {
                ans = root;
            }
            return lson || rson || (root.val == p.val || root.val == q.val);
        }

        public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
            this.dfs(root, p, q);
            return this.ans;
        }
    }

}
