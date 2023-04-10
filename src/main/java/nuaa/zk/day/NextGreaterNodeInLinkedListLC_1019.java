package nuaa.zk.day;

import java.util.*;

/**
 * @author brain
 * @version 1.0
 * @date 2023/4/10 9:02
 */
public class NextGreaterNodeInLinkedListLC_1019 {
    private class ListNode {
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

    public int[] nextLargerNodes(ListNode head) {
        ListNode cur = head;
        List<Integer> ans = new ArrayList<>();
        while (cur != null) {
            ListNode next = cur.next;
            int temp = cur.val;
            while (next != null) {
                if (next.val > temp) {
                    ans.add(next.val);
                    break;
                }
                next = next.next;
            }
            if (next == null) ans.add(0);
            cur = cur.next;
        }
        int[] res = new int[ans.size()];
        for (int i = 0; i < ans.size(); i++) {
            res[i] = ans.get(i);
        }
        return res;
    }

    //单调栈(递减栈) 从右向左
    int[] ans;
    Deque<Integer> st = new ArrayDeque<>();

    public int[] nextLargerNodes2(ListNode head) {
        f(head, 0);
        return ans;
    }

    private void f(ListNode head, int i) {
        //base case
        if (head == null) {
            //i为数组长度
            ans = new int[i];
            return;
        }
        //递
        f(head.next, i + 1);
        //归
        while (!st.isEmpty() && head.val >= st.peek()) {
            //弹栈
            st.pop();
        }
        if (!st.isEmpty()) {
            ans[i] = st.peek();
        }
        //压栈
        st.push(head.val);
    }

    //从左向右
    public int[] nextLargerNodes3(ListNode head) {
        int len = 0;
        ListNode cur = head;
        while (cur != null) {
            len++;
            cur = cur.next;
        }
        int[] ans = new int[len];
        Deque<Integer[]> st = new ArrayDeque<>();
        cur = head;
        int i = 0;
        while (cur != null){
           while (!st.isEmpty() && cur.val > st.peek()[0]){
               ans[st.pop()[1]] = cur.val;
           }
           st.push(new Integer[]{cur.val,i++});
           cur = cur.next;
        }
        while (!st.isEmpty()){
            ans[st.pop()[1]] = 0;
        }
        return ans;
    }
    //优化 3
    public int[] nextLargerNodes3_2(ListNode head) {
        int n = 0;
        for (ListNode cur = head; cur != null; cur = cur.next)
            ++n; // 确定返回值的长度
        int[] ans = new int[n];
        Deque<Integer> st = new ArrayDeque<Integer>(); // 单调栈（节点下标）
        int i = 0;
        for (ListNode cur = head; cur != null; cur = cur.next) {
            while (!st.isEmpty() && ans[st.peek()] < cur.val)
                ans[st.pop()] = cur.val; // ans[st.pop()] 后面不会再访问到了
            st.push(i);
            ans[i++] = cur.val;
        }
        for (Integer idx : st)
            ans[idx] = 0; // 没有下一个更大元素
        return ans;
    }

}
