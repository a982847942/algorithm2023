package nuaa.zk.backtrack;

import org.junit.Test;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * @Classname PalindromePartitioning_131
 * @Description
 * @Date 2023/2/26 10:48
 * @Created by brain
 */
public class PalindromePartitioning_131 {
    public List<List<String>> partition(String s) {
        List<List<String>> res = new ArrayList<>();
        dfs(0, s, res, new ArrayList<String>());
        return res;
    }

    /**
     * index表示当前字符串开始位置，for循环遍历每一个可能的结尾
     * @param index
     * @param s
     * @param res
     * @param temp
     */
    private void dfs(int index, String s, List<List<String>> res, ArrayList<String> temp) {
        if (index == s.length()){
            res.add(new ArrayList<>(temp));
            return;
        }
        for (int i = index; i < s.length(); i++) {
            //判断是否是回文串
            String str = s.substring(index, i + 1);
           if (isPalindrome(str)){
               temp.add(str);
               dfs(i + 1,s,res,temp);
               temp.remove(temp.size() - 1);
           }
        }
    }

    private boolean isPalindrome(String str) {
        int len = str.length();
        for (int i = 0; i < len; i++) {
            if (str.charAt(i) != str.charAt(len - i - 1))return false;
        }
        return true;
    }

    @Test
    public void test(){
        String s = "a";
        List<List<String>> partition = partition(s);
        for (List<String> temp : partition) {
            temp.forEach(t->{
                System.out.print(t + " ");
            });
            System.out.println();
        }
    }

    private final List<List<String>> ans = new ArrayList<>();
    private final List<String> path = new ArrayList<>();
    private String s;

    public List<List<String>> partition3(String s) {
        this.s = s;
        dfs(0, 0);
        return ans;
    }

    private boolean isPalindrome(int left, int right) {
        while (left < right)
            if (s.charAt(left++) != s.charAt(right--))
                return false;
        return true;
    }

    // start 表示当前这段回文子串的开始位置 i表示当前需要进行选择的字符位置
    private void dfs(int i, int start) {
        if (i == s.length()) {
            ans.add(new ArrayList<>(path)); // 固定答案
            return;
        }

        // 不选 i 和 i+1 之间的逗号（i=n-1 时右边没有逗号）
        if (i < s.length() - 1)
            dfs(i + 1, start);

        // 选 i 和 i+1 之间的逗号
        if (isPalindrome(start, i)) {
            path.add(s.substring(start, i + 1));
            dfs(i + 1, i + 1);
            path.remove(path.size() - 1); // 恢复现场
        }
    }

    //动态规划预处理
    public List<List<String>> partition2(String s) {
        int len = s.length();
        List<List<String>> res = new ArrayList<>();
        if (len == 0) {
            return res;
        }

        char[] charArray = s.toCharArray();
        // 预处理
        // 状态：dp[i][j] 表示 s[i][j] 是否是回文
        boolean[][] dp = new boolean[len][len];
        // 状态转移方程：在 s[i] == s[j] 的时候，dp[i][j] 参考 dp[i + 1][j - 1]
        for (int right = 0; right < len; right++) {
            // 注意：left <= right 取等号表示 1 个字符的时候也需要判断
            for (int left = 0; left <= right; left++) {
                if (charArray[left] == charArray[right] && (right - left <= 2 || dp[left + 1][right - 1])) {
                    dp[left][right] = true;
                }
            }
        }

        Deque<String> stack = new ArrayDeque<>();
        dfs(s, 0, len, dp, stack, res);
        return res;
    }

    private void dfs(String s, int index, int len, boolean[][] dp, Deque<String> path, List<List<String>> res) {
        if (index == len) {
            res.add(new ArrayList<>(path));
            return;
        }

        for (int i = index; i < len; i++) {
            if (dp[index][i]) {
                path.addLast(s.substring(index, i + 1));
                dfs(s, i + 1, len, dp, path, res);
                path.removeLast();
            }
        }
    }
}
