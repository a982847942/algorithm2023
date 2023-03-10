package nuaa.zk.doublepointer;

import org.junit.Test;

/**
 * @author brain
 * @version 1.0
 * @date 2023/3/10 21:17
 */
public class LinkedListCycleIILC_142 {
    class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }

    public ListNode detectCycle(ListNode head) {
        if (head == null || head.next == null ||head.next.next == null) return null;
        ListNode fast = head.next.next;
        ListNode slow = head.next;
        while (fast != slow && fast.next != null && fast.next.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        if (fast.next == null ||fast.next.next == null ) return null;
        fast = head;
        while (fast != slow) {
            fast = fast.next;
            slow = slow.next;
        }
        return fast;
    }
    public ListNode detectCycle2(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {// 有环
                ListNode index1 = fast;
                ListNode index2 = head;
                // 两个指针，从头结点和相遇结点，各走一步，直到相遇，相遇点即为环入口
                while (index1 != index2) {
                    index1 = index1.next;
                    index2 = index2.next;
                }
                return index1;
            }
        }
        return null;
    }
    public ListNode detectCycle3(ListNode head) {
        ListNode fast = head, slow = head;
        while (true) {
            if (fast == null || fast.next == null) return null;
            fast = fast.next.next;
            slow = slow.next;
            if (fast == slow) break;
        }
        fast = head;
        while (slow != fast) {
            slow = slow.next;
            fast = fast.next;
        }
        return fast;
    }

    @Test
    public void test() {
        ListNode head1 = new ListNode(3);
        ListNode head2 = new ListNode(2);
        ListNode head3 = new ListNode(0);
        ListNode head4 = new ListNode(-4);
        head1.next = head2;
        head2.next = head3;
        head3.next = head4;
        head4.next = head2;
        System.out.println(detectCycle(head1).val);
    }
}
