
import java.util.*;

class Solution {

    class Fenwick {
        int n;
        long[] bit;

        Fenwick(int n) {
            this.n = n;
            bit = new long[n + 1];
        }

        void update(int i, long val) {
            while (i <= n) {
                bit[i] += val;
                i += i & -i;
            }
        }

        long query(int i) {
            long sum = 0;

            while (i > 0) {
                sum += bit[i];
                i -= i & -i;
            }

            return sum;
        }

        // Find index of the kth smallest element
        int kth(long k) {
            int idx = 0;

            for (int step = Integer.highestOneBit(n);
                 step > 0; step >>= 1) {

                int next = idx + step;

                if (next <= n && bit[next] < k) {
                    idx = next;
                    k -= bit[next];
                }
            }

            return idx + 1;
        }
    }

    public long minOperations(int[] nums, int x, int k) {

        int n = nums.length;

        // Step 1: Coordinate compression
        int[] sorted = nums.clone();
        Arrays.sort(sorted);

        int m = 0;

        for (int v : sorted) {
            if (m == 0 || sorted[m - 1] != v) {
                sorted[m++] = v;
            }
        }

        Fenwick count = new Fenwick(m);
        Fenwick sum = new Fenwick(m);

        int[] id = new int[n];

        for (int i = 0; i < n; i++) {
            id[i] = Arrays.binarySearch(
                sorted, 0, m, nums[i]
            ) + 1;
        }

        // Step 2: Calculate cost of every window
        int windows = n - x + 1;
        long[] cost = new long[windows];

        long total = 0;

        // Insert first window
        for (int i = 0; i < x; i++) {
            count.update(id[i], 1);
            sum.update(id[i], nums[i]);
            total += nums[i];
        }

        for (int i = 0; i < windows; i++) {

            // Find median
            int medianIndex = count.kth((x + 1L) / 2);
            long median = sorted[medianIndex - 1];

            // Elements <= median
            long leftCount = count.query(medianIndex);
            long leftSum = sum.query(medianIndex);

            long rightCount = x - leftCount;
            long rightSum = total - leftSum;

            // Calculate minimum operations
            cost[i] =
                median * leftCount - leftSum
                + rightSum - median * rightCount;

            // Slide window
            if (i + 1 < windows) {

                int remove = i;
                int add = i + x;

                count.update(id[remove], -1);
                sum.update(id[remove], -nums[remove]);
                total -= nums[remove];

                count.update(id[add], 1);
                sum.update(id[add], nums[add]);
                total += nums[add];
            }
        }

        // Step 3: Dynamic Programming
        long INF = Long.MAX_VALUE / 4;

        long[][] dp = new long[k + 1][n + 1];

        for (int j = 0; j <= k; j++) {
            Arrays.fill(dp[j], INF);
        }

        // Zero subarrays requires zero cost
        for (int i = 0; i <= n; i++) {
            dp[0][i] = 0;
        }

        for (int j = 1; j <= k; j++) {

            for (int i = 1; i <= n; i++) {

                // Option 1: Skip current element
                dp[j][i] = dp[j][i - 1];

                // Option 2: Select a window ending at i-1
                if (i >= x && dp[j - 1][i - x] != INF) {

                    long candidate =
                        dp[j - 1][i - x] + cost[i - x];

                    dp[j][i] = Math.min(
                        dp[j][i], candidate
                    );
                }
            }
        }

        return dp[k][n];
    }
}
