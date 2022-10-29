package dp1;
// QUE - https://www.codingninjas.com/codestudio/problems/ninja-s-training_3621003


import java.util.*;
class ninjaTraining {
    // brute force solution 
    public static int solve(int day , int last, int points[][]){
        if(day == 0){
            int maxii = Integer.MIN_VALUE;
            for(int i = 0; i < 3 ; i++){
                if(i != last){
                    maxii = Math.max(maxii , points[0][i]);
                }
            }
            return maxii;
        }
        int maxi = 0;
        for (int i = 0; i <= 2; i++) {
            if (i != last) {
                int activity = points[day][i] + solve(day - 1, i, points);
                maxi = Math.max(maxi, activity);
            }
        }
        return maxi;
    }
    // memoization TC - O(N * 4) * 3   SC - O(N) + O(N*4)
    static int maxPointsMemo(int day, int last, int[][] points, int[][] dp) {

        if (dp[day][last] != -1) return dp[day][last];

        if (day == 0) {
            int maxi = 0;
            for (int i = 0; i <= 2; i++) {
                if (i != last)
                    maxi = Math.max(maxi, points[0][i]);
            }
            return dp[day][last] = maxi;
        }

        int maxi = 0;
        for (int task = 0; task <= 2; task++) {
            if (task != last) {
                int activity = points[day][task] + maxPointsMemo(day - 1, task, points, dp);
                maxi = Math.max(maxi, activity);
            }

        }

        return dp[day][last] = maxi;
    }
    // TABULATION TC - O(N * 4) * 3   SC - O(N*4)
    public static int maxPointTabu(int n ,int[][] points){
        int[][] dpp = new int[n][4];
        dpp[0][0] = Math.max(points[0][1] , points[0][2]);
        dpp[0][1] = Math.max(points[0][0] , points[0][2]);
        dpp[0][2] = Math.max(points[0][0] , points[0][1]);
        dpp[0][3] = Math.max(points[0][0], Math.max(points[0][1] , points[0][2]));

        for(int day = 1; day < n;day++){
            for(int last = 0 ; last < 4;last++){
                int maxi = 0;
                for(int task = 0; task <= 2; task++){
                    if(task != last){
                        int pointsGained = points[day][task] + dpp[day-1][task];
                        maxi = Math.max(dpp[day][last] , pointsGained);
                    }
                }
                dpp[day][last] = maxi;
            }
        }
        return dpp[n-1][3];
    }
    public static void main(String args[]) {

        int[][] points = {{10,40,70},
                          {20,50,80},
                          {30,60,90}};
           

        int n = points.length;
        int dp[][] = new int[n][4];
        for (int[] row: dp){
            Arrays.fill(row, -1);
        }
        // passed 3 as last cause its out of bound therefore allows to pick task without any contraints for first run.
        System.out.println("bruteForce : " + solve(n-1, 3, points));
        System.out.println("Memoization : " + maxPointsMemo(n - 1, 3, points, dp));
        System.out.println("Tabulation :" + maxPointTabu(n, points));
    }
}
