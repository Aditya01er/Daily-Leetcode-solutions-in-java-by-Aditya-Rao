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
    public int[] nodesBetweenCriticalPoints(ListNode head) {
        int [] ans = {-1,-1};
        int idx = 1, firstIdx = -1, lastIdx = -1, minDist = Integer.MAX_VALUE;
        ListNode a = head;
        ListNode b =a.next;
        ListNode c = b.next;
        if(c==null) return ans;
        while(c!=null){
            if((b.val > a.val && b.val > c.val) || (b.val < a.val && b.val < c.val)){
         if(firstIdx==-1) firstIdx = idx;
         if(lastIdx!=-1){
            int dist = idx -lastIdx;
           minDist = Math.min(dist,minDist);
         }   lastIdx = idx;   
            }
            idx++;
            a= a.next;
            b= b.next;
            c= c.next;
        }
       int maxDist = lastIdx - firstIdx;
        if(maxDist==0) maxDist = -1;
        if(minDist==Integer.MAX_VALUE) minDist=-1;
        ans[0] = minDist;
        ans[1]= maxDist;
        return ans;
    }
}