package dpOnGrids;
import java.util.*;

class maxPathSum {
    //brute force  TC - O(3^N)   SC - O(N)
    static int getMax(int i, int j, int m, int[][] matrix) {
        if (j < 0 || j >= m)
            return (int) Math.pow(-10, 9);
        if (i == 0)
            return matrix[0][j];

        int up = matrix[i][j] + getMax(i - 1, j, m, matrix);
        int leftDiagonal = matrix[i][j] + getMax(i - 1, j - 1, m, matrix);
        int rightDiagonal = matrix[i][j] + getMax(i - 1, j + 1, m, matrix);

        return Math.max(up, Math.max(leftDiagonal, rightDiagonal));

    }
    // MEMOIZATION SC - O(N) O(N)     TC -  
    static int getMaxMemo(int i, int j, int m, int[][] matrix, int[][] dp) {
        if (j < 0 || j >= m)
            return (int) Math.pow(-10, 9);
        if (i == 0)
            return matrix[0][j];

        if (dp[i][j] != -1)
            return dp[i][j];

        int up = matrix[i][j] + getMaxMemo(i - 1, j, m, matrix, dp);
        int leftDiagonal = matrix[i][j] + getMaxMemo(i - 1, j - 1, m, matrix, dp);
        int rightDiagonal = matrix[i][j] + getMaxMemo(i - 1, j + 1, m, matrix, dp);

        return dp[i][j] = Math.max(up, Math.max(leftDiagonal, rightDiagonal));

    }
    public static int getMaxTabu(int[][] matrix){

        int n = matrix.length;
        int m = matrix[0].length;

        int[][] dp = new int[n][m];
        for (int j = 0; j < n; j++) {
            dp[0][j] = matrix[0][j];
        }

        for (int i = 1; i < n; i++) {
            for (int j = 0; j < m; j++) {

                int up = matrix[i][j] + dp[i-1][j];

                int leftD  = 0;
                if(j-1 >=0){
                    leftD =matrix[i][j] + dp[i-1][j-1];
                }else{
                    leftD = (int)Math.pow(-10,9);
                }
                
                int rightD = 0;
                if(j + 1 < m){
                    rightD = matrix[i][j] + dp[i-1][j+1];
                }else{
                    rightD = (int)Math.pow(-10 , 9);
                }
                dp[i][j] = Math.max(up , Math.max(leftD, rightD));
            }
        }
        int maxi = Integer.MIN_VALUE;
        for (int i = 0; i < m; i++) {
            maxi = Math.max(maxi , dp[n-1][i]);
        }
        return maxi;
    }
    public static void main(String args[]) {

        int matrix[][] = { { 1, 2, 10, 4 },
                { 100, 3, 2, 1 },
                { 1, 1, 20, 2 },
                { 1, 2, 2, 1 } };

        int n = matrix.length;
        int m = matrix[0].length;

        int maxiBrute = Integer.MIN_VALUE;

        for (int j = 0; j < m; j++) {
            int ans = getMax(n - 1, j, m, matrix);
            maxiBrute = Math.max(maxiBrute, ans);
        }
        System.out.println("brute : " + maxiBrute);

        int dp[][] = new int[n][m];
        for (int row[] : dp)
            Arrays.fill(row, -1);

        int maxi = Integer.MIN_VALUE;

        for (int j = 0; j < m; j++) {
            int ans = getMaxMemo(n - 1, j, m, matrix, dp);
            maxi = Math.max(maxi, ans);
        }
        System.out.println("Memo : " + maxi);

        System.out.println("tabu :" + getMaxTabu(matrix));
    }
}