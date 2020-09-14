package com.algorithm.august.one_serven;

/**
 * @author Ming
 * @date 2020/8/17 - 13:20
 * @describe
 */
public class LeetCode {

    /**
     * 自己做的
     * 执行用时：1 ms, 在所有 Java 提交中击败了99.76%的用户
     * 内存消耗：39.9 MB, 在所有 Java 提交中击败了55.55%的用户
     */
    public boolean isBalanced(TreeNode root) {
        return judge(root)!=-1;
    }

    public int judge(TreeNode treeNode){
        if (null == treeNode) return 0;
        int left = judge(treeNode.left) ,right = judge(treeNode.right);

        if (-1 == left || -1 == right || Math.abs(left-right)>1) return -1;
        return left>right?left+1:right+1;
    }

    /**
     * 官方另一种题解（较差）
     */
    class Solution {
        public boolean isBalanced(TreeNode root) {
            if (root == null) {
                return true;
            } else {
                return Math.abs(height(root.left) - height(root.right)) <= 1 && isBalanced(root.left) && isBalanced(root.right);
            }
        }

        public int height(TreeNode root) {
            if (root == null) {
                return 0;
            } else {
                return Math.max(height(root.left), height(root.right)) + 1;
            }
        }
    }


}
