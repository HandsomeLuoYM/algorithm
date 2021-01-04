package com.algorithm.two_zero_two_zero.september.three_zero;

/**
 * @author Ming
 * @date 2020/9/30 - 9:42
 * @describe
 */
public class LeetCode {

    /**
     * 给定二叉搜索树（BST）的根节点和要插入树中的值，将值插入二叉搜索树。
     * 返回插入后二叉搜索树的根节点。 输入数据保证，新值和原始二叉搜索树中的任意节点值都不同。
     *
     * 注意，可能存在多种有效的插入方式，只要树在插入后仍保持为二叉搜索树即可。 你可以返回任意有效的结果
     *
     */
    /**
     * 自己的思路：递归遍历判断大小
     *
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
     * 内存消耗：39.4 MB, 在所有 Java 提交中击败了65.69%的用户
     */
    public TreeNode insertIntoBST(TreeNode root, int val) {
        if (root == null) return new TreeNode(val);
        insert(root,val);
        return root;
    }

    private void insert(TreeNode node, int val) {
        if (node.val>val){
            if (node.left==null){
                node.left = new TreeNode(val);
                return;
            }
            insert(node.left,val);
        }else {
            if (node.right==null){
                node.right = new TreeNode(val);
                return;
            }
            insert(node.right,val);
        }
    }

    /**
     * 非递归做法
     */
    class Solution {
        public TreeNode insertIntoBST(TreeNode root, int val) {
            if (root == null) {
                return new TreeNode(val);
            }
            TreeNode pos = root;
            while (pos != null) {
                if (val < pos.val) {
                    if (pos.left == null) {
                        pos.left = new TreeNode(val);
                        break;
                    } else {
                        pos = pos.left;
                    }
                } else {
                    if (pos.right == null) {
                        pos.right = new TreeNode(val);
                        break;
                    } else {
                        pos = pos.right;
                    }
                }
            }
            return root;
        }
    }
}
