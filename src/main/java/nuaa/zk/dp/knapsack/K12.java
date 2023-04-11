package nuaa.zk.dp.knapsack;

import org.junit.Test;

/**
 * @author brain
 * @version 1.0
 * @date 2023/4/11 10:29
 * 分组背包
 * 给定 N 个物品组，和容量为 C 的背包。
 * 第 i 个物品组共有 S[i] 件物品，其中第 i 组的第 j 件物品的成本为v[i][j] ，价值为w[i][j] 。
 * 每组有若干个物品，同一组内的物品最多只能选一个。
 * 求解将哪些物品装入背包可使这些物品的费用总和不超过背包容量，且价值总和最大。
 */
public class K12 {
    public int maxValue(int N, int C, int[] S, int[][] v, int[][] w) {
        int[][] dp = new int[N + 1][C + 1];
        for (int i = 1; i <= N; i++) {
            int[] vi = v[i - 1];
            int[] wi = w[i - 1];
            int si = S[i - 1];
            for (int j = 0; j <= C; j++) {
                dp[i][j] = dp[i - 1][j];
                for (int k = 0; k < si; k++) {
                    if (j - vi[k] >= 0) {
                        dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - vi[k]] + wi[k]);
                    }
                }
            }
        }
        return dp[N][C];
    }

    public int maxValue2(int N, int C, int[] S, int[][] v, int[][] w) {
        int[] dp = new int[C + 1];
        for (int i = 0; i < N; i++) {
            int si = S[i];
            int[] vi = v[i];
            int[] wi = w[i];
            for (int j = C; j >= 0; j--) {
                for (int k = 0; k < si; k++) {
                    if (j - vi[k] >= 0){
                        dp[j] = Math.max(dp[j],dp[j - vi[k]] + wi[k]);
                    }
                }
            }
        }
        return dp[C];
    }
    @Test
    public void test() {
        int N = 2, C = 9;
        int[] S = {2, 3};
        int[][] v = {{1, 2, -1}, {1, 2, 3}}, w = {{2, 4, -1}, {1, 3, 6}};
        System.out.println(maxValue(N, C, S, v, w));
        System.out.println(maxValue2(N, C, S, v, w));
    }
}
