package com.algorithm.november.one_three;

/**
 * @author Ming
 * @date 2020/11/13 - 1:01
 * @describe
 */
public class LeetCode {
    /**
     * 给定一个单链表，把所有的奇数节点和偶数节点分别排在一起。请注意，这里的奇数节点和偶数节点指的是节点编号的奇偶性，而不是节点的值的奇偶性。
     * <p>
     * 请尝试使用原地算法完成。你的算法的空间复杂度应为 O(1)，时间复杂度应为 O(nodes)，nodes 为节点总数。
     * <p>
     * 输入: 1->2->3->4->5->NULL
     * 输出: 1->3->5->2->4->NULL
     * <p>
     * 入: 2->1->3->5->6->4->7->NULL
     * 输出: 2->3->6->7->1->5->4->NULL
     */
    /**
     * 自己的思路：（原地排序）
     *      设置快慢指针，然后快指针找到单数的节点插入到慢指针后面，依次遍历，知道快指针结束
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
     * 内存消耗：37.9 MB, 在所有 Java 提交中击败了93.69%的用户
     */
    public ListNode oddEvenList(ListNode head) {
        if (head == null||head.next==null) return head;
        ListNode fast = head.next, slow = head, temp;
        while (fast != null){
            if (fast.next!=null){
                temp = fast.next;
                fast.next=fast.next.next;
                temp.next = slow.next;
                slow.next = temp;
            }
            slow = slow.next;
            fast = fast.next;
        }
        return head;
    }

    /**
     * 官方题解：先分离后再合并
     */
    class Solution {
        public ListNode oddEvenList(ListNode head) {
            if (head == null) {
                return head;
            }
            ListNode evenHead = head.next;
            ListNode odd = head, even = evenHead;
            while (even != null && even.next != null) {
                odd.next = even.next;
                odd = odd.next;
                even.next = odd.next;
                even = even.next;
            }
            odd.next = evenHead;
            return head;
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
