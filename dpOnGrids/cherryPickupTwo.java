package dpOnGrids;
import java.util.*;

class cherryPickupTwo {

    public static void main(String args[]) {

        int grid[][] = { { 2, 3, 1, 2 },{ 3, 4, 2, 2 },{ 5, 6, 3, 5 } };

        int row = grid.length;
        int col = grid[0].length;  
        System.out.println("Brute Force : " + maxCherryBF(grid, 0, 0, col-1, col, row));


        int[][][] dp = new int[row][col][col];
        for (int row1[][]: dp) {
            for (int row2[]: row1) {
            Arrays.fill(row2, -1);
            }
        }
        System.out.println("Memo : "+maxCherry(grid , 0 , 0 , col -1 , col , row ,dp));
    }
    // brute force    TC - O(3^N * 3^N)    
    public static int maxCherryBF(int[][] grid , int i , int j1 , int j2 , int col , int row){
        if(j1 < 0 || j1 >= col || j2 < 0 || j2 >= col){
            return (int)Math.pow(-10 , 9);
        }
        if(i == row - 1){
            if(j1 == j2){
                return grid[i][j1];
            }else{
                return grid[i][j1] + grid[i][j2];
            }
        }
        
        int maxi = (int)Math.pow(-10 , 9);
        for(int di = -1 ; di <= 1 ; di++){
            for(int dj = -1; dj <= 1 ; dj++){
                int ans = 0;
                if(j1 == j2){
                    ans = grid[i][j1] + maxCherryBF(grid , i + 1 , j1 + di , j2 + dj , col , row);
                }else{
                    ans = grid[i][j1] + grid[i][j2] + maxCherryBF(grid , i + 1 , j1 + di , j2 + dj , col , row);
                }
                maxi = Math.max(ans , maxi);
            }
        }
        return maxi;
    }
    // Memoization
    public static int maxCherry(int[][] grid , int i , int j1 , int j2 , int col , int row , int[][][] dp){
        if(j1 < 0 || j1 >= col || j2 < 0 || j2 >= col){
            return (int)Math.pow(-10 , 9);
        }
        if(i == row - 1){
            if(j1 == j2){
                return grid[i][j1];
            }else{
                return grid[i][j1] + grid[i][j2];
            }
        }
        if(dp[i][j1][j2] != -1){
            return dp[i][j1][j2];
        }
        int maxi = (int)Math.pow(-10 , 9);
        for(int di = -1 ; di <= 1 ; di++){
            for(int dj = -1; dj <= 1 ; dj++){
                int ans = 0;
                if(j1 == j2){
                    ans = grid[i][j1] + maxCherry(grid , i + 1 , j1 + di , j2 + dj , col , row , dp);
                }else{
                    ans = grid[i][j1] + grid[i][j2] + maxCherry(grid , i + 1 , j1 + di , j2 + dj , col , row , dp);
                }
                maxi = Math.max(ans , maxi);
            }
        }
        return dp[i][j1][j2] = maxi;
    }
}
