package com.algorithm.november.two_zero;


/**
 * @author Ming
 * @date 2020/11/20 - 0:47
 * @describe
 */
public class LeetCode {

    /**
     * 插入排序的动画演示如上。从第一个元素开始，该链表可以被认为已经部分排序（用黑色表示）。
     * 每次迭代时，从输入数据中移除一个元素（用红色表示），并原地将其插入到已排好序的链表中。
     *  
     * 插入排序算法：
     *      插入排序是迭代的，每次只移动一个元素，直到所有元素可以形成一个有序的输出列表。
     *      每次迭代中，插入排序只从输入数据中移除一个待排序的元素，找到它在序列中适当的位置，并将其插入。
     *      重复直到所有输入数据插入完为止。
     *  
     */
    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    /**
     * 自己的思路：
     *      按照提议来，边插入边遍历
     * 执行用时：22 ms, 在所有 Java 提交中击败了38.45%的用户
     * 内存消耗：38.2 MB, 在所有 Java 提交中击败了80.16%的用户
     */
    public ListNode insertionSortList(ListNode head) {
        ListNode newHead = new ListNode(Integer.MIN_VALUE);
        if (head == null) return null;
        ListNode temp, pre, now;

        while (head!=null){
            temp = head;
            head = head.next;
            pre = newHead;
            now = newHead.next;
            while (now!=null && temp.val > now.val){
                now = now.next;
                pre = pre.next;
            }
            pre.next = temp;
            temp.next = now;
        }
        return newHead.next;
    }

    /**
     * 官方题解：和我的做法类似
     */
    class Solution {
        public ListNode insertionSortList(ListNode head) {
            if (head == null) {
                return head;
            }
            ListNode dummyHead = new ListNode(0);
            dummyHead.next = head;
            ListNode lastSorted = head, curr = head.next;
            while (curr != null) {
                if (lastSorted.val <= curr.val) {
                    lastSorted = lastSorted.next;
                } else {
                    ListNode prev = dummyHead;
                    while (prev.next.val <= curr.val) {
                        prev = prev.next;
                    }
                    lastSorted.next = curr.next;
                    curr.next = prev.next;
                    prev.next = curr;
                }
                curr = lastSorted.next;
            }
            return dummyHead.next;
        }
    }

}
