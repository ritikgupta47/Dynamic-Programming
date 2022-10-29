package DpOnSubsequence;
import java.util.Arrays;

class countSubsetOfSumK{
    public static void main(String[] args) {
        int num[] = {1,2,2,3};
        int tar=3;

        System.out.println("Recursion : " + solve(num, tar, tar));

        int[][] dp = new int[num.length][tar + 1];
        for(int[] rows : dp){
            Arrays.fill(rows , -1);
        }
        System.out.println("Memo : " + solve(num , tar , num.length -1 , dp));
    }
    // Recursion
    public static int solve(int num[] , int tar , int idx){
        if(tar == 0) return 1;
        if(idx == 0){
            if(num[0] == tar){
                return 1;
            }else{
                return 0;
            }
        }
        int notPick = solve(num , tar , idx-1 );
        int pick = 0;
        if(num[idx] <= tar){
            pick = solve(num , tar-num[idx] , idx -1);
        }
        return pick + notPick;
    }
    //memoization
    public static int solve(int num[] , int tar , int idx , int[][] dp){
        if(tar == 0) return 1;
        if(idx == 0){
            if(num[0] == tar){
                return 1;
            }else{
                return 0;
            }
        }
        if(dp[idx][tar] != -1){
            return dp[idx][tar];
        }
        int notPick = solve(num , tar , idx-1 , dp);
        int pick = 0;
        if(num[idx] <= tar){
            pick = solve(num , tar-num[idx] , idx -1 ,dp);
        }
        return dp[idx][tar] = pick + notPick;
    }
}