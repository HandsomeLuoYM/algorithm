package com.algorithm.two_zero_two_zero.september.one_nine;


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @author Ming
 * @date 2020/9/19 - 13:11
 * @describe
 */
public class Sixty {

    /**
     * 从上到下按层打印二叉树，同一层结点从左至右输出。每一层输出一行。
     */
    /**
     * 深度遍历
     * 运行时间：92ms
     *
     * 占用内存：15312k
     */
    ArrayList<ArrayList<Integer>> lists = new ArrayList<>();
    ArrayList<ArrayList<Integer>> Print(TreeNode pRoot) {
        if (pRoot==null) return lists;
        ArrayList<TreeNode> list = new ArrayList();
        list.add(pRoot);
        dfs(list);
        return lists;
    }
    void dfs(List<TreeNode> list){
        if (list.isEmpty()) return;
        ArrayList<Integer> numList = new ArrayList<>();
        ArrayList<TreeNode> treeList = new ArrayList<>();
        list.forEach(node->{
            numList.add(node.val);
            if (node.left!=null) treeList.add(node.left);
            if (node.right!=null) treeList.add(node.right);
        });
        lists.add(numList);
        dfs(treeList);
    }

    /**
     * 广度遍历
     *
     * 运行时间：14ms
     *
     * 占用内存：9616k
     *
     */
    ArrayList<ArrayList<Integer>> Print1(TreeNode pRoot) {
        if (pRoot==null) return lists;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(pRoot);
        int length;
        ArrayList<Integer> arrayList;
        TreeNode treeNode;
        while (!queue.isEmpty()){
            length = queue.size();
            arrayList = new ArrayList<>();
            for (int i = 0;i<length;i++){
                treeNode = queue.poll();
                arrayList.add(treeNode.val);
                if (treeNode.left!=null) queue.offer(treeNode.left);
                if (treeNode.right!=null) queue.offer(treeNode.right);
            }
            lists.add(arrayList);
        }
        return lists;
    }
}
