package com.algorithm.april.twenty_one;

import java.util.ArrayList;
import java.util.List;

/**
   @author Ming
   @date 2020/4/21 - 10:18
   @describe

   输入一个链表，按链表从尾到头的顺序返回一个ArrayList。
  */
public class Three {

    /**
          public class ListNode {
              int val;
              ListNode next = null;

              ListNode(int val) {
                  this.val = val;
              }
          }

      */

    /**
       自己的方法

       运行时间：15ms

       占用内存：9296k

      */
    public ArrayList<Integer> printListFromTailToHead(ListNode listNode) {
        ArrayList<Integer> list = new ArrayList<Integer>();
        while (listNode!=null){
            list.add(0,listNode.val);
            listNode=listNode.next;
        }
        return list;
    }


    /**
       网上递归方法

       运行时间：16ms

       占用内存：9404k

      */
    ArrayList<Integer> list = new ArrayList();
    public ArrayList<Integer> printListFromTailToHeadOther1(ListNode listNode) {
        if(listNode!=null){
            printListFromTailToHead(listNode.next);
            list.add(listNode.val);
        }
        return list;
    }
}
