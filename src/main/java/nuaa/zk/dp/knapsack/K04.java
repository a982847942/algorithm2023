package nuaa.zk.dp.knapsack;

import org.junit.Test;

/**
 * @author brain
 * @version 1.0
 * @date 2023/4/10 10:16
 */
public class K04 {
    //同样是子集型回溯 不选或选多次
    private int getMaxValue(int N, int C, int[] v, int[] w) {
        return dfs(0, C, v, w);
    }

    private int dfs(int index, int c, int[] v, int[] w) {
        if (index == v.length) return 0;
        int ans = 0;
        for (int i = 0; c - i * v[index] >= 0; i++) {
            ans = Math.max(ans, dfs(index + 1, c - c - i * v[index], v, w) + i * w[index]);
        }
        return ans;
    }

    private int getMaxValue2(int N, int C, int[] v, int[] w) {
        int[][] dp = new int[N + 1][C + 1];
        for (int i = 1; i <= N; i++) {
            for (int j = 0; j <= C; j++) {
                int temp = v[i - 1];
                dp[i][j] = dp[i - 1][j];
                for (int k = 1; j - k * temp >= 0; k++) {
                    dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - k * temp] + w[i - 1] * k);
                }
            }
        }
        return dp[N][C];
    }

    private int getMaxValue3(int N, int C, int[] v, int[] w) {
        int[][] dp = new int[N + 1][C + 1];
        for (int i = 1; i <= N; i++) {
            int temp = v[i - 1];
            for (int j = 0; j <= C; j++) {
                if (j - temp >= 0) {
                    dp[i][j] = Math.max(dp[i][j - temp] + w[i - 1], dp[i - 1][j]);
                }
            }
        }
        return dp[N][C];
    }

    private int getMaxValue4(int N, int C, int[] v, int[] w) {
        int[] dp = new int[C + 1];
        for (int i = 1; i <= N; i++) {
            int temp = v[i - 1];
            for (int j = temp; j <= C; j++) {
                dp[j] = Math.max(dp[j - temp] + w[i - 1], dp[j]);
            }
        }
        return dp[C];
    }


    @Test
    public void test() {
        int N = 2, C = 5;
        int[] v = {1, 2}, w = {1, 2};
        System.out.println(getMaxValue(N, C, v, w));
        System.out.println(getMaxValue2(N, C, v, w));
        System.out.println(getMaxValue3(N, C, v, w));
        System.out.println(getMaxValue4(N, C, v, w));
    }
}
