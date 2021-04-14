package com.algorithm.two_zero_two_one.january.K个一组翻转链表;

/**
 * @author Ming
 * @date 2021/1/21 - 18:15
 * @describe
 */
public class LeetCode {

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
    /**
     * 给你一个链表，每 k 个节点一组进行翻转，请你返回翻转后的链表。
     * k 是一个正整数，它的值小于或等于链表的长度。
     * 如果节点总数不是 k 的整数倍，那么请将最后剩余的节点保持原有顺序。
     *
     *
     * 示例：
     *
     * 给你这个链表：1->2->3->4->5
     * 当 k = 2 时，应当返回: 2->1->4->3->5
     * 当 k = 3 时，应当返回: 3->2->1->4->5
     *
     */

    /**
     * 分段逆转，记录每个位置的值
     *
     * 执行用时：1 ms, 在所有 Java 提交中击败了36.94%的用户
     * 内存消耗：38.7 MB, 在所有 Java 提交中击败了54.83%的用户
     */
    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode newHead = new ListNode(), nowIndex = head, nowHead = newHead, tailIndex, temp, next;
        newHead.next = head;
        int length = 0;
        while (nowIndex != null) {
            //找到最新的一段
            while (length < k && nowIndex != null) {
                nowIndex = nowIndex.next;
                length++;
            }
            if (length < k) return newHead.next;
            temp = tailIndex = nowHead.next;
            nowHead.next = null;
            //翻转组
            while (length > 0) {
                next = temp.next;

                temp.next = nowHead.next;

                nowHead.next = temp;

                temp = next;
                length--;
            }
            nowHead = tailIndex;
            tailIndex.next = nowIndex;
        }
        return newHead.next;
    }
}
