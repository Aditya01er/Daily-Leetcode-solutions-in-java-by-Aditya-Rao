class Solution {
    public int[] dailyTemperatures(int[] temperatures) {
   int[] result = new int [temperatures.length];
   Stack<Integer> st = new Stack<>();
   for(int i=0 ; i<temperatures.length; i++){
    while(!st.isEmpty() && temperatures[i] > temperatures[st.peek()]){
        int prevIndex= st.pop();
        result[prevIndex]= i-prevIndex;
    }
        st.push(i);
    






   }


return result;


    }
}