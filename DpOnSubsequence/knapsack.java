package DpOnSubsequence;
import java.util.Arrays;

public class knapsack {
    public static void main(String[] args) {
        int wt[] = { 1, 2, 4, 5 };
        int val[] = { 5, 4, 8, 6 };
        int W = 5;
        int n = wt.length;

        System.out.println("recursive : " + knapsackk(wt, val, n-1, W));
        int[][]dp = new int[n][W+1];
        for(int[] rows : dp){
            Arrays.fill(rows, -1);
        }
        System.out.println("memo : "+knapsackUtil(wt, val, n-1, W, dp));

        System.out.println("Tabulation : " + knapsackTabu(wt, val, n-1, W));
    }

    // recursion soln    
    static int knapsackk(int[] wt, int[] val, int ind, int W) {

        if (ind == 0) {
            if (wt[0] <= W)
                return val[0];
            else
                return 0;
        }

        int notTaken = 0 + knapsackk(wt, val, ind - 1, W);

        int taken = Integer.MIN_VALUE;
        if (wt[ind] <= W)
            taken = val[ind] + knapsackk(wt, val, ind - 1, W - wt[ind]);

        return Math.max(notTaken, taken);
    }

    // memoizatoin solution
    static int knapsackUtil(int[] wt, int[] val, int ind, int W, int[][] dp) {

        if (ind == 0) {
            if (wt[0] <= W)
                return val[0];
            else
                return 0;
        }

        if (dp[ind][W] != -1)
            return dp[ind][W];

        int notTaken = 0 + knapsackUtil(wt, val, ind - 1, W, dp);

        int taken = Integer.MIN_VALUE;
        if (wt[ind] <= W)
            taken = val[ind] + knapsackUtil(wt, val, ind - 1, W - wt[ind], dp);

        return dp[ind][W] = Math.max(notTaken, taken);
    }
    static int knapsackTabu(int[] wt,int[] val, int n, int W){
    
        int dp[][] = new int[n][W+1];
        
        //Base Condition
        
        for(int i=wt[0]; i<=W; i++){
            dp[0][i] = val[0];
        }
        
        for(int ind =1; ind<n; ind++){
            for(int cap=0; cap<=W; cap++){
                
                int notTaken = 0 + dp[ind-1][cap];
                
                int taken = Integer.MIN_VALUE;
                if(wt[ind] <= cap)
                    taken = val[ind] + dp[ind-1][cap - wt[ind]];
                    
                dp[ind][cap] = Math.max(notTaken, taken);
            }
        }
        
        return dp[n-1][W];
    }
    
}
