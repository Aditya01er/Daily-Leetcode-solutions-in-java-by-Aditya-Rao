/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode reverseBetween(ListNode head, int a, int b) {

ArrayList<ListNode > arr = new ArrayList<>();
ListNode temp = head;
while(temp != null){
    arr.add(temp);
    temp = temp.next;
}
int i=a-1, j=b-1;
while(i<j){
    ListNode t1 = arr.get(i);
    ListNode t2 = arr.get(j);
    arr.set(i,t2);
    arr.set(j,t1);
    i++;
    j--;
}
for(i=0; i<arr.size(); i++){
    arr.get(i).next = (i==arr.size()-1) ? null : arr.get(i+1);
}
return arr.get(0);










































    /* ListNode curr = head;
        ListNode prev = null; 
        ListNode fwd = null;
        while(curr != null){
            fwd = curr.next;
            curr.next = prev;
            prev = curr;
            curr = fwd;
        }
        return prev;      

       
       
       if( head== null || head.next == null) return head;
      ListNode a = head.next;
      head.next = null;
      ListNode b = reverseBetween(a);
      a.next = head;
      return b;    */



    }
}