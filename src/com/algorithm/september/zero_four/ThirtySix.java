package com.algorithm.september.zero_four;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Ming
 * @date 2020/9/4 - 10:09
 * @describe
 */
public class ThirtySix {

    /**
     *
     * 题目描述
     * 输入两个链表，找出它们的第一个公共结点。
     * （注意因为传入数据是链表，所以错误测试数据的提示是用其他方式显示的，保证传入数据是正确的）
     *
     * public class ListNode {
     *     int val;
     *     ListNode next = null;
     *
     *     ListNode(int val) {
     *         this.val = val;
     *     }
     * }
     */
    /**
     * 自己的解法
     *
     * 思路：用一个HashSet来存储节点，以减低时间复杂度，当有一个为null时直接返回
     * 注意点：return pHead2.equals(p)?null:p;不能写出p.equals(pHead)，如果p为空会抛出异常
     *
     * 运行时间：11ms
     *
     * 占用内存：9596k
     */
    public ListNode FindFirstCommonNode(ListNode pHead1, ListNode pHead2) {
        if (pHead1==null||pHead2==null) return null;

        Set<ListNode> set = new HashSet<>();
        ListNode p =pHead1;
        while (p!=null){
            set.add(p);
            p=p.next;
        }
        if (set.contains(pHead2)) return pHead2;
        p=pHead2;
        while (p!=null&&!set.contains(p)){
            p=p.next;
        }
        return pHead2.equals(p)?null:p;
    }

    public static void main(String[] args) {
        //{1,2,3,4},{5,6,7},
        ListNode root = new ListNode(1);
        root.next = new ListNode(2);
        root.next.next = new ListNode(3);
        root.next.next.next = new ListNode(4);

        ListNode root2 = new ListNode(5);
        root.next = new ListNode(6);
        root.next.next = new ListNode(7);

        ThirtySix thirtySix = new ThirtySix();
        ListNode listNode = thirtySix.FindFirstCommonNode(root, root2);
        System.out.println(listNode);

    }
}
