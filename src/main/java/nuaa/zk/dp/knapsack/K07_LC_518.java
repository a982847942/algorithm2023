package nuaa.zk.dp.knapsack;

import org.junit.Test;

import java.util.Arrays;

/**
 * @author brain
 * @version 1.0
 * @date 2023/4/10 21:09
 */
public class K07_LC_518 {
    public int change(int amount, int[] coins) {
        int len = coins.length;
        int[][] dp = new int[len + 1][amount + 1];
        dp[0][0] = 1;
        for (int i = 1; i <= len; i++) {
            int temp = coins[i - 1];
            for (int j = 0; j <= amount; j++) {
                dp[i][j] = dp[i - 1][j];
                for (int k = 1; j - k * temp >= 0; k++) {
                    dp[i][j] += dp[i - 1][j - k * temp];
                }
            }
        }
        return dp[len][amount];
    }

    public int change2(int amount, int[] coins) {
        int len = coins.length;
        int[] dp = new int[amount + 1];
        dp[0] = 1;
        for (int i = 1; i <= len; i++) {
            int temp = coins[i - 1];
            for (int j = temp; j <= amount; j++) {
                dp[j] += dp[j - temp];
            }
        }
        return dp[amount];
    }

    @Test
    public void test() {
        int amount = 10;
        int[] coins = {10};
        System.out.println(change(amount, coins));
        System.out.println(change2(amount, coins));
    }
}
