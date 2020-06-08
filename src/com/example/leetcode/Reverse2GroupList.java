package com.example.leetcode;

/**
 * @author dw_dingdan1
 * @date 2020/6/2
 */
public class Reverse2GroupList {

    public static void main(String[] args){
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(4);
        ListNode node5 = new ListNode(5);
        ListNode node6 = new ListNode(6);
        ListNode node7 = new ListNode(7);
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        node5.next = node6;
        node6.next = node7;
        ListNode newNode = reverse2GroupList2(node1);
        printListNode(newNode);
    }

    //递归
    public static ListNode reverse2GroupList2(ListNode head){
        if(head == null || head.next == null){
            return head;
        }
        ListNode node = reverse2GroupList2(head.next.next);
        ListNode tmp = head.next;
        head.next.next = head;
        head.next = node;
        return tmp;
    }

    //循环
    public static ListNode reverse2GroupList(ListNode head){
        if(head == null){
            return null;
        }
        ListNode newNode = new ListNode(0);
        newNode.next = head;
        ListNode curNode = head;
        ListNode parent = newNode;
        while (curNode != null && curNode.next != null){
            ListNode tmp = curNode.next.next;
            parent.next = curNode.next;
            parent = curNode;
            curNode.next.next = curNode;
            curNode.next = null;
            curNode = tmp;
        }
        if(curNode != null){
            parent.next = curNode;
        }
        return newNode.next;
    }

    static class ListNode{
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }

    static void printListNode(ListNode head){
        ListNode cur = head;
        while (cur != null){
            System.out.print(cur.val + " ");
            cur = cur.next;
        }
        System.out.println();
    }

}
