package com.example.leetcode;

/**
 * @author dw_dingdan1
 * @date 2020/5/25
 */
public class MergeKSortedList {

    public static void main(String[] args){

    }

    static class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }

    static ListNode mergeKLists(ListNode[] lists){
        if(lists == null || lists.length == 0){
            return null;
        }
        return doMergeKLists(lists, 0, lists.length - 1);
    }

    static ListNode doMergeKLists(ListNode[] lists, int start, int end){
        if(start > end){
            return null;
        }
        if(end == start){
            return lists[start];
        }
        if(end - start == 1){
            return merge2List(lists[start], lists[end]);
        }
        int middleIndex = (start + end)/2;
        ListNode left = doMergeKLists(lists, start, middleIndex);
        ListNode right = doMergeKLists(lists, middleIndex+1, end);
        return merge2List(left, right);
    }

    static ListNode merge2List(ListNode node1, ListNode node2){
        if(node1==null)
            return node2;
        if(node2 == null)
            return node1;
        ListNode newNode = new ListNode(0);
        ListNode curNode = newNode;
        ListNode p1 = node1, p2 = node2;
        while(p1 != null && p2 != null){
            if(p1.val < p2.val){
                curNode.next = p1;
                p1 = p1.next;
            }else {
                curNode.next = p2;
                p2 = p2.next;
            }
            curNode = curNode.next;
        }
        if(p1 == null && p2 == null){
            return newNode.next;
        }else if(p1 != null){
            curNode.next = p1;
        }else {
            curNode.next = p2;
        }
        return newNode.next;
    }
}
