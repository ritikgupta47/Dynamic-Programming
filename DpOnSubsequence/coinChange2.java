package DpOnSubsequence;
import java.util.*;

class coinChange2 {
    //tabulation 
    static int countWaysToMakeChange(int[] arr, int n, int T){
    
        int dp[][]=new int[n][T+1];
        
        
        //Initializing base condition
        for(int i=0;i<=T;i++){
            if(i%arr[0]==0)
                dp[0][i]=1;
            // Else condition is automatically fulfilled,
            // as dp array is initialized to zero
        }
        
        for(int ind=1; ind<n;ind++){
            for(int target=0;target<=T;target++){
                int notTaken = dp[ind-1][target];
                
                int  taken = 0;
                if(arr[ind]<=target)
                    taken = dp[ind][target-arr[ind]];
                    
                dp[ind][target] = notTaken + taken;
            }
        }
        
        return dp[n-1][T];
    }
    // memoization
    static int countWaysToMakeChangeUtil(int[] arr, int ind, int T, int[][] dp) {

        if (ind == 0) {
            if (T % arr[0] == 0)
                return 1;
            else
                return 0;
        }

        if (dp[ind][T] != -1)
            return dp[ind][T];

        int notTaken = countWaysToMakeChangeUtil(arr, ind - 1, T, dp);

        int taken = 0;
        if (arr[ind] <= T)
            taken = countWaysToMakeChangeUtil(arr, ind, T - arr[ind], dp);

        return dp[ind][T] = notTaken + taken;
    }

    public static void main(String args[]) {

        int arr[] = { 1, 2, 3 };
        int target = 4;

        int n = arr.length;

        int dp[][] = new int[n][target + 1];
        for (int row[] : dp)
            Arrays.fill(row, -1);
        int ans = countWaysToMakeChangeUtil(arr, n - 1, target, dp);
        System.out.println("memo : " + ans);
        System.out.println("tabu :" + countWaysToMakeChange(arr, n, target));
    }
}
