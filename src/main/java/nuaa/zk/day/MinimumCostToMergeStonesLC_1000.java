package nuaa.zk.day;

import org.junit.Test;

import java.util.Arrays;

/**
 * @author brain
 * @version 1.0
 * @date 2023/4/4 9:12
 */
public class MinimumCostToMergeStonesLC_1000 {
    int[] preSum;
    int k;
    int[][][] cache;

    public int mergeStones(int[] stones, int _k) {
        k = _k;
        int len = stones.length;
        if ((len - 1) % (k - 1) != 0) return -1;
        preSum = new int[len + 1];
        for (int i = 0; i < len; i++) {
            preSum[i + 1] = stones[i] + preSum[i];
        }
        cache = new int[len][len][k + 1];
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len; j++) {
                Arrays.fill(cache[i][j], -1);
            }
        }
        return dfs(0, len - 1, 1);
    }

    private int dfs(int start, int end, int p) {
        if (cache[start][end][p] != -1)return cache[start][end][p];
            if (p == 1)
                return cache[start][end][p] = start == end ? 0 : (dfs(start, end, k) + preSum[end + 1] - preSum[start]);
        int ans = Integer.MAX_VALUE;
        for (int i = start; i < end; i += k - 1) {
            ans = Math.min(ans, dfs(start, i, 1) + dfs(i + 1, end, p - 1));
        }
        return cache[start][end][p] = ans;
    }
    // 不用Integer.MAX_VALUE,因为Integer.MAX_VALUE + 正数 会溢出变为负数
    private int MAX_VALUE = 99999999;
    public int mergeStones1_2(int[] stones, int k) {
        int n = stones.length;
        if ((n - 1) % (k - 1) != 0) return -1;
        int[][][] dp = new int[n + 1][n + 1][k + 1];
        int[] sum = new int[n + 1];
        for (int i = 1; i <= n; ++i) sum[i] = sum[i - 1] + stones[i - 1];
        for (int i = 1; i <= n; ++i) {
            for (int j = i; j <= n; ++j) {
                for (int m = 2; m <= k; ++m) dp[i][j][m] = MAX_VALUE;
            }
            dp[i][i][1] = 0;
        }
        for (int len = 2; len <= n; ++len) { // 枚举区间长度
            for (int i = 1; i + len - 1 <= n; ++i) { // 枚举区间起点
                int j = i + len - 1;
                for (int m = 2; m <= k; ++m) { // 枚举堆数
                    for (int p = i; p < j; p += k - 1) {  // 枚举分界点
                        dp[i][j][m] = Math.min(dp[i][j][m], dp[i][p][1] + dp[p + 1][j][m - 1]);
                    }
                }
                dp[i][j][1] = dp[i][j][k] + sum[j] - sum[i - 1];
            }
        }
        return dp[1][n][1];
    }









    private int[][] memo;
    private int[] s;
//    private int k;

    //优化
    public int mergeStones2(int[] stones, int k) {
        int n = stones.length;
        if ((n - 1) % (k - 1) > 0) // 无法合并成一堆
            return -1;

        s = new int[n + 1];
        for (int i = 0; i < n; i++)
            s[i + 1] = s[i] + stones[i]; // 前缀和
        this.k = k;
        memo = new int[n][n];
        for (int i = 0; i < n; ++i)
            Arrays.fill(memo[i], -1); // -1 表示还没有计算过
        return dfs(0, n - 1);
    }

    private int dfs(int i, int j) {
        if (i == j) return 0; // 只有一堆石头，无需合并
        if (memo[i][j] != -1) return memo[i][j];
        int res = Integer.MAX_VALUE;
        for (int m = i; m < j; m += k - 1)
            res = Math.min(res, dfs(i, m) + dfs(m + 1, j));
        if ((j - i) % (k - 1) == 0) // 可以合并成一堆
            res += s[j + 1] - s[i];
        return memo[i][j] = res;
    }


    public int mergeStones3(int[] stones, int k) {
        int n = stones.length;
        if ((n - 1) % (k - 1) > 0) // 无法合并成一堆
            return -1;

        int[] s = new int[n + 1];
        for (int i = 0; i < n; i++)
            s[i + 1] = s[i] + stones[i]; // 前缀和

        int[][] f = new int[n][n];
        for (int i = n - 1; i >= 0; --i)
            for (int j = i + 1; j < n; ++j) {
                f[i][j] = Integer.MAX_VALUE;
                for (int m = i; m < j; m += k - 1)
                    f[i][j] = Math.min(f[i][j], f[i][m] + f[m + 1][j]);
                if ((j - i) % (k - 1) == 0) // 可以合并成一堆
                    f[i][j] += s[j + 1] - s[i];
            }
        return f[0][n - 1];
    }
    @Test
    public void test() {
        int[] stones = {3, 2, 4, 1};
        int k = 2;
        System.out.println(mergeStones(stones, k));
    }
}
