class Solution {
    public int rob(int[] arr) {
        int[] dp = new int[arr.length];
        Arrays.fill(dp, -1);
        return loot(0, arr, dp);
    }

    private int loot(int i, int[] arr, int[] dp) {
        if (i >= arr.length) {
            return 0;
        }

        if (dp[i] != -1) {
            return dp[i];
        }

        int pick = arr[i] + loot(i + 2, arr, dp);
        int skip = loot(i + 1, arr, dp);

        return dp[i] = Math.max(pick, skip);
    }
}