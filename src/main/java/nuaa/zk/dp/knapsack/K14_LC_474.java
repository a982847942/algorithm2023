package nuaa.zk.dp.knapsack;

import org.junit.Test;

import java.util.Arrays;

/**
 * @author brain
 * @version 1.0
 * @date 2023/4/11 21:44
 */
public class K14_LC_474 {
    int[][][] cache;

    public int findMaxForm(String[] strs, int m, int n) {
        cache = new int[strs.length][m + 1][n + 1];
        for (int i = 0; i < strs.length; i++) {
            for (int j = 0; j <= m; j++) {
                Arrays.fill(cache[i][j], -1);
            }
        }
        return dfs(0, strs, m, n);
    }

    private int dfs(int index, String[] strs, int m, int n) {
        if (index == strs.length) return 0;
        if (cache[index][m][n] != -1) return cache[index][m][n];
        //不选
        int p1 = dfs(index + 1, strs, m, n);
        //选
        String str = strs[index];
        int _m = m;
        int _n = n;
        int len = str.length();
        for (int i = 0; i < len; i++) {
            if (str.charAt(i) == '1') n--;
            else m--;
        }
        int p2 = 0;
        if (m >= 0 && n >= 0) {
            p2 = dfs(index + 1, strs, m, n) + 1;
        }

        return cache[index][_m][_n] = Math.max(p1, p2);
    }

    public int findMaxForm2(String[] strs, int m, int n) {
        int len = strs.length;
        int[][][] dp = new int[len + 1][m + 1][n + 1];
        for (int i = len - 1; i >= 0; i--) {
            String str = strs[i];
            int sumM = 0;
            int sumN = 0;
            for (int j = 0; j < str.length(); j++) {
                if (str.charAt(j) == '0') sumM++;
                else sumN++;
            }
            for (int j = 0; j <= m; j++) {
                for (int k = 0; k <= n; k++) {
                    dp[i][j][k] = dp[i + 1][j][k];
                    if (j >= sumM && k >= sumN) {
                        dp[i][j][k] = Math.max(dp[i][j][k], dp[i + 1][j - sumM][k - sumN] + 1);
                    }
                }
            }
        }
        return dp[0][m][n];
    }

    public int findMaxForm3(String[] strs, int m, int n) {
        int len = strs.length;
        int[][] dp = new int[m + 1][n + 1];
        for (int i = len - 1; i >= 0; i--) {
            String str = strs[i];
            int sumM = 0;
            int sumN = 0;
            //这里也可以在最外层预处理一个二维数组 count[strs.length][2]
            for (int j = 0; j < str.length(); j++) {
                if (str.charAt(j) == '0') sumM++;
                else sumN++;
            }
            for (int j = m; j >= sumM; j--) {
                for (int k = n; k >= sumN; k--) {
                    dp[j][k] = Math.max(dp[j][k], dp[j - sumM][k - sumN] + 1);
                }
            }
        }
        return dp[m][n];
    }
    public int findMaxForm4(String[] strs, int m, int n) {
        int len = strs.length;
        int[][] dp = new int[m + 1][n + 1];
        for (int i = 0; i < len; i++) {
            String str = strs[i];
            int sumM = 0;
            int sumN = 0;
            //这里也可以在最外层预处理一个二维数组 count[strs.length][2]
            for (int j = 0; j < str.length(); j++) {
                if (str.charAt(j) == '0') sumM++;
                else sumN++;
            }
            for (int j = m; j >= sumM; j--) {
                for (int k = n; k >= sumN ; k--) {
                    dp[j][k] = Math.max(dp[j][k],dp[j-sumM][k-sumN] + 1);
                }
            }
        }
        return dp[m][n];
    }


    @Test
    public void test() {
        String[] strs = {"10", "0001", "111001", "1", "0"};
        int m = 5, n = 3;
        System.out.println(findMaxForm(strs, m, n));
        System.out.println(findMaxForm2(strs, m, n));
        System.out.println(findMaxForm3(strs, m, n));
        System.out.println(findMaxForm4(strs, m, n));
    }
}
