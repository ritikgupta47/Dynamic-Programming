package DP_Strings;
public class ShortestCommonSupersequence {
    public static void main(String[] args) {
        String str1 = "brute";
        String str2 = "groot";
        int n = str1.length();
        int m = str2.length();

        int[][] dp = new int[n + 1][m + 1];

        for (int ind1 = 1; ind1 <= n; ind1++) {
            for (int ind2 = 1; ind2 <= m; ind2++) {
                if (str1.charAt(ind1 - 1) == str2.charAt(ind2 - 1)) {
                    dp[ind1][ind2] = 1 + dp[ind1 - 1][ind2 - 1];
                } else {
                    dp[ind1][ind2] = 0 + Math.max(dp[ind1 - 1][ind2], dp[ind1][ind2 - 1]);
                }
            }
        }

        int i = n;
        int j = m;
        String ans = "";

        while (i > 0 && j > 0) {
            if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
                ans += str1.charAt(i - 1);
                i--;
                j--;
            } else if (dp[i - 1][j] > dp[i][j - 1]) {
                ans += str1.charAt(i - 1);
                i--;
            } else {
                ans += str2.charAt(j - 1);
                j--;
            }
        }
        // Adding Remaing Characters - Only one of the below two while loops will run
        while (i > 0) {
            ans += str1.charAt(i - 1);
            i--;
        }
        while (j > 0) {
            ans += str2.charAt(j - 1);
            j--;
        }
        String ans2 = new StringBuilder(ans).reverse().toString();
        System.out.println(ans2);
    }
}
