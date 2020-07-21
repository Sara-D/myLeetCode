package com.example.leetcode;

/**
 * @author dw_dingdan1
 * @date 2020/7/21
 *
 * 题号：剑指 Offer 63. leetCode 121
 *
 * 题目：股票的最大利润
 *
 * 假设把某股票的价格按照时间先后顺序存储在数组中，请问买卖该股票一次可能获得的最大利润是多少？
 *
 * 示例 1:
 *
 * 输入: [7,1,5,3,6,4]
 * 输出: 5
 * 解释: 在第 2 天（股票价格 = 1）的时候买入，在第 5 天（股票价格 = 6）的时候卖出，最大利润 = 6-1 = 5 。
 *      注意利润不能是 7-1 = 6, 因为卖出价格需要大于买入价格。
 * 示例 2:
 *
 * 输入: [7,6,4,3,1]
 * 输出: 0
 * 解释: 在这种情况下, 没有交易完成, 所以最大利润为 0。
 *
 * 注意：本题与主站 121 题相同：https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock/
 *
 *
 * 限制：0 <= 数组长度 <= 10^5
 *
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/gu-piao-de-zui-da-li-run-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class MaxProfitV2 {

    public static void main(String[] args){
        int[] prices = new int[]{7,6,4,3,1,10};
        System.out.println(new MaxProfitV2().maxProfit3(prices));
    }

    public int maxProfit(int[] prices) {
        if(prices.length == 0)
            return 0;
        //存储两种状态的最大值
        //0-买入 1-卖出
        int[] dp = new int[]{-prices[0], 0};
        for(int i=1;i<prices.length;i++){
            int tmp1 = Math.max(dp[0], -prices[i]);
            int tmp2 = Math.max(dp[1], prices[i] + dp[0]);
            dp[0] = tmp1;
            dp[1] = tmp2;
        }
        return dp[1];
    }

    public int maxProfit2(int[] prices) {
        int maxProfit = 0;
        int buy = Integer.MAX_VALUE;
        for(int i=0;i<prices.length;i++){
            maxProfit = Math.max(maxProfit, prices[i] - buy);
            buy = Math.min(buy, prices[i]);
        }
        return maxProfit;
    }

    //速度最快的解法 少算一步
    public int maxProfit3(int[] prices){
        int minprice = Integer.MAX_VALUE;
        int maxprofit = 0;
        for (int i = 0; i < prices.length; i++) {
            if (prices[i] < minprice)
                minprice = prices[i];
            else if (prices[i] - minprice > maxprofit)
                maxprofit = prices[i] - minprice;
        }
        return maxprofit;
    }
}
