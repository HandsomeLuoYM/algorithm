package com.algorithm.two_zero_two_one.march.删除排序链表中的重复元素II;

/**
 * @author Ming
 * @date 2021/3/25 - 0:06
 * @describe
 */
public class LeetCode {
    /**
     * 给定一个排序链表，删除所有含有重复数字的节点，只保留原始链表中 没有重复出现 的数字。
     * <p>
     * 示例 1:
     * <p>
     * 输入: 1->2->3->3->4->4->5
     * 输出: 1->2->5
     * 示例 2:
     * <p>
     * 输入: 1->1->1->2->3
     * 输出: 2->3
     */
    /**
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
     * 内存消耗：37.9 MB, 在所有 Java 提交中击败了44.92%的用户
     */
    public ListNode deleteDuplicates(ListNode head) {
        ListNode newHead = new ListNode(-1, head);
        ListNode prev = newHead, now = head;
        while (now != null) {
            if (now.next != null && now.next.val == now.val) {
                while (now.next != null && now.next.val == now.val) {
                    now = now.next;
                }
                prev.next = now.next;
                continue;
            }
            prev.next = now;
            now = now.next;
        }
        return newHead.next;
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
