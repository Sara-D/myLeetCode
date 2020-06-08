package com.example.leetcode;
import java.util.Arrays;

/**
 * @author dw_dingdan1
 * @date 2020/6/2
 */
public class ClosestSumOfThree {

    public static void main(String[] args){
        //-5 -5 -4 0 0 3 3 4 5
        int[] nums = {4,0,5,-5,3,3,0,-4,-5};
        int target = -2;
        System.out.println(closestSumOfThree(nums, target));
    }

    //优化点：1、去重
    public static int closestSumOfThree(int[] nums, int target){
        if(nums==null || nums.length < 3){
            return 0;
        }
        Arrays.sort(nums);
        int closestTarget = 0;
        int outerDiff = Integer.MAX_VALUE;
        //2、i<nums.length-2
        for(int i=0;i<nums.length;i++){
            int diff = Integer.MAX_VALUE;
            //边界问题判断 最小值:nums[j] + nums[j+1] 最大值: nums[m] + nums[m-1]
            int twoSum = target - nums[i], j = i+1, m = nums.length-1;
            while (j < m){
                int tmp = nums[j] + nums[m];
                if(tmp == twoSum){
                    return target;
                }else if(tmp < twoSum){
                    j++;
                }else {
                    m--;
                }
                //不能提前break
                if(Math.abs(twoSum - tmp) < diff){
                    diff = Math.abs(twoSum - tmp);
                    if(diff < outerDiff) {
                        closestTarget = nums[i] + tmp;
                        outerDiff = diff;
                    }
                }
            }
        }
        return closestTarget;
    }
}
