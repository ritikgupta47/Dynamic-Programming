package DpOnSubsequence;
import java.util.*;

class targetSum {
    // memoization
    static int countPartitionsUtil(int ind, int target, int[] arr, int[][] dp) {
        if (ind == 0) {
            if (target == 0 && arr[0] == 0)
                return 2;
            if (target == 0 || target == arr[0])
                return 1;
            return 0;
        }

        if (dp[ind][target] != -1)
            return dp[ind][target];

        int notTaken = countPartitionsUtil(ind - 1, target, arr, dp);

        int taken = 0;
        if (arr[ind] <= target)
            taken = countPartitionsUtil(ind - 1, target - arr[ind], arr, dp);

        return dp[ind][target] = (notTaken + taken);
    }

    // tabulation
    static int findWays(int[] num, int tar) {
        int n = num.length;

        int[][] dp = new int[n][tar + 1];

        if (num[0] == 0)
            dp[0][0] = 2; // 2 cases -pick and not pick
        else
            dp[0][0] = 1; // 1 case - not pick

        if (num[0] != 0 && num[0] <= tar)
            dp[0][num[0]] = 1; // 1 case -pick

        for (int ind = 1; ind < n; ind++) {
            for (int target = 0; target <= tar; target++) {

                int notTaken = dp[ind - 1][target];

                int taken = 0;
                if (num[ind] <= target)
                    taken = dp[ind - 1][target - num[ind]];

                dp[ind][target] = (notTaken + taken);
            }
        }
        return dp[n - 1][tar];
    }

    public static void main(String args[]) {

        int arr[] = { 1, 2, 3, 1 };
        int target = 3;
        int n = arr.length;
        int totSum = 0;
        for (int i = 0; i < arr.length; i++) {
            totSum += arr[i];
        }

        // Checking for edge cases
        if (totSum - target < 0) {
            // return 0;
            System.out.println("there are no patterns ");
        }
        if ((totSum - target) % 2 == 1) {
            // return 0;
            System.out.println("there are 0 ways ");
        }

        int s2 = (totSum - target) / 2;

        int dp[][] = new int[n][s2 + 1];
        for (int row[] : dp)
            Arrays.fill(row, -1);
        int ans = countPartitionsUtil(n - 1, s2, arr, dp);
        System.out.println("There are : " + ans + " ways");
        System.out.println("tabulation : " + findWays(arr, target));
    }
}
