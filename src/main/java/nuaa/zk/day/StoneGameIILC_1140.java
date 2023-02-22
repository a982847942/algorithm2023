package nuaa.zk.day;

import org.junit.Test;

/**
 * @Classname StoneGameIILC_1140
 * @Description
 * @Date 2023/2/22 9:07
 * @Created by brain
 */
public class StoneGameIILC_1140 {

    private int[] s;
    private Integer[][] f;
    private int n;

    public int stoneGameII(int[] piles) {
        n = piles.length;
        s = new int[n + 1];
        f = new Integer[n][n + 1];
        for (int i = 0; i < n; ++i) {
            s[i + 1] = s[i] + piles[i];
        }
        return dfs(0, 1);
    }

    private int dfs(int i, int m) {
        if (m * 2 >= n - i) {
            return s[n] - s[i];
        }
        if (f[i][m] != null) {
            return f[i][m];
        }
        int res = 0;
        for (int x = 1; x <= m * 2; ++x) {
            res = Math.max(res, s[n] - s[i] - dfs(i + x, Math.max(m, x)));
        }
        return f[i][m] = res;
    }


    public int stoneGameII2(int[] piles) {
        int len = piles.length;
        int[] suffixSum = new int[len];
        suffixSum[len - 1] = piles[len - 1];
        for (int i = len - 2; i >= 0; i--) {
            suffixSum[i] = suffixSum[i + 1] + piles[i];
        }
        //从0位置开始 M取1 能获得的最大值
        return dfs2(0, 1, suffixSum);
    }

    private int dfs2(int index, int M, int[] suffixSum) {
        if (M * 2 + index >= suffixSum.length) return suffixSum[index];
        //当前选择的最大值 等价于 后手所有可能选择中的最小值
        int min = Integer.MAX_VALUE;
        int len = suffixSum.length;
        for (int i = 1; i <= 2 * M; i++) {
            min = Math.min(min, dfs2(index + i, Math.max(M, i), suffixSum));
        }
        return suffixSum[index] - min;
    }

    //记忆化搜索
    public int stoneGameII2_cache(int[] piles) {
        int len = piles.length;
        int[] suffixSum = new int[len];
        suffixSum[len - 1] = piles[len - 1];
        for (int i = len - 2; i >= 0; i--) {
            suffixSum[i] = suffixSum[i + 1] + piles[i];
        }
        int[][] cache = new int[len][2*len];
        //从0位置开始 M取1 能获得的最大值
        return dfs2_cache(0, 1, suffixSum,cache);
    }

    private int dfs2_cache(int index, int M, int[] suffixSum, int[][] cache) {
        if (cache[index][M] != 0) return cache[index][M];
        if (M * 2 + index >= suffixSum.length) return cache[index][M] = suffixSum[index];
        //当前选择的最大值 等价于 后手所有可能选择中的最小值
        int min = Integer.MAX_VALUE;
        int len = suffixSum.length;
        for (int i = 1; i <= 2 * M; i++) {
            min = Math.min(min, dfs2_cache(index + i, Math.max(M, i), suffixSum,cache));
        }
        return cache[index][M] = (suffixSum[index] - min);
    }

    //动态规划
    public int stoneGameII2_dp(int[] piles) {
        int s = 0, n = piles.length;
        int[][] f = new int[n][n + 1];
        for (int i = n - 1; i >= 0; --i) {
            s += piles[i];
            for (int m = 1; m <= i / 2 + 1; ++m) {
                if (i + m * 2 >= n) f[i][m] = s;
                else {
                    int mn = Integer.MAX_VALUE;
                    for (int x = 1; x <= m * 2; ++x)
                        mn = Math.min(mn, f[i + x][Math.max(m, x)]);
                    f[i][m] = s - mn;
                }
            }
        }
        return f[0][1];
    }

    // TODO: 2023/2/22 看相似问题(灵神题解有标注)
    @Test
    public void test() {
        int[] piles = {2,7,9,4,4};
        System.out.println(stoneGameII2_cache(piles));
    }
}
