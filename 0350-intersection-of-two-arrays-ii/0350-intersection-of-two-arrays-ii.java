class Solution {
    public int[] intersect(int[] nums1, int[] nums2) {
        Arrays.sort(nums1);
        Arrays.sort(nums2);

        int i = 0, j = 0;
        List<Integer> result = new ArrayList<>();

        while (i < nums1.length && j < nums2.length) {
            if (nums1[i] < nums2[j]) i++;
            else if (nums1[i] > nums2[j]) j++;
            else {
                result.add(nums1[i]);
                i++;
                j++;
            }
        }

        int[] res = new int[result.size()];
        for (int k = 0; k < result.size(); k++) {
            res[k] = result.get(k);
        }

        return res;
    }
}