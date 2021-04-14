package com.algorithm.two_zero_two_one.march.旋转链表;

/**
 * @author Ming
 * @date 2021/3/27 - 0:51
 * @describe
 */
public class LeetCode {
    /**
     * 给你一个链表的头节点 head ，旋转链表，将链表每个节点向右移动 k 个位置。
     * 输入：head = [1,2,3,4,5], k = 2
     * 输出：[4,5,1,2,3]
     */
    /**
     * 执行用时：1476 ms, 在所有 Java 提交中击败了5.02%的用户
     * 内存消耗：38 MB, 在所有 Java 提交中击败了25.23%的用户
     */
    public ListNode rotateRight(ListNode head, int k) {
        if (head == null) return head;
        ListNode fastNode = head, slowNode = head;
        //先走 k 个节点
        for (int i = 0; i < k; i++) {
            fastNode = fastNode.next == null ? head : fastNode.next;
        }
        while (fastNode.next != null) {
            fastNode = fastNode.next;
            slowNode = slowNode.next;
        }
        fastNode.next = head;
        head = slowNode.next;
        slowNode.next = null;
        return head;
    }

    public class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }
}
