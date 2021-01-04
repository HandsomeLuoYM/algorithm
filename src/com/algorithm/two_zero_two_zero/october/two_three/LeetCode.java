package com.algorithm.two_zero_two_zero.october.two_three;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Ming
 * @date 2020/10/23 - 0:14
 * @describe
 */
public class LeetCode {
    /**
     * 请判断一个链表是否为回文链表。
     * <p>
     * 输入: 1->2
     * 输出: false
     * <p>
     * 输入: 1->2->2->1
     * 输出: true
     */
    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }

        ListNode(int x, ListNode listNode) {
            val = x;
            next = listNode;
        }
    }

    /**
     * 思路：
     *      先用快慢指针找到中间点，再倒置后面一半的节点，再让前半段和后半段来挨个比较，当存在不相等时结束判断返回false，否则在最后返回true。
     *
     * 时间复杂度：O（n）
     * 空间复杂度：O（1）
     *
     * 执行用时：1 ms, 在所有 Java 提交中击败了99.86%的用户
     * 内存消耗：41.1 MB, 在所有 Java 提交中击败了92.75%的用户
     */
    public boolean isPalindrome(ListNode head) {
        if (head==null || head.next == null) return true;
        ListNode fast = head.next, slow = head;
        //快慢指针，找出中间点
        while (fast != null){
            if (fast.next==null || fast.next.next == null) break;;
            fast = fast.next.next;
            slow = slow.next;
        }
        //倒置后半段
        ListNode node = slow.next, lastHead = node, temp;
        slow.next = null;
        while (node!=null){
            temp = node;
            node = node.next;
            temp.next = lastHead;
            lastHead = temp;
        }
        //从头开始遍历
        node = head;
        while (node != null){
            if (lastHead.val != node.val) return false;
            lastHead = lastHead.next;
            node = node.next;
        }
        return true;
    }

    /**
     * 思路：
     *      快慢指针，并且用List存储前面半个node段，然后再遍历后面半段时，再判断是否相等
     *      当然也可以存储所有节点再List中
     * 时间复杂度：O（n）
     * 空间复杂度：O（n）
     *
     * 执行用时：1 ms, 在所有 Java 提交中击败了99.86%的用户
     * 内存消耗：41.1 MB, 在所有 Java 提交中击败了92.48%的用户
     */
    public boolean isPalindrome1(ListNode head) {
        if (head==null) return true;
        //存放前半段节点
        List<ListNode> list = new ArrayList<>();
        ListNode fast = head.next, slow = head;
        //快慢指针，找出中间点
        while (fast != null){
            list.add(slow);
            if (fast.next==null || fast.next.next == null) break;
            fast = fast.next.next;
            slow = slow.next;
        }
        if (fast.next != null) slow = slow.next.next;
        else slow = slow.next;
        int length = list.size()-1;
        while (slow != null){
            if (list.get(length--).val != slow.val) return false;
            slow = slow.next;
        }
        return true;
    }

    /**
     * 递归方法：可以学习，很有想法
     */
    class Solution {
        private ListNode frontPointer;

        private boolean recursivelyCheck(ListNode currentNode) {
            if (currentNode != null) {
                if (!recursivelyCheck(currentNode.next)) {
                    return false;
                }
                if (currentNode.val != frontPointer.val) {
                    return false;
                }
                frontPointer = frontPointer.next;
            }
            return true;
        }

        public boolean isPalindrome(ListNode head) {
            frontPointer = head;
            return recursivelyCheck(head);
        }
    }


    public static void main(String[] args) {
        LeetCode leetCode = new LeetCode();
        ListNode listNode = leetCode.new ListNode(1,
                leetCode.new ListNode(2,
                        leetCode.new ListNode(3,
                                leetCode.new ListNode(2,
                                        leetCode.new ListNode(1))
        )));
        boolean palindrome1 = leetCode.isPalindrome1(listNode);
    }

}
