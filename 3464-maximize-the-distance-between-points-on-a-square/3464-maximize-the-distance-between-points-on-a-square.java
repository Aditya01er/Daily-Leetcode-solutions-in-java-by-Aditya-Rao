import java.util.*;

class Solution {

    public int maxDistance(int side, int[][] points, int k) {
        int n = points.length;
        long[] arr = new long[n];

        // Step 1: map to 1D perimeter
        for (int i = 0; i < n; i++) {
            int x = points[i][0];
            int y = points[i][1];

            if (y == 0) arr[i] = x;
            else if (x == side) arr[i] = side + y;
            else if (y == side) arr[i] = 3L * side - x;
            else arr[i] = 4L * side - y;
        }

        Arrays.sort(arr);

        // Step 2: extend array
        long[] ext = new long[2 * n];
        for (int i = 0; i < n; i++) {
            ext[i] = arr[i];
            ext[i + n] = arr[i] + 4L * side;
        }

        long low = 0, high = 4L * side, ans = 0;

        while (low <= high) {
            long mid = (low + high) / 2;
            if (canPlace(ext, n, k, mid, 4L * side)) {
                ans = mid;
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        return (int) ans;
    }

    private boolean canPlace(long[] arr, int n, int k, long dist, long perimeter) {

        for (int i = 0; i < n; i++) {
            int count = 1;
            long last = arr[i];
            int idx = i;

            while (count < k) {
                // binary search for next valid point
                int next = lowerBound(arr, idx + 1, i + n, last + dist);
                if (next == i + n) break;

                count++;
                last = arr[next];
                idx = next;
            }

            if (count == k) {
                // circular check
                if (arr[i] + perimeter - last >= dist) {
                    return true;
                }
            }
        }

        return false;
    }

    private int lowerBound(long[] arr, int l, int r, long target) {
        while (l < r) {
            int mid = (l + r) / 2;
            if (arr[mid] >= target) r = mid;
            else l = mid + 1;
        }
        return l;
    }
}