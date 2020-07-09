package com.example.leetcode;
/**
 * @author dw_dingdan1
 * @date 2020/6/23
 * 两数相除，不能使用乘法、除法和mod运算符
 */
public class Divide {
    public static int divide(int dividend, int divisor) {
        //除数和被除数的符号 -1-相反 1-相同
        int sign = 0;
        if(dividend <= 0 && divisor > 0 || dividend >= 0 && divisor<0){
            sign = -1;
        }else {
            sign = 1;
        }
        int a = Math.abs(dividend);
        int b = Math.abs(divisor);
        if(b > a){
            return 0;
        }
        int value = 0; int sum = 1;
        while (b<a){
            b = b << 1;
            sum = sum << 1;
        }
        return 1;
    }
}
