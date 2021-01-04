package com.algorithm.two_zero_two_zero.september.two_serven;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Ming
 * @date 2020/9/27 - 17:48
 * @describe
 */
public class LeetCode {

    /**
     * 给定一个二叉搜索树, 找到该树中两个指定节点的最近公共祖先。
     *
     * 百度百科中最近公共祖先的定义为：“对于有根树 T 的两个结点 p、q，
     * 最近公共祖先表示为一个结点 x，满足 x 是 p、q 的祖先且 x 的深度尽可能大（一个节点也可以是它自己的祖先）。”
     *
     * 例如，给定如下二叉搜索树:  root = [6,2,8,0,4,7,9,null,null,3,5]
     *
     * 输入: root = [6,2,8,0,4,7,9,null,null,3,5], p = 2, q = 8
     * 输出: 6
     * 解释: 节点 2 和节点 8 的最近公共祖先是 6。
     *
     * 输入: root = [6,2,8,0,4,7,9,null,null,3,5], p = 2, q = 4
     * 输出: 2
     * 解释: 节点 2 和节点 4 的最近公共祖先是 2, 因为根据定义最近公共祖先节点可以为节点本身。
     *
     */

    /**
     * 注意到题目中给出的是一棵「二叉搜索树」，因此我们可以快速地找出树中的某个节点以及从根节点到该节点的路径，例如我们需要找到节点 pp：
     *
     *      我们从根节点开始遍历；
     *
     *      如果当前节点就是 pp，那么成功地找到了节点；
     *
     *      如果当前节点的值大于 pp 的值，说明 pp 应该在当前节点的左子树，因此将当前节点移动到它的左子节点；
     *
     *      如果当前节点的值小于 pp 的值，说明 pp 应该在当前节点的右子树，因此将当前节点移动到它的右子节点。
     *
     */
    class Solution {
        public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
            List<TreeNode> path_p = getPath(root, p);
            List<TreeNode> path_q = getPath(root, q);
            TreeNode ancestor = null;
            for (int i = 0; i < path_p.size() && i < path_q.size(); ++i) {
                if (path_p.get(i) == path_q.get(i)) {
                    ancestor = path_p.get(i);
                } else {
                    break;
                }
            }
            return ancestor;
        }

        public List<TreeNode> getPath(TreeNode root, TreeNode target) {
            List<TreeNode> path = new ArrayList<TreeNode>();
            TreeNode node = root;
            while (node != target) {
                path.add(node);
                if (target.val < node.val) {
                    node = node.left;
                } else {
                    node = node.right;
                }
            }
            path.add(node);
            return path;
        }
    }

    /**
     * 自己的想法：
     *      当两个节点存在根节点两边时，直接返回
     *      当两个都在一边时，进行递归
     *
     * 执行用时：6 ms, 在所有 Java 提交中击败了99.70%的用户
     * 内存消耗：39.4 MB, 在所有 Java 提交中击败了88.48%的用户
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if((p.val>=root.val&&root.val>=q.val)||(p.val<=root.val&&root.val<=q.val)) return root;
        if (p.val<root.val&&q.val<root.val) return lowestCommonAncestor(root.left,p,q);
        if (p.val>root.val&&q.val>root.val) return lowestCommonAncestor(root.right,p,q);
        return null;
    }
    class Solution1 {
        /**
         * 思路与上面一致
         */
        public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
            TreeNode ancestor = root;
            while (true) {
                if (p.val < ancestor.val && q.val < ancestor.val) {
                    ancestor = ancestor.left;
                } else if (p.val > ancestor.val && q.val > ancestor.val) {
                    ancestor = ancestor.right;
                } else {
                    break;
                }
            }
            return ancestor;
        }
    }

}
