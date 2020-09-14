package com.algorithm.september.zero_four;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @author Ming
 * @date 2020/9/4 - 0:44
 * @describe 257. 二叉树的所有路径
 */
public class LeetCode {

    /**
     * 给定一个二叉树，返回所有从根节点到叶子节点的路径。
     *
     * 说明: 叶子节点是指没有子节点的节点。
     *
     * 输入:
     *
     *    1
     *  /   \
     * 2     3
     *  \
     *   5
     *
     * 输出: ["1->2->5", "1->3"]
     *
     */
    /**
     * 自己的解法（深度遍历算法）
     *
     * 思路：设置一个list集合来存放当前走过的路径，每一次遍历都要把当前的位置添加到list中，当没有左右孩子时即已经走到叶子节点了。
     *       然后构造string来记录，在每一次遍历结束时要把当前的位置从list中除去。
     *
     * 执行用时：10 ms, 在所有 Java 提交中击败了53.77%的用户
     * 内存消耗：39.6 MB, 在所有 Java 提交中击败了95.33%的用户
     */
    List<String> all = new ArrayList<String>();

    public List<String> binaryTreePaths(TreeNode root) {
        if (root==null) return all;
        getNode(new ArrayList<Integer>(),root);
        return all;
    }

    public void getNode(List<Integer> now,TreeNode nowTreeNode){
        now.add(nowTreeNode.val);
        //添加路径
        if (nowTreeNode.left==null&&nowTreeNode.right==null) {
            int length = now.size();
            String str = "";
            for (int i=0;i<length;i++){
                str+=now.get(i)+"->";
            }
            str = str.substring(0,str.length()-2);
            all.add(str);
        }
        if (nowTreeNode.right!=null){
            getNode(now,nowTreeNode.right);
        }
        if (nowTreeNode.left!=null){
            getNode(now,nowTreeNode.left);
        }
        now.remove(now.size()-1);
    }

//////////////////////////////////////  官方题解（广度优先算法）  //////////////////////////////////////////
    class Solution {
        public List<String> binaryTreePaths(TreeNode root) {
            //存放满足的路径
            List<String> paths = new ArrayList<String>();
            if (root == null) {
                return paths;
            }
            //两个队列为同步的，每一个节点都对应一个路径
            Queue<TreeNode> nodeQueue = new LinkedList<TreeNode>();
            Queue<String> pathQueue = new LinkedList<String>();

            nodeQueue.offer(root);
            pathQueue.offer(Integer.toString(root.val));

            while (!nodeQueue.isEmpty()) {
                //取出当前要处理的路径（不放回）
                TreeNode node = nodeQueue.poll();
                String path = pathQueue.poll();

                if (node.left == null && node.right == null) {
                    //该路径满足条件
                    paths.add(path);
                } else {
                    //还有孩子
                    //构造路径
                    //构造最新路径并且添加到路径中
                    if (node.left != null) {
                        nodeQueue.offer(node.left);
                        pathQueue.offer(new StringBuffer(path).append("->").append(node.left.val).toString());
                    }
                    //构造最新路径并且添加到路径中
                    if (node.right != null) {
                        nodeQueue.offer(node.right);
                        pathQueue.offer(new StringBuffer(path).append("->").append(node.right.val).toString());
                    }
                }
            }
            return paths;
        }
    }
}
