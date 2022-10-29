package dpOnGrids;
import java.util.*;

class triangle {

    //memoization
    static int minimumPathSum(int i, int j, int[][] triangle, int n, int[][] dp) {

        if (dp[i][j] != -1)
            return dp[i][j];

        if (i == n - 1)
            return triangle[i][j];

        int down = triangle[i][j] + minimumPathSum(i + 1, j, triangle, n, dp);
        int diagonal = triangle[i][j] + minimumPathSum(i + 1, j + 1, triangle, n, dp);

        return dp[i][j] = Math.min(down, diagonal);

    }
    //tabulation
    public static int solveTabu(int[][] triangle , int n){
        int[][] dp =new int[n][n];
        for(int j = 0; j < n ; j++){
            dp[n-1][j] = triangle[n-1][j];
        }

        for(int i = n-2 ; i >= 0 ; i--){
            for(int j = i ; j >= 0 ; j--){
                int up = triangle[i][j] + dp[i+1][j];
                int dia = triangle[i][j] + dp[i+1][j+1];

                dp[i][j] = Math.min(up , dia);
            }
        }
        return dp[0][0];
    }
    public static void main(String args[]) {

        int triangle[][] = { { 1 },
                { 2, 3 },
                { 3, 6, 7 },
                { 8, 9, 6, 10 } };

        int n = triangle.length;
        int dp[][]=new int[n][n];
        for(int row[]: dp){
            Arrays.fill(row,-1);
        }
        System.out.println("Memo : "+ minimumPathSum(0, 0, triangle, n, dp));
        System.out.println("tabulation : " + solveTabu(triangle, n));
    }
}
