package nuaa.zk.day;

/**
 * @author brain
 * @version 1.0
 * @date 2023/4/12 9:49
 */
public class LongestChunkedPalindromeDecompositionLC_1147 {
    public int longestDecomposition(String text) {
        int len = text.length();
        if (len == 0)return 0;
        for (int i = 1; i <= len / 2; i++) {
            if (text.substring(0,i).equals(text.substring(len - i)))
                return 2 + longestDecomposition(text.substring(i,len - i));
        }
        return 1;
    }
    public int longestDecomposition2(String s) {
        int ans = 0;
        while (!s.isEmpty()) {
            int i = 1, n = s.length();
            while (i <= n / 2 && !s.substring(0, i).equals(s.substring(n - i))) // 枚举前后缀
                ++i;
            if (i > n / 2) { // 无法分割
                ++ans;
                break;
            }
            ans += 2; // 分割出 s[:i] 和 s[n-i:]
            s = s.substring(i, n - i);
        }
        return ans;
    }
}
