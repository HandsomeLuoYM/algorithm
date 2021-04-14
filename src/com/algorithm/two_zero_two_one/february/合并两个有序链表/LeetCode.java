package com.algorithm.two_zero_two_one.february.合并两个有序链表;

/**
 * @author Ming
 * @date 2021/2/3 - 15:40
 * @describe
 */
public class LeetCode {
    /**
     * 思路：遍历（迭代）
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
     * 内存消耗：37.9 MB, 在所有 Java 提交中击败了33.59%的用户
     */
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode head = new ListNode(), l1Now = l1, l2Now = l2, now = head;
        while (l1Now != null && l2Now != null) {
            if (l1Now.val > l2Now.val) {
                now.next = l2Now;
                l2Now = l2Now.next;
                now = now.next;
            }else {
                now.next = l1Now;
                l1Now = l1Now.next;
                now = now.next;
            }
        }
        if (l1Now != null) {
            now.next = l1Now;
        }else {
            now.next = l2Now;
        }
        return head.next;
    }

    /**
     * 递归
     */
    class Solution {
        public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
            if (l1 == null) {
                return l2;
            } else if (l2 == null) {
                return l1;
            } else if (l1.val < l2.val) {
                l1.next = mergeTwoLists(l1.next, l2);
                return l1;
            } else {
                l2.next = mergeTwoLists(l1, l2.next);
                return l2;
            }

        }
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
