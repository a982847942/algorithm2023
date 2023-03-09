package nuaa.zk.doublepointer;

import org.junit.Test;

/**
 * @author brain
 * @version 1.0
 * @date 2023/3/9 22:00
 */
public class ReverseLinkedListLC_206 {

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

    //双指针迭代
    public ListNode reverseList(ListNode head) {
        if (head == null) return head;
        ListNode pre = null;
        ListNode cur = head;
        while (cur != null) {

            ListNode temp = cur.next;
            cur.next = pre;
            pre = cur;
            cur = temp;
        }
        return pre;
    }

    private void printList(ListNode head) {
        while (head != null) {
            System.out.print(head.val + " ");
            head = head.next;
        }
        System.out.println();
    }
    //递归
    public ListNode reverseList2(ListNode head) {
        return reverse(null,head);
    }
    public ListNode reverse(ListNode pre,ListNode cur){
        if (cur == null) return pre;
        ListNode temp = cur.next;
        cur.next = pre;
        return reverse(cur,temp);
    }

    ListNode reverseList3(ListNode head) {
        // 边缘条件判断
        if(head == null) return null;
        if (head.next == null) return head;

        // 递归调用，翻转第二个节点开始往后的链表
        ListNode last = reverseList3(head.next);
        // 翻转头节点与第二个节点的指向
        head.next.next = head;
        // 此时的 head 节点为尾节点，next 需要指向 NULL
        head.next = null;
        return last;
    }
    @Test
    public void test(){
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);
        printList(head);
        ListNode res = reverseList2(head);
        printList(res);
    }

}
