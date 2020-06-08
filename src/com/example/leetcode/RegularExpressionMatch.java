package com.example.leetcode;
/**
 * @author dw_dingdan1
 * @date 2020/5/25
 */
public class RegularExpressionMatch {

    public static void main(String[] args){
        String s = "mississippi"; String p = "mis*is*p*.";
        System.out.println(isMatchForDp(s, p));
    }

    static boolean isMatch(String s, String p){
        if(p.isEmpty()) return s.isEmpty();
        boolean firstMatch = !s.isEmpty() && (s.charAt(0)==p.charAt(0) || p.charAt(0)=='.');
        if(p.length() > 1 && p.charAt(1)=='*'){
            return isMatch(s, p.substring(2)) || firstMatch && isMatch(s.substring(1), p);
        }else {
            return firstMatch && isMatch(s.substring(1), p.substring(1));
        }
    }

    static boolean isMatchForDp(String s, String p){
        Boolean[][] memo = new Boolean[s.length()+1][p.length()+1];
        return isMatchForDpInner(0, 0, s, p, memo);
    }

    static boolean isMatchForDpInner(int i, int j, String s, String p, Boolean[][] memo){
        System.out.println("i:" + i + " j:" + j);
        if(memo[i][j] != null){
            //什么情况会重复计算呢
            System.out.println("i:" + i + " j:" + j + " has calculated!");
            return memo[i][j] == Boolean.TRUE;
        }
        boolean ans;
        if(j == p.length()){
            ans = i == s.length();
        }else {
            boolean firstMatch = i < s.length() && (s.charAt(i) == p.charAt(j) || p.charAt(j) == '.');
            if(j+1 < p.length() && p.charAt(j+1)=='*'){
                ans = isMatchForDpInner(i, j+2, s, p, memo) || firstMatch && isMatchForDpInner(i+1, j, s, p, memo);
            }else {
                ans = firstMatch && isMatchForDpInner(i+1, j+1, s, p, memo);
            }
        }
        memo[i][j] = ans ? Boolean.TRUE : Boolean.FALSE;
        return ans;
    }
}
