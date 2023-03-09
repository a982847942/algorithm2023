package nuaa.zk.doublepointer;

import org.junit.Test;

/**
 * @author brain
 * @version 1.0
 * @date 2023/3/9 22:28
 */
public class SwapNodesInPairsLC_24 {
    public class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    public ListNode swapPairs(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode cur = head;
        return reverse(cur);
    }

    public ListNode reverse(ListNode head) {
        if (head != null && head.next != null) {
            ListNode first = head;
            ListNode second = head.next;
            ListNode next = second.next;
            second.next = first;
            first.next = reverse(next);
            return second;
        } else if (head != null) {
            return head;
        }
        return null;
    }

    private void printList(ListNode head) {
        while (head != null) {
            System.out.print(head.val + " ");
            head = head.next;
        }
        System.out.println();
    }

    @Test
    public void test() {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);
        printList(head);
        ListNode res = swapPairs(head);
        printList(res);
    }
}
