class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int a= nums1.length;
        int b= nums2.length;
        int [] merged = new int [a+b];
       int i=0, j=0, k=0;
       while(i<a && j<b){
        if(nums1[i]< nums2[j]){
            merged[k++] = nums1[i++];

        }
        else{
            merged[k++] = nums2[j++];
        }


       }

while(i<a){
    merged[k++] = nums1[i++];
}

while(j<b){
    merged[k++] = nums2[j++];
}




        int t= a+b;
        if(t%2==1){
            return merged[t/2];
        } else{
            return( merged[t/2]+ merged[t/2-1])/2.0;
        }
      
    }
}