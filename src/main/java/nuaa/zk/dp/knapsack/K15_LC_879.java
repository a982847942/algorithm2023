package nuaa.zk.dp.knapsack;

import org.junit.Test;

import java.math.BigInteger;
import java.util.Arrays;

/**
 * @author brain
 * @version 1.0
 * @date 2023/4/11 22:33
 */
public class K15_LC_879 {
    /**
     * 定义 f[i][j][k] 为考虑前 i 件物品，使用人数不超过 j，所得利润至少为 k 的方案数
     * 至少为 K 与之前的不超过状态不同，此时负数也存在意义。（负数时可以按0处理）
     */
    int mod = (int) 1e9 + 7;

    public int profitableSchemes(int n, int minProfit, int[] group, int[] profit) {
        int len = group.length;
        int[][][] dp = new int[len + 1][n + 1][minProfit + 1];
        for (int i = 0; i <= n; i++) {
            dp[0][i][0] = 1;
        }
        for (int i = 1; i <= len; i++) {
            int gs = group[i - 1];
            int ps = profit[i - 1];
            for (int j = 0; j <= n; j++) {

                for (int k = 0; k <= minProfit; k++) {
                    dp[i][j][k] = dp[i - 1][j][k];
                    if (j >= gs) {
                        dp[i][j][k] = (dp[i][j][k] + dp[i - 1][j - gs][Math.max(0, k - ps)]) % mod;
                    }
                }
            }
        }
        return dp[len][n][minProfit];
    }

    public int profitableSchemes2(int n, int minProfit, int[] group, int[] profit) {
        int len = group.length;
        int[][] dp = new int[n + 1][minProfit + 1];
        for (int i = 0; i <= n; i++) {
            dp[i][0] = 1;
        }
        for (int i = 0; i < len; i++) {
            int gs = group[i];
            int ps = profit[i];
            for (int j = n; j >= gs; j--) {
                for (int k = minProfit; k >= 0; k--) {
                    dp[j][k] = (dp[j][k] + dp[j - gs][Math.max(0, k - ps)]) % mod;
                }
            }
        }
        return dp[n][minProfit];
    }


    static int N = 105;
    static BigInteger[][] f = new BigInteger[2][N];
    static BigInteger[][][] g = new BigInteger[2][N][N];
    static BigInteger mod2 = new BigInteger("1000000007");

    public int profitableSchemes3(int n, int min, int[] gs, int[] ps) {
        int m = gs.length;

        for (int j = 0; j <= n; j++) {
            //只考虑前 0 个任务 不超过人数限制的方案数
            f[0][j] = new BigInteger("1");
            //这里无意义 只是为了初始化BigInteger
            f[1][j] = new BigInteger("0");
        }
        for (int j = 0; j <= n; j++) {
            for (int k = 0; k <= min; k++) {
                g[0][j][k] = new BigInteger("1");
                g[1][j][k] = new BigInteger("0");
            }
        }

        for (int i = 1; i <= m; i++) {
            int a = gs[i - 1], b = ps[i - 1];
            int x = i & 1, y = (i - 1) & 1;
            for (int j = 0; j <= n; j++) {
                f[x][j] = f[y][j];
                if (j >= a) {
                    f[x][j] = f[x][j].add(f[y][j-a]);
                }
            }
        }
        if (min == 0) return (f[m&1][n]).mod(mod2).intValue();

        for (int i = 1; i <= m; i++) {
            int a = gs[i - 1], b = ps[i - 1];
            int x = i & 1, y = (i - 1) & 1;
            for (int j = 0; j <= n; j++) {
                for (int k = 0; k < min; k++) {
                    g[x][j][k] = g[y][j][k];
                    if (j - a >= 0 && k - b >= 0) {
                        g[x][j][k] = g[x][j][k].add(g[y][j-a][k-b]);
                    }
                }
            }
        }

        return f[m&1][n].subtract(g[m&1][n][min-1]).mod(mod2).intValue();
    }

    @Test
    public void test() {
        int n = 5, minProfit = 3;
        int[] group = {2, 2}, profit = {2, 3};
        System.out.println(profitableSchemes(n, minProfit, group, profit));
        System.out.println(profitableSchemes2(n, minProfit, group, profit));
    }
}
