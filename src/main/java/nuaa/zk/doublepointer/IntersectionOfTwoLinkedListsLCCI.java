package nuaa.zk.doublepointer;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author brain
 * @version 1.0
 * @date 2023/3/10 20:44
 */
public class IntersectionOfTwoLinkedListsLCCI {
    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }


    //长度
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        int len1 = 0;
        int len2 = 0;
        ListNode cur1 = headA;
        ListNode cur2 = headB;
        while (cur1 != null) {
            len1++;
            cur1 = cur1.next;
        }
        while (cur2 != null) {
            len2++;
            cur2 = cur2.next;
        }
        int diff = 0;
        ListNode fast = null;
        ListNode slow = null;
        if (len1 > len2) {
            fast = headA;
            slow = headB;
            diff = len1 - len2;
        } else {
            fast = headB;
            slow = headA;
            diff = len2 - len1;
        }
        while (diff > 0){
            fast = fast.next;
            diff--;
        }
        while (fast != slow){
            fast =fast.next;
            slow = slow.next;
        }
        if (fast == null)return null;
        return fast;
    }


    //缓存
    public ListNode getIntersectionNode2(ListNode headA, ListNode headB) {
        Set<ListNode> cache = new HashSet<>();
        ListNode cur1 = headA;
        while (cur1 != null){
            cache.add(cur1);
            cur1 = cur1.next;
        }
        cur1 = headB;
        while (cur1 != null){
            if (cache.contains(cur1)) return cur1;
            cur1 = cur1.next;
        }
        return null;
    }

    //走的路径长度相同
    public ListNode getIntersectionNode3(ListNode headA, ListNode headB) {
        ListNode cur1 = headA;
        ListNode cur2 = headB;
        while (cur1 != null || cur2 != null){
            if (cur1 == cur2) return cur1;
            if (cur1 == null){
                cur1 = headB;
            }else {
                cur1 = cur1.next;
            }
            if (cur2 == null){
                cur2 = headA;
            }else {
                cur2 = cur2.next;
            }
        }
        return cur2;
    }
}

