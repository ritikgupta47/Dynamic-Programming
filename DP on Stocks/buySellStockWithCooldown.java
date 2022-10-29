import java.util.Arrays;

public class buySellStockWithCooldown {
    public static void main(String[] args) {
        int[] prices = { 1, 2, 3, 0, 2 };
        int n = prices.length;
        int[][] dp = new int[n][2];
        for (int[] rows : dp) {
            Arrays.fill(rows, -1);
        }
        System.out.println("memo : " + solve(prices, 0, 0, n, dp));
        System.out.println("tabulation : " + maxProfit(prices));
    }

    // memoization soln
    static int solve(int[] prices, int i, int buy, int n, int[][] dp) {
        if (i >= n)
            return 0; // base case
        if (dp[i][buy] != -1)
            return dp[i][buy];
        int profit = 0;
        if (buy == 0) {// We can buy the stock
            profit = Math.max(0 + solve(prices, i + 1, 0, n, dp), -prices[i] + solve(prices, i + 1, 1, n, dp));
        }
        if (buy == 1) {// We can sell the stock
            profit = Math.max(0 + solve(prices, i + 1, 1, n, dp), prices[i] + solve(prices, i + 2, 0, n, dp));
        }
        return dp[i][buy] = profit;
    }

    // tabulation
    public static int maxProfit(int[] prices) {
        int n = prices.length;
        int[][] dp = new int[n + 2][2];

        for (int i = n - 1; i >= 0; i--) {
            for (int buy = 0; buy < 2; buy++) {
                int profit = 0;
                if (buy == 1) {
                    profit = Math.max((-prices[i] + dp[i + 1][0]), dp[i + 1][1]);
                } else {
                    profit = Math.max((prices[i] + dp[i + 2][1]), dp[i + 1][0]);
                }
                dp[i][buy] = profit;
            }
        }
        return dp[0][1];
    }
}
