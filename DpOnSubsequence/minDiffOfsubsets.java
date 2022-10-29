package DpOnSubsequence;
public class minDiffOfsubsets {
    public static void main(String[] args) {
        int arr[] = { 1, 2, 3, 4 };
        int n = arr.length;
        System.out.println(minSubsetSumDifference(arr, n));
    }
    // same as p14subsetsumequalsK
    public static int minSubsetSumDifference(int[] arr, int n) {
        int totSum = 0;
        for (int i = 0; i < n; i++) {
            totSum += arr[i];
        }
        boolean dp[][]= new boolean[n][totSum+1];
        for(int i=0; i<n; i++){
            dp[i][0] = true;
        }
        if(arr[0]<=totSum){
            dp[0][arr[0]] = true;   
        }
        for(int ind = 1; ind<n; ind++){
            for(int target= 1; target<= totSum; target++){      
                boolean notTaken = dp[ind-1][target];
                boolean taken = false;
                    if(arr[ind]<=target){
                        taken = dp[ind-1][target-arr[ind]];   
                    }
                dp[ind][target]= notTaken||taken;
            }
        }
       int mini = (int)Math.pow(10 , 9);
       for (int i = 0; i <= totSum; i++) {
           if (dp[n - 1][i] == true) {
              int diff = Math.abs(i - (totSum - i));  //   diff = s1 - (S - s1).  (hence S - s1 = s2) from s1 + s2 = S  i.e 
              mini = Math.min(mini, diff);
            }
          }
      return mini;
	}
}
