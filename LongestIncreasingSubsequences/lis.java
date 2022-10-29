
import java.util.Arrays;

/*  LEETCODE 300 : LONGEST COMMON SUBSEQUENCE
 * Given an integer array nums, return the length of the longest strictly increasing subsequence.
A subsequence is a sequence that can be derived from an array by deleting some or no elements without 
changing the order of the remaining elements. 
For example, [3,6,2,7] is a subsequence of the array [0,3,1,6,2,2,7].

Example 1:
Input: nums = [10,9,2,5,3,7,101,18]
Output: 4
Explanation: The longest increasing subsequence is [2,3,7,101], therefore the length is 4.

Example 2:
Input: nums = [0,1,0,3,2,3]
Output: 4

Example 3:
Input: nums = [7,7,7,7,7,7,7]
Output: 1
 */

public class lis {
    public static void main(String[] args) {
        int[] nums = {10,9,2,5,3,7,101,18};
        int n = nums.length;
        int[][] dp =new int[n][n+1];
        for(int[] rows : dp){
            Arrays.fill(rows , -1);
        }
        
        System.out.println("LIS : "+solve(nums, 0, -1, dp));
    }
    // memoization
    public static int solve(int[] nums , int i , int prev , int[][] dp){
        if(i == nums.length) return 0;
        
        if(dp[i][prev + 1] != -1) return dp[i][prev+1];
        
        int notTake = 0 + solve(nums , i + 1 , prev , dp); // not take
        int take = Integer.MIN_VALUE; //take
        if(prev == -1 || nums[i] > nums[prev]){
            take = 1 + solve(nums , i + 1 , i , dp);
        }
        return dp[i][prev + 1]=Math.max(take , notTake);
    }
}
