package com.algorithm.two_zero_two_zero.september.two_four;

import java.util.*;

/**
 * @author Ming
 * @date 2020/9/24 - 12:43
 * @describe 501 二叉搜索树的众数
 */
public class LeetCode {

    /**
     * 给定一个有相同值的二叉搜索树（BST），找出 BST 中的所有众数（出现频率最高的元素）。
     *
     * 假定 BST 有如下定义：
     *
     * 结点左子树中所含结点的值小于等于当前结点的值
     * 结点右子树中所含结点的值大于等于当前结点的值
     * 左子树和右子树都是二叉搜索树
     *
     */

    /**
     * 自己的思路：遍历的同时记录目前最大数以及其对应的数据，所以只需要遍历一次（Morris 中序遍历）
     *
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
     * 内存消耗：39.4 MB, 在所有 Java 提交中击败了61.50%的用户
     */
    List<Integer> list = new ArrayList<>();
    int num = 0;
    int nowNum = Integer.MIN_VALUE , nowCount = 0;
    public int[] findMode(TreeNode root) {
        if (null == root) return new int[0];
        dfs(root);
        if (nowCount>num){
            return new int[]{nowNum};
        }else if (nowCount==num){
            list.add(nowNum);
        }
        int length = list.size();
        int[] result = new int[length];
        for (int i =0;i<length;i++){
            result[i] = list.get(i);
        }
        return result;
    }
    void dfs(TreeNode node){
        if (node == null) return;
        dfs(node.left);
        if(node.val==nowNum){
            nowCount++;
        }else {
            if (nowCount>num){
                list.clear();
                list.add(nowNum);
                num = nowCount;
            }else if (nowCount==num){
                list.add(nowNum);
            }
            nowNum = node.val;
            nowCount = 1;
        }
        dfs(node.right);
    }

    class Solution {
        int base, count, maxCount;
        List<Integer> answer = new ArrayList<Integer>();

        public int[] findMode(TreeNode root) {
            TreeNode cur = root, pre = null;
            while (cur != null) {
                if (cur.left == null) {
                    update(cur.val);
                    cur = cur.right;
                    continue;
                }
                pre = cur.left;
                while (pre.right != null && pre.right != cur) {
                    pre = pre.right;
                }
                if (pre.right == null) {
                    pre.right = cur;
                    cur = cur.left;
                } else {
                    pre.right = null;
                    update(cur.val);
                    cur = cur.right;
                }
            }
            int[] mode = new int[answer.size()];
            for (int i = 0; i < answer.size(); ++i) {
                mode[i] = answer.get(i);
            }
            return mode;
        }

        public void update(int x) {
            if (x == base) {
                ++count;
            } else {
                count = 1;
                base = x;
            }
            if (count == maxCount) {
                answer.add(base);
            }
            if (count > maxCount) {
                maxCount = count;
                answer.clear();
                answer.add(base);
            }
        }
    }

}
