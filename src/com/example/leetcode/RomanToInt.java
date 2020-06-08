package com.example.leetcode;
import java.util.HashMap;
import java.util.Map;

/**
 * @author dw_dingdan1
 * @date 2020/6/1
 */
public class RomanToInt {

    public static void main(String[] args){
        System.out.println(romanToInt2("MCMXCIV"));
    }

    static int romanToInt2(String s){
        if(s == null || s.length() == 0){
            return 0;
        }
        int num = 0;
        int prev = getValue(s.charAt(0));
        for(int i=1;i<s.length();i++){
            int cur = getValue(s.charAt(i));
            if(prev < cur){
                num -= prev;
            }else {
                num += prev;
            }
            prev = cur;
        }
        num += prev;
        return num;
    }

    static int getValue(char i){
        switch (i){
            case 'M' : return 1000;
            case 'D' : return 500;
            case 'C' : return 100;
            case 'L' : return 50;
            case 'X' : return 10;
            case 'V' : return 5;
            case 'I' : return 1;
            default : return 0;
        }
    }

    static int romanToInt(String s) {
        if(s == null || s.length() == 0){
            return 0;
        }
        Map<Character, Integer> map = new HashMap();
        map.put('M', 1000);
        map.put('D', 500);
        map.put('C', 100);
        map.put('L', 50);
        map.put('X', 10);
        map.put('V', 5);
        map.put('I', 1);
        int num = 0;
        for(int i=0;i<s.length();i++){
            char tmp = s.charAt(i);
            if(tmp=='I' || tmp=='X' || tmp=='C'){
                char tmp2;
                if(i+1<s.length() && ((tmp2=s.charAt(i+1))=='V' || tmp2=='X' || tmp2=='L' || tmp2=='C' || tmp2=='D' || tmp2=='M')){
                    num += map.get(tmp2);
                    num -= map.get(tmp);
                    i += 1;
                }else{
                    num += map.get(tmp);
                }
            }else{
                num += map.get(tmp);
            }
        }
        return num;
    }
}
