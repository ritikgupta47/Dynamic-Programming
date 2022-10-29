import java.util.*;
/*
 *  LEETCODE 368. Largest Divisible Subset

Given a set of distinct positive integers nums, return the largest subset answer such that every pair
 (answer[i], answer[j]) of elements in this subset satisfies:

answer[i] % answer[j] == 0, or
answer[j] % answer[i] == 0
If there are multiple solutions, return any of them.

Example 1:

Input: nums = [1,2,3]
Output: [1,2]
Explanation: [1,3] is also accepted.

Example 2:

Input: nums = [1,2,4,8]
Output: [1,2,4,8]
 
 */

public class largestDivisibleSubset {
    public static void main(String[] args) {
        int[] nums = { 1, 2, 4, 8 };
        System.out.println(largestDivisibleSubsets(nums));
    }

    public static List<Integer> largestDivisibleSubsets(int[] nums) {
        Arrays.sort(nums);
        int N = nums.length;
        List<Integer> ans = new ArrayList<Integer>();
        int[] dp = new int[N];
        Arrays.fill(dp, 1);
        int[] hash = new int[N];
        for (int i = 0; i < N; i++) {
            hash[i] = i;
        }
        int lastindex = 0;
        int maxi = 1;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[i] % nums[j] == 0 && dp[j] + 1 > dp[i]) {
                    dp[i] = dp[j] + 1;
                    hash[i] = j;
                }
                if (dp[i] > maxi) {
                    maxi = dp[i];
                    lastindex = i;
                }
            }
        } // for ends
        ans.add(nums[lastindex]);
        while (hash[lastindex] != lastindex) {
            lastindex = hash[lastindex];
            ans.add(nums[lastindex]);
        }
        return ans;
    }
}