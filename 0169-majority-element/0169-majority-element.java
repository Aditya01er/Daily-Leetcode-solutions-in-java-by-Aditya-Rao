class Solution {
    public int majorityElement(int[] nums) {
        int n = nums.length;
       int count =0;
        int max=0;
        for(int i=0; i<n-1; i++){
            count=0;
            for(int j=0; j<n; j++){
            if( nums[i]==nums[j]){
                count++;
                if(count > n/2){
                    return nums[i];
                }
            }
            }
        }
        return nums[0];
    }
}