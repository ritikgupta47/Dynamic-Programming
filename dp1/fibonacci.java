package dp1;
//  NOTES - https://takeuforward.org/data-structure/dynamic-programming-introduction/

public class fibonacci {
    public static void main(String[] args) {
        int n = 6;
        // MEMOIZATION
        int[] dp = new int[n+1];
        for (int i = 0; i < dp.length; i++) {
            dp[i] = -1;
        }
        System.out.println(fiboTab(n, dp));

        // tabulation
        int[] dp2 = new int[n+1];
        dp2[0] = 0; dp2[1] = 1;
        System.out.println(fibo(n, dp2));

        // space optimaztion
        int prev = 1;
        int prev2 = 0;
        int ans = 0;
        System.out.println(fiboo(n, prev, prev2, ans));
    }

    // memoization 
    // TC - 0(N)                     // SC - O(N) + O(N) stack space + dp array
    public static int fibo(int n , int[] dp){
        if(n <= 1){
            return n;
        }
        if(dp[n] != -1){
            return dp[n];
        }
        return dp[n] = fibo(n-1, dp) + fibo(n-2, dp);
    }
    // tabulation
    // *** TC - 0(N)    *** SC - O(N)

    public static int fiboTab(int n , int[] dp2){
        for (int i = 2; i < dp2.length; i++) {
            dp2[i] = dp2[i-1] + dp2[i-2];
        }
        return dp2[n];
    }
    // SPACE OPTIMAZTION
    // TC - O(N)      // SC - O(1)
    public static int fiboo(int n , int prev , int prev2 , int ans){
        for (int i = 2; i <= n; i++) {
            ans = prev + prev2;
            prev2 = prev;
            prev = ans;
        }
        return ans;
    }
}
