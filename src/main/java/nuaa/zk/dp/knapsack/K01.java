package nuaa.zk.dp.knapsack;

import org.junit.Test;

/**
 * @author brain
 * @version 1.0
 * @date 2023/4/9 22:22
 * 有 N件物品和一个容量是 V 的背包。每件物品有且只有一件。
 * 第 i 件物品的体积是 v[i]，价值是w[i]。
 * 求解将哪些物品装入背包，可使这些物品的总体积不超过背包容量，且总价值最大
 */
public class K01 {

    /**
     * 典型的子集型回溯
     * 考虑第 i 件物品 要还是不要
     */
    private int getMaxValue(int N, int C, int[] v, int[] w) {
        return dfs(0, C, v, w);
    }

    /**
     *这个dfs()签名有不同的理解
     * 1.dfs(i,c) 考虑 第i件物品及后面的物品 在容量不超过c的条件下的最大价值 则返回值应该是dfs(0,C)
     * 2.dfs(i,c) 考虑第i件物品及前面的物品，在容量不超过c的条件下的最代价值 则返回值应该是dfs(N-1,C);
     * 这里以第一种方案为例
     */
    private int dfs(int index, int capacity, int[] v, int[] w) {
        if (index == v.length) return 0;
        //不要
        int p1 = dfs(index + 1, capacity, v, w);
        //要
        int p2 = 0;
        if (capacity - v[index] >= 0) {
           p2 =  dfs(index + 1, capacity - v[index], v, w) + w[index];
        }
        return Math.max(p1,p2);
    }

    //dp
    private int getMaxValue2(int N, int C, int[] v, int[] w) {
        int[][] dp = new int[N + 1][C + 1];
        for (int i = N - 1; i >= 0; i--) {
            for (int j = 0; j <= C; j++) {
                dp[i][j] = dp[i + 1][j];
                if (j - v[i] >= 0){
                    dp[i][j] = Math.max(dp[i][j],dp[i + 1][j - v[i]] + w[i]);
                }

            }
        }
        return dp[0][C];
    }
    private int getMaxValue3(int N, int C, int[] v, int[] w) {
        int[] dp = new int[C + 1];
        for (int i = N - 1; i >=0 ; i--) {
            for (int j = C; j >= v[i]; j--) {
//                if (j - v[i] >= 0){
//                    dp[j] = Math.max(dp[j],dp[j - v[i]] + w[i]);
//                }
                dp[j] = Math.max(dp[j],dp[j - v[i]] + w[i]);
            }
        }
        return dp[C];
    }

    private int getMaxValue4(int N, int C, int[] v, int[] w) {
        int[][] dp = new int[2][C + 1];
        for (int i = N - 1; i >= 0; i--) {
            for (int j = 0; j <= C; j++) {
                dp[i&1][j] = dp[(i + 1)&1][j];
                if (j - v[i] >= 0){
                    dp[i&1][j] = Math.max(dp[i&1][j],dp[(i + 1)&1][j - v[i]] + w[i]);
                }

            }
        }
        return dp[0][C];
    }

    //第二种思想的写法
    private int getMaxValue5(int N, int C, int[] v, int[] w) {
        int[] dp = new int[C + 1];
        for (int i = 1; i <= N ; i++) {
            for (int j = C; j >= v[i - 1] ; j--) {
                dp[j] = Math.max(dp[j],dp[j - v[i - 1]] + w[i - 1]);
            }
        }
        return dp[C];
    }
    @Test
    public void test(){
        int N = 3, C = 5;
        int[] v = {4,2,3}, w = {4,2,3};
        System.out.println(getMaxValue(N, C, v, w));
        System.out.println(getMaxValue2(N, C, v, w));
        System.out.println(getMaxValue3(N, C, v, w));
        System.out.println(getMaxValue4(N, C, v, w));
        System.out.println(getMaxValue5(N, C, v, w));
    }
}
