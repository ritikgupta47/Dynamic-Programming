package DP_Strings;
public class longestCommonSubstring {
    static int lcsubstring(String s1, String s2) {

        int n = s1.length();
        int m = s2.length();

        int[][] dp = new int[n + 1][m + 1];
        int ans = 0;

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    dp[i][j] = 1 + dp[i - 1][j - 1];
                    
                } else{
                    dp[i][j] = 0;
                }
                ans = Math.max(ans, dp[i][j]);
            }
        }

        return ans;

    }

    public static void main(String args[]) {

        String s1 = "abcjklp";
        String s2 = "acjkp";
        System.out.println(lcsubstring(s1, s2));
    }
}
