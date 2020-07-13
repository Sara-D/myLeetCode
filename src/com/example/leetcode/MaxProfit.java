package com.example.leetcode;

/**
 * @author dw_dingdan1
 * @date 2020/7/10
 *
 * 题号 309
 *
 * 给定一个整数数组，其中第 i 个元素代表了第 i 天的股票价格 。​
 *
 * 设计一个算法计算出最大利润。在满足以下约束条件下，你可以尽可能地完成更多的交易（多次买卖一支股票）:
 *
 * 你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
 * 卖出股票后，你无法在第二天买入股票 (即冷冻期为 1 天)。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-with-cooldown
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * 示例:
 *
 * 输入: [1,2,3,0,2]
 * 输出: 3
 * 解释: 对应的交易状态为: [买入, 卖出, 冷冻期, 买入, 卖出]
 */
public class MaxProfit {

    public static void main(String[] args){
        int[] prices = {1,2,3,0,2};
        System.out.println(maxProfit(prices));
    }

    public static int maxProfit(int[] prices) {
        //存储每天结束后每种状态的最大收益
        // 0-当天持有股票 (1、当天买入则前一天不持有股票且不处于冷冻期 2、当天没有进行任何操作则前一天持有股票)
        // 1-当天没有股票且处于冷冻期 (1、前一天持有股票当天卖出)
        // 2-当天没有股票且不处于冷冻期 (1、前一天没有股票且处于冷冻期 2、前一天没有股票且不处于冷冻期)
        //通过	2 ms	39.5 MB	Java
        //1、去掉 prices == null 等不必要的边界检测 2、二维数组赋值优化（不要先new一个一维数据）
        //执行用时 1 ms, 在所有 Java 提交中击败了99.47%的用户 内存消耗：39.4 MB, 在所有 Java 提交中击败了11.11%的用户
        if(prices.length==0){
            return 0;
        }
        //int[][] maxProfits = new int[prices.length][3];
        //存储前一天的利润数据
        int[] maxProfits = new int[]{-prices[0], 0, 0};
        //maxProfits[0][0] = -prices[0];
        for(int i=1;i<prices.length;i++){
            //maxProfits[i][0] = Math.max(maxProfits[i-1][2] - prices[i], maxProfits[i-1][0]);
            //maxProfits[i][1] = maxProfits[i-1][0] + prices[i];
            //maxProfits[i][2] = Math.max(maxProfits[i-1][1], maxProfits[i-1][2]);
            int tmp1 = Math.max(maxProfits[2] - prices[i], maxProfits[0]);
            int tmp2 = maxProfits[0] + prices[i];
            int tmp3 = Math.max(maxProfits[1], maxProfits[2]);
            maxProfits[0] = tmp1;
            maxProfits[1] = tmp2;
            maxProfits[2] = tmp3;
        }
        //return Math.max(maxProfits[prices.length-1][1], maxProfits[prices.length-1][2]);
        return Math.max(maxProfits[1], maxProfits[2]);
    }
}
