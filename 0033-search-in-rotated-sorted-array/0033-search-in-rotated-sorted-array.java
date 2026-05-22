class Solution {
    public int search(int[] arr, int tar) {
       int lo=0, hi=arr.length-1;
       while(lo<=hi) {
        int mid= lo +(hi-lo)/2;
        if(arr[mid]==tar)return mid;
        else if (arr[lo]<=arr[mid]){
            if(arr[lo]<=tar && tar<arr[mid]) hi=mid-1;
            else lo = mid+1;
        }
        else{
            if(arr[mid]<tar && tar<=arr[hi]) lo=mid +1;
            else hi = mid-1;
        }

        
        
    
       }
       return -1;
        
    }
}