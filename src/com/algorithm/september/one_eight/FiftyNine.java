package com.algorithm.september.one_eight;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Stack;

/**
 * @author Ming
 * @date 2020/9/18 - 12:55
 * @describe
 */
public class FiftyNine {
    /**
     * 请实现一个函数按照之字形打印二叉树，即第一行按照从左到右的顺序打印，
     * 第二层按照从右至左的顺序打印，第三行按照从左到右的顺序打印，其他行以此类推。
     */

    /**
     * 运行时间：15ms
     *
     * 占用内存：9812k
     */
    ArrayList<ArrayList<Integer>> lists = new ArrayList<>();
    public ArrayList<ArrayList<Integer>> Print(TreeNode pRoot) {
        if (pRoot==null) return lists;
        Stack<TreeNode> stack = new Stack<>();
        stack.push(pRoot);
        add(stack,1);
        return lists;
    }
    private void add(Stack<TreeNode> stack,int i){
        Stack<TreeNode> newStack = new Stack<>();
        ArrayList<Integer> list = new ArrayList<>();
        while (!stack.isEmpty()){
            TreeNode poll = stack.pop();
            list.add(poll.val);
            if (i==1){
                if (poll.left!=null) newStack.push(poll.left);
                if (poll.right!=null) newStack.push(poll.right);
            }else {
                if (poll.right!=null) newStack.push(poll.right);
                if (poll.left!=null) newStack.push(poll.left);
            }
        }
        if (list.isEmpty()) return;
        lists.add(list);
        add(newStack,-i);
    }

    /**
     * 使用队列，也设置一个标志标识添加位置，不需要递归，在遍历完一层后拿到现在那一层的个数，
     * 然后再里面在设置一个for循环，来遍历当前的队列
     */
    public class Solution {
        public ArrayList<ArrayList<Integer> > Print(TreeNode pRoot) {
            LinkedList<TreeNode> q = new LinkedList<>();
            ArrayList<ArrayList<Integer>> res = new ArrayList<>();
            boolean rev = true;
            q.add(pRoot);
            while(!q.isEmpty()){
                int size = q.size();
                ArrayList<Integer> list = new ArrayList<>();
                for(int i=0; i<size; i++){
                    TreeNode node = q.poll();
                    if(node == null){continue;}
                    if(rev){
                        list.add(node.val);
                    }else{
                        list.add(0, node.val);
                    }
                    q.offer(node.left);
                    q.offer(node.right);
                }
                if(list.size()!=0){res.add(list);}
                rev=!rev;
            }
            return res;
        }
    }

}
