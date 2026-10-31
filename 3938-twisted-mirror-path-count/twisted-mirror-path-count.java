class Solution {
    public int uniquePaths(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int MOD = 1_000_000_007;

        int[] dp = new int[n + 1];
        dp[1] = 1;

        for (int i = 0; i < m; i++) {
            int left = 0;

            for (int j = 0; j < n; j++) {
                int top = dp[j + 1];

                if (grid[i][j] == 1) {
                    dp[j + 1] = left;
                    left = top;
                } else {
                    dp[j + 1] = (left + top) % MOD;
                    left = dp[j + 1];
                }
            }
        }

        return dp[n];
    }
}