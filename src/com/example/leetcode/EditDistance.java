package com.example.leetcode;

import javax.swing.text.AttributeSet;

/**
 * @author dw_dingdan1
 * @date 2021/7/7
 *
 * 72. 编辑距离
 *
 * 给你两个单词 word1 和 word2，请你计算出将 word1 转换成 word2 所使用的最少操作数 。
 *
 * 你可以对一个单词进行如下三种操作：
 *
 * 插入一个字符
 * 删除一个字符
 * 替换一个字符
 *  
 *
 * 示例 1：
 *
 * 输入：word1 = "horse", word2 = "ros"
 * 输出：3
 * 解释：
 * horse -> rorse (将 'h' 替换为 'r')
 * rorse -> rose (删除 'r')
 * rose -> ros (删除 'e')
 * 示例 2：
 *
 * 输入：word1 = "intention", word2 = "execution"
 * 输出：5
 * 解释：
 * intention -> inention (删除 't')
 * inention -> enention (将 'i' 替换为 'e')
 * enention -> exention (将 'n' 替换为 'x')
 * exention -> exection (将 'n' 替换为 'c')
 * exection -> execution (插入 'u')
 *  
 *
 * 提示：
 *
 * 0 <= word1.length, word2.length <= 500
 * word1 和 word2 由小写英文字母组成
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/edit-distance
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */



public class EditDistance {
    public int getDistance(String word1, String word2) {
        return getDistanceRecursive(word1, word2, 0, 0);
    }

    //递归
    private int getDistanceRecursive(String word1, String word2, int index1, int index2){
        if(index1 >= word1.length()){
            return Math.max(0, word2.length() - index2);
        }
        if(index2 >= word2.length()){
            return Math.max(0, word1.length() - index1);
        }
        char a = word1.charAt(index1);
        char b = word2.charAt(index2);
        if(a == b){
            return getDistanceRecursive(word1, word2, index1 + 1, index2 + 1);
        }else {
            return 1 + Math.min(getDistanceRecursive(word1, word2, index1 + 1, index2 + 1),
                    Math.min(getDistanceRecursive(word1, word2, index1 + 1, index2), getDistanceRecursive(word1, word2, index1, index2 + 1)));
        }
    }

    private int getDistanceDp(String word1, String word2){
        int n = Math.min(word1.length(), word2.length());
        int[][] dp = new int[n][n];
        return 0;
    }

    public static void main(String[] args){
        EditDistance editDistance = new EditDistance();
        int distance = editDistance.getDistanceRecursive("horse", "ros", 0, 0);
        System.out.println("distance:" + distance);
    }
}
