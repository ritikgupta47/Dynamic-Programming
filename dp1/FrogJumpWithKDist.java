package dp1;
import java.util.Arrays;

//This is a follow-up question to “Frog Jump” {P03} discussed in the previous article. 
//In the previous question, the frog was allowed to jump either one or two steps at a time. 
//In this question, the frog is allowed to jump up to ‘K’ steps at a time. 
//If K=4, the frog can jump 1,2,3, or 4 steps at every index.

public class FrogJumpWithKDist {
    public static void main(String[] args) {
        int height[]={30,10,60 , 10 , 60 , 50};
        int n=height.length;
        int k = 2;
        int dp[] = new int[n];
        Arrays.fill(dp , -1);
        System.out.println(min(n-1, k, height, dp));
    }
    // memoization Solution TC - 0(N) * K SC - O(N) + O(N)
    public static int min(int n , int k ,int[] height , int[] dp){
        if( n == 0) return 0;
        if(dp[n] != -1) return dp[n];
        int mmSteps = Integer.MAX_VALUE;
        for(int j = 1 ; j <= k ; j++){
            if(n - j >= 0){
                int jump = min(n - j , k , height , dp) + Math.abs(height[n] - height[n-j]);
                mmSteps = Math.min(jump , mmSteps);
            }
        }
        return dp[n] = mmSteps;
    }
}
