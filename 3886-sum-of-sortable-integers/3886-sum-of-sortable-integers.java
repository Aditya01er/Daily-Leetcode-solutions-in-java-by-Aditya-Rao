class Solution {
    public int sortableIntegers(int[] nums) {
        int n = nums.length;
        int answer = 0;

        for (int k = 1; k <= n; k++) {
            if (n % k == 0 && canSort(nums, k)) {
                answer += k;
            }
        }

        return answer;
    }

    private boolean canSort(int[] nums, int k) {
        int previousMax = Integer.MIN_VALUE;

        for (int start = 0; start < nums.length; start += k) {
            int end = start + k;
            int min = nums[start];
            int max = nums[start];
            int decreases = 0;

            for (int i = start; i < end; i++) {
                min = Math.min(min, nums[i]);
                max = Math.max(max, nums[i]);

                int next = (i + 1 == end) ? start : i + 1;

                if (nums[i] > nums[next]) {
                    decreases++;
                }

                if (decreases > 1) {
                    return false;
                }
            }

            if (previousMax > min) {
                return false;
            }

            previousMax = max;
        }

        return true;
    }
}