package com.example.leetcode;

import java.util.*;

/**
 * 题号 350
 * @author dw_dingdan1
 * @date 2020/7/13
 *
 * 给定两个数组，编写一个函数来计算它们的交集。
 *
 * 示例 1：
 *
 * 输入：nums1 = [1,2,2,1], nums2 = [2,2]
 * 输出：[2,2]
 *
 * 示例 2:
 *
 * 输入：nums1 = [4,9,5], nums2 = [9,4,9,8,4]
 * 输出：[4,9]
 *
 * 输出结果中每个元素出现的次数，应与元素在两个数组中出现次数的最小值一致。
 * 我们可以不考虑输出结果的顺序。
 */
public class IntersectOfTwoArrays {

    public static void main(String[] args){
        int[] nums1 = new int[]{1,2,2,1};
        int[] nums2 = new int[]{2,2};
        System.out.println(Arrays.toString(new IntersectOfTwoArrays().intersect(nums1, nums2)));
    }

    public int[] intersect(int[] nums1, int[] nums2) {
        return inner2(nums1, nums2);
    }

    private int[] inner1(int[] nums1, int[] nums2){
        //将长度较小的数组存储在HashMap中 节省空间和时间
        if(nums1.length == 0)
            return nums1;
        if(nums2.length == 0)
            return nums2;
        int[] lonngerNums, shorterNums;
        if(nums1.length > nums2.length){
            lonngerNums = nums1;
            shorterNums = nums2;
        }else {
            lonngerNums = nums2;
            shorterNums = nums1;
        }
        Map<Integer, Integer> map = new HashMap<>(shorterNums.length);
        for(int num : shorterNums){
            Integer counter = map.get(num);
            map.put(num, counter == null ? 1 : ++counter);
        }
        List<Integer> value = new ArrayList<>(lonngerNums.length);
        for(int num : lonngerNums){
            Integer counter = map.get(num);
            if(counter != null && counter > 0){
                value.add(num);
                map.put(num, --counter);
            }
        }
        int[] ints = new int[value.size()];
        for(int i=0;i<value.size();i++){
            ints[i] = value.get(i);
        }
        return ints;
    }

    private int[] inner2(int[] nums1, int[] nums2){
        //将长度较小的数组存储在HashMap中 节省空间和时间
        if(nums1.length == 0)
            return nums1;
        if(nums2.length == 0)
            return nums2;
        int[] lonngerNums, shorterNums;
        if(nums1.length > nums2.length){
            lonngerNums = nums1;
            shorterNums = nums2;
        }else {
            lonngerNums = nums2;
            shorterNums = nums1;
        }
        Map<Integer, Integer> map = new HashMap<>(shorterNums.length);
        for(int num : shorterNums){
            Integer counter = map.get(num);
            map.put(num, counter == null ? 1 : ++counter);
        }
        int[] intersection = new int[shorterNums.length];
        int index = 0;
        for (int num : lonngerNums) {
            int count = map.getOrDefault(num, 0);
            if (count > 0) {
                intersection[index++] = num;
                count--;
                if (count > 0) {
                    map.put(num, count);
                } else {
                    map.remove(num);
                }
            }
        }
        return Arrays.copyOfRange(intersection, 0, index);
    }
}
