package dp1;
//  https://takeuforward.org/data-structure/dynamic-programming-frog-jump-dp-3/

//Given a number of stairs and a frog, the frog wants to climb from the 0th stair to the (N-1)th stair.
// At a time the frog can climb either one or two steps. A height[N] array is also given. 
//Whenever the frog jumps from a stair i to stair j, the energy consumed in the jump is abs(height[i]- height[j]),
// where abs() means the absolute difference. We need to return the minimum energy that can be used 
//by the frog to jump from stair 0 to stair N-1.

import java.util.Arrays;

// memoization TC - O(N) SC - O(N) + O(N)
public class FrogJump {
    static int solve(int ind,int[] height,int[] dp){
        if(ind==0) return 0;
        if(dp[ind]!=-1) return dp[ind];
        int jumpTwo = Integer.MAX_VALUE;
        int jumpOne= solve(ind-1, height,dp)+ Math.abs(height[ind]-height[ind-1]);
        if(ind>1)
            jumpTwo = solve(ind-2, height,dp)+ Math.abs(height[ind]-height[ind-2]);
        
        return dp[ind]=Math.min(jumpOne, jumpTwo);
    }

    public static void main(String[] args) {
        int height[]={30,10,60 , 10 , 60 , 50};
        int n=height.length;
        // memo
        int dp[]=new int[n];
        Arrays.fill(dp,-1);
        System.out.println( "Memoization : " + solve(n-1,height,dp));

        // tabulation TC - O(N) SC - O(N)
        
        int dpp[] = new int[n];
        Arrays.fill(dpp , -1);
        dpp[0] = 0;
        for (int i = 1; i < n; i++) {
            int left = dpp[i-1] + Math.abs(height[i] - height[i-1]);
            int right = Integer.MAX_VALUE;
            if( i > 1){
                right = dpp[i-2] + Math.abs(height[i] - height[i-2]);
            }
            dpp[i] = Math.min(left , right);
        }
        System.out.print("Tabulation : "+dpp[n-1]);
    }
}
