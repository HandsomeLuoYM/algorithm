package com.algorithm.two_zero_two_one.march.删除排序链表中的重复元素;

/**
 * @author Ming
 * @date 2021/3/26 - 0:42
 * @describe
 */
public class LeetCode {

    /**
     * 存在一个按升序排列的链表，给你这个链表的头节点 head ，请你删除所有重复的元素，使每个元素 只出现一次 。
     * <p>
     * 返回同样按升序排列的结果链表。
     * <p>
     * 示例 1：
     * 输入：head = [1,1,2]
     * 输出：[1,2]
     * <p>
     * 示例 2：
     * 输入：head = [1,1,2,3,3]
     * 输出：[1,2,3]
     */
    /**
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
     * 内存消耗：37.9 MB, 在所有 Java 提交中击败了48.50%的用户
     */
    public ListNode deleteDuplicates(ListNode head) {
        ListNode now = head, tempNode;
        while (now != null) {
            if (null != now.next && now.next.val == now.val) {
                tempNode = now.next;
                while (tempNode != null && tempNode.val == now.val) {
                    tempNode = tempNode.next;
                }
                now.next = tempNode;
            }
            now = now.next;
        }
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
