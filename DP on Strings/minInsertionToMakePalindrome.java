package DP_Strings;

//  SAME CODE FOR MIN DELETION REQUIRED.
class minInsertionToMakePalindrome {
    static int lcs(String s1, String s2) {

        int n = s1.length();
        int m = s2.length();

        int dp[][] = new int[n + 1][m + 1];
        
        for (int ind1 = 1; ind1 <= n; ind1++) {
            for (int ind2 = 1; ind2 <= m; ind2++) {
                if (s1.charAt(ind1 - 1) == s2.charAt(ind2 - 1)){
                    dp[ind1][ind2] = 1 + dp[ind1 - 1][ind2 - 1];
                }else{
                    dp[ind1][ind2] = 0 + Math.max(dp[ind1 - 1][ind2], dp[ind1][ind2 - 1]);
                }
            }
        }
        return dp[n][m];
    }

    public static void main(String args[]) {

        String s = "abcaa";
        int len = s.length();
        String revStr = new StringBuilder(s).reverse().toString();
        int lenOFlongestPalindrome = lcs(s, revStr);
        int ans = len - lenOFlongestPalindrome;
        System.out.println("min insertion required are : " + ans );
    }
}