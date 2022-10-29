package dpOnGrids;
import java.util.Arrays;

public class minPathSum {
    // Memoization

    public static int solve(int[][] grid , int m ,int n , int[][] dp){
        if(m == 0 && n == 0){
        return grid[0][0];
        }
        if(m < 0 || n < 0){
        return (int)Math.pow(10,9);
        // we don't use Integer.MAX_VALUE because adding anything to it makes it MIN_VALUE
        }
        if(dp[m][n] != -1) return dp[m][n];

        int up = grid[m][n] + solve(grid , m-1 , n , dp);
        int left = grid[m][n] + solve(grid , m , n-1, dp);

        return dp[m][n] = Math.min(up , left);
        }

    // TABULATION 
    public static int solvee(int[][] matrix, int m, int n) {
        int dp[][] = new int[n][m];
        dp[0][0] = matrix[0][0];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (i == 0 && j == 0)
                    continue;
                else {
                    int up = 0;
                    if (i > 0)
                        up = matrix[i][j] + dp[i - 1][j];
                    else
                        up += (int) Math.pow(10, 9);

                    int left = 0;
                    if (j > 0)
                        left = matrix[i][j] + dp[i][j - 1];
                    else
                        left += (int) Math.pow(10, 9);

                    dp[i][j] = Math.min(up, left);
                }
            }
        }
        return dp[n - 1][m - 1];
    }

    public static void main(String[] args) {
        int grid[][] = {{1,3,1},{1,5,1},{4,2,1}};
        int m = grid.length;
        int n = grid[0].length;
        int[][] dp = new int[m][n];
        for (int[] row : dp) {
            Arrays.fill(row, -1);
        }
        System.out.println("memoization : " + solve(grid, m-1, n-1, dp));
        System.out.println("tabulation : " + solvee(grid, m, n));
    }
}
