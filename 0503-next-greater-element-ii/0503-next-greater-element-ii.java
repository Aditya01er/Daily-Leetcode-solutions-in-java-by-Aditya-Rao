class Solution {
    public int[] nextGreaterElements( int [] arr){

    int n= arr.length;
    int[] result = new int[n];
  Stack<Integer> st= new Stack<>();
    for(int i=n-1; i>=0; i--){
        st.push(arr[i]);

    }
    for(int i=n-1; i>=0; i--){
        while(!st.isEmpty() && arr[i] >= st.peek()) st.pop();
       if(st.size()==0) result[i]=-1;
       else result[i]= st.peek();
       st.push(arr[i]);
           
    }
    return result;
    }
    
}