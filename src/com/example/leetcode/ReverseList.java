package com.example.leetcode;
/**
 * @author dw_dingdan1
 * @date 2020/6/2
 */
public class ReverseList {

    public static void main(String[] args){
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(4);
        ListNode node5 = new ListNode(5);
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        System.out.println(reverseList2(node1).val);
    }

    //第一个节点丢失了？
    public static ListNode reverseList2(ListNode head){
        if(head == null || head.next == null){
            return head;
        }
        ListNode node = reverseList2(head.next);
        head.next.next = head;
        head.next = null;
        return node;
    }

    //循环
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
    }
}
