package com.algorithm.two_zero_two_zero.august.three_zero;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Ming
 * @date 2020/8/14 - 11:41
 * @describe
 */
public class twenty_four {
    /**
     * 输入一颗二叉树的根节点和一个整数，
     * 按字典序打印出二叉树中结点值的和为输入整数的所有路径。
     * 路径定义为从树的根结点开始往下一直到叶结点所经过的结点形成一条路径。
     */

    /**
     * 运行时间：12ms
     *
     * 占用内存：9528k
     */
    ArrayList all = new ArrayList<ArrayList<Integer>>();
    List list = new ArrayList<Integer>();
    int add = 0;

    public ArrayList<ArrayList<Integer>> FindPath(TreeNode root, int target) {
        if (root==null) return all;
        list.add(root.val);
        add+=root.val;
        if (null!=root.left) {
            FindPath(root.left,target);
        }
        if (null!=root.right) {
            FindPath(root.right,target);
        }
        if (null==root.left&&null==root.right&&target==add) {
            all.add(new ArrayList<Integer>(list));
        }
        list.remove(list.size()-1);
        add-=root.val;
        return all;
    }

    public static void main(String[] args) {
        twenty_four twenty_four = new twenty_four();
        TreeNode root = new TreeNode(10);
        root.left = new TreeNode(5);
        root.right = new TreeNode(12);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(7);

        ArrayList<ArrayList<Integer>> arrayLists = twenty_four.FindPath(root, 22);
        System.out.println(arrayLists);
    }
}
