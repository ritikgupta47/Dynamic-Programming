package dpOnGrids;
// https://leetcode.com/problems/unique-paths-ii/

import java.util.Arrays;

public class uniqPathTwo {
    public static void main(String[] args) {
        int[][] obstacleGrid = { { 0, 0, 0 }, { 0, 1, 0 }, { 0, 0, 0 } };

        if (obstacleGrid[0][0] == 1) { // as the robot is on blocked path it cant move so zero ways to reach destination
            System.out.println("zero ways.");
        }
        int m = obstacleGrid.length - 1;
        int n = obstacleGrid[0].length - 1;
        int[][] dp = new int[m + 1][n + 1];
        for (int[] row : dp) {
            Arrays.fill(row, -1);
        }
        System.out.println("total ways : " + solve(obstacleGrid, m, n, dp));
        System.out.println("tabu : " + solveTabu(obstacleGrid, m, n));
    }
    // memoization soln
    public static int solve(int[][] obstacleGrid, int i, int j, int[][] dp) {
        if (i == 0 && j == 0)
            return 1;
        if (i < 0 || j < 0)
            return 0;
        if (obstacleGrid[i][j] == 1)
            return 0;
        if (dp[i][j] != -1)
            return dp[i][j];
        int up = solve(obstacleGrid, i - 1, j, dp);
        int left = solve(obstacleGrid, i, j - 1, dp);

        return dp[i][j] = up + left;
    }
    public static int solveTabu(int[][] obstacleGrid , int m , int n ){
        int[][] dp = new int[m][n];
        dp[0][0] = 1;
        if(obstacleGrid[m][n] == 1) return 0;
        for(int i = 0; i < m ; i++){
            for(int j = 0; j < n ; j++){
                if(i == 0 && j == 0) continue;

                int up = 0, left = 0;
                if( i > 0){
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
