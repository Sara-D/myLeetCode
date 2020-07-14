package com.example.leetcode;

import com.sun.javafx.logging.PulseLogger;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author dw_dingdan1
 * @date 2020/7/14
 *
 * 题号 120
 *
 * 题目 三角形最小路径和
 *
 * 给定一个三角形，找出自顶向下的最小路径和。每一步只能移动到下一行中相邻的结点上。
 *
 * 相邻的结点 在这里指的是 下标 与 上一层结点下标 相同或者等于 上一层结点下标 + 1 的两个结点。
 *
 *  
 *
 * 例如，给定三角形：
 *
 * [
 *      [2],
 *     [3,4],
 *    [6,5,7],
 *   [4,1,8,3]
 * ]
 * 自顶向下的最小路径和为 11（即，2 + 3 + 5 + 1 = 11）。
 *
 *  
 *
 * 说明：
 *
 * 如果你可以只使用 O(n) 的额外空间（n 为三角形的总行数）来解决这个问题，那么你的算法会很加分。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/triangle
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class MinimumTotalOfTriangle {

    public static void main(String[] args){
        List<List<Integer>> triangle = new ArrayList<>();
        triangle.add(Arrays.asList(2));
        triangle.add(Arrays.asList(3,4));
        triangle.add(Arrays.asList(6,5,7));
        triangle.add(Arrays.asList(4,1,8,3));
        System.out.println(new MinimumTotalOfTriangle().minimumTotal(triangle));
    }

    public int minimumTotal(List<List<Integer>> triangle) {
        //int[][] dp = new int[triangle.size()][triangle.get(triangle.size() - 1).size()];
        //for(int i=0;i<triangle.size();i++){
        //    Arrays.fill(dp[i], Integer.MAX_VALUE);
        //}
        //return minimumTotalInner(triangle, 0, 0, dp);
        return minimumTotalInner2(triangle);
    }

    //超时了 有很多重复计算
    public int minimumTotalInner(List<List<Integer>> triangle, int rowNo, int columnNo, int[][] dp){
        if(rowNo >= triangle.size()){
            return 0;
        }
        if(columnNo >= triangle.get(rowNo).size()){
            return 0;
        }
        if(dp[rowNo][columnNo] != Integer.MAX_VALUE)
            return dp[rowNo][columnNo];
        Integer cur = triangle.get(rowNo).get(columnNo);
        int left = minimumTotalInner(triangle, rowNo + 1, columnNo, dp);
        int right = minimumTotalInner(triangle, rowNo+1, columnNo+1, dp);
        dp[rowNo][columnNo] = left < right ? cur + left : cur + right;
        return dp[rowNo][columnNo];
    }

    //动态规划
    public int minimumTotalInner2(List<List<Integer>> triangle){
        int rows = triangle.size() + 1;
        int columns = triangle.get(triangle.size()-1).size() + 1;
        int[][] dp = new int[rows][columns];
        for(int i=1;i<=triangle.size();i++){
            List<Integer> innerList = triangle.get(i-1);
            for(int j=0; j<innerList.size(); j++){
                if(j==0) {
                    dp[i][j] = dp[i - 1][j] + innerList.get(j);
                    dp[i][columns - 1] = dp[i][j];
                }else if(j==innerList.size()-1) {
                    dp[i][j] = dp[i - 1][j - 1] + innerList.get(j);
                    dp[i][columns - 1] = Math.min(dp[i][columns - 1], dp[i][j]);
                }else {
                    dp[i][j] = Math.min(dp[i - 1][j], dp[i - 1][j - 1]) + innerList.get(j);
                    dp[i][columns - 1] = Math.min(dp[i][columns - 1], dp[i][j]);
                }
            }
        }
        return dp[rows-1][columns-1];
    }

    public int minimumTotalInner3(List<List<Integer>> triangle){
        int n = triangle.size();
        int[][] f = new int[n][n];
        f[0][0] = triangle.get(0).get(0);
        for (int i = 1; i < n; ++i) {
            f[i][0] = f[i - 1][0] + triangle.get(i).get(0);
            for (int j = 1; j < i; ++j) {
                f[i][j] = Math.min(f[i - 1][j - 1], f[i - 1][j]) + triangle.get(i).get(j);
            }
            f[i][i] = f[i - 1][i - 1] + triangle.get(i).get(i);
        }
        int minTotal = f[n - 1][0];
        for (int i = 1; i < n; ++i) {
            minTotal = Math.min(minTotal, f[n - 1][i]);
        }
        return minTotal;
    }


}
