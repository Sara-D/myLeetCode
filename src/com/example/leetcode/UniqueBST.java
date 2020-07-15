package com.example.leetcode;
/**
 * @author dw_dingdan1
 * @date 2020/7/15
 *
 * 题号：96
 *
 * 题目：不同的二叉搜索树
 *
 * 给定一个整数 n，求以 1 ... n 为节点组成的二叉搜索树有多少种？
 *
 * 示例:
 *
 * 输入: 3
 * 输出: 5
 * 解释:
 * 给定 n = 3, 一共有 5 种不同结构的二叉搜索树:
 *
 *    1         3     3      2      1
 *     \       /     /      / \      \
 *      3     2     1      1   3      2
 *     /     /       \                 \
 *    2     1         2                 3
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/unique-binary-search-trees
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class UniqueBST {

    public static void main(String[] args){
        System.out.println(new UniqueBST().numTrees(5));
    }

    public int numTrees(int n) {
        //存储计算过的值 避免重复计算
        //int[][] dp = new int[n][n];
        //return numTrees(1, n, dp);
        return numTreesInner(n);
    }

    public int numTrees(int from, int to, int[][] dp){
        if(dp[from-1][to-1] != 0){
            return dp[from-1][to-1];
        }
        if(from >= to){
           return 1;
        }
        int sum = numTrees(from + 1, to, dp);
        for(int i = from + 1; i < to; i++){
            sum += numTrees(from, i-1, dp) * numTrees(i+1, to, dp);
        }
        sum += numTrees(from, to-1, dp);
        dp[from-1][to-1] = sum;
        return sum;
    }

    //动态规划
    public int numTreesInner(int n){
        int[] dp = new int[n+1];
        dp[0] = 1;
        dp[1] = 1;
        for(int i=2;i<=n;i++){
            int sum = 0;
            for(int j = 1; j<=i;j++){
                sum += dp[j-1] * dp[i-j];
            }
            dp[i] = sum;
        }
        return dp[n];
    }
}
