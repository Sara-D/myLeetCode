package com.example.leetcode;
import java.util.concurrent.atomic.AtomicLong;

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

    AtomicLong atomicLong = new AtomicLong();

    public int getDistance(String word1, String word2) {
        return getDistanceDp(word1, word2);
    }

    //递归
    private int getDistanceRecursive(String word1, String word2, int index1, int index2){
        atomicLong.incrementAndGet();
        System.out.println("index1=" + index1 + ";index2=" + index2);
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
            //return 1 + getDistanceRecursive(word1, word2, index1 + 1, index2 + 1);
        }
    }

    //递归
    private int getDistanceDp(String word1, String word2){
        if(word1.length() > word2.length()){
            String tmp = word1;
            word1 = word2;
            word2 = tmp;
        }
        int n = word1.length();
        int m = word2.length();
        int[][] dp = new int[n+1][m+1];
        //初始化
        for(int i=0; i<=m;){
            dp[0][i] = i++;
        }
        for(int l=0; l<=n;){
            dp[l][0] = l++;
        }
        for(int j=1; j<=m; j++){
            char a = word2.charAt(j-1);
            for(int k=1; k<=n; k++){
                char b = word1.charAt(k-1);
                if(a == b){
                    dp[k][j] = dp[k-1][j-1];
                }else {
                    dp[k][j] = Math.min(dp[k-1][j-1] + 1, Math.min(dp[k-1][j] + 1, dp[k][j-1] + 1));
                }
            }
        }
        return dp[n][m];
    }

    private int getDistanceDp2(String word1, String word2){
        if(word1.length() > word2.length()){
            String tmp = word1;
            word1 = word2;
            word2 = tmp;
        }
        int n = word1.length();
        int m = word2.length();
        //int[] last = new int[m+1];
        int[] cur = new int[n+1];
        for(int i=0; i<=n;){
            cur[i] = i++;
        }
        for(int k=1; k<=m; k++){
            int upperLeft = cur[0];
            cur[0] = k;
            char a = word2.charAt(k-1);
            for(int j=1; j<=n; j++){
                int tmp = cur[j];
                char b = word1.charAt(j-1);
                if(a == b)
                    upperLeft--;
                cur[j] = 1 + Math.min(upperLeft, Math.min(cur[j], cur[j-1]));
                upperLeft = tmp;
            }
            //int[] tmp = cur;
            //cur = last;
            //last = tmp;
        }
        //return last[m];
        return cur[n];
    }


    //"intention"
    //"execution"
    public static void main(String[] args){
        EditDistance editDistance = new EditDistance();
        String word1 = "intention";
        String word2 = "execution";
        System.out.println("length1=" + word1.length() + ";length2=" + word2.length());
        int distance = editDistance.getDistanceDp2(word1, word2);
        System.out.println("distance:" + distance);
    }
}
