package com.algorithm.two_zero_two_one.february.相交链表;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author Ming
 * @date 2021/2/6 - 11:46
 * @describe
 */
public class LeetCode {
    /**
     * 编写一个程序，找到两个单链表相交的起始节点。
     */
    /**
     * 思路：哈希
     * 执行用时：8 ms, 在所有 Java 提交中击败了18.49%的用户
     * 内存消耗：42.3 MB, 在所有 Java 提交中击败了5.64%的用户
     */
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        Set<ListNode> set = new HashSet<>();
        while (headA != null) {
            set.add(headA);
            headA = headA.next;
        }
        while (headB != null) {
            if (set.contains(headB)) return headB;
            headB = headB.next;
        }
        return null;
    }

    /**
     * 思路：双指针
     * 执行用时：1 ms, 在所有 Java 提交中击败了99.99%的用户
     * 内存消耗：41.1 MB, 在所有 Java 提交中击败了70.78%的用户
     */
    public ListNode getIntersectionNode1(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) return null;
        ListNode aNow = headA, bNow = headB;
        while (aNow != bNow) {
            if (aNow == null) aNow = headB;
            else aNow = aNow.next;
            if (bNow == null) bNow = headA;
            else bNow = bNow.next;
        }
        return aNow;
    }




    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }
}
