import java.util.Arrays;

public class longestStringChain {
    public static void main(String[] args) {
        String[] words = { "a", "b", "ba", "bca", "bda", "bdca" };
        // int n = words.length;
        System.out.println(longestStrChain(words));
    }

    public static int longestStrChain(String[] words) {

        Arrays.sort(words, (a, b) -> a.length() - b.length());
        // SORT THE words[] acc to the length of the strings in asc order of length of
        // Strings
        int n = words.length;
        int[] dp = new int[n];

        Arrays.fill(dp, 1);// Since at each index LIS is 1 atleast

        int maxLength = 0;

        for (int i = 0; i < n; i++) {
            for (int prev = 0; prev < i; prev++) {
                if (isPredecessor(words[i], words[prev])) {
                    if (1 + dp[prev] > dp[i]) { // If the Subsequence is greater by including Subsequence till a[prev]
                        dp[i] = 1 + dp[prev];// then update dp[i]
                    }
                }
            }

            maxLength = Math.max(maxLength, dp[i]);
        }

        return maxLength;
    }

    private static boolean isPredecessor(String a, String b) {
        if (a.length() != b.length() + 1)
            return false; // If diff of length is greater than 1 . Then it is not possible

        int i = 0, j = 0; // Two pointers to compare Strings

        int noOfMismatches = 0; // Count the no of mismatching characters
        while (i < a.length() && j < b.length()) {
            if (a.charAt(i) == b.charAt(j)) {
                i++;
                j++;
            } else {
                noOfMismatches++;
                i++; // ONLY i++ . Since a is large and the extra character should only be in a
            }
        }
        // It is possible that mismatch characters are at the end and hence a has still
        // some chars left
        // For eg: abcd & abc
        while (i < a.length()) {
            noOfMismatches++;
            i++;
        }
        if (noOfMismatches > 1)
            return false;
        return true;
    }
}
