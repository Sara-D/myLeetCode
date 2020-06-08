package com.example.leetcode;

import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author dw_dingdan1
 * @date 2020/5/15
 */
public class LargestRectangleInHistogram {

    //divide and conquer 时间复杂度主要取决于分解后形成的树的高度有关
    public static void main(String[] str) {
        int size = 1000000;
        int[] heights = new int[size];
        Random random = new Random();
        for(int i=0;i<size;i++){
            heights[i] = random.nextInt(size);
        }
        System.out.println("节点数:" + calNodeSize(heights.length));
        //为什么时间复杂度是 nlogn 呢?
        SegTreeNode root = buildTree(heights, 0, heights.length-1);
        AtomicInteger counter = new AtomicInteger(0);
        System.out.println("面积:" + maxArea(root, heights, 0, heights.length-1, counter));
        System.out.println("次数:" + counter.get());
        AtomicInteger counter2 = new AtomicInteger(0);
        System.out.println("面积:" + largestRectangleArea(heights, counter2));
        System.out.println("次数:" + counter2.get());
    }

    static long maxArea(SegTreeNode root, int[] heights, int start, int end, AtomicInteger counter){
        counter.incrementAndGet();
        if(start>end){
            return -1;
        }
        int minIndex = calMinIndex(root, heights, start, end);
        long maxArea = (end - start + 1) * heights[minIndex];
        long maxAreaLeft = maxArea(root, heights, start, minIndex-1, counter);
        long maxAreaRight = maxArea(root, heights, minIndex+1, end, counter);
        return Math.max(Math.max(maxAreaLeft, maxAreaRight), maxArea);
    }

    static int calMinIndex(SegTreeNode root, int[] heights, int start, int end){
        if(start<=root.start && end>=root.end){
            return root.min;
        }
        if(end<root.start || start > root.end){
            return -1;
        }
        int minLeft = calMinIndex(root.left, heights, start, end);
        int minRight = calMinIndex(root.right, heights, start, end);
        if(minLeft == -1){
            return minRight;
        }
        if(minRight == -1){
            return minLeft;
        }
        return heights[minLeft]<heights[minRight] ? minLeft : minRight;
    }

    static SegTreeNode buildTree(int[] heights, int start, int end){
        if(start > end){
            return null;
        }
        SegTreeNode root = new SegTreeNode(start, end);
        if(start == end){
            root.min = start;
            return root;
        }
        int middle = (start + end)/2;
        root.left = buildTree(heights, start, middle);
        root.right = buildTree(heights, middle+1, end);
        root.min = heights[root.left.min] < heights[root.right.min] ? root.left.min : root.right.min;
        return root;
    }

    static int calNodeSize(int size){
        double sum = 0;
        for(int i=1;i<=size;i*=2){
            sum += 1.0/i;
        }
        return (int)(Math.ceil(size * sum));
    }

    static int calculateArea(int[] heights, int start, int end, AtomicInteger counter) {
        counter.incrementAndGet();
        if (start > end)
            return 0;
        int minindex = start;
        for (int i = start; i <= end; i++)
            if (heights[minindex] > heights[i])
                minindex = i;
        return Math.max(heights[minindex] * (end - start + 1), Math.max(calculateArea(heights, start, minindex - 1, counter), calculateArea(heights, minindex + 1, end, counter)));
    }

    static int largestRectangleArea(int[] heights, AtomicInteger counter) {
        return calculateArea(heights, 0, heights.length - 1, counter);
    }

    static class SegTreeNode{
        int start;
        int end;
        int min;
        SegTreeNode left;
        SegTreeNode right;
        SegTreeNode(int start, int end){
            this.start = start;
            this.end = end;
        }
    }
}
