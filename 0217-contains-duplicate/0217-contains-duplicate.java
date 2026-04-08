class Solution {
    public boolean containsDuplicate(int[] nums) {

  /*    int n = nums.length;
      for(int i=0; i<n ; i++) {
       for(int j= i+1; j<n; j++){
            if(nums[i]== nums[j]){
                return true;
            }
        
      }
      }
      return false; 
      */

      HashSet<Integer> set = new HashSet<>();
      for( int num : nums){
        if(set.contains(num)){
            return true;

        }
        set.add(num);
      }
      return false;
    } 
}