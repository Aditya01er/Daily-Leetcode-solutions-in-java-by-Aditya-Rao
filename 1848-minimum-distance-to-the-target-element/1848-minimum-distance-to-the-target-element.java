class Solution {
    public int getMinDistance(int[] nums, int target, int start) {
        int min = Integer.MAX_VALUE;
    
      for(int i=0; i<nums.length;i++) {
        if(nums[i]==target){
            if(i>start){
            if( (i-start)<min){
                min=(i-start);
            }
            }
        
        else{
            if((start-i)<min){
                min = start-i;
            }
        }
      }
      } 
      return min;
    }
}