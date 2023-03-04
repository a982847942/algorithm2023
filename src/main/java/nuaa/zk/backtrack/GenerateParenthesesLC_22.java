package nuaa.zk.backtrack;

import org.junit.Test;

import java.util.*;

/**
 * @author brain
 * @version 1.0
 * @date 2023/3/4 21:19
 */
public class GenerateParenthesesLC_22 {
    public List<String> generateParenthesis(int n) {
        List<String> res = new ArrayList<>();
        dfs(0,0,n,res,new StringBuilder());
        return res;
    }

    private void dfs(int index, int left, int n, List<String> res, StringBuilder temp) {
        if (index ==  2 * n){
            res.add(new String(temp));
            return;
        }
        if (left == n){
            //只能选择右
            temp.append(')');
            dfs(index + 1,left,n,res,temp);
            temp.deleteCharAt(temp.length() - 1);
        }else {
            //可左可右
            //左
            temp.append('(');
            dfs(index + 1,left + 1,n,res,temp);
            temp.deleteCharAt(temp.length() - 1);
            //右
            if (index < left * 2){
                temp.append(')');
                dfs(index + 1,left,n,res,temp);
                temp.deleteCharAt(temp.length() - 1);
            }
        }
    }

    public List<String> generateParenthesis2(int n) {
        List<String> res = new ArrayList<>();
        if (n == 0) return res;
        backTrackingGP(n, n, res, new StringBuilder());
        return res;
    }

    private void backTrackingGP(int left, int right, List<String> res, StringBuilder builder) {
        if (left == 0 && right == 0){
            res.add(builder.toString());
            return;
        }else {
            if (left > right) return;
            if (left > 0){
                builder.append("(");
                backTrackingGP(left - 1,right,res,builder);
                builder.deleteCharAt(builder.length() - 1);
            }
            if (right > 0){
                builder.append(")");
                backTrackingGP(left,right - 1,res,builder);
                builder.deleteCharAt(builder.length() - 1);
            }
        }
    }

    // TODO: 2023/3/4 写一个动态规划，看一下广度优先的实现
    @Test
    public void test(){
        int n = 1;
        List<String> res = generateParenthesis(n);
        res.forEach(t->{
            System.out.println(t);
        });
    }

    //广度优先遍历
    class Node {
        /**
         * 当前得到的字符串
         */
        private String res;
        /**
         * 剩余左括号数量
         */
        private int left;
        /**
         * 剩余右括号数量
         */
        private int right;

        public Node(String str, int left, int right) {
            this.res = str;
            this.left = left;
            this.right = right;
        }
    }

    public List<String> generateParenthesis3(int n) {
        List<String> res = new ArrayList<>();
        if (n == 0) {
            return res;
        }
        Queue<Node> queue = new LinkedList<>();
        queue.offer(new Node("", n, n));

        while (!queue.isEmpty()) {

            Node curNode = queue.poll();
            if (curNode.left == 0 && curNode.right == 0) {
                res.add(curNode.res);
            }
            if (curNode.left > 0) {
                queue.offer(new Node(curNode.res + "(", curNode.left - 1, curNode.right));
            }
            if (curNode.right > 0 && curNode.left < curNode.right) {
                queue.offer(new Node(curNode.res + ")", curNode.left, curNode.right - 1));
            }
        }
        return res;
    }

    class Node2 {
        /**
         * 当前得到的字符串
         */
        private String res;
        /**
         * 剩余左括号数量
         */
        private int left;
        /**
         * 剩余右括号数量
         */
        private int right;

        public Node2(String str, int left, int right) {
            this.res = str;
            this.left = left;
            this.right = right;
        }
    }

    // 注意：这是深度优先遍历
    public List<String> generateParenthesis4(int n) {
        List<String> res = new ArrayList<>();
        if (n == 0) {
            return res;
        }

        // 查看了 Stack 源码，官方推荐使用 Deque 对象，
        // 注意：只使用栈相关的接口，即只使用 `addLast()` 和 `removeLast()`
        Deque<Node2> stack = new ArrayDeque<>();
        stack.addLast(new Node2("", n, n));

        while (!stack.isEmpty()) {

            Node2 curNode = stack.removeLast();
            if (curNode.left == 0 && curNode.right == 0) {
                res.add(curNode.res);
            }
            if (curNode.left > 0) {
                stack.addLast(new Node2(curNode.res + "(", curNode.left - 1, curNode.right));
            }
            if (curNode.right > 0 && curNode.left < curNode.right) {
                stack.addLast(new Node2(curNode.res + ")", curNode.left, curNode.right - 1));
            }
        }
        return res;
    }

}
