package com.example.leetcode;

import java.util.*;

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
        int[] nums = new int[]{2, 1, 1, 1};
        //List<List<Integer>> list = new PermutationV2().permuteUnique(nums);
        List<List<Integer>> list = new PermutationV2().permuteUnique2(nums);
        for(List<Integer> value : list){
            System.out.println(Arrays.toString(value.toArray()));
        }
    }

    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> returnValue = new ArrayList<>();
        //使用Deque没有List的性能好
        //List<Integer> innerLlist = new ArrayList<>();
        Deque<Integer> deque = new ArrayDeque<>(nums.length);
        Arrays.sort(nums);
        boolean[] states = new boolean[nums.length];
        int[] prevValue = new int[nums.length];
        Arrays.fill(prevValue, nums[0] - 1);
        permuteUniqueInner(nums, returnValue, deque, 0, states, prevValue);
        return returnValue;
    }

    public void permuteUniqueInner(int[] nums, List<List<Integer>> returnValue, Deque<Integer> innerLlist, int count, boolean[] states, int[] prevValue){
        if(count == nums.length){
            returnValue.add(new ArrayList<>(innerLlist));
        }
        //同一层比较是否和前一个值相等
        for(int i=0;i<nums.length;i++){
            if(!states[i] && nums[i] != prevValue[count]){
                prevValue[count] = nums[i];
                states[i] = true;
                innerLlist.addLast(nums[i]);
                permuteUniqueInner(nums, returnValue, innerLlist, count + 1, states, prevValue);
                innerLlist.removeLast();
                states[i] = false;
                //把下一层的数据重置
                if(count + 1 < nums.length) {
                    prevValue[count + 1] = nums[0] - 1;
                }
            }
        }
    }

    public List<List<Integer>> permuteUnique2(int[] nums) {
        List<List<Integer>> returnValue = new ArrayList<>();
        // 使用 Deque 是 Java 官方 Stack 类的建议
        Deque<Integer> path = new ArrayDeque<>(nums.length);
        //Arrays.sort(nums);
        boolean[] states = new boolean[nums.length];
        permuteUniqueInner2(nums, returnValue, path, 0, states);
        return returnValue;
    }

    public void permuteUniqueInner2(int[] nums, List<List<Integer>> returnValue, Deque innerLlist, int count, boolean[] states){
        if(count == nums.length){
            returnValue.add(new ArrayList<>(innerLlist));
        }
        for(int i=0;i<nums.length;i++){
            if(states[i])
                continue;
            // states[i-1] 或者 !states[i-1] 都可
            // !states[i-1]剪枝更彻底 因为这样只会取第一个相同元素，后面的忽略 states[i-1] 反之
            if(i > 0 && nums[i] == nums[i-1] && states[i-1])
                continue;
            states[i] = true;
            innerLlist.addLast(nums[i]);
            permuteUniqueInner2(nums, returnValue, innerLlist, count + 1, states);
            innerLlist.removeLast();
            states[i] = false;
        }
    }
}
