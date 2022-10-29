public class buyAndSellStocks3 {
    public static void main(String[] args) {
        int[] prices = { 3,3,5,0,0,3,1,4};
        System.out.println(f(prices, 0, 1, 2));
        System.out.println(maxProfit(prices));
    }
    // recursion
    public static int f(int[] prices, int ind, int buy, int cap) {
        if(ind==prices.length || cap==0){
            return 0;
        }

        if(buy==1){
            return Math.max(-prices[ind]+f(prices, ind+1, 0, cap), f(prices, ind+1, 1, cap));
        } else {
            return Math.max(prices[ind]+f(prices, ind+1, 1, cap-1), f(prices, ind+1, 0, cap));
        }
    }
    // tabulation 
    public static int maxProfit(int[] prices) {
        int n = prices.length;

        int[][][] dp = new int[n+1][2][3];         //length buy  cap

        for(int buy=0;buy<=1;buy++){
            for(int cap=0;cap<=2;cap++){
                dp[n][buy][cap] = 0;
            }
        }

        for(int idx=0;idx<=n;idx++){
            for(int buy=0;buy<2;buy++){
                dp[idx][buy][0] = 0;
            }
        }

        for(int idx=n-1;idx>=0;idx--){
            for(int buy=0;buy<=1;buy++){
                for(int cap=1;cap<=2;cap++){
                    if(buy==1){
                        dp[idx][buy][cap] = Math.max(-prices[idx]+dp[idx+1][0][cap], dp[idx+1][1][cap]);
                    } else {
                        dp[idx][buy][cap] = Math.max(prices[idx]+dp[idx+1][1][cap-1], dp[idx+1][0][cap]);  
                    }
                }
            }
        }

        return dp[0][1][2];
    }
}
