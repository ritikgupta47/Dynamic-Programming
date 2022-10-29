package dp1;
import java.util.Arrays;

public class climbingStairs {
    public static void main(String[] args) {
        int n = 6;
        int dp[] =new int[n];
        Arrays.fill(dp ,-1);
        System.out.println(min(n-1, dp)); 
    }
    public static int min(int n , int[] dp){
        if(n <= 1){
            return n;
        }
        if(dp[n] != -1){
            return dp[n];
        }
        return dp[n] = min(n-1, dp) + min(n-2, dp);
    }
}