import java.util.Arrays;

public class buyAndSellStocks2 {
    public static void main(String[] args) {
        int[] prices = { 7, 1, 5, 3, 6, 4 };
        int n = prices.length;
        int[][] dp = new int[n][2];
        for (int[] rows : dp) {
            Arrays.fill(rows, -1);
        }
        System.out.println("max profit : " + solve(prices, 0, 0, dp));
        System.out.println("tabulation :" + maxProfit(prices));
    }
    // memoization solution
    public static int solve(int[] prices, int idx, int buy, int[][] dp) {
        if (idx == prices.length) {
            return 0;
        }
        if (dp[idx][buy] != -1)
            return dp[idx][buy];
        int profit = 0;
        if (buy == 0) { // can buy
            profit = Math.max(0 + solve(prices, idx + 1, 0, dp), (-prices[idx] + solve(prices, idx + 1, 1, dp)));
        }
        if (buy == 1) { // can sell
            profit = Math.max(0 + solve(prices, idx + 1, 1, dp), (prices[idx] + solve(prices, idx + 1, 0, dp)));
        }
        return dp[idx][buy]=profit;
    }
    // tabulation solution
    public static int maxProfit(int[] prices) {
        int n = prices.length;
        int dp[][] = new int[n + 1][2];
        dp[n][0] = dp[n][1] = 0;
        // int profit=0;
        for (int ind = n - 1; ind >= 0; ind--) {
            for (int buy = 0; buy <= 1; buy++) {
                int profit = 0;
                if (buy == 0) {// We can buy the stock
                    profit = Math.max(0 + dp[ind + 1][0], -prices[ind] + dp[ind + 1][1]);
                }
                if (buy == 1) {// We can sell the stock
                    profit = Math.max(0 + dp[ind + 1][1], prices[ind] + dp[ind + 1][0]);
                }
                dp[ind][buy] = profit;
            }
        }
        return dp[0][0];
    }
}

