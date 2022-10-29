package DpOnSubsequence;
public class partitionEqualsSubsetSum {
    public static void main(String[] args) {
        int arr[] = {2,3,3,3,4,5};
        System.out.println(canPartition(arr));
    }
    // same as previous que.
    // tabulation solution 
    public static boolean canPartition(int[] nums) {
        int n = nums.length;
        int totSum=0;
        for(int i=0; i<n;i++){
            totSum+= nums[i];
        }
        if (totSum%2==1) return false;
        int k = totSum/2;
        boolean dp[][]=new boolean[n][k+1];   
        for(int i=0; i<n; i++){
            dp[i][0] = true;
        }
        if(nums[0]<=k){
            dp[0][nums[0]] = true;
        }
        
        for(int ind = 1; ind<n; ind++){
            for(int target= 1; target<=k; target++){
                boolean notTaken = dp[ind-1][target];
                boolean taken = false;
                    if(nums[ind]<=target){
                        taken = dp[ind-1][target-nums[ind]];
                    }
                dp[ind][target]= notTaken||taken;
            }
        }
        return dp[n-1][k];
    }
}
