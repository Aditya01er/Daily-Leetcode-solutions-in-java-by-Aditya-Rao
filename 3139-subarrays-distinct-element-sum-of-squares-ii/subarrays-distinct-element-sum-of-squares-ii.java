
class Solution {
    private static final long MOD = 1_000_000_007L;

    private int n;
    private long[] sum;
    private long[] square;
    private long[] lazy;

    public int sumCounts(int[] nums) {
        n = nums.length;

        sum = new long[4 * n];
        square = new long[4 * n];
        lazy = new long[4 * n];

        int[] last = new int[100001];
        java.util.Arrays.fill(last, -1);

        long ans = 0;

        for (int i = 0; i < n; i++) {
            int prev = last[nums[i]];

            update(1, 0, n - 1, prev + 1, i);

            ans = (ans + square[1]) % MOD;
            last[nums[i]] = i;
        }

        return (int) ans;
    }

    private void apply(int node, int left, int right, long val) {
        long len = right - left + 1L;

        square[node] = (
            square[node]
            + 2L * val % MOD * sum[node] % MOD
            + len % MOD * val % MOD * val % MOD
        ) % MOD;

        sum[node] = (sum[node] + len * val) % MOD;
        lazy[node] = (lazy[node] + val) % MOD;
    }

    private void pushDown(int node, int left, int right) {
        if (lazy[node] == 0 || left == right) {
            return;
        }

        int mid = (left + right) / 2;

        apply(node * 2, left, mid, lazy[node]);
        apply(node * 2 + 1, mid + 1, right, lazy[node]);

        lazy[node] = 0;
    }

    private void update(
        int node, int left, int right,
        int ql, int qr
    ) {
        if (ql > right || qr < left) {
            return;
        }

        if (ql <= left && right <= qr) {
            apply(node, left, right, 1);
            return;
        }

        pushDown(node, left, right);

        int mid = (left + right) / 2;

        update(node * 2, left, mid, ql, qr);
        update(node * 2 + 1, mid + 1, right, ql, qr);

        sum[node] = (sum[node * 2] + sum[node * 2 + 1]) % MOD;

        square[node] = (
            square[node * 2] + square[node * 2 + 1]
        ) % MOD;
    }
}
