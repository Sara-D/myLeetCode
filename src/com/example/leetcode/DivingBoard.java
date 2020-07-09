package com.example.leetcode;


import java.util.Arrays;

/**
 * @author dw_dingdan1
 * @date 2020/7/8
 */
public class DivingBoard {

    public static void main(String[] args){
        int[] array = divingBoard(1, 2, 3);
        System.out.println(Arrays.toString(array));
    }

    public static int[] divingBoard(int shorter, int longer, int k) {
        if(k == 0){
            return new int[]{};
        }
        if(shorter == longer){
            return new int[]{shorter * k};
        }
        int[] array = new int[k+1];
        for(int i=0;i<=k;i++){
            array[i] = longer*i + shorter*(k-i);
        }
        return array;
    }
}
