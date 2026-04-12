class Solution {
    public int differenceOfSum(int[] nums) {

int eleSum=0;
int digSum =0;
for(int i=0; i<nums.length; i++){
    eleSum = eleSum + nums[i];
    int num = nums[i];
    while(num >0){
        int dig = num%10;
        digSum = digSum +dig;
        num = num/10;
    }
}



return eleSum-digSum;

    }
}