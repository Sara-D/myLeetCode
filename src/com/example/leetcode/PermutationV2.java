package com.example.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author dw_dingdan1
 * @date 2020/7/20
 *
 * 题号：47
 *
 * 题目：全排列 II
 *
 * 给定一个可包含重复数字的序列，返回所有不重复的全排列。
 *
 * 示例:
 *
 * 输入: [1,1,2]
 * 输出:
 * [
 *   [1,1,2],
 *   [1,2,1],
 *   [2,1,1]
 * ]
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/permutations
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class PermutationV2 {

    public static void main(String[] args){
        int[] nums = new int[]{1, 1, 2, 3};
        List<List<Integer>> list = new PermutationV2().permuteUnique(nums);
        for(List<Integer> value : list){
            System.out.println(Arrays.toString(value.toArray()));
        }
    }

    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> returnValue = new ArrayList<>();
        List<Integer> innerLlist = new ArrayList<>();
        Arrays.sort(nums);
        boolean[] states = new boolean[nums.length];
        int[] prevValue = new int[nums.length];
        Arrays.fill(prevValue, nums[0] - 1);
        permuteUniqueInner(nums, returnValue, innerLlist, 0, states, prevValue);
        return returnValue;
    }

    public void permuteUniqueInner(int[] nums, List<List<Integer>> returnValue, List<Integer> innerLlist, int count, boolean[] states, int[] prevValue){
        if(count == nums.length){
            returnValue.add(new ArrayList<>(innerLlist));
        }
        //同一层比较是否和前一个值相等
        for(int i=0;i<nums.length;i++){
            if(!states[i] && nums[i] != prevValue[count]){
                prevValue[count] = nums[i];
                states[i] = true;
                innerLlist.add(nums[i]);
                permuteUniqueInner(nums, returnValue, innerLlist, count + 1, states, prevValue);
                innerLlist.remove(innerLlist.size() - 1);
                states[i] = false;
                //把下一层的数据重置
                if(count + 1 < nums.length) {
                    prevValue[count + 1] = nums[0] - 1;
                }
            }
        }
    }
}
