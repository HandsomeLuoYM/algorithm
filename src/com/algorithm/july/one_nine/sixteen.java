package com.algorithm.july.one_nine;

/**
   @author Ming
   @date 2020/7/20 - 1:43
   @describe
  */
public class sixteen {

    /**
    public class ListNode {
        int val;
        ListNode next = null;

        ListNode(int val) {
            this.val = val;
        }
    } */

    /**
       输入两个单调递增的链表，输出两个链表合成后的链表，当然我们需要合成后的链表满足单调不减规则。
       运行时间：15ms

       占用内存：9684k
       @param list1
       @param list2
       @return
      */
    public ListNode Merge(ListNode list1,ListNode list2) {
        if (list1==null) return list2;
        if (list2==null) return list1;
        ListNode head,p;
        if (list1.val<list2.val){
            head = list1;
            list1 = list1.next;
            head.next=null;
        }else {
            head = list2;
            list2 = list2.next;
            head.next=null;
        }
        p=head;
        while (list1!=null&&list2!=null){
            if (list1.val<list2.val){
                p.next = list1;
                list1 = list1.next;
                p=p.next;
            }else {
                p.next = list2;
                list2 = list2.next;
                p=p.next;
            }
        }
        p.next = list1==null ? list2:list1;
        //优化代码
//        if (list1!=null){
//            while (list1!=null){
//                p.next=list1;
//                list1 = list1.next;
//                p=p.next;
//            }
//        }
//        if (list2!=null){
//            while (list2!=null){
//                p.next=list2;
//                list2 = list2.next;
//                p=p.next;
//            }
//        }
        return head;
    }

    /**
       运行时间：16ms

       占用内存：9784k
       递归调用
       @param list1
       @param list2
       @return
      */
    public ListNode Merge2(ListNode list1,ListNode list2) {

        if (list1==null) return list2;
        if (list2==null) return list1;
        if (list1.val<=list2.val){
            list1.next = Merge2(list1.next,list2);
            return list1;
        }else {
            list2.next = Merge2(list1,list2.next);
            return list2;
        }

    }

}
