package nuaa.zk.dp.knapsack;

import org.junit.Test;

import java.util.Arrays;

/**
 * @author brain
 * @version 1.0
 * @date 2023/4/10 20:53
 */
public class K06_LC_322 {
    public int coinChange(int[] coins, int amount) {
        int len = coins.length;
        int[][] dp = new int[len + 1][amount + 1];
        Arrays.fill(dp[0], Integer.MAX_VALUE);
        dp[0][0] = 0;
        for (int i = 1; i <= len; i++) {
            int temp = coins[i - 1];
            for (int j = 0; j <= amount; j++) {
                dp[i][j] = dp[i - 1][j];
                for (int k = 1; j - k * temp >= 0; k++) {
                    if (dp[i - 1][j - k * temp] != Integer.MAX_VALUE) {
                        dp[i][j] = Math.min(dp[i][j], dp[i - 1][j - k * temp] + k);
                    }
                }
            }
        }
        return dp[len][amount] == Integer.MAX_VALUE ? -1 : dp[len][amount];
    }

    int INF = 0x3f3f3f3f;

    public int coinChange2(int[] coins, int amount) {
        int len = coins.length;
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, INF);
        dp[0] = 0;
        for (int i = 1; i <= len; i++) {
            int coin = coins[i - 1];
            for (int j = coin; j <= amount; j++) {
                dp[j] = Math.min(dp[j], dp[j - coin] + 1);
            }
        }
        return dp[amount] == INF ? -1 : dp[amount];
    }

    @Test
    public void test() {
        int[] coins = {1};
        int amount = 2;
        System.out.println(coinChange(coins, amount));
        System.out.println(coinChange2(coins, amount));
    }
}
