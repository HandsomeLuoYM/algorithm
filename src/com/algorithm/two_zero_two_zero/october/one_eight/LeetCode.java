package com.algorithm.two_zero_two_zero.october.one_eight;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Ming
 * @date 2020/10/18 - 0:09
 * @describe 19. 删除链表的倒数第N个节点
 */
public class LeetCode {

    /**
     * 给定一个链表，删除链表的倒数第 n 个节点，并且返回链表的头结点。
     *
     * 给定一个链表: 1->2->3->4->5, 和 n = 2.
     * 当删除了倒数第二个节点后，链表变为 1->2->3->5.
     *
     * 说明：
     * 给定的 n 保证是有效的。
     */
    /**
     * 自己的思路：
     *      将节点存入数组中，然后去除掉倒数第 n 个
     * 执行用时：1 ms, 在所有 Java 提交中击败了21.08%的用户
     * 内存消耗：36.1 MB, 在所有 Java 提交中击败了99.76%的用户
     */
    public ListNode removeNthFromEnd(ListNode head, int n) {
        List<ListNode> listNodes = new ArrayList<>();
        ListNode node = head;
        while (node!=null){
            listNodes.add(node);
            node = node.next;
        }
        int length = listNodes.size();
        if (n==length) return head.next;
        else {
            listNodes.get(length-n-1).next = listNodes.get(length-n).next;
        }
        return head;
    }

    /**
     * 思路：
     *      先设置一个空的头节点，然后再用滑动数组（快慢指针）来使快指针比慢指针快 n 个，当快指针到达终点时，慢指针就是倒数第 n 个
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
     * 内存消耗：36.4 MB, 在所有 Java 提交中击败了98.24%的用户
     */
    public ListNode removeNthFromEnd1(ListNode head, int n) {
        ListNode dummy = new ListNode(0, head);
        ListNode fast=head,slow=dummy;
        for (int i = 0; i < n; i++){
            fast = fast.next;
        }
        while (fast!=null){
            fast = fast.next;
            slow = slow.next;
        }
        slow.next = slow.next.next;
        return dummy.next;
    }

    /**
     * 官方题解一：计算链表长度，然后再去掉
     */
    class Solution {
        public ListNode removeNthFromEnd(ListNode head, int n) {
            ListNode dummy = new ListNode(0, head);
            int length = getLength(head);
            ListNode cur = dummy;
            for (int i = 1; i < length - n + 1; ++i) {
                cur = cur.next;
            }
            cur.next = cur.next.next;
            ListNode ans = dummy.next;
            return ans;
        }

        public int getLength(ListNode head) {
            int length = 0;
            while (head != null) {
                ++length;
                head = head.next;
            }
            return length;
        }
    }
}
