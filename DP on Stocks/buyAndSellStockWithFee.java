import java.util.Arrays;

public class buyAndSellStockWithFee {
    public static void main(String[] args) {
        int[] prices = { 1, 3, 2, 8, 4, 9 };
        int n = prices.length;
        int[][] dp = new int[n+1][2];
        for(int[] rows : dp){
            Arrays.fill(rows , -1);
        }
        int fee = 2;
        System.out.println("memo : " + solve(prices, 0, 0, n, fee, dp));
        System.out.println("tabulation : " + maxProfit(prices, fee));
    }
    static int solve(int[] prices, int i, int buy, int n, int fee ,  int[][] dp) {
        if (i == n)
            return 0; // base case
        if (dp[i][buy] != -1)
            return dp[i][buy];
        int profit = 0;
        if (buy == 0) {// We can buy the stock
            profit = Math.max(0 + solve(prices, i + 1, 0, n, fee ,  dp), -prices[i] + solve(prices, i + 1, 1, n,fee, dp));
        }
        if (buy == 1) {// We can sell the stock
            profit = Math.max(0 + solve(prices, i + 1, 1, n,fee, dp), prices[i] -fee + solve(prices, i + 1, 0, n,fee, dp));
        }
        return dp[i][buy] = profit;
    }
    // tabulation
    public static int maxProfit(int[] prices, int fee) {
        int n = prices.length;
        int[][] dp = new int[n + 2][2];

        for (int i = n - 1; i >= 0; i--) {
            for (int buy = 0; buy < 2; buy++) {
                int profit = 0;
                if (buy == 1) {
                    profit = Math.max((-prices[i] + dp[i + 1][0]), dp[i + 1][1]);
                } else {
                    profit = Math.max((prices[i] - fee + dp[i + 1][1]), dp[i + 1][0]);
                }
                dp[i][buy] = profit;
            }
        }
        return dp[0][1];
    }
}
