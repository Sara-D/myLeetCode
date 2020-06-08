package com.example.javas;
/**
 * @author dw_dingdan1
 * @date 2020/6/4
 */
public class ClosestPowerOfTwo {

    public static void main(String[] args){
        //System.out.println(Math.pow(2, 4));
        //System.out.println(getClosestPowerOfTwo(8));
        //int a = 10;
        //System.out.println(~a+1);
        //System.out.println(Math.abs(-10));
        //Integer b = 10;

        /*int a = Integer.MAX_VALUE + 1;

        System.out.println(Integer.reverse(a));

        System.out.println(getReverseOrderOfInt(a));

        a = ((a & 0xAAAAAAAA) >> 1) | ((a & 0x55555555) << 1);
        a = ((a & 0xCCCCCCCC) >> 2) | ((a & 0x33333333) << 2);
        a = ((a & 0xF0F0F0F0) >> 4) | ((a & 0x0F0F0F0F) << 4);
        a = ((a & 0xFF00FF00) >> 8) | ((a & 0x00FF00FF) << 8);
        a = ((a & 0xFFFF0000) >> 16) | ((a & 0x0000FFFF) << 16);
        System.out.println(a);*/

        int c = 0xF0000000;
        System.out.println(c);
        System.out.println(c>>1);
        System.out.println(c>>>1);
    }

    //return Math.pow(2,n) closest and bigger than or equal to cap
    public static int getClosestPowerOfTwo(int cap){
        //assume that n has k bits
        //int n = cap - 1;
        int n = cap;
        //let k-1 bit become 1
        n |= n >>> 1;
        //let k-2 and k-3 bit become 1
        n |= n >>> 2;
        //let k-4 and k-5 and k-6 and k-7 become 1
        n |= n >>> 4;
        n |= n >>> 8;
        n |= n >>> 16;
        return n + 1;
    }

    public static int getReverseOrderOfInt(long a){
        long b = a;
        b = ((b & 0xAAAAAAAA) >> 1) | ((b & 0x55555555) << 1);
        b = ((b & 0xCCCCCCCC) >> 2) | ((b & 0x33333333) << 2);
        b = ((b & 0xF0F0F0F0) >> 4) | ((b & 0x0F0F0F0F) << 4);
        b = ((b & 0xFF00FF00) >> 8) | ((b & 0x00FF00FF) << 8);
        b = ((b & 0xFFFF0000) >> 16) | ((b & 0x0000FFFF) << 16);
        return (int)b;
    }


}
