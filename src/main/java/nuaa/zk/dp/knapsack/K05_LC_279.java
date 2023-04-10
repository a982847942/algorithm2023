package nuaa.zk.dp.knapsack;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author brain
 * @version 1.0
 * @date 2023/4/10 10:39
 */
public class K05_LC_279 {
    public int numSquares(int n) {
        int idx = 1;
        while (idx * idx <= n) idx++;
        idx--;
        int[][] dp = new int[idx + 1][n + 1];
        Arrays.fill(dp[0], Integer.MAX_VALUE);
        dp[0][0] = 0;
        for (int i = 1; i <= idx; i++) {
            int temp = i * i;
            for (int j = 0; j <= n; j++) {
                dp[i][j] = dp[i - 1][j];
                for (int k = 1; j - k * temp >= 0; k++) {
                    if (dp[i - 1][j - k * temp] != Integer.MAX_VALUE) {
                        dp[i][j] = Math.min(dp[i][j], dp[i - 1][j - k * temp] + k);
                    }
                }
            }
        }
        return dp[idx][n];
    }

    public int numSquares2(int n) {
        int idx = 1;
        while (idx * idx <= n) idx++;
        idx--;
        int[] dp = new int[n + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;
        for (int i = 1; i <= idx; i++) {
            int temp = i * i;
            for (int j = temp; j <= n; j++) {
                if (dp[j - temp] != Integer.MAX_VALUE) {
                    dp[j] = Math.min(dp[j], dp[j - temp] + 1);
                }
            }
        }
        return dp[n];
    }

    @Test
    public void test() {
        int n = 13;
        System.out.println(numSquares2(n));
    }



}
