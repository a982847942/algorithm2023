package nuaa.zk.dp.knapsack;

import org.junit.Test;

/**
 * @author brain
 * @version 1.0
 * @date 2023/4/16 12:16
 */
public class ClimbingStairsLC_70 {
    //递归 斐波那契数列问题 f(n) = f(n - 1) + f(n - 2)
    public int climbStairs(int n) {
        if (n == 1)return 1;
        return dfs(n);
    }

    private int dfs(int i) {
        if (i < 0)return 0;
        if (i == 0)return 1;
        return dfs(i - 1) + dfs(i - 2);
    }
    //dp
    public int climbStairs2(int n) {
        if (n == 1)return 1;
        if (n == 2)return 2;
        int first = 1;
        int second = 2;
        int ans = 0;
        for (int i = 3; i <= n; i++) {
            ans = first + second;
            first = second;
            second = ans;
        }
        return ans;
    }
    //矩阵快速幂
    public int climbStairs3(int n) {
        int[][] base = {{1,1},{1,0}};
        int[][] res = power(base,n-1);
        return res[0][0] + res[0][1];
    }

    private int[][] power(int[][] base, int n) {
        int[][] t = new int[base.length][base[0].length];
        for (int i = 0; i < base.length; i++) {
                t[i][i] = 1;
        }
        int[][] res = base;
        while (n > 0){
            if ((n & 1 ) != 0)t = multiMatrix(res,t);
            res = multiMatrix(res,res);
            n >>= 1;
        }
        return t;

    }

    private int[][] multiMatrix(int[][] m1, int[][] m2) {
        int[][] res = new int[m1.length][m1[0].length];
        for (int i = 0; i < m1.length; i++) {
            for (int k = 0; k < m1[0].length; k++) {
                for (int j = 0; j < m1[0].length; j++) {
                    res[i][k] += m1[i][j] * m2[j][k];
                }
            }
        }
        return res;
    }

    //通项公式
    public int climbStairs4(int n) {
        double sqrt5 = Math.sqrt(5);
        double fibn = Math.pow((1 + sqrt5) / 2, n + 1) - Math.pow((1 - sqrt5) / 2, n + 1);
        return (int) Math.round(fibn / sqrt5);
    }

    @Test
    public void test(){
        System.out.println(climbStairs(4));
        System.out.println(climbStairs2(4));
        System.out.println(climbStairs3(4));
    }


}
