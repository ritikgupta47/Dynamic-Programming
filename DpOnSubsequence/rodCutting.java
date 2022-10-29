package DpOnSubsequence;
import java.util.*;

class rodCutting {
    static int cutRod(int[] price, int N) {

        int dp[][] = new int[N][N + 1];

        for (int row[] : dp)
            Arrays.fill(row, -1);

        for (int i = 0; i <= N; i++) {
            dp[0][i] = i * price[0];
        }

        for (int ind = 1; ind < N; ind++) {
            for (int length = 0; length <= N; length++) {

                int notTaken = 0 + dp[ind - 1][length];

                int taken = Integer.MIN_VALUE;
                int rodLength = ind + 1;
                if (rodLength <= length)
                    taken = price[ind] + dp[ind][length - rodLength];

                dp[ind][length] = Math.max(notTaken, taken);
            }
        }

        return dp[N - 1][N];
    }

    // memoization
    static int cutRodUtil(int[] price, int ind, int N, int[][] dp) {

        if (ind == 0) {
            return N * price[0];
        }

        if (dp[ind][N] != -1)
            return dp[ind][N];

        int notTaken = 0 + cutRodUtil(price, ind - 1, N, dp);

        int taken = Integer.MIN_VALUE;
        int rodLength = ind + 1;
        if (rodLength <= N)
            taken = price[ind] + cutRodUtil(price, ind, N - rodLength, dp);

        return dp[ind][N] = Math.max(notTaken, taken);
    }

    public static void main(String args[]) {

        int price[] = { 2, 5, 7, 8, 10 };

        int N = price.length;

        int dp[][] = new int[N][N + 1];
        for (int row[] : dp)
            Arrays.fill(row, -1);
        System.out.println("memo : " + cutRodUtil(price, N - 1, N, dp));
        System.out.println("tabulation : " + cutRod(price, N));
    }
}
