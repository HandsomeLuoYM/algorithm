package com.algorithm.two_zero_two_zero.october.one_eight;

/**
 * @author Ming
 * @date 2020/10/9 - 9:16
 * @describe
 */
public class ListNode {

    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
        next = null;
    }

    public ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}
