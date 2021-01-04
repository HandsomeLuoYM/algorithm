package com.algorithm.two_zero_two_zero.october.one_three;

/**
 * @author Ming
 * @date 2020/10/13 - 0:59
 * @describe 24. 两两交换链表中的节点
 */
public class LeetCode {
    /**
     * 给定一个链表，两两交换其中相邻的节点，并返回交换后的链表。
     *
     * 你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。
     *
     * 实例：
     * 给定 1->2->3->4, 你应该返回 2->1->4->3.
     */
    /**
     * 自己的思路：设置一个标志代表是否要交换节点，在要交换的两个节点前面一个节点就将下面两个节点先交换掉
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
     * 内存消耗：36.4 MB, 在所有 Java 提交中击败了80.23%的用户
     */
    public ListNode swapPairs(ListNode head) {
        if (head==null||head.next==null) return head;
        ListNode root = head.next;
        head.next = root.next;
        root.next = head;
        change(root,false);
        return root;
    }

    private void change(ListNode node, boolean flag) {
        if (flag){
            if (node.next==null||node.next.next==null) return;
            ListNode nextNode = node.next;
            node.next = nextNode.next;
            nextNode.next = node.next.next;
            node.next.next = nextNode;
        }
        change(node.next,!flag);
    }

    /**
     * 自己的思路：迭代
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
     * 内存消耗：36.4 MB, 在所有 Java 提交中击败了80.23%的用户
     */
    public ListNode swapPairs1(ListNode head) {
        if (head==null||head.next==null) return head;
        ListNode root = head.next,node,nextNode;
        head.next = root.next;
        root.next = head;
        node=root.next;
        while (node!=null){
            if (node.next==null||node.next.next==null) return root;
            nextNode = node.next;
            node.next = nextNode.next;
            nextNode.next = node.next.next;
            node.next.next = nextNode;
            node = node.next.next;
        }
        return root;
    }

    /**
     * 官方题解：递归，递归交换，然后返回新的节点
     */
    public ListNode swapPairs2(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode newHead = head.next;
        head.next = swapPairs(newHead.next);
        newHead.next = head;
        return newHead;
    }

    /**
     * 官方题解：迭代，可以先new一个新的节点，作为起始点，后面再把它去掉
     */
    class Solution {
        public ListNode swapPairs(ListNode head) {
            ListNode dummyHead = new ListNode(0);
            dummyHead.next = head;
            ListNode temp = dummyHead;
            while (temp.next != null && temp.next.next != null) {
                ListNode node1 = temp.next;
                ListNode node2 = temp.next.next;
                temp.next = node2;
                node1.next = node2.next;
                node2.next = node1;
                temp = node1;
            }
            return dummyHead.next;
        }
    }


}
