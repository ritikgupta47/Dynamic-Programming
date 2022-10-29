package DpOnSubsequence;
import java.util.Arrays;

public class coinChange {
    public static void main(String[] args) {
        int[] coins = {1,2,5};
        int amount = 11;
        int n = coins.length;

        System.out.println("recursive Soln : " + solve(coins, amount, n-1));

        int[][]dp = new int[n][amount + 1];
        for(int[] rows : dp){
            Arrays.fill(rows , -1);
        }
        System.out.println("Memo : " + solve(coins, amount,n-1 , dp));

        System.out.println("Tabulation : " + solve(coins, amount));
    }
    // recursion
    public static int solve(int[] coins, int amount, int idx) {
        if (idx == 0) {
            if (amount % coins[0] == 0) {
                return amount / coins[0];
            } else {
                return (int) Math.pow(10, 9);
            }
        }

        int notPick = 0 + solve(coins, amount, idx - 1);
        int pick = (int) Math.pow(10, 9);
        if (coins[idx] <= amount) {
            pick = 1 + solve(coins, amount - coins[idx], idx);
        }
        return Math.min(pick, notPick);
    }
    // memoization
    public static int solve(int[] coins, int amount, int idx, int[][] dp) {
        if (idx == 0) {
            if (amount % coins[0] == 0) {
                return amount / coins[0];
            } else {
                return (int) Math.pow(10, 9);
            }
        }
        if (dp[idx][amount] != -1) {
            return dp[idx][amount];
        }
        int notPick = 0 + solve(coins, amount, idx - 1, dp);
        int pick = (int) Math.pow(10, 9);
        if (coins[idx] <= amount) {
            pick = 1 + solve(coins, amount - coins[idx], idx, dp);
        }
        return dp[idx][amount] = Math.min(pick, notPick);
    }
    // tabulation
    public static int solve(int[] coins , int amount){
        int n = coins.length;
        int[][] dp = new int[n][amount+1];
        for(int i = 0 ; i <= amount; i++){
           if(i % coins[0] == 0){
               dp[0][i] = i/coins[0];
           }else{
               dp[0][i] = (int)Math.pow(10 , 9);
           }
        }
        
        for(int idx = 1; idx < n ; idx++ ){
            for(int target = 0; target <= amount ;target++){
                
                int notPick = 0 + dp[idx-1][target];
                
                int pick = (int)Math.pow(10 , 9);
                if(coins[idx] <= target){
                    pick = 1 + dp[idx][target - coins[idx]];
                }
                
                dp[idx][target] = Math.min(pick , notPick);
            }
        }
        int ans = dp[n-1][amount];
        if(ans >= (int)Math.pow(10 , 9)) return -1;
        return ans;
    }
}
