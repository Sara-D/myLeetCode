package com.example.leetcode;

/**
 * @author dw_dingdan1
 * @date 2020/6/2
 */
public class ReverseKGroupList {

    /*public static ListNode reverseKGroupList(ListNode head, int k){
        if(head == null || head.next == null){
            return head;
        }
        int count = 1;
        while (head.next != null){
            count++;
        }
        int remains = count % k;
        int multiples = count / k;
        ListNode cur = head;
        ListNode next = head.next;
        for(int i=0;i<count;i+=k){
            for(int j=i;j<k;j++){

            }
        }
    }

    public static ListNode reverseList(ListNode head){
        if(head == null){
            return null;
        }
        ListNode cur = head;
        ListNode next = cur.next;
        while (next != null){
            ListNode tmp = next.next;
            next.next = cur;
            cur = next;
            next = tmp;
        }
        head.next = null;
        return cur;
    }

    static class ListNode{
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }*/
}
