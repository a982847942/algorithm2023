package nuaa.zk.doublepointer;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @author brain
 * @version 1.0
 * @date 2023/3/10 20:23
 */
public class RemoveNthNodeFromEndOfListLC_19 {
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

    public ListNode removeNthFromEnd(ListNode head, int n) {
        int len = 0;
        ListNode cur = head;
        while (cur != null){
            len++;
            cur = cur.next;
        }
        int t = len - n + 1;
        if (t == 1)return head.next;
        cur = head;
        len = 0;
        ListNode pre = null;
        while (cur != null){
            len++;
            if (len == t){
                pre.next = cur.next;
                cur.next = null;
                break;
            }
            pre = cur;
            cur = cur.next;
        }
        return head;
    }
    public ListNode removeNthFromEnd2(ListNode head, int n) {
        ListNode dummyNode = new ListNode(-1);
        dummyNode.next = head;
        ListNode fast = dummyNode;
        ListNode slow = dummyNode;
        int index = 0;
        while (index <= n ){
            fast = fast.next;
            index++;
        }
        while (fast != null){
            slow = slow.next;
            fast = fast.next;
        }
        ListNode delete = slow.next;
        slow.next = delete.next;
        delete.next = null;
        return dummyNode.next;
    }

    /**
     *一定要注意，大部分链表相关题目 使用栈都可以很简单求解(浪费空间)
     */
    public ListNode removeNthFromEnd3(ListNode head, int n) {
        ListNode dummy = new ListNode(0, head);
        Deque<ListNode> stack = new LinkedList<ListNode>();
        ListNode cur = dummy;
        while (cur != null) {
            stack.push(cur);
            cur = cur.next;
        }
        for (int i = 0; i < n; ++i) {
            stack.pop();
        }
        ListNode prev = stack.peek();
        prev.next = prev.next.next;
        ListNode ans = dummy.next;
        return ans;
    }

}
