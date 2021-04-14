package com.algorithm.two_zero_two_one.january.反转链表;

import java.sql.ResultSet;

/**
 * @author Ming
 * @date 2021/1/18 - 19:46
 * @describe
 */
public class LeetCode {

    /**
     * 反转一个单链表。
     *
     * 示例:
     * 输入: 1->2->3->4->5->NULL
     * 输出: 5->4->3->2->1->NULL
     */

    public class ListNode {
        int val;
        ListNode next;
        ListNode(int x) {
            val = x;
        }
    }

    /**
     * 迭代
     */
    public ListNode reverseList(ListNode head) {
        if (head == null) return null;
        ListNode node = head, newHead = new ListNode(-1), next;
        while (node != null) {
            next = node.next;
            node.next = newHead.next;
            newHead.next = node;
            node = next;
        }
        return newHead.next;
    }

    /**
     * 递归
     */
    public ListNode reverseList1(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode p = reverseList1(head.next);
        head.next.next = head;
        head.next = null;
        return p;
    }


}
