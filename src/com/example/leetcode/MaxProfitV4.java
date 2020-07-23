package com.example.leetcode;

/**
 * @author dw_dingdan1
 * @date 2020/7/22
 *
 * 题号：714
 *
 * 题目：买卖股票的最佳时机含手续费
 *
 *给定一个整数数组 prices，其中第 i 个元素代表了第 i 天的股票价格 ；非负整数 fee 代表了交易股票的手续费用。
 *
 * 你可以无限次地完成交易，但是你每笔交易都需要付手续费。如果你已经购买了一个股票，在卖出它之前你就不能再继续购买股票了。
 *
 * 返回获得利润的最大值。
 *
 * 注意：这里的一笔交易指买入持有并卖出股票的整个过程，每笔交易你只需要为支付一次手续费。
 *
 * 示例 1:
 *
 * 输入: prices = [1, 3, 2, 8, 4, 9], fee = 2
 * 输出: 8
 * 解释: 能够达到的最大利润:
 * 在此处买入 prices[0] = 1
 * 在此处卖出 prices[3] = 8
 * 在此处买入 prices[4] = 4
 * 在此处卖出 prices[5] = 9
 * 总利润: ((8 - 1) - 2) + ((9 - 4) - 2) = 8.
 * 注意:
 *
 * 0 < prices.length <= 50000.
 * 0 < prices[i] < 50000.
 * 0 <= fee < 50000.
 *
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-with-transaction-fee
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class MaxProfitV4 {

    public static void main(String[] args){
        int[] prices = new int[]{2,1,4,4,2,3,2,5,1,2};
        System.out.println(new MaxProfitV4().maxProfit2(prices, 1));
    }

    //因为有手续费 所以能一笔交易完成就一笔交易完成，否则会多给手续费
    public int maxProfit1(int[] prices, int fee) {
        int lastBuyPrice = prices[0];
        int lastSalePrice = prices[0];
        int maxProfit = 0;
        for(int i=1;i<prices.length;i++){
            if(prices[i] >= lastSalePrice) {
                lastSalePrice = prices[i];
            }else if(lastSalePrice - prices[i] > fee){
                //结算旧的交易
                int tmp = lastSalePrice - lastBuyPrice - fee;
                if(tmp > 0)
                    maxProfit += tmp;
                //开始新的交易
                lastBuyPrice = prices[i];
                lastSalePrice = prices[i];
            }else if(prices[i] <= lastBuyPrice){
                lastBuyPrice = prices[i];
                lastSalePrice = prices[i];
            }
        }
        int tmp = lastSalePrice - lastBuyPrice - fee;
        if(tmp > 0)
            maxProfit += tmp;
        return maxProfit;
    }

    //动态规划
    public int maxProfit2(int[] prices, int fee){
        if(prices.length == 0)
            return 0;
        //每天每种状态的收益 0-持有现金 1-持有股票
        //int[][] dp = new int[prices.length][2];
        int cash = 0;
        int stock = -prices[0];
        for(int i=1;i<prices.length;i++){
            //为什么不用临时变量存储 cash 呢
            cash = Math.max(cash, stock + prices[i] - fee);
            stock = Math.max(stock, cash - prices[i]);
        }
        return cash;
    }
}
