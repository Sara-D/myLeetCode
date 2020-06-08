package com.example.leetcode;
import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;

/**
 * @author dw_dingdan1
 * @date 2020/6/1
 */
public class IntToRoman {

    public static void main(String[] args){
        System.out.println(intToRoman5(999));
    }

    static String intToRoman5(int num){
        //hard encode
        String[] thousands = new String[]{"", "M", "MM", "MMM"};
        String[] hundreds = new String[]{"", "C", "CC", "CCC", "CD", "D", "DC", "DCC", "DCCC", "CM"};
        String[] tens = new String[]{"", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC"};
        String[] ones = new String[]{"", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX"};
        StringBuilder sb = new StringBuilder();
        sb.append(thousands[num / 1000]);
        sb.append(hundreds[(num % 1000) / 100]);
        sb.append(tens[(num % 100) / 10]);
        sb.append(ones[num % 10]);
        return sb.toString();
    }

    static String intToRoman4(int num){
        int[] ints = new int[]{1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        String[] romans = new String[]{"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
        StringBuilder sb = new StringBuilder();
        for(int i=0;i<ints.length && num > 0;i++){
            while (num >= ints[i]) {
                sb.append(romans[i]);
                num -= ints[i];
            }
        }
        return sb.toString();
    }

    static String intToRoman3(int num){
        int[] ints = new int[]{1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        String[] romans = new String[]{"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
        StringBuilder sb = new StringBuilder();
        for(int i=0;i<ints.length;i++){
            while (num >= ints[i]) {
                sb.append(romans[i]);
                num -= ints[i];
            }
        }
        return sb.toString();
    }

    static String intToRoman2(int num){
        int[] ints = new int[]{1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        String[] romans = new String[]{"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
        StringBuilder sb = new StringBuilder();
        for(int i=0;i<ints.length;i++){
            if(num >= ints[i]){
                int a = num / ints[i];
                for(int j=1;j<=a;j++){
                    sb.append(romans[i]);
                    num -= ints[i];
                }
            }
        }
        return sb.toString();
    }

    static String intToRoman(int num){
        Map<Integer, String> map = new TreeMap<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2.compareTo(o1);
            }
        });
        map.put(1, "I");
        map.put(5, "V");
        map.put(10, "X");
        map.put(50, "L");
        map.put(100, "C");
        map.put(500, "D");
        map.put(1000, "M");
        map.put(4, "IV");
        map.put(9, "IX");
        map.put(40, "XL");
        map.put(90, "XC");
        map.put(400, "CD");
        map.put(900, "CM");
        StringBuilder sb = new StringBuilder();
        for(Map.Entry<Integer, String> entry : map.entrySet()){
            if(num >= entry.getKey()){
                int a = num / entry.getKey();
                for(int j=1;j<=a;j++){
                    sb.append(entry.getValue());
                    num -= entry.getKey();
                }
            }
        }
        return sb.toString();
    }
}
