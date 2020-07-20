package com.example.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author dw_dingdan1
 * @date 2020/7/20
 *
 * 题号：46
 *
 * 题目：全排列
 *
 * 给定一个 没有重复 数字的序列，返回其所有可能的全排列
 *
 * 示例:
 *
 * 输入: [1,2,3]
 * 输出:
 * [
 *   [1,2,3],
 *   [1,3,2],
 *   [2,1,3],
 *   [2,3,1],
 *   [3,1,2],
 *   [3,2,1]
 * ]
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/permutations
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Permutation {

    public static void main(String[] args){
        int[] nums = new int[]{1,2,3,4};
        List<List<Integer>> list = new Permutation().permute(nums);
        for(List<Integer> value : list){
            System.out.println(Arrays.toString(value.toArray()));
        }
    }

    public List<List<Integer>> permute(int[] nums) {
        //return permute1(nums);
        return permute2(nums);
    }

    public List<List<Integer>> permute1(int[] nums){
        List<List<Integer>> returnValue = new ArrayList<>();
        boolean[] states = new boolean[nums.length];
        for(int i=0;i<nums.length;i++){
            //states状态是可以复用的
            returnValue.addAll(permuteInner(nums, i, states, 0));
        }
        return returnValue;
    }

    public List<List<Integer>> permuteInner(int[] nums, int index, boolean[] states, int count){
        List<List<Integer>> value = new ArrayList<>();
        if(count == nums.length - 1){
            List<Integer> list = new ArrayList<>();
            list.add(nums[index]);
            value.add(list);
            return value;
        }
        states[index] = true;
        for(int i=0;i<nums.length;i++){
            if(states[i])
                continue;
            List<List<Integer>> innerLists = permuteInner(nums, i, states, count + 1);
            for(List<Integer> innerList : innerLists){
                List<Integer> inner = new ArrayList<>();
                inner.add(nums[index]);
                inner.addAll(innerList);
                value.add(inner);
            }
        }
        states[index] = false;
        return value;
    }

    public List<List<Integer>> permute2(int[] nums){
        List<List<Integer>> returnValue = new ArrayList<>();
        boolean[] states = new boolean[nums.length];
        List<Integer> innerList = new ArrayList<>();
        permute2Inner(nums, returnValue, innerList, 0, states);
        return returnValue;
    }

    public void permute2Inner(int[] nums, List<List<Integer>> returnValue, List<Integer> innerList, int count, boolean[] states){
        if(count == nums.length){
            returnValue.add(new ArrayList<>(innerList));
            return;
        }
        for(int i=0;i<nums.length;i++){
            if(!states[i]){
                states[i] = true;
                innerList.add(nums[i]);
                permute2Inner(nums, returnValue, innerList, count+1, states);
                innerList.remove(innerList.size() - 1);
                states[i] = false;
            }
        }
    }
}
