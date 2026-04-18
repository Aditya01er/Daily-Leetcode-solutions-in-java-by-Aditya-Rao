class Solution {
    public int mirrorDistance(int n) {
        int t=n;
     int sum =0;
     while(n>0){
      sum = sum*10 + n%10;
      n/=10;
     }
     return Math.abs(sum - t);

    }
}