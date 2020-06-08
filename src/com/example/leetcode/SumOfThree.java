package com.example.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author dw_dingdan1
 * @date 2020/6/2
 */
public class SumOfThree {

    public static void main(String[] args){
        //-4 -1 -1 0 1 2
        List<List<Integer>> list = sumOfThree(new int[]{-1,0,1,2,-1,-4});
        for(List<Integer> inner : list){
            for(Integer integer : inner){
                System.out.print(integer + " ");
            }
            System.out.println();
        }
    }

    public static List<List<Integer>> sumOfThree(int[] nums){
        List<List<Integer>> list = new ArrayList<>();
        if(nums==null || nums.length<3){
            return list;
        }
        Arrays.sort(nums);
        int prev = 0;
        for(int i=0;i<nums.length;i++){
            int first = nums[i];
            if(i>0 && prev == first){
                continue;
            }
            prev = first;
            int j = i+1, m = nums.length - 1;
            int prevLeft = 0, prevRight = 0;
            while (j<m){
                if(j > i+1 && prevLeft == nums[j]){
                    j++;
                    continue;
                }
                if(m < nums.length - 1 && prevRight == nums[m]){
                    m--;
                    continue;
                }
                if(nums[j] + nums[m] == -first) {
                    List<Integer> inner = new ArrayList<>();
                    inner.add(first);
                    inner.add(nums[j]);
                    inner.add(nums[m]);
                    list.add(inner);
                    prevLeft = nums[j];
                    prevRight = nums[m];
                    j++;
                    m--;
                }else if(nums[j] + nums[m] < -first){
                    prevLeft = nums[j];
                    j++;
                }else {
                    prevRight = nums[m];
                    m--;
                }
            }
        }
        return list;
    }
}
