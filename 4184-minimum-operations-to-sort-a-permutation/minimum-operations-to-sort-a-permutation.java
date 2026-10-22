
class Solution {
    public int minOperations(int[] nums) {
        int n = nums.length;
        int ans = Integer.MAX_VALUE;

        boolean inc = true;
        for (int i = 0; i < n; i++) {
            if (nums[i] != (nums[0] + i) % n) {
                inc = false;
                break;
            }
        }

        if (inc) {
            int k = nums[0];
            ans = Math.min(ans, Math.min((n - k) % n, k + 2));
        }

        boolean dec = true;
        for (int i = 0; i < n; i++) {
            if (nums[i] != (nums[0] - i + n) % n) {
                dec = false;
                break;
            }
        }

        if (dec) {
            int k = (n - 1 - nums[0] + n) % n;
            ans = Math.min(ans, 1 + Math.min(k, (n - k) % n));
        }

        return ans == Integer.MAX_VALUE ? -1 : ans;
    }
}
