package dp1;
import java.util.Arrays;

public class houseRobber {
    public static void main(String[] args) {
        int nums[]={2,1,4,9};
        int idx = nums.length-1;

        System.out.println("Brute Force : " + rob(nums, idx));
        
        //memoization
        int[] dp = new int[nums.length];
        Arrays.fill(dp, -1);
        System.out.println("Memoization : " + robMemo(nums, dp, idx));

        // tabulation 
        int[] dpp = new int[nums.length];
        Arrays.fill(dpp , -1);
        dpp[0] = nums[0];
        System.out.println("Tabulation : " + robTabu(idx, dpp, nums));

        //space optimization 
        System.out.println("space optimaztion : " + robSo(idx, nums));
    }
    // BRUTE FORCE TC - O(N^2)  SC - O(N)
    public static int rob(int[] nums , int idx){
        if(idx == 0) return nums[0];
        if(idx < 0) return 0;
        
        int pick = nums[idx] + rob(nums , idx - 2);
        int notPick = 0 + rob(nums , idx - 1);

        return Math.max(pick , notPick);
    }
    // MEMOIZATION   TC - O(N)   SC - 0(N)
    public static int robMemo(int[] nums , int[] dp, int idx){
        if(idx == 0) return nums[0];
        if(idx < 0) return 0;
        if(dp[idx] != -1){
            return dp[idx];
        }
        int pick = nums[idx] + robMemo(nums , dp,  idx - 2);
        int notPick = 0 + robMemo(nums ,dp, idx - 1);
        return dp[idx] = Math.max(pick , notPick);
    }
    // Tabulation TC - O(N)     SC - o(N)
    public static int robTabu(int idx , int[] dpp , int[] nums){
        for (int i = 1; i < nums.length; i++) {
            int pick = nums[i]; // to avoid negative index 
            if(i > 1){
                pick = pick + dpp[i - 2];
            }
            int notPick = 0 + dpp[i - 1];
            dpp[i] = Math.max(pick , notPick);
        }
        return dpp[dpp.length - 1];
    }
    //SPACE OPTIMIZATION  TC - O(N)   SC - O(1)
    public static int robSo(int idx , int[] nums){
        int prev = nums[0];
            int prev2 = Integer.MIN_VALUE;
        for (int i = 1; i < nums.length; i++) {
            int pick = nums[i]; // to avoid negative index 
            if(i > 1){
                pick = pick + prev2;
            }
            int notPick = 0 + prev;

            prev2 = prev;
            prev = Math.max(pick , notPick);
        }
        return prev;
    }
}
