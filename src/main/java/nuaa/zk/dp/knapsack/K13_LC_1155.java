package nuaa.zk.dp.knapsack;

import org.junit.Test;

/**
 * @author brain
 * @version 1.0
 * @date 2023/4/11 10:43
 */
public class K13_LC_1155 {
    int mod = (int) 1e9 + 7;

    public int numRollsToTarget(int n, int k, int target) {
        int[] dp = new int[target + 1];
        dp[0] = 1;
        for (int i = 0; i < n; i++) {
            for (int j = target; j >= 0; j--) {
                dp[j] = 0;//必须要在改组选一个点数 不存在不选用任何一个的情况
                for (int l = 1; l <= k; l++) {
                    if (j - l >= 0) dp[j] = (dp[j] + dp[j - l]) % mod;
                }
            }
        }
        return dp[target];
    }

    @Test
    public void test() {
        int n = 30, k = 30, target = 500;
        System.out.println(numRollsToTarget(n, k, target));
    }
}
