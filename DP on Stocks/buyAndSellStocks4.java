import java.util.Arrays;

public class buyAndSellStocks4 {
    public static void main(String[] args) {
        int[]  prices = {3,2,6,5,0,3};
        int k = 2;
        int n = prices.length;
        int[][][] dp = new int[n][2][k+1];
        for(int[][] rows : dp){
            for(int[] row : rows){
                Arrays.fill(row , -1);
            }
        }
        System.out.println("memoization : " + solve(prices, k, 0, 1, dp));
        System.out.println("tabulation : " + maxProfit(k, prices));

    }
    // memoization
    public static int solve(int[] prices , int k , int i , int buy , int[][][] dp){
        if(k == 0) return 0;
        if(i == prices.length) return 0;
        
        if(dp[i][buy][k] != -1) return dp[i][buy][k];
        
        int profit = 0;
        if(buy == 1){  // 1 means buy
            profit = Math.max((-prices[i] + solve(prices , k , i+1 , 0 ,dp)) , solve(prices , k , i+1 , 1 , dp));
        }else{
            profit = Math.max((prices[i] + solve(prices , k - 1 , i+1 , 1 , dp)) , solve(prices , k , i + 1 , 0 , dp));
        }
        return dp[i][buy][k] = profit;
    }
    // tabulation
    public static int maxProfit(int k, int[] prices) {
        int n = prices.length;
        int[][][] dp = new int[n+1][2][k+1];
        
        for(int i = 0; i <= n; i++){
            for(int buy = 0 ; buy <= 1 ;buy++){
                dp[i][buy][0] = 0;
            }
        }
        for(int buy = 0; buy <= 1; buy++){
            for(int cap = 0; cap <= k ; cap++){
                dp[n][buy][cap] = 0;
            }
        }
        
        for(int i = n-1; i>=0; i--){
            for(int buy = 0; buy<=1; buy++){
                for(int cap=1; cap<=k; cap++){
                    if(buy==0){// We can buy the stock
                        dp[i][buy][cap] = Math.max(0+dp[i+1][0][cap], -prices[i] + dp[i+1][1][cap]);
                    }
                    if(buy==1){// We can sell the stock
                        dp[i][buy][cap] = Math.max(0+dp[i+1][1][cap],prices[i] + dp[i+1][0][cap-1]);
                    }
                }
            }
        }
        return dp[0][0][k];
    }
}
