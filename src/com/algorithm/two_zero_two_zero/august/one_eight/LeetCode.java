package com.algorithm.two_zero_two_zero.august.one_eight;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Ming
 * @date 2020/8/18 - 11:12
 * @describe
 */
public class LeetCode {

    /**
     * 给定一个单链表，其中的元素按升序排序，将其转换为高度平衡的二叉搜索树。
     *
     * 本题中，一个高度平衡二叉树是指一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过 1。
     *给定的有序链表： [-10, -3, 0, 5, 9],
     *
     * 一个可能的答案是：[0, -3, 9, -10, null, 5], 它可以表示下面这个高度平衡二叉搜索树：
     *
     *       0
     *      / \
     *    -3   9
     *    /   /
     *  -10  5
     *
     */


    /**
     * 自己的解法
     * 执行用时：1 ms, 在所有 Java 提交中击败了66.07%的用户
     * 内存消耗：41.1 MB, 在所有 Java 提交中击败了34.39%的用户
     */
    public TreeNode change(List<Integer> list , int start , int end){
        if (end<start) return null;
        if (start==end) return new TreeNode(list.get(start));
        int min = (end+start+1)/2;//2
        TreeNode node = new TreeNode(list.get(min));

        node.left = change(list,start,min-1);//0 1
        node.right = change(list,min+1,end);//3 4
        return node;
    }

    public TreeNode sortedListToBST(ListNode head) {
        List<Integer> list = new ArrayList<>();
        ListNode listNode=head;
        Integer node;
        while (listNode!=null) {
            list.add(listNode.val);
            listNode = listNode.next;
        }
        return change(list,0 , list.size()-1);
    }

//////////////////////////  官方题解1  //////////////////////////////


    public TreeNode sortedListToBST1(ListNode head) {
        return buildTree(head, null);
    }

    public TreeNode buildTree(ListNode left, ListNode right) {
        if (left == right) {
            return null;
        }
        ListNode mid = getMedian(left, right);
        TreeNode root = new TreeNode(mid.val);
        root.left = buildTree(left, mid);
        root.right = buildTree(mid.next, right);
        return root;
    }

    /**
     * 快慢指针获取ListNode
     * @param left
     * @param right
     * @return
     */
    public ListNode getMedian(ListNode left, ListNode right) {
        ListNode fast = left;
        ListNode slow = left;
        while (fast != right && fast.next != right) {
            fast = fast.next;
            fast = fast.next;
            slow = slow.next;
        }
        return slow;
    }

//////////////////////////  官方题解2   /////////////////////////////
    ListNode globalHead;

    public TreeNode sortedListToBST2(ListNode head) {
        //全局使用
        globalHead = head;
        int length = getLength(head);
        return buildTree(0, length - 1);
    }

    public int getLength(ListNode head) {
        int ret = 0;
        while (head != null) {
            ++ret;
            head = head.next;
        }
        return ret;
    }

    /**
     * 类似中序遍历
     */
    public TreeNode buildTree(int left, int right) {
        if (left > right) {
            return null;
        }
        int mid = (left + right + 1) / 2;
        TreeNode root = new TreeNode();
        root.left = buildTree(left, mid - 1);
        root.val = globalHead.val;
        globalHead = globalHead.next;
        root.right = buildTree(mid + 1, right);
        return root;
    }

}
