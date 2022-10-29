package dpOnGrids;
// https://leetcode.com/problems/unique-paths/

import java.util.Arrays;

class uniquePath{
    public static void main(String[] args) {
        int m = 3;
        int n = 7;
        System.out.println("BruteForce : " + uniqPath(m-1, n-1));

        int[][] dp = new int[m][n];
        for(int[] row : dp){
            Arrays.fill(row , -1);
        }
        System.out.println("Memoization : " + uniqPathsMemo(m-1, n-1, dp));
        System.out.println("tabulation : " + uniqPathTabu(m, n));
    }
    // brute force solution   TC - 2^M*N       SC - (M-1) + (N-1)
    public static int uniqPath(int i , int j){
        if(i == 0 && j == 0) return 1;
        if(i < 0 || j < 0) return 0;

        int up = uniqPath(i - 1 , j);
        int left = uniqPath(i, j - 1);
        return left + up;
    }

    // Memoization  TC - O(N *M)     SC - O((N-1) + (M-1)) + O(M * N); 
    public static int uniqPathsMemo(int i, int j , int[][] dp){
        if(i == 0 && j == 0) return 1;
        if(i < 0 || j < 0) return 0;

        if(dp[i][j] != -1) return dp[i][j];

        int up = uniqPathsMemo(i - 1 , j, dp);
        int left = uniqPathsMemo(i, j - 1 ,dp);
        return dp[i][j] =  left + up;
        
    }
    // TABULATION  tc - 0{N * M}    SC - 0(N *M)
    public static int uniqPathTabu(int m , int n ){
        int[][] dp = new int[m][n];
        dp[0][0] = 1;
        for(int i = 0; i < m ; i++){
            for(int j = 0; j < n ; j++){
                if(i == 0 && j == 0) continue; // as dp[0][0] is defined
                int up = 0;
                int left = 0;
                if(i > 0){
                    up = dp[i-1][j];
                }
                if(j > 0){
                    left = dp[i][j-1];
                }
                dp[i][j] = left + up;
            }
        }
        return dp[m-1][n-1];
    }
}