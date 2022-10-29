package DP_Strings;
public class minOperation {
    public static void main(String[] args) {
        String str = "abcd";
        String ptr = "anc";
        int n = str.length();
        int m = ptr.length();
        int ans = n + m - (2 * solve(str, ptr));
        System.out.println("min insertoin and deletion required are : " + ans);
    }
    public static int solve(String str , String ptr){
        int n = str.length();
        int m = ptr.length();
        
        int[][] dp = new int[n+1][m+1];
        
        for(int i = 1; i <= n ; i++){
            for(int j = 1; j <= m ; j++){
                if(str.charAt(i-1) == ptr.charAt(j-1)){
                    dp[i][j] = 1 + dp[i-1][j-1];
                }else{
                    dp[i][j] = Math.max(dp[i-1][j] , dp[i][j-1]);
                }
            }
        }
        return dp[n][m];
    }
}
