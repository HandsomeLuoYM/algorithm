package com.algorithm.two_zero_two_zero.november.two_one;

/**
 * @author Ming
 * @date 2020/11/21 - 12:05
 * @describe 148 排序链表
 */
public class LeetCode {

    /**
     * 给你链表的头结点 head ，请将其按 升序 排列并返回 排序后的链表 。
     *
     * 进阶：
     *      你可以在 O(n log n) 时间复杂度和常数级空间复杂度下，对链表进行排序吗？
     *
     */
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
     * 自己的思路：快排
     */
    public ListNode sortList(ListNode head) {
        if (head==null||head.next == null) return head;
        int left = 0, right = 0, number = 0, temp = head.val;
        ListNode now = head.next, newList = head, leftIndex = null, rightIndex = head;
        while (now != null){
            ListNode next = now.next;
            if (now.val <= temp){
                now.next = leftIndex;
                leftIndex = now;
                left++;
            }else {
                rightIndex.next = now;
                rightIndex = now;
                right++;
            }
            now = next;
            number++;
        }
        rightIndex.next=null;
        ListNode newHead = new ListNode(Integer.MIN_VALUE);
        newHead.next = leftIndex;
        ListNode rightTail = fastSort(newList, right);
        ListNode leftTail = fastSort(newHead, left);
        leftTail.next = newList;
        return newHead.next;
    }

    /**
     * 返回最右边节点
     */
    private ListNode fastSort(ListNode head, int number) {
        if (number == 0 || head.next == null) return head;
        int left = 0, right = 0, temp = head.next.val;
        ListNode now = head.next.next, newList = head.next, leftIndex = null, rightIndex = head.next;
        number--;
        ListNode next = now;
        while (number > 0){
            now = next;
            next = next.next;
            if (now.val <= temp){
                now.next = leftIndex;
                leftIndex = now;
                left++;
            }else {
                rightIndex.next = now;
                rightIndex = now;
                right++;
            }
            number--;
        }
        rightIndex.next = null;
        head.next = leftIndex;
        ListNode rightTail = fastSort(newList, right);
        ListNode leftTail = fastSort(head, left);
        leftTail.next = newList;
        return rightIndex;
    }

    /**
     * 官方题解一：归并排序
     */
    class Solution {
        public ListNode sortList(ListNode head) {
            return sortList(head, null);
        }

        public ListNode sortList(ListNode head, ListNode tail) {
            if (head == null) {
                return head;
            }
            if (head.next == tail) {
                head.next = null;
                return head;
            }
            ListNode slow = head, fast = head;
            while (fast != tail) {
                slow = slow.next;
                fast = fast.next;
                if (fast != tail) {
                    fast = fast.next;
                }
            }
            ListNode mid = slow;
            ListNode list1 = sortList(head, mid);
            ListNode list2 = sortList(mid, tail);
            ListNode sorted = merge(list1, list2);
            return sorted;
        }

        public ListNode merge(ListNode head1, ListNode head2) {
            ListNode dummyHead = new ListNode(0);
            ListNode temp = dummyHead, temp1 = head1, temp2 = head2;
            while (temp1 != null && temp2 != null) {
                if (temp1.val <= temp2.val) {
                    temp.next = temp1;
                    temp1 = temp1.next;
                } else {
                    temp.next = temp2;
                    temp2 = temp2.next;
                }
                temp = temp.next;
            }
            if (temp1 != null) {
                temp.next = temp1;
            } else if (temp2 != null) {
                temp.next = temp2;
            }
            return dummyHead.next;
        }
    }

}
